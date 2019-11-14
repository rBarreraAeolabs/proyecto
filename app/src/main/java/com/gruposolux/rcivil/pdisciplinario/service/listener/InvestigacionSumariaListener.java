package com.gruposolux.rcivil.pdisciplinario.service.listener;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;
import com.gruposolux.rcivil.pdisciplinario.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hescobar on 20-02-2019.
 */
@Component
public class InvestigacionSumariaListener {

    private final Logger log = LoggerFactory.getLogger(InvestigacionSumaria.class);

    private final UserService userService;

    @Autowired
    public InvestigacionSumariaListener(UserService userService) {
        this.userService = userService;
    }

//    @EventListener
//    public void handlePostCreateEvent(PostCreateInvestigacionSumariaEvent event) {
//        log.debug("Listener: handlePostCreateEvent");
//
//        InvestigacionSumaria investigacionSumaria = event.getInvestigacionSumaria();
//        User user = userService.get
//
//        String mensaje = "El usuario " + user.getFirstName() + " " + user.getLastName() + " ha creado la investigacion sumaria N " + investigacionSumaria.getId();
//
//        bitacoraService.regitrarParaInvestigacionSumaria(investigacionSumaria, mensaje);
//    }
}
