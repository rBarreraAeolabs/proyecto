package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;

import java.time.Instant;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * A DTO for the FichaIngresoSdj entity.
 */
public class FichaIngresoSdjDTO implements Serializable {

    private Long id;

    private Instant fechaInicio;

    private String observacion;

    private Integer plazo;

    private Instant fechaHasta;

    private Long numeroReferencia;

    private Long numeroProvidencia;

    private String tipoSolicitud;

    private Collection<InstruccionesProvidencia> instrucciones;

    private String atentamente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public Instant getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Instant fechaHasta) {
        this.fechaHasta = fechaHasta;
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

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public Collection<InstruccionesProvidencia> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(Collection<InstruccionesProvidencia> instrucciones) {
        this.instrucciones = instrucciones;
    }


    public String getAtentamente() {
        return atentamente;
    }

    public void setAtentamente(String atentamente) {
        this.atentamente = atentamente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FichaIngresoSdjDTO fichaIngresoSdjDTO = (FichaIngresoSdjDTO) o;
        if (fichaIngresoSdjDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichaIngresoSdjDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichaIngresoSdjDTO{" +
            "id=" + id +
            ", fechaInicio=" + fechaInicio +
            ", observacion='" + observacion + '\'' +
            ", plazo=" + plazo +
            ", fechaHasta=" + fechaHasta +
            ", numeroReferencia=" + numeroReferencia +
            ", numeroReferencia=" + numeroProvidencia +
            ", tipoSolicitud=" + tipoSolicitud +
            ", instrucciones=" + instrucciones +
            ", atentamente='" + atentamente + '\'' +
            '}';
    }
}
