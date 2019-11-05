package com.gruposolux.rcivil.pdisciplinario.storage;

import com.gruposolux.rcivil.pdisciplinario.config.ApplicationProperties;
import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoAdjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoDocumento;
import com.gruposolux.rcivil.pdisciplinario.service.dto.AdjuntoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.DocumentoDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FileUploadResponseDTO;
import com.gruposolux.rcivil.pdisciplinario.service.util.PdfConverter;
import com.gruposolux.rcivil.pdisciplinario.web.rest.DocumentoResource;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardOpenOption.READ;

@Service
@Primary
public class FileSystemStorageService implements StorageServiceInterface {

    private final int maxFileSize;
    private final Path adjuntosPath;
    private final Path temporalPath;
    private final Path excelPath;
    private final Path imprimirPath;
    private final int maxTimeForFileExpire;
    private final Path uploadPath;

    private final Logger log = LoggerFactory.getLogger(DocumentoResource.class);

    private final ApplicationProperties applicationProperties;

    @Autowired
    public FileSystemStorageService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;

        this.temporalPath = checkOrCreatePath(Paths.get(this.applicationProperties.getStorage().getTmpDir()));
        this.adjuntosPath = checkOrCreatePath(Paths.get(this.applicationProperties.getStorage().getAdjuntosDir()));
        this.excelPath = checkOrCreatePath(Paths.get(this.applicationProperties.getStorage().getExcelDir()));
        this.imprimirPath = checkOrCreatePath(Paths.get(this.applicationProperties.getStorage().getImprimirDir()));
        this.uploadPath = Paths.get(this.applicationProperties.getStorage().getTmpDir());
        if (!Files.exists(this.uploadPath)) {
            try {
                Files.createDirectories(this.uploadPath);
            } catch (IOException e) {
                log.info("No se pudo crear el directorio " + uploadPath.toString());
                e.printStackTrace();
            }
        }
        this.maxTimeForFileExpire = this.applicationProperties.getStorage().getMaxTimeForFileExpire();
        this.maxFileSize = this.applicationProperties.getStorage().getMaxFileSize();

    }

    public Path getAdjuntosPath() {
        return adjuntosPath;
    }

    public Path getTemporalPath() {
        return temporalPath;
    }

    public Path getExcelPath() {
        return excelPath;
    }

    public Path getUploadPath() {
        return uploadPath;
    }

    private FileUploadResponseDTO virusValidation(FileUploadResponseDTO responseDTO, MultipartFile file) {
        return responseDTO;
    }

    private Path checkOrCreatePath(Path folder) {
        if (!Files.exists(folder)) {
            try {
                Files.createDirectories(folder);
                return folder;
            } catch (IOException e) {
                log.info("No se pudo crear el directorio " + folder.toString());
                e.printStackTrace();
            }
        } else {
            return folder;
        }
        return null;
    }

    @Override
    public FileUploadResponseDTO store(MultipartFile file) {

        FileUploadResponseDTO responseDTO = new FileUploadResponseDTO();

        try {
            if (file.isEmpty()) {
                throw new StorageException("Error al almacenar el archivo vacío " + file.getOriginalFilename());
            }

            //valida tamaño del formato
            FileUploadResponseDTO validation = this.validateFormatAndSize(responseDTO, file);

            if (!validation.hasError()) {
                // validar antivirus
                validation = this.virusValidation(responseDTO, file);
                if (validation.hasError()) {
                    responseDTO.setError("El archivo está infectado");
                }
            } else {
                responseDTO.setError("Archivo excede el tamaño máximo o su formato no es aceptado");
            }

            AdjuntoDTO fileInfo = responseDTO.getFileinfo();
            //copiar destino
            String hash = UUID.randomUUID().toString();
            Path destino = null;
            if (!validation.hasError()) {
//              if (file.getContentType().equalsIgnoreCase(MediaType.APPLICATION_PDF.toString()))
//                {
                    destino = this.adjuntosPath.resolve(hash);
//                }
//                else
//                {
              //    destino = this.uploadPath.resolve(hash);
//                }

                Files.copy(file.getInputStream(), destino);

                responseDTO.setHash(hash);
                Long milisegundos = Math.addExact(TimeUnit.MINUTES.toMillis(maxTimeForFileExpire), System.currentTimeMillis());
                responseDTO.setExpires(milisegundos);
                fileInfo.setLocalPath(destino.toString());
                fileInfo.setAlfrescoNodeId(hash);

                fileInfo.setHash(hash);

                fileInfo.setFechaSubido(ZonedDateTime.now().toInstant());
                fileInfo.setTipoAdjunto(TipoAdjunto.DESIGNA_NUEVO_FISCAL);

                Tika tika = new Tika();
                String mimeType = tika.detect(destino);
                fileInfo.setArchivoMimeType(mimeType);

                fileInfo.setArchivoNombre(file.getOriginalFilename());
                fileInfo.setArchivoSize(file.getSize());

                responseDTO.setFileinfo(fileInfo);
            }

            return responseDTO;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Documento store(Documento documento)
    {
        String hash = UUID.randomUUID().toString();
        Path destino = this.adjuntosPath.resolve(hash);
        Path localPath = Paths.get(documento.getLocalPath());

        try
        {
            Files.copy(Files.newInputStream(localPath, READ), destino);
            documento.setLocalPath(destino.toString());
            documento.setHash(hash);
            documento.setArchivoSize(Files.size(localPath));

        }
        catch (IOException e)
        {
            throw new StorageException("Failed to store file " + documento.getArchivoNombre(), e);
        }

        return documento;
    }

    private FileUploadResponseDTO validateFormatAndSize(FileUploadResponseDTO responseDTO, MultipartFile file) {
        AdjuntoDTO fileinfo = responseDTO.getFileinfo();

        FileUploadStatus estado = FileUploadStatus.FORMATO_Y_PESO_VALIDADO;

        if (8 * 1024 * 1024 < file.getSize()) {
            estado = FileUploadStatus.ERROR;
        }

        responseDTO.setStatus(estado);
//        fileinfo.setEstado(estado);

        return responseDTO;
    }

    @Override
    public Path load(String filename) {
        return uploadPath.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Resource loadResourceFromPdfDir(String filename) {
        try {
            Path resolved = imprimirPath.resolve(filename);
            Resource resource = new UrlResource(resolved.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(temporalPath.toFile());
    }

    @Override
    public HashMap<String, String> moveToArchivosFolder(ArrayList<String> documentos, SumarioAdministrativo sumarioAdministrativo) {
        return null;
    }

    @Override
    public HashMap<String, String> moveToArchivosFolder(ArrayList<String> documentos, InvestigacionSumaria investigacionSumaria) {
        HashMap<String, String> nuevasRutas = new HashMap<>();

        for (String hash: documentos) {
            try{
                Path moved = Files.move(this.uploadPath.resolve(hash), this.adjuntosPath.resolve(hash));
                nuevasRutas.put(hash, moved.toString());

            }catch (Exception ex){
                return null;
            }
        }

        return nuevasRutas;
    }

    @Override
    public Resource loadFromArchivosFile(String nombre) {
        Path path = Paths.get(this.uploadPath.toString(), nombre);
        if (Files.exists(path)) {
            File file = path.toFile();
            if (file != null) {
                return loadAsResource(file.getAbsolutePath());
            }
        }

        Path path2 = Paths.get(this.adjuntosPath.toString(), nombre);
        if (Files.exists(path2)) {
            File file2 = path2.toFile();
            if (file2 != null) {
                return loadAsResource(file2.getAbsolutePath());
            }
        }

        return null;
    }

    @Override
    public Resource loadFromTmpFile(String hash) {
        Path path = Paths.get(this.temporalPath.toString(), hash);

        if (Files.exists(path)) {
            File file = path.toFile();
            return loadAsResource(file.getAbsolutePath());
        }

        return null;
    }

    @Override
    public Resource loadFromAdjuntoFile(String hash)
    {
        Path path = Paths.get(this.adjuntosPath.toString(), hash);

        if (Files.exists(path)) {
            File file = path.toFile();
            return loadAsResource(file.getAbsolutePath());
        }

        return null;
    }

    @Override
    public boolean deleteFile(Path ruta) {

        try {
            if (Files.exists(ruta)) {
                Files.delete(ruta);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public File createExcelFile(String randomFilename) {
        Path path = Paths.get(this.excelPath.toString(), randomFilename);
        File tmp = path.toFile();

        return tmp;
    }

    @Override
    public Resource loadExcelTmpFileAsResource(String nombre) {
        Path path = Paths.get(this.excelPath.toString(), nombre);
        if (Files.exists(path)) {
            File file = path.toFile();
            return loadAsResource(file.getAbsolutePath());
        }
        return null;
    }

    @Override
    public Path createImprimirTmpFile(String s) {
        Path path = Paths.get(imprimirPath.toString(), s);
        if (path.toFile().exists())
            path.toFile().delete();

        return path;
    }

    @Override
    public File createImprimirTmpHtmlFile(String s, String contenido) {
        Path path = Paths.get(this.imprimirPath.toString(), contenido);
        File tmp = path.toFile();

        try {
            FileUtils.writeStringToFile(tmp, contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmp;
    }

    private void moveToAdjuntosFolder(String hash)
    {
        try
        {
            Path moved = Files.move(this.uploadPath.resolve(hash), this.adjuntosPath.resolve(hash));
        }
        catch (Exception ex)
        {
            log.debug("Error " + ex.getMessage());
        }
    }

    public String storePdf(String filename, String contenido) throws ParserConfigurationException, IOException, DocumentException
    {
        return PdfConverter.generatePDFFromHTML(filename, contenido, this.adjuntosPath);
    }
}
