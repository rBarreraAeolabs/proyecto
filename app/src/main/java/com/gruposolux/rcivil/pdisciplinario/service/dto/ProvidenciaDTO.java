package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.*;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Accion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Caracter;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * A DTO for the Providencia entity.
 */
public class ProvidenciaDTO implements Serializable {

    private Long id;

    private Long numero;

    private EstadoProvidencia estadoActual;

    private TipoProvidencia tipo;

    private String comentario;

    private Instant fechaSolicitud;

    private Instant fechaCreacion;

    private Collection<Accion> acciones;

    private Set<AdjuntoDTO> adjuntos = new HashSet<>();

    private Set<DocumentoDTO> documentos = new HashSet<>();

    private Set<MovimientoProvidenciaDTO> movimientos = new HashSet<>();

    private Long sumarioAdministrativoId;

    private Long investigacionSumariaId;

    private Instant fechaHasta;

    @Size(min = 4, max = 13, message = "El RUT debe ser de 4 a 13 caracteres.")
    private String runSolicitante;

    private String nombreSolicitante;

    @Size(min = 4, max = 13, message = "El RUT debe ser de 4 a 13 caracteres.")
    private String runImplicado;

    private String nombreImplicado;

    private EntidadDTO entidadSolicitante;

    private EntidadDTO entidadImplicada;

    private String nombreFiscalAsignado;

    private Long providenciaMadreId;

    private Long numeroReferenciaPorvidenciaMadre;

    public ProvidenciaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public EstadoProvidencia getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoProvidencia estadoActual) {
        this.estadoActual = estadoActual;
    }

    public TipoProvidencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoProvidencia tipo) {
        this.tipo = tipo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Instant getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Instant fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Collection<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(Collection<Accion> acciones) {
        this.acciones = acciones;
    }

    public Set<AdjuntoDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<AdjuntoDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Set<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    public Set<MovimientoProvidenciaDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<MovimientoProvidenciaDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public Long getSumarioAdministrativoId() {
        return sumarioAdministrativoId;
    }

    public void setSumarioAdministrativoId(Long sumarioAdministrativoId) {
        this.sumarioAdministrativoId = sumarioAdministrativoId;
    }

    public Long getInvestigacionSumariaId() {
        return investigacionSumariaId;
    }

    public void setInvestigacionSumariaId(Long investigacionSumariaId) {
        this.investigacionSumariaId = investigacionSumariaId;
    }

    public Instant getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Instant fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getRunSolicitante() {
        return runSolicitante;
    }

    public void setRunSolicitante(String runSolicitante) {
        this.runSolicitante = runSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public EntidadDTO getEntidadSolicitante() {
        return entidadSolicitante;
    }

    public void setEntidadSolicitante(EntidadDTO entidadSolicitante) {
        this.entidadSolicitante = entidadSolicitante;
    }

    public String getRunImplicado() {
        return runImplicado;
    }

    public void setRunImplicado(String runImplicado) {
        this.runImplicado = runImplicado;
    }

    public String getNombreImplicado() {
        return nombreImplicado;
    }

    public void setNombreImplicado(String nombreImplicado) {
        this.nombreImplicado = nombreImplicado;
    }

    public EntidadDTO getEntidadImplicada() {
        return entidadImplicada;
    }

    public void setEntidadImplicada(EntidadDTO entidadImplicada) {
        this.entidadImplicada = entidadImplicada;
    }

    public String getNombreFiscalAsignado() {
        return nombreFiscalAsignado;
    }

    public void setNombreFiscalAsignado(String nombreFiscalAsignado) {
        this.nombreFiscalAsignado = nombreFiscalAsignado;
    }

    public Long getProvidenciaMadreId() {
        return providenciaMadreId;
    }

    public void setProvidenciaMadreId(Long providenciaMadreId) {
        this.providenciaMadreId = providenciaMadreId;
    }

    public Long getNumeroReferenciaPorvidenciaMadre() {
        return numeroReferenciaPorvidenciaMadre;
    }

    public void setNumeroReferenciaPorvidenciaMadre(Long numeroReferenciaPorvidenciaMadre) {
        this.numeroReferenciaPorvidenciaMadre = numeroReferenciaPorvidenciaMadre;
    }

    @Override
    public String toString() {
        return "ProvidenciaDTO{" +
            "id=" + id +
            ", numero=" + numero +
            ", estadoActual=" + estadoActual +
            ", tipo=" + tipo +
            ", comentario='" + comentario + '\'' +
            ", fechaSolicitud=" + fechaSolicitud +
            ", fechaCreacion=" + fechaCreacion +
            ", acciones=" + acciones +
            ", adjuntos=" + adjuntos +
            ", documentos=" + documentos +
            ", movimientos=" + movimientos +
            ", sumarioAdministrativoId=" + sumarioAdministrativoId +
            ", investigacionSumariaId=" + investigacionSumariaId +
            ", fechaHasta=" + fechaHasta +
            ", runSolicitante='" + runSolicitante + '\'' +
            ", nombreSolicitante='" + nombreSolicitante + '\'' +
            ", runImplicado='" + runImplicado + '\'' +
            ", nombreImplicado='" + nombreImplicado + '\'' +
            ", entidadSolicitante=" + entidadSolicitante +
            ", entidadImplicada=" + entidadImplicada +
            ", nombreFiscalAsignado='" + nombreFiscalAsignado + '\'' +
            ", providenciaMadreId=" + providenciaMadreId +
            ", numeroReferenciaPorvidenciaMadre=" + numeroReferenciaPorvidenciaMadre +
            '}';
    }
}
