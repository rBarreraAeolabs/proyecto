package com.gruposolux.rcivil.pdisciplinario.service.dto;


import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;

import java.io.Serializable;

public class FileUploadResponseDTO implements Serializable {
    private FileUploadStatus status;
    private String error;
    private AdjuntoDTO fileinfo;
    private String hash;
    // equivalente en milisegundos de la hora de expiracion
    private Long expires;

    public FileUploadResponseDTO() {
        fileinfo = new AdjuntoDTO();
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public AdjuntoDTO getFileinfo() {
        return fileinfo;
    }

    public void setFileinfo(AdjuntoDTO fileinfo) {
        this.fileinfo = fileinfo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public FileUploadStatus getStatus() {
        return status;
    }

    public void setStatus(FileUploadStatus status) {
        this.status = status;
    }

    public boolean hasError() {
        return status == FileUploadStatus.ERROR || status == FileUploadStatus.INFECTADO;
    }

}
