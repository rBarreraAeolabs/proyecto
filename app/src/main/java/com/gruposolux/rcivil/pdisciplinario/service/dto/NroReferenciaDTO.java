/*
 * *
 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 * /
 */

package com.gruposolux.rcivil.pdisciplinario.service.dto;



import java.time.Instant;
import java.io.Serializable;
import java.util.*;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;


import javax.validation.constraints.Size;

/**
 * Created by rbarrera on 8/9/2019.
 */
public class NroReferenciaDTO  {
    private Long id;

    private EstadoProvidencia etapa;

    private Long numeroReferencia;

    private Long numeroProvidencia;

    private String estadoActual;

    private EstadoProvidencia subEtapa;

    private EstadoProvidencia requisito;

    private TipoProvidencia tipo;

    private Long providenciaMadreId;

    public NroReferenciaDTO(){
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

    public Long getProvidenciaMadreId() {
        return providenciaMadreId;
    }

    public void setProvidenciaMadreId(Long providenciaMadreId) {
        this.providenciaMadreId = providenciaMadreId;
    }

    @Override
    public String toString() {
        return "NroReferenciaDTO{" +
            "id=" + id +
//            ", numeroReferencia=" + numeroReferencia +
//            ", numeroProvidencia=" + numeroProvidencia +
//            ", estadoActual='" + estadoActual + '\'' +
//            ", etapa=" + etapa +
//            ", subEtapa=" + subEtapa +
//            ", requisito=" + requisito +
//            ", tipo=" + tipo +
//            ", providenciaMadreId=" + providenciaMadreId +
//            ", numeroReferenciaPorvidenciaMadre=" + numeroReferenciaPorvidenciaMadre +
            '}';
    }
}
