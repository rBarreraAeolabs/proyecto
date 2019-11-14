package com.gruposolux.rcivil.pdisciplinario.cucumber.stepdefs;

import com.gruposolux.rcivil.pdisciplinario.web.rest.ProvidenciaResource;
import com.gruposolux.rcivil.pdisciplinario.web.rest.UserResource;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProvidenciaStepDefs {

    protected ResultActions actions;
    private MockMvc restUserMockMvc;

    @Autowired
    private ProvidenciaResource providenciaResource;

    @Before
    public void setup() {
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(providenciaResource).build();
    }

    @Dado("que existe el endpoint {string}")
    public void entramos_a_la_url(String url) throws Exception {
        actions = restUserMockMvc.perform(get(url)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Cuando("llenamos el formulario")
    public void llenamos_el_formulario() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Cuando("presionamos el boton {string}")
    public void presionamos_el_boton(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Entonces("se crea la providencia")
    public void se_crea_la_providencia() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Entonces("se redirige al detalle")
    public void se_redirige_al_detalle() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Entonces("se muestra el id {string}")
    public void se_muestra_el_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Dado("que existe la providencia con id {string}")
    public void que_existe_la_providencia_con_id(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Cuando("presionamos boton {string}")
    public void presionamos_boton(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Entonces("la providencia con id {string} se ha validado")
    public void la_providencia_con_id_se_ha_validado(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Entonces("se muestra el estado {string}")
    public void se_muestra_el_estado(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the user is not found")
    public void the_user_is_not_found() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

}
