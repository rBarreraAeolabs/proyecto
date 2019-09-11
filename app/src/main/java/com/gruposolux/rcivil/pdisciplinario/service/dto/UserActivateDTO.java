package com.gruposolux.rcivil.pdisciplinario.service.dto;

/**
 * Created by sneiraillanes on 20-03-2019.
 */
public class UserActivateDTO {
    private Long id;
    private Boolean activated;

    public UserActivateDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "UserActivateDTO{" +
            "id=" + id +
            ", activated=" + activated +
            '}';
    }
}
