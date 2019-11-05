/*
 *
 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 *
 */

package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;

import java.time.Instant;
import java.util.Collection;


public class DetalleProvidenciaDTO  {

    private Long id;

    private Long numeroReferencia;

    private Long numeroProvidencia;

    private Long numeroDgdp;

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

    private Long sumarioAdministrativoId;

    private Long investigacionSumariaId;

    private Instant fechaHasta;

    private String runSolicitante;

    private String nombreSolicitante;

    private String runImplicado;

    private String nombreImplicado;

    private EntidadDTO entidadSolicitante;

    private EntidadDTO entidadImplicada;

    private String nombreFiscalAsignado;

    private Long providenciaMadreId;

     private Boolean standby;

     private Integer totalAdjuntos;

    public DetalleProvidenciaDTO(
        Long id,
        Long numeroReferencia,
        Long numeroProvidencia,
        Long numeroDgdp,
        Long numeroDgd,
        String estadoActual,
        EstadoProvidencia etapa,
        EstadoProvidencia subEtapa,
        EstadoProvidencia requisito,
        TipoProvidencia tipo,
        String comentario,
        Instant fechaSolicitud,
        Instant fechaCreacion,
        Collection<InstruccionesProvidencia> instrucciones,
//        Long sumarioAdministrativoId,
//        Long investigacionSumariaId,
        Instant fechaHasta, String runSolicitante,
        String nombreSolicitante, String runImplicado,
        String nombreImplicado, String nombreFiscalAsignado,
        Long providenciaMadreId,  Boolean standby,Integer totalAdjuntos) {
        this.id = id;
        this.numeroReferencia = numeroReferencia;
        this.numeroProvidencia = numeroProvidencia;
        this.numeroDgdp = numeroDgdp;
        this.numeroDgd = numeroDgd;
        this.estadoActual = estadoActual;
        this.etapa = etapa;
        this.subEtapa = subEtapa;
        this.requisito = requisito;
        this.tipo = tipo;
        this.comentario = comentario;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaCreacion = fechaCreacion;
        this.instrucciones = instrucciones;
//        this.sumarioAdministrativoId = sumarioAdministrativoId;
//        this.investigacionSumariaId = investigacionSumariaId;
        this.fechaHasta = fechaHasta;
        this.runSolicitante = runSolicitante;
        this.nombreSolicitante = nombreSolicitante;
        this.runImplicado = runImplicado;
        this.nombreImplicado = nombreImplicado;
        this.nombreFiscalAsignado = nombreFiscalAsignado;
        this.providenciaMadreId = providenciaMadreId;
              this.standby = standby;
              this.totalAdjuntos= totalAdjuntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Long getNumeroProvidencia() {
        return numeroProvidencia;
    }

    public void setNumeroProvidencia(Long numeroProvidencia) {
        this.numeroProvidencia = numeroProvidencia;
    }

    public Long getNumeroDgdp() {
        return numeroDgdp;
    }

    public void setNumeroDgdp(Long numeroDgdp) {
        this.numeroDgdp = numeroDgdp;
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

    public EstadoProvidencia getSubEtapa() {
        return subEtapa;
    }

    public void setSubEtapa(EstadoProvidencia subEtapa) {
        this.subEtapa = subEtapa;
    }

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

    public EntidadDTO getEntidadSolicitante() {
        return entidadSolicitante;
    }

    public void setEntidadSolicitante(EntidadDTO entidadSolicitante) {
        this.entidadSolicitante = entidadSolicitante;
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



    public Boolean getStandby() {
        return standby;
    }

    public void setStandby(Boolean standby) {
        this.standby = standby;
    }

    public Integer getTotalAdjuntos() {
        return totalAdjuntos;
    }

    public void setTotalAdjuntos(Integer totalAdjuntos) {
        this.totalAdjuntos = totalAdjuntos;
    }

    @Override
    public String toString() {
        return "DetalleProvidenciaDTO{" +
            "id=" + id +
            ", numeroReferencia=" + numeroReferencia +
            ", numeroProvidencia=" + numeroProvidencia +
            ", numeroDgdp=" + numeroDgdp +
            ", numeroDgd=" + numeroDgd +
            ", estadoActual='" + estadoActual + '\'' +
            ", etapa=" + etapa +
            ", subEtapa=" + subEtapa +
            ", requisito=" + requisito +
            ", tipo=" + tipo +
            ", comentario='" + comentario + '\'' +
            ", fechaSolicitud=" + fechaSolicitud +
            ", fechaCreacion=" + fechaCreacion +
            ", instrucciones=" + instrucciones +
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
            ", standby=" + standby +
            ", totalAdjuntos=" + totalAdjuntos +
            '}';
    }
}
