package com.gruposolux.rcivil.pdisciplinario.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.gruposolux.rcivil.pdisciplinario.domain.Adjunto;
import com.gruposolux.rcivil.pdisciplinario.domain.Documento;
import com.gruposolux.rcivil.pdisciplinario.domain.Plazo;
import com.gruposolux.rcivil.pdisciplinario.domain.User;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import javax.validation.constraints.NotNull;

/**
 * A DTO for the MovimientoProvidencia entity.
 */
public class MovimientoProvidenciaDTO implements Serializable, Comparable<MovimientoProvidenciaDTO> {

    private Long id;

    private String estadoAnterior;

    private String estadoNuevo;

    private Instant fecha;

    private String accion;

    private Long providenciaId;

    private Long plazoId;

    private Integer plazoDias;

    private String comentario;

    private Long userId;

    private String userFirstName;

    private String userLastName;

    private Set<DocumentoDTO> documentos = new HashSet<>();

    private Set<AdjuntoDTO> adjuntos = new HashSet<>();

    private Long numero_dgd;

    private Long numero_dgdp;



//    public MovimientoProvidenciaDTO(
//        Long id,
//        String estadoAnterior,
//        String estadoNuevo,
//        Instant fecha,
//        String accion,
//        Long providenciaId,
//        Long plazoId,
//        Integer plazoDias,
//        String comentario,
//        Long userId,
//        String userFirstName,
//        String userLastName,
////        Set<DocumentoDTO> documentos,
////        Set<AdjuntoDTO> adjuntos,
//        Long numero_dgd,
//        Long numero_dgdp) {
//        this.id = id;
//        this.estadoAnterior = estadoAnterior;
//        this.estadoNuevo = estadoNuevo;
//        this.fecha = fecha;
//        this.accion = accion;
//        this.providenciaId = providenciaId;
//        this.plazoId = plazoId;
//        this.plazoDias = plazoDias;
//        this.comentario = comentario;
//        this.userId = userId;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
////        this.documentos = documentos;
////        this.adjuntos = adjuntos;
//        this.numero_dgd = numero_dgd;
//        this.numero_dgdp = numero_dgdp;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getEstadoNuevo() {
        return estadoNuevo;
    }

    public void setEstadoNuevo(String estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Long getProvidenciaId() {
        return providenciaId;
    }

    public void setProvidenciaId(Long providenciaId) {
        this.providenciaId = providenciaId;
    }

    public Long getPlazoId() {
        return plazoId;
    }

    public void setPlazoId(Long plazoId) {
        this.plazoId = plazoId;
    }

    public Set<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    public Set<AdjuntoDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<AdjuntoDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Integer plazoDias) {
        this.plazoDias = plazoDias;
    }

    public Long getNumero_dgd() {
        return numero_dgd;
    }

    public Long getNumero_dgdp() {
        return numero_dgdp;
    }

    public void setNumero_dgdp(Long numero_dgdp) {
        this.numero_dgdp = numero_dgdp;
    }

    public void setNumero_dgd(Long numero_dgd) {
        this.numero_dgd = numero_dgd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovimientoProvidenciaDTO movimientoProvidenciaDTO = (MovimientoProvidenciaDTO) o;
        if (movimientoProvidenciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movimientoProvidenciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovimientoProvidenciaDTO{" +
            "id=" + id +
            ", estadoAnterior='" + estadoAnterior + '\'' +
            ", estadoNuevo='" + estadoNuevo + '\'' +
            ", fecha=" + fecha +
            ", accion='" + accion + '\'' +
            ", providenciaId=" + providenciaId +
            ", plazoId=" + plazoId +
            ", plazoDias=" + plazoDias +
            ", comentario='" + comentario + '\'' +
            ", userId=" + userId +
            ", userFirstName='" + userFirstName + '\'' +
            ", userLastName='" + userLastName + '\'' +
            ", documentos=" + documentos +
            ", adjuntos=" + adjuntos +
            ", numero_dgd=" + numero_dgd +
            ", numero_dgdp=" + numero_dgdp +
            '}';
    }

    @Override
    public int compareTo(@NotNull MovimientoProvidenciaDTO movimientoProvidenciaDTO)
    {
        if (this.getId() > movimientoProvidenciaDTO.getId())
        {
            return 1;
        }
        else if (this.getId() < movimientoProvidenciaDTO.getId())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
