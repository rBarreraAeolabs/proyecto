package com.gruposolux.rcivil.pdisciplinario.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * A Documento.
 */
@Entity
@Table(name = "documento")
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column (name = "numero_resolucion")
    private Long numeroResolucion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha_creado")
    private LocalDate fechaCreado;

    @Column(name = "archivo_nombre")
    private String archivoNombre;

    @Column(name = "archivo_mime_type")
    private String archivoMimeType;

    @Column(name = "archivo_size")
    private Long archivoSize;

    @Lob
    @Column(name = "archivo")
    private byte[] archivo;

    @Column(name = "archivo_content_type")
    private String archivoContentType;

    @Column(name = "alfresco_node_id")
    private String alfrescoNodeId;

    @Column(name = "alfresco_node_path")
    private String alfrescoNodePath;

    @Column(name = "local_path")
    private String localPath;

    @Column(name = "jhi_hash")
    private String hash;

    @ManyToOne
    @JsonIgnoreProperties("hashesDocumentos")
    private Providencia providencia;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private Derivacion derivacion;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private MovimientoProvidencia movimientoProvidencia;

    @ManyToOne
    private Respuesta respuesta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPlantilla tipoPlantilla;

    @Column(name = "version")
    private Integer version;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getNumeroResolucion(){return numeroResolucion; }

    public void setNumeroResolucion(Long numeroResolucion) { this.numeroResolucion = numeroResolucion; }

    public Documento numeroResolucion(Long numeroResolucion){
        this.numeroResolucion = numeroResolucion;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Documento descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public Documento contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    public Documento fechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
        return this;
    }

    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public Documento archivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
        return this;
    }

    public void setArchivoNombre(String archivoNombre) {
        if (archivoNombre != null && archivoNombre.length() > 0)
        {
            this.archivoNombre = archivoNombre.trim();
        }
        else
        {
            this.archivoNombre = null;
        }

    }

    public String getArchivoMimeType() {
        return archivoMimeType;
    }

    public Documento archivoMimeType(String archivoMimeType) {
        this.archivoMimeType = archivoMimeType;
        return this;
    }

    public void setArchivoMimeType(String archivoMimeType) {
        this.archivoMimeType = archivoMimeType;
    }

    public Long getArchivoSize() {
        return archivoSize;
    }

    public Documento archivoSize(Long archivoSize) {
        this.archivoSize = archivoSize;
        return this;
    }

    public void setArchivoSize(Long archivoSize) {
        this.archivoSize = archivoSize;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public Documento archivo(byte[] archivo) {
        this.archivo = archivo;
        return this;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoContentType() {
        return archivoContentType;
    }

    public Documento archivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
        return this;
    }

    public void setArchivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
    }

    public String getAlfrescoNodeId() {
        return alfrescoNodeId;
    }

    public Documento alfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
        return this;
    }

    public void setAlfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
    }

    public String getAlfrescoNodePath() {
        return alfrescoNodePath;
    }

    public Documento alfrescoNodePath(String alfrescoNodePath) {
        this.alfrescoNodePath = alfrescoNodePath;
        return this;
    }

    public void setAlfrescoNodePath(String alfrescoNodePath) {
        this.alfrescoNodePath = alfrescoNodePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Documento localPath(String localPath) {
        this.localPath = localPath;
        return this;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getHash() {
        return hash;
    }

    public Documento hash(String hash) {
        this.hash = hash;
        return this;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public Documento providencia(Providencia providencia) {
        this.providencia = providencia;
        return this;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }

    public Derivacion getDerivacion() {
        return derivacion;
    }

    public Documento derivacion(Derivacion derivacion) {
        this.derivacion = derivacion;
        return this;
    }

    public void setDerivacion(Derivacion derivacion) {
        this.derivacion = derivacion;
    }

    public MovimientoProvidencia getMovimientoProvidencia() {
        return movimientoProvidencia;
    }

    public Documento movimientoProvidencia(MovimientoProvidencia movimientoProvidencia) {
        this.movimientoProvidencia = movimientoProvidencia;
        return this;
    }

    public void setMovimientoProvidencia(MovimientoProvidencia movimientoProvidencia) {
        this.movimientoProvidencia = movimientoProvidencia;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Documento documento = (Documento) o;
        if (documento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Documento{" +
            "id=" + id +
            ", numeroResolucion=" + numeroResolucion +
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
            ", providencia=" + providencia +
            ", derivacion=" + derivacion +
            ", movimientoProvidencia=" + movimientoProvidencia +
            ", respuesta=" + respuesta +
            ", tipoPlantilla=" + tipoPlantilla +
            ", version=" + version +
            '}';
    }
}
