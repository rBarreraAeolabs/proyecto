package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Lob;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoAdjunto;

/**
 * A DTO for the Adjunto entity.
 */
public class AdjuntoDTO implements Serializable {

    private Long id;

    private TipoAdjunto tipoAdjunto;

    private String nombre;

    private String descripcion;

    private LocalDate fechaCreado;

    private Instant fechaSubido;

    private String archivoNombre;

    private String archivoMimeType;

    private Long archivoSize;

    private FileUploadStatus estado;

    @Lob
    private byte[] archivo;
    private String archivoContentType;

    private String alfrescoNodeId;

    private String alfrescoNodePath;

    private String localPath;

    private String hash;

    private Long providenciaId;

    private Long movimientoProvidenciaId;

    private Long respuestaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAdjunto getTipoAdjunto() {
        return tipoAdjunto;
    }

    public void setTipoAdjunto(TipoAdjunto tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Instant getFechaSubido() {
        return fechaSubido;
    }

    public void setFechaSubido(Instant fechaSubido) {
        this.fechaSubido = fechaSubido;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoMimeType() {
        return archivoMimeType;
    }

    public void setArchivoMimeType(String archivoMimeType) {
        this.archivoMimeType = archivoMimeType;
    }

    public Long getArchivoSize() {
        return archivoSize;
    }

    public void setArchivoSize(Long archivoSize) {
        this.archivoSize = archivoSize;
    }

    public FileUploadStatus getEstado() {
        return estado;
    }

    public void setEstado(FileUploadStatus estado) {
        this.estado = estado;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoContentType() {
        return archivoContentType;
    }

    public void setArchivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
    }

    public String getAlfrescoNodeId() {
        return alfrescoNodeId;
    }

    public void setAlfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
    }

    public String getAlfrescoNodePath() {
        return alfrescoNodePath;
    }

    public void setAlfrescoNodePath(String alfrescoNodePath) {
        this.alfrescoNodePath = alfrescoNodePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Long getMovimientoProvidenciaId() {
        return movimientoProvidenciaId;
    }

    public void setMovimientoProvidenciaId(Long movimientoProvidenciaId) {
        this.movimientoProvidenciaId = movimientoProvidenciaId;
    }

    public Long getRespuestaId() {
        return respuestaId;
    }

    public void setRespuestaId(Long respuestaId) {
        this.respuestaId = respuestaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdjuntoDTO adjuntoDTO = (AdjuntoDTO) o;
        if (adjuntoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjuntoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdjuntoDTO{" +
            "id=" + id +
            ", tipoAdjunto=" + tipoAdjunto +
            ", nombre='" + nombre + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", fechaCreado=" + fechaCreado +
            ", fechaSubido=" + fechaSubido +
            ", archivoNombre='" + archivoNombre + '\'' +
            ", archivoMimeType='" + archivoMimeType + '\'' +
            ", archivoSize=" + archivoSize +
            ", estado=" + estado +
            ", archivo=" + Arrays.toString(archivo) +
            ", archivoContentType='" + archivoContentType + '\'' +
            ", alfrescoNodeId='" + alfrescoNodeId + '\'' +
            ", alfrescoNodePath='" + alfrescoNodePath + '\'' +
            ", localPath='" + localPath + '\'' +
            ", hash='" + hash + '\'' +
            ", providenciaId=" + providenciaId +
            ", movimientoProvidenciaId=" + movimientoProvidenciaId +
            ", respuestaId=" + respuestaId +
            '}';
    }
}
