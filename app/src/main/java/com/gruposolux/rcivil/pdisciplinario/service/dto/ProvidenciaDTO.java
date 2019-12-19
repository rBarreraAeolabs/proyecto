/**

 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 *
 */

package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.*;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;

import javax.validation.constraints.Size;

/**
 * A DTO for the Providencia entity.
 */
public class ProvidenciaDTO implements Serializable {

    private Long id;

    private Long numeroReferencia;

    private Long numeroProvidencia;

    private Long numeroDgdp;

    private Long folio;

    private Long numeroDgd;

    private String estadoActual;

    private EstadoProvidencia etapa;

    private EstadoProvidencia subEtapa;

    private EstadoProvidencia requisito;

    private TipoProvidencia tipo;

    private String comentario;

    private Instant fechaSolicitud;

    private Instant fechaCreacion;

    private Collection<InstruccionesProvidencia> instrucciones;

    private Set<AdjuntoDTO> adjuntos = new HashSet<>();

    private Set<DocumentoDTO> documentos = new HashSet<>();

//    private Long movimientos;

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

    private Boolean standby;

    public ProvidenciaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getnumeroReferencia() { return numeroReferencia; }

    public void setnumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }


    public Long getNumeroProvidencia() { return numeroProvidencia; }

    public void setNumeroProvidencia(Long numeroProvidencia) { this.numeroProvidencia = numeroProvidencia; }


    public Long getNumeroDgdp() {
        return numeroDgdp;
    }

    public void setNumeroDgdp(Long numeroDgdp) {
        this.numeroDgdp = numeroDgdp;
    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public Long getNumeroDgd() {
        return numeroDgd;
    }

    public void setNumeroDgd(Long numeroDgd) {
        this.numeroDgd = numeroDgd;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public EstadoProvidencia getEtapa() {
        return etapa;
    }

    public void setEtapa(EstadoProvidencia etapa) {
        this.etapa = etapa;
    }

    public EstadoProvidencia getSubEtapa() { return subEtapa; }

    public void setSubEtapa(EstadoProvidencia subEtapa) { this.subEtapa = subEtapa; }

    public EstadoProvidencia getRequisito() {
        return requisito;
    }

    public void setRequisito(EstadoProvidencia requisito) {
        this.requisito = requisito;
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

    public Collection<InstruccionesProvidencia> getinstrucciones() {
        return instrucciones;
    }

    public void setinstrucciones(Collection<InstruccionesProvidencia> instrucciones) {
        this.instrucciones = instrucciones;
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

    public Boolean getStandby() {
        return standby;
    }

    public void setStandby(Boolean standby) {
        this.standby = standby;
    }

    @Override
    public String toString() {
        return "ProvidenciaDTO{" +
            "id=" + id +
            ", numeroReferencia=" + numeroReferencia +
            ", numeroProvidencia=" + numeroProvidencia +
            ", numeroDgdp=" + numeroDgdp +
            ", folio=" + folio +
            ", estadoActual='" + estadoActual + '\'' +
            ", etapa=" + etapa +
            ", subEtapa=" + subEtapa +
            ", requisito=" + requisito +
            ", tipo=" + tipo +
            ", comentario='" + comentario + '\'' +
            ", fechaSolicitud=" + fechaSolicitud +
            ", fechaCreacion=" + fechaCreacion +
            ", instrucciones=" + instrucciones +
            ", adjuntos=" + adjuntos +
            ", documentos=" + documentos +
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
            ", standby=" + standby +
            '}';
    }
}
