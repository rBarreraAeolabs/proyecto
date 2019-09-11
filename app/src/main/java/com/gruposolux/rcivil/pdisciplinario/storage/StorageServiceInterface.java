package com.gruposolux.rcivil.pdisciplinario.storage;

import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.domain.SumarioAdministrativo;
import com.gruposolux.rcivil.pdisciplinario.service.dto.FileUploadResponseDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public interface StorageServiceInterface {

    //    void init();

    FileUploadResponseDTO store(MultipartFile file);

    Documento store(Documento documento);

//    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    Resource loadResourceFromPdfDir(String filename);

    void deleteAll();

//    File createTmpFile(String nombre);

//    Resource loadExcelTmpFileAsResource(String nombre);

    HashMap<String, String> moveToArchivosFolder(ArrayList<String> documentos, SumarioAdministrativo sumarioAdministrativo);

    HashMap<String, String> moveToArchivosFolder(ArrayList<String> documentos, InvestigacionSumaria investigacionSumaria);

    Resource loadFromArchivosFile(String nombre);

//    Resource loadPdfFromTmpFile(String nombre);

    Resource loadFromTmpFile(String hash);

    Resource loadFromAdjuntoFile(String hash);

    boolean deleteFile(Path ruta);

    File createExcelFile(String randomFilename);

    Resource loadExcelTmpFileAsResource(String nombre);

    Path createImprimirTmpFile(String s);

    File createImprimirTmpHtmlFile(String s, String contenido);
}
