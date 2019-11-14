package com.gruposolux.rcivil.pdisciplinario.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AuthorityDTO implements Serializable {

    @NotNull
    private String name;

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if(o == null || getClass() != o.getClass()){
            return false;
        }

        AuthorityDTO authorityDTO = (AuthorityDTO) o;

        return true;
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
            ", nombre='" + name + "'" +
            ", descripcion='" + descripcion + "'" +
            '}';
    }
}
