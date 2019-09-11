package com.gruposolux.rcivil.pdisciplinario.storage;

import com.gruposolux.rcivil.pdisciplinario.config.ApplicationProperties;
import com.gruposolux.rcivil.pdisciplinario.domain.*;
import com.gruposolux.rcivil.pdisciplinario.repository.AdjuntoRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.DocumentoRepository;
import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlfrescoStorageService {

    private final Path uploadPath;
    private final ApplicationProperties applicationProperties;
    private final Logger log = LoggerFactory.getLogger(AlfrescoStorageService.class);
    private Session alfrescoSession;
    private Folder alfrescoFolder;
    private DocumentoRepository documentoRepository;
    private String rootTempFolder;
    private String rootFinalFolder;
    private String baseUrl;
    private String atomPub_Alfresco_Url;
    private String userAlfresco;
    private String passAlfresco;
    private final AdjuntoRepository adjuntoRepository;

    public AlfrescoStorageService(ApplicationProperties applicationProperties, DocumentoRepository documentoRepository, AdjuntoRepository adjuntoRepository) {
        this.applicationProperties = applicationProperties;
        this.documentoRepository = documentoRepository;
        this.adjuntoRepository = adjuntoRepository;
        this.uploadPath = Paths.get(this.applicationProperties.getStorage().getTmpDir());
        this.rootTempFolder = this.applicationProperties.getStorage().getAlfrescoTempRootFolder();
        this.rootFinalFolder = this.applicationProperties.getStorage().getAlfrescoFinalRootFolder();
        this.baseUrl = this.applicationProperties.getStorage().getBaseUrl();
        this.atomPub_Alfresco_Url = this.applicationProperties.getStorage().getAlfrsco_atompub_url();
        this.userAlfresco = this.applicationProperties.getStorage().getUserAlfresco();
        this.passAlfresco = this.applicationProperties.getStorage().getPassAlfresco();
    }

    private Session getAlfrescoSession() {
        if (this.alfrescoSession != null) {
            return this.alfrescoSession;
        }

        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<>();
        parameter.put(SessionParameter.USER, userAlfresco);
        parameter.put(SessionParameter.PASSWORD, passAlfresco);
        parameter.put(SessionParameter.ATOMPUB_URL, baseUrl + atomPub_Alfresco_Url);
        parameter.put(SessionParameter.BINDING_TYPE,
            BindingType.ATOMPUB.value());
        parameter.put(SessionParameter.REPOSITORY_ID, this.applicationProperties.getStorage().getRepository_id());
        this.alfrescoSession = sessionFactory.createSession(parameter);
        this.alfrescoFolder = alfrescoSession.getRootFolder();

        return this.alfrescoSession;
    }

    /**
     * Metodo que realiza peticion a alfresco para crear la ruta de las carpetas
     *
     * @param ruta           ruta para crear
     * @param folderName     el nombre de la carpeta
     * @param rootFolderName root de la carpeta en alfresco
     * @return Folder
     * @throws CmisObjectNotFoundException
     */
    public Folder createFolder(String ruta, String folderName, String rootFolderName) throws CmisObjectNotFoundException {

        ObjectId pathCreado = null;

        String aux = String.format("%s/%s", ruta, folderName);

        Map<String, Object> props = new HashMap<>();
        props.put(PropertyIds.NAME, folderName);
        props.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");

        try {
            pathCreado = getAlfrescoSession().createPath(aux, props);
        } catch (CmisObjectNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return (Folder) getAlfrescoSession().getObject(pathCreado);

    }

    /**
     * Mueve archivos a alfresco en la ruta creatada dependiendo de la informacion de las investigacionSumaria
     *
     * @param archivosHash archivos al crear investigacion
     * @param providencia  recibe objecto providencia
     * @return HashMap<String, String>
     */
    public HashMap<String, String> moveToArchivosFolder(List<String> archivosHash, Providencia providencia) {
        String rootFolderName = rootTempFolder;

        Instant fechaCreacion = providencia.getFechaCreacion();
        LocalDate instantDateConverted = fechaCreacion.atZone(ZoneId.systemDefault()).toLocalDate();
        int year = instantDateConverted.getYear();
        int monthValue = instantDateConverted.getMonthValue();

        System.out.println("rootFolderName");
        System.out.println(rootFolderName);

        String destinoPath = String.format("%s/%s/%s/providencia", rootFolderName, year, monthValue);

        return createFolderPathInAlfresco(archivosHash, destinoPath, providencia.getId());
    }

    /**
     * Para evitar duplicado de codigo se crea metodo con sentencias comunes
     *
     * @param archivosHash archivos en sumarioAdministrativo o sumarioAdministrativo
     * @param destinoPath  destino de la carpeta
     * @param id           id de investigacion sumaria o sumarioAdministrativo
     * @return HashMap<String, String>
     */
    public HashMap<String, String> createFolderPathInAlfresco(List<String> archivosHash, String destinoPath, Long id) {
        HashMap<String, String> nuevasRutas = new HashMap<>();

        String rootFolderName = rootTempFolder;

        Folder investigacionFolder = createFolder(destinoPath, String.valueOf(id), rootFolderName);

        String fullPath = investigacionFolder.getPath();

        for (String hash : archivosHash) {
            Path origin = this.uploadPath.resolve(hash);
            try {
                uploadFileAlfresco(origin.toString(), investigacionFolder, fullPath, hash);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        return nuevasRutas;
    }

//    /**
//     * Mueve archivos a alfresco en la ruta creatada dependiendo de la informacion del SumarioADministrativo
//     *
//     * @param archivosHash          archivos en sumarioAdministrativo
//     * @param sumarioAdministrativo recibe objecto sumarioAdministrativo
//     * @return HashMap<String   ,       String>
//     */
//    public HashMap<String, String> moveToArchivosFolder(ArrayList<String> archivosHash, SumarioAdministrativo sumarioAdministrativo) {
//        String rootFolderName = rootTempFolder;
//
//        LocalDate fechaSolicitud = sumarioAdministrativo.getFechaCreado();
//        int year = fechaSolicitud.getYear();
//        int month = fechaSolicitud.getMonthValue();
//
//        String destinoPath = String.format("%s/%s/%s/sumarioAdministrativo", rootFolderName, year, month);
//
//        return createFolderPathInAlfresco(archivosHash, destinoPath, sumarioAdministrativo.getId());
//    }

    /**
     * Metodo que se encarga de subir los archivos a alfresco
     *
     * @param originPath         path principal de los archivos
     * @param carpetaDeSolicitud carpeta creada con la informacion de investigacionSumaria y sumarioAdministrativo
     * @param fullPath           fullpath en alfresco
     * @param hash               archivos
     * @return Document
     * @throws IOException excepcion
     */
    public Document uploadFileAlfresco(String originPath, Folder carpetaDeSolicitud, String fullPath, String hash) throws IOException {
        String mimetype = "application/octet-stream";

        Adjunto adjunto = adjuntoRepository.findByHash(hash);

        File archivoAMover = new File(originPath);

        byte[] contentBytes = new byte[0];

        try {
            contentBytes = FileUtils.readFileToByteArray(archivoAMover);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(contentBytes);


        ContentStream contentStream = getAlfrescoSession().getObjectFactory().createContentStream(archivoAMover.getName(), contentBytes.length, mimetype, stream);

        String timeParaNombre = DateTimeFormatter.ofPattern("ddMMyyy_hhmmss").format(ZonedDateTime.now());
        Map<String, Object> propiedades = new HashMap<>();
        propiedades.put(PropertyIds.NAME, timeParaNombre + "_" + adjunto.getArchivoNombre());
        propiedades.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");

        Document newDocumento = carpetaDeSolicitud.createDocument(propiedades, contentStream, VersioningState.MAJOR);

        String idArchivo = newDocumento.getId();

        adjunto.setAlfrescoNodeId(idArchivo);
        adjunto.setAlfrescoNodePath(fullPath);
        adjuntoRepository.save(adjunto);

        return newDocumento;
    }

    //todo: Arreglar para el flujo requerido de investigaciones
    public void moveToFinalFolder(String materia, String areaResponde, Providencia providencia, Adjunto adjunto) {

    }

    /**
     * busca en alfresco los documentos por hash para descargarlos
     *
     * @param adjunto adjunto
     * @return ContentStream
     */
    public ContentStream downloadFileFromHash(Adjunto adjunto) {
        System.out.println("entra a alfrescoStorageService");
        if (adjunto.getAlfrescoNodeId() == null)
            return null;

        CmisObject object = getAlfrescoSession().getObject(adjunto.getAlfrescoNodeId());

        if (object instanceof Document) {
            Document document = (Document) object;
            return document.getContentStream();
        }
        return null;
    }
}
