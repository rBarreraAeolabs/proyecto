package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import java.util.Set;

/**
 * Created by sneiraillanes on 04-04-2019.
 */
public class ProvidenciaPermisoDTO {

    private Long id;
    private String estadoActual;
    private EstadoProvidencia etapa;

    private EstadoProvidencia subEtapa;

    private EstadoProvidencia requisito;

    private String observacion;

    private Long numeroReferencia;

    private Long numeroProvidencia;

    private Long numeroDgdp;

    private Long folio;

    private Long numeroDgd;

    public ProvidenciaPermisoDTO() {
    }


    public String getEstadoActual() {
        return estadoActual;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProvidenciaPermisoDTO{" +
            "id=" + id +
            ", estadoActual='" + estadoActual + '\'' +
            ", etapa=" + etapa +
            ", subEtapa=" + subEtapa +
            ", requisito=" + requisito +
            ", observacion='" + observacion + '\'' +
            ", numeroReferencia=" + numeroReferencia +
            ", numeroProvidencia=" + numeroProvidencia +
            ", numeroDgdp=" + numeroDgdp +
            ", folio=" + folio +
            ", numeroDgd=" + numeroDgd +
            '}';
    }
}
