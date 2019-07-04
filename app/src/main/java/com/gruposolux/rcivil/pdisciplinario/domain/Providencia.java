package com.gruposolux.rcivil.pdisciplinario.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Accion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.OrderStates;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;
import org.checkerframework.checker.units.qual.C;

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

    @Column(name = "numero")
    private Long numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_actual")
    private EstadoProvidencia estadoActual;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoProvidencia tipo;

    @Column(name = "comentario")
    private String comentario;

    @Column(name = "fecha_solicitud")
    private Instant fechaSolicitud;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "providencia")
    @JsonIgnore
    private Set<Derivacion> derivaciones = new HashSet<>();

    @OneToMany(mappedBy = "providencia", fetch = FetchType.EAGER)
    private Set<Documento> documentos = new HashSet<>();

    @OneToMany(mappedBy = "providencia", fetch = FetchType.EAGER)
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

    @ElementCollection(targetClass = Accion.class)
    @CollectionTable(name = "accion_providencia", joinColumns = @JoinColumn(name = "providencia_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "acciones")
    private Collection<Accion> acciones;

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

    @ManyToOne
    private Entidad entidadSolicitante;

    @ManyToOne
    private Entidad entidadImplicada;

    @Column(name = "nombre_fiscal_asignado")
    private String nombreFiscalAsignado;

    @ManyToOne
    private Providencia providenciaMadre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Providencia() {
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

    public Collection<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(Collection<Accion> acciones) {
        this.acciones = acciones;
    }

    public String getRunSolicitante() {
        return runSolicitante;
    }

    public void setRunSolicitante(String runSolicitante) {
        if (this.validateRun(runSolicitante))
        {
            this.runSolicitante = runSolicitante;
        }
        else
        {
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
        if (this.validateRun(runImplicado))
        {
            this.runImplicado = runImplicado;
        }
        else
        {
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

    private boolean validateRun(String run)
    {
        if (run != null && run.length() >= 2)
        {
            String dv = run.trim().substring(run.indexOf("-") + 1);

            if (dv.length() > 1)
            {
                return false;
            }

            String runWithoutDv = run.trim().substring(0, run.indexOf("-"));

            int result = 0;
            int n = 2;

            for(int i = 0; i < runWithoutDv.length(); i++)
            {
                if (n < 8)
                {
                    result += Integer.valueOf(String.valueOf(runWithoutDv.charAt(runWithoutDv.length() - 1 - i))) * n;
                }
                else
                {
                    n = 2;
                    result += Integer.valueOf(String.valueOf(runWithoutDv.charAt(runWithoutDv.length() - 1 - i))) * n;
                }

                n++;
            }

            int dvResult = 11 - (result % 11);

            if (dvResult < 10)
            {
                return Integer.valueOf(dv) == dvResult;
            }
            else if (dvResult == 10)
            {
                return dv.equalsIgnoreCase("k");
            }
            else
            {
                return Integer.valueOf(dv) == 0;
            }
        }

        return false;
    }

    public String getNombreFiscalAsignado() {
        return nombreFiscalAsignado;
    }

    public void setNombreFiscalAsignado(String nombreFiscalAsignado) {
        if (nombreFiscalAsignado != null && nombreFiscalAsignado.trim().length() > 0)
        {
            this.nombreFiscalAsignado = nombreFiscalAsignado;
        }
        else
        {
            this.nombreFiscalAsignado = null;
        }
    }

    public Providencia getProvidenciaMadre() {
        return providenciaMadre;
    }

    public void setProvidenciaMadre(Providencia providenciaMadre) {
        this.providenciaMadre = providenciaMadre;
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
            ", numero=" + numero +
            ", estadoActual=" + estadoActual +
            ", tipo=" + tipo +
            ", comentario='" + comentario + '\'' +
            ", fechaSolicitud=" + fechaSolicitud +
            ", fechaCreacion=" + fechaCreacion +
            ", derivaciones=" + derivaciones +
            ", documentos=" + documentos +
            ", adjuntos=" + adjuntos +
            ", movimientos=" + movimientos +
            ", sumarioAdministrativo=" + sumarioAdministrativo +
            ", investigacionSumaria=" + investigacionSumaria +
            ", acciones=" + acciones +
            ", fechaHasta=" + fechaHasta +
            ", runSolicitante='" + runSolicitante + '\'' +
            ", nombreSolicitante='" + nombreSolicitante + '\'' +
            ", runImplicado='" + runImplicado + '\'' +
            ", nombreImplicado='" + nombreImplicado + '\'' +
            ", entidadSolicitante=" + entidadSolicitante +
            ", entidadImplicada=" + entidadImplicada +
            ", nombreFiscalAsignado='" + nombreFiscalAsignado + '\'' +
            ", providenciaMadre=" + providenciaMadre +
            '}';
    }


}
