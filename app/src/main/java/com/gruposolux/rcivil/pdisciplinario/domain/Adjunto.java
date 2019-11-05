package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoAdjunto;

/**
 * A Adjunto.
 */
@Entity
@Table(name = "adjunto")
public class Adjunto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_adjunto")
    private TipoAdjunto tipoAdjunto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creado")
    private LocalDate fechaCreado;

    @Column(name = "fecha_subido")
    private Instant fechaSubido;

    @Column(name = "archivo_nombre")
    private String archivoNombre;

    @Column(name = "archivo_mime_type")
    private String archivoMimeType;

    @Column(name = "archivo_size")
    private Long archivoSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private FileUploadStatus estado;

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
    @JsonIgnoreProperties("adjuntos")
    private Providencia providencia;

    @ManyToOne
    @JsonIgnoreProperties("adjuntos")
    private MovimientoProvidencia movimientoProvidencia;

    @ManyToOne
    @JsonIgnore
    private Derivacion derivacion;

    @ManyToOne
    private Respuesta respuesta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAdjunto getTipoAdjunto() {
        return tipoAdjunto;
    }

    public Adjunto tipoAdjunto(TipoAdjunto tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
        return this;
    }

    public void setTipoAdjunto(TipoAdjunto tipoAdjunto) {
        this.tipoAdjunto = tipoAdjunto;
    }

    public String getNombre() {
        return nombre;
    }

    public Adjunto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Adjunto descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCreado() {
        return fechaCreado;
    }

    public Adjunto fechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
        return this;
    }

    public void setFechaCreado(LocalDate fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public Instant getFechaSubido() {
        return fechaSubido;
    }

    public Adjunto fechaSubido(Instant fechaSubido) {
        this.fechaSubido = fechaSubido;
        return this;
    }

    public void setFechaSubido(Instant fechaSubido) {
        this.fechaSubido = fechaSubido;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public Adjunto archivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
        return this;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoMimeType() {
        return archivoMimeType;
    }

    public Adjunto archivoMimeType(String archivoMimeType) {
        this.archivoMimeType = archivoMimeType;
        return this;
    }

    public void setArchivoMimeType(String archivoMimeType) {
        this.archivoMimeType = archivoMimeType;
    }

    public Long getArchivoSize() {
        return archivoSize;
    }

    public Adjunto archivoSize(Long archivoSize) {
        this.archivoSize = archivoSize;
        return this;
    }

    public void setArchivoSize(Long archivoSize) {
        this.archivoSize = archivoSize;
    }

    public FileUploadStatus getEstado() {
        return estado;
    }

    public Adjunto estado(FileUploadStatus estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(FileUploadStatus estado) {
        this.estado = estado;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public Adjunto archivo(byte[] archivo) {
        this.archivo = archivo;
        return this;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoContentType() {
        return archivoContentType;
    }

    public Adjunto archivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
        return this;
    }

    public void setArchivoContentType(String archivoContentType) {
        this.archivoContentType = archivoContentType;
    }

    public String getAlfrescoNodeId() {
        return alfrescoNodeId;
    }

    public Adjunto alfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
        return this;
    }

    public void setAlfrescoNodeId(String alfrescoNodeId) {
        this.alfrescoNodeId = alfrescoNodeId;
    }

    public String getAlfrescoNodePath() {
        return alfrescoNodePath;
    }

    public Adjunto alfrescoNodePath(String alfrescoNodePath) {
        this.alfrescoNodePath = alfrescoNodePath;
        return this;
    }

    public void setAlfrescoNodePath(String alfrescoNodePath) {
        this.alfrescoNodePath = alfrescoNodePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Adjunto localPath(String localPath) {
        this.localPath = localPath;
        return this;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getHash() {
        return hash;
    }

    public Adjunto hash(String hash) {
        this.hash = hash;
        return this;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Providencia getProvidencia() {
        return providencia;
    }

    public Adjunto providencia(Providencia providencia) {
        this.providencia = providencia;
        return this;
    }

    public void setProvidencia(Providencia providencia) {
        this.providencia = providencia;
    }

    public MovimientoProvidencia getMovimientoProvidencia() {
        return movimientoProvidencia;
    }

    public Adjunto movimientoProvidencia(MovimientoProvidencia movimientoProvidencia) {
        this.movimientoProvidencia = movimientoProvidencia;
        return this;
    }

    public void setMovimientoProvidencia(MovimientoProvidencia movimientoProvidencia) {
        this.movimientoProvidencia = movimientoProvidencia;
    }

    public Derivacion getDerivacion() {
        return derivacion;
    }

    public void setDerivacion(Derivacion derivacion) {
        this.derivacion = derivacion;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
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
        Adjunto adjunto = (Adjunto) o;
        if (adjunto.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adjunto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Adjunto{" +
            "id=" + getId() +
            ", tipoAdjunto='" + getTipoAdjunto() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", fechaCreado='" + getFechaCreado() + "'" +
            ", fechaSubido='" + getFechaSubido() + "'" +
            ", archivoNombre='" + getArchivoNombre() + "'" +
            ", archivoMimeType='" + getArchivoMimeType() + "'" +
            ", archivoSize=" + getArchivoSize() +
            ", estado='" + getEstado() + "'" +
            ", archivo='" + getArchivo() + "'" +
            ", archivoContentType='" + getArchivoContentType() + "'" +
            ", alfrescoNodeId='" + getAlfrescoNodeId() + "'" +
            ", alfrescoNodePath='" + getAlfrescoNodePath() + "'" +
            ", localPath='" + getLocalPath() + "'" +
            ", hash='" + getHash() + "'" +
            "}";
    }
}
