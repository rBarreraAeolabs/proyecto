package com.gruposolux.rcivil.pdisciplinario.service.events;

import com.gruposolux.rcivil.pdisciplinario.domain.InvestigacionSumaria;

/**
 * Created by hescobar on 20-02-2019.
 */
public class PostCreateInvestigacionSumariaEvent {

    private InvestigacionSumaria investigacionSumaria;

    public PostCreateInvestigacionSumariaEvent(InvestigacionSumaria investigacionSumaria) {
        this.investigacionSumaria = investigacionSumaria;
    }

    public InvestigacionSumaria getInvestigacionSumaria() {
        return investigacionSumaria;
    }
}
