package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Documento entity.
 */
public class DocumentoDTO implements Serializable {

    private Long id;

    private String descripcion;

    private String contenido;

    private LocalDate fechaCreado;

    private String archivoNombre;

    private String archivoMimeType;

    private Long archivoSize;

    @Lob
    private byte[] archivo;
    private String archivoContentType;

    private String alfrescoNodeId;

    private String alfrescoNodePath;

    private String localPath;

    private String hash;

    private Long providenciaId;

    private Long derivacionId;

    private Long movimientoProvidenciaId;

    private Long respuestaId;

    private Long numeroResolucion;

    private TipoPlantilla tipoPlantilla;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
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

    public Long getDerivacionId() {
        return derivacionId;
    }

    public void setDerivacionId(Long derivacionId) {
        this.derivacionId = derivacionId;
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

    public Long getNumeroResolucion() {
        return numeroResolucion;
    }

    public void setNumeroResolucion(Long numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }

    public TipoPlantilla getTipoPlantilla() {
        return tipoPlantilla;
    }

    public void setTipoPlantilla(TipoPlantilla tipoPlantilla) {
        this.tipoPlantilla = tipoPlantilla;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentoDTO documentoDTO = (DocumentoDTO) o;
        if (documentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentoDTO{" +
            "id=" + id +
            ", descripcion='" + descripcion + '\'' +
            ", contenido='" + contenido + '\'' +
            ", fechaCreado=" + fechaCreado +
            ", archivoNombre='" + archivoNombre + '\'' +
            ", archivoMimeType='" + archivoMimeType + '\'' +
            ", archivoSize=" + archivoSize +
            ", archivo=" + Arrays.toString(archivo) +
            ", archivoContentType='" + archivoContentType + '\'' +
            ", alfrescoNodeId='" + alfrescoNodeId + '\'' +
            ", alfrescoNodePath='" + alfrescoNodePath + '\'' +
            ", localPath='" + localPath + '\'' +
            ", hash='" + hash + '\'' +
            ", providenciaId=" + providenciaId +
            ", derivacionId=" + derivacionId +
            ", movimientoProvidenciaId=" + movimientoProvidenciaId +
            ", respuestaId=" + respuestaId +
            ", numeroResolucion=" + numeroResolucion +
            ", tipoPlantilla=" + tipoPlantilla +
            ", version=" + version +
            '}';
    }
}
