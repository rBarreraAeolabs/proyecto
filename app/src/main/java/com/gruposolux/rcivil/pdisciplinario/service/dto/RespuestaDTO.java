package com.gruposolux.rcivil.pdisciplinario.service.dto;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;

import java.util.Set;

/**
 * Created by sneiraillanes on 22-04-2019.
 */
public class RespuestaDTO
{
    private Long id;

    private String comentario;

    private ProvidenciaDTO providencia;

    private MovimientoProvidenciaDTO movimientoProvidenciaDTO;

    private Set<AdjuntoDTO> adjuntos;

    private Set<DocumentoDTO> documentos;

    private Boolean guardada;

    private String userName;

    private String userLastname;

    private Long userId;

    private EstadoProvidencia estadoProvidencia;

    public RespuestaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public MovimientoProvidenciaDTO getMovimientoProvidenciaDTO() {
        return movimientoProvidenciaDTO;
    }

    public void setMovimientoProvidenciaDTO(MovimientoProvidenciaDTO movimientoProvidenciaDTO) {
        this.movimientoProvidenciaDTO = movimientoProvidenciaDTO;
    }

    public Set<AdjuntoDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(Set<AdjuntoDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public Set<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public ProvidenciaDTO getProvidencia() {
        return providencia;
    }

    public void setProvidencia(ProvidenciaDTO providencia) {
        this.providencia = providencia;
    }

    public void setDocumentos(Set<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }

    public Boolean getGuardada() {
        return guardada;
    }

    public void setGuardada(Boolean guardada) {
        this.guardada = guardada;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EstadoProvidencia getEstadoProvidencia() {
        return estadoProvidencia;
    }

    public void setEstadoProvidencia(EstadoProvidencia estadoProvidencia) {
        this.estadoProvidencia = estadoProvidencia;
    }

    @Override
    public String toString() {
        return "RespuestaDTO{" +
            "id=" + id +
            ", comentario='" + comentario + '\'' +
            ", providencia=" + providencia +
            ", movimientoProvidenciaDTO=" + movimientoProvidenciaDTO +
            ", adjuntos=" + adjuntos +
            ", documentos=" + documentos +
            ", guardada=" + guardada +
            ", userName='" + userName + '\'' +
            ", userLastname='" + userLastname + '\'' +
            ", userId=" + userId +
            ", estadoProvidencia=" + estadoProvidencia +
            '}';
    }
}
