package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Providencia.
 */
@Entity
@Table(name = "providencia")
public class Providencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "numeroReferencia")
    private Long numeroReferencia;

    @Column(name = "numeroProvidencia")
    private Long numeroProvidencia;

    @Column(name = "estado_actual")
    private String estadoActual;

    @Column(name = "providencia_madre_id")
    private Long providencia_madre_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "etapa")
    private EstadoProvidencia etapa;

    @Enumerated(EnumType.STRING)
    @Column(name = "subEtapa")
    private EstadoProvidencia subEtapa;

    @Enumerated(EnumType.STRING)
    @Column(name = "requisito")
    private EstadoProvidencia requisito;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoProvidencia tipo;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud;

    @Column(name = "fecha_creacion")
    @JsonIgnore
    private Instant fechaCreacion;


    @Column(name = "fecha_hasta")
    private Instant fechaHasta;

    @Column(name = "run_solicitante")
    private String runSolicitante;

    @Column(name = "nombre_solicitante")
    private String nombreSolicitante;

    @Column(name = "run_implicado")
    private String runImplicado;

    @Column(name = "nombre_implicado")
    private String nombreImplicado;

    @OneToMany(mappedBy = "providencia")
    @JsonIgnore
    private Set<Derivacion> derivaciones = new HashSet<>();

    @OneToMany(mappedBy = "providencia", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Documento> documentos = new HashSet<>();

    @OneToMany(mappedBy = "providencia", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Adjunto> adjuntos = new HashSet<>();

    @OneToMany(mappedBy = "providencia")
    @JsonIgnore
    private Set<MovimientoProvidencia> movimientos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("providencias")
    private SumarioAdministrativo sumarioAdministrativo;

    @ManyToOne
    @JsonIgnoreProperties("providencias")
    private InvestigacionSumaria investigacionSumaria;


    public Collection<InstruccionesProvidencia> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(Collection<InstruccionesProvidencia> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @ElementCollection(targetClass = InstruccionesProvidencia.class)
    @CollectionTable(name = "instruccion_providencia", joinColumns = @JoinColumn(name = "providencia_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "instrucciones")
    private Collection<InstruccionesProvidencia> instrucciones;


    @ManyToOne
    private Entidad entidadSolicitante;

    @ManyToOne
    private Entidad entidadImplicada;

    @Column(name = "nombre_fiscal_asignado")
    private String nombreFiscalAsignado;


    public Providencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // get and setter para el id de la madre


    public Long getProvidencia_madre_id() {
        return providencia_madre_id;
    }

    public void setProvidencia_madre_id(Long providencia_madre_id) {
        this.providencia_madre_id = providencia_madre_id;
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

    public Set<Derivacion> getDerivaciones() {
        return derivaciones;
    }

    public void setDerivaciones(Set<Derivacion> derivaciones) {
        this.derivaciones = derivaciones;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<Adjunto> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<Adjunto> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Set<MovimientoProvidencia> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<MovimientoProvidencia> movimientos) {
        this.movimientos = movimientos;
    }

    public SumarioAdministrativo getSumarioAdministrativo() {
        return sumarioAdministrativo;
    }

    public void setSumarioAdministrativo(SumarioAdministrativo sumarioAdministrativo) {
        this.sumarioAdministrativo = sumarioAdministrativo;
    }

    public InvestigacionSumaria getInvestigacionSumaria() {
        return investigacionSumaria;
    }

    public void setInvestigacionSumaria(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
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
        if (this.validateRun(runSolicitante)) {
            this.runSolicitante = runSolicitante;
        } else {
            this.runSolicitante = "malo";
        }
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public Entidad getEntidadSolicitante() {
        return entidadSolicitante;
    }

    public void setEntidadSolicitante(Entidad entidadSolicitante) {
        this.entidadSolicitante = entidadSolicitante;
    }

    public String getRunImplicado() {
        return runImplicado;
    }

    public void setRunImplicado(String runImplicado) {
        if (this.validateRun(runImplicado)) {
            this.runImplicado = runImplicado;
        } else {
            this.runImplicado = "malo";
        }
    }

    public String getNombreImplicado() {
        return nombreImplicado;
    }

    public void setNombreImplicado(String nombreImplicado) {
        this.nombreImplicado = nombreImplicado;
    }

    public Entidad getEntidadImplicada() {
        return entidadImplicada;
    }

    public void setEntidadImplicada(Entidad entidadImplicada) {
        this.entidadImplicada = entidadImplicada;
    }

    private boolean validateRun(String run) {
        if (run != null && run.length() >= 2) {
            String dv = run.trim().substring(run.indexOf("-") + 1);

            if (dv.length() > 1) {
                return false;
            }

            String runWithoutDv = run.trim().substring(0, run.indexOf("-"));

            int result = 0;
            int n = 2;

            for (int i = 0; i < runWithoutDv.length(); i++) {
                if (n < 8) {
                    result += Integer.valueOf(String.valueOf(runWithoutDv.charAt(runWithoutDv.length() - 1 - i))) * n;
                } else {
                    n = 2;
                    result += Integer.valueOf(String.valueOf(runWithoutDv.charAt(runWithoutDv.length() - 1 - i))) * n;
                }

                n++;
            }

            int dvResult = 11 - (result % 11);

            if (dvResult < 10) {
                return Integer.valueOf(dv) == dvResult;
            } else if (dvResult == 10) {
                return dv.equalsIgnoreCase("k");
            } else {
                return Integer.valueOf(dv) == 0;
            }
        }

        return false;
    }

    public String getNombreFiscalAsignado() {
        return nombreFiscalAsignado;
    }

    public void setNombreFiscalAsignado(String nombreFiscalAsignado) {
        if (nombreFiscalAsignado != null && nombreFiscalAsignado.trim().length() > 0) {
            this.nombreFiscalAsignado = nombreFiscalAsignado;
        } else {
            this.nombreFiscalAsignado = null;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Providencia that = (Providencia) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

    @Override
    public String toString() {
        return "Providencia{" +
            "id=" + id +
//
            '}';
    }
}
