/*
 * *
 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 * /
 */
package com.gruposolux.rcivil.pdisciplinario.service.dto;


import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import com.gruposolux.rcivil.pdisciplinario.domain.User;

import java.time.Instant;



public class NotificacionInBrowserDTO {

  private long id;
  private String contenido;
    private Long userId;
  private Long grupoId;
  private Instant createdAt;
  private Boolean visto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    @Override
    public String toString() {
        return "NotificacionInBrowserDTO{" +
            "id=" + id +
            ", contenido='" + contenido + '\'' +
            ", grupoId=" + grupoId +
            ", userId=" + userId +
            ", createdAt='" + createdAt + '\'' +
            ", visto=" + visto +
            '}';
    }
}
