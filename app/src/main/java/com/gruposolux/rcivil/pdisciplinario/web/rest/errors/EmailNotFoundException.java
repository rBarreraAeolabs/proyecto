package com.gruposolux.rcivil.pdisciplinario.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EmailNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public EmailNotFoundException() {
        super(ErrorConstants.EMAIL_NOT_FOUND_TYPE, "\n" + "Dirección de correo electrónico no registrada", Status.BAD_REQUEST);
    }
}
