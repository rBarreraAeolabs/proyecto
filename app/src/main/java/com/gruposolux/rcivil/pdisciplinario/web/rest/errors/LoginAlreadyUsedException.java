package com.gruposolux.rcivil.pdisciplinario.web.rest.errors;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Nombre de inicio de sesión ya usado!", "userManagement", "userexists");
    }
}
