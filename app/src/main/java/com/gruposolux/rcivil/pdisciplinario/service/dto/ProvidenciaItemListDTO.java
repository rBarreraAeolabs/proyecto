package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import java.time.Instant;

/**
 * Created by sneiraillanes on 02-05-2019.
 */
public class ProvidenciaItemListDTO
{
    private Long id;
    private Instant fechaCreacion;
    private String estadoProvidencia;
    private String nombreGrupo;
    private Long diasDesdeCreacion;
    private Long diasUltimoTramite;
    private Instant fechaVencimiento;
    private boolean standby;


    public ProvidenciaItemListDTO(
        Long id,
        Instant fechaCreacion,
        String estadoProvidencia,
        String nombreGrupo,
        Long diasDesdeCreacion,
        Long diasUltimoTramite,
        Instant fechaVencimiento,
        boolean standby) {

        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estadoProvidencia = estadoProvidencia;
        this.nombreGrupo = nombreGrupo;
        this.diasDesdeCreacion = diasDesdeCreacion;
        this.diasUltimoTramite = diasUltimoTramite;
        this.fechaVencimiento = fechaVencimiento;
        this.standby = standby;
    }

    public ProvidenciaItemListDTO(Long id, Instant fechaCreacion, String estadoActual, String nombreGrupo, long between, long between1, Instant fechaHasta) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstadoProvidencia() {
        return estadoProvidencia;
    }

    public void setEstadoProvidencia(String estadoProvidencia) {
        this.estadoProvidencia = estadoProvidencia;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Long getDiasDesdeCreacion() {
        return diasDesdeCreacion;
    }

    public void setDiasDesdeCreacion(Long diasDesdeCreacion) {
        this.diasDesdeCreacion = diasDesdeCreacion;
    }

    public Long getDiasUltimoTramite() {
        return diasUltimoTramite;
    }

    public void setDiasUltimoTramite(Long diasUltimoTramite) {
        this.diasUltimoTramite = diasUltimoTramite;
    }

    public Instant getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Instant fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isStandby() {
        return standby;
    }

    public void setStandby(boolean standby) {
        this.standby = standby;
    }

    @Override
    public String toString() {
        return "ProvidenciaItemListDTO{" +
            "id=" + id +
            ", fechaCreacion=" + fechaCreacion +
            ", estadoProvidencia='" + estadoProvidencia + '\'' +
            ", nombreGrupo='" + nombreGrupo + '\'' +
            ", diasDesdeCreacion=" + diasDesdeCreacion +
            ", diasUltimoTramite=" + diasUltimoTramite +
            ", fechaVencimiento=" + fechaVencimiento +
            ", standby=" + standby +
            '}';
    }
}
