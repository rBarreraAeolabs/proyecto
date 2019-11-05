package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Apelacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.OrdenJuridico;

/**
 * Created by RBarrera on 03-09-2019.
 */
public class ProvidenciaUpdateForTypeDTO {

    private Long providenciaId;
    private Long providenciaMadreId;
    private Long numeroReferencia;
    private OrdenJuridico ordenJuridico;
    private Apelacion tipoApelacion;

    public ProvidenciaUpdateForTypeDTO() {
    }
    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Long getProvidenciaMadreId() {
        return providenciaMadreId;
    }

    public void setProvidenciaMadreId(Long providenciaMadreId) {
        this.providenciaMadreId = providenciaMadreId;
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public OrdenJuridico getOrdenJuridico() {
        return ordenJuridico;
    }

    public void setOrdenJuridico(OrdenJuridico ordenJuridico) {
        this.ordenJuridico = ordenJuridico;
    }

    public Apelacion getTipoApelacion() {
        return tipoApelacion;
    }

    public void setTipoApelacion(Apelacion tipoApelacion) {
        this.tipoApelacion = tipoApelacion;
    }

    @Override
    public String toString() {
        return "ProvidenciaUpdateForTypeDTO{" +
            "providenciaId=" + providenciaId +
            ", providenciaMadreId=" + providenciaMadreId +
            ", numeroReferencia=" + numeroReferencia +
            ", ordenJuridico=" + ordenJuridico +
            ", tipoApelacion=" + tipoApelacion +
            '}';
    }
}


