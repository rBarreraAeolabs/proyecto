package com.gruposolux.rcivil.pdisciplinario.domain;


import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;

/**
 * A FichaIngresoSdj.
 */
@Entity
@Table(name = "ficha_ingreso_sdj")
public class FichaIngresoSdj implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "plazo")
    private Integer plazo;

    @Column(name = "fecha_hasta")
    private Instant fechaHasta;

    @Column(name = "numero_providencia")
    private Long numeroProvidencia;

    @Column(name = "numero_referencia")
    private Long numeroReferencia;

    @Column(name = "tipo_solicitud")
    private String tipoSolicitud;


    @ElementCollection(targetClass = InstruccionesProvidencia.class)
    @CollectionTable(name = "accion_ficha_ingreso", joinColumns = @JoinColumn(name = "ficha_ingreso_sdj_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "instrucciones")
    private Collection<InstruccionesProvidencia> instrucciones;

    @Column(name = "atentamente")
    private String atentamente;
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public FichaIngresoSdj() {
    }


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

    public Long getNumeroProvidencia() {
        return numeroProvidencia;
    }

    public void setNumeroProvidencia(Long numeroProvidencia) {
        this.numeroProvidencia = numeroProvidencia;
    }

    public Long getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Long numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FichaIngresoSdj that = (FichaIngresoSdj) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return "FichaIngresoSdj{" +
            "id=" + id +
            ", fechaInicio=" + fechaInicio +
            ", observacion='" + observacion + '\'' +
            ", plazo=" + plazo +
            ", fechaHasta=" + fechaHasta +
            ", numeroProvidencia=" + numeroProvidencia +
            ", numeroReferencia=" + numeroReferencia +
            ", tipoSolicitud=" + tipoSolicitud +
            ", Instrucciones=" + instrucciones +
            ", atentamente='" + atentamente + '\'' +
            '}';
    }
}
