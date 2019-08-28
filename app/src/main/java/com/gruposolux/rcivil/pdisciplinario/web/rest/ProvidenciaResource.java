package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.Apelacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.OrdenJuridico;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.ProvidenciaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.checkerframework.checker.units.qual.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Providencia.
 */
@RestController
@RequestMapping("/api")
public class ProvidenciaResource {

    private final Logger log = LoggerFactory.getLogger(ProvidenciaResource.class);

    private static final String ENTITY_NAME = "providencia";

    private final ProvidenciaService providenciaService;

    public ProvidenciaResource(ProvidenciaService providenciaService) {
        this.providenciaService = providenciaService;
    }

    /**
     * POST  /providencias : Create a new providencia.
     *
     * @param providenciaDTO the providenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new providenciaDTO, or with status 400 (Bad Request) if the providencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> createProvidencia(@RequestBody @Valid ProvidenciaDTO providenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Providencia : {}", providenciaDTO);

        if (providenciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new providencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProvidenciaDTO result = providenciaService.save(providenciaDTO);
        return ResponseEntity.created(new URI("/api/providencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /providencias : Updates an existing providencia.
     *
     * @param providenciaDTO the providenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated providenciaDTO,
     * or with status 400 (Bad Request) if the providenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the providenciaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.EDITAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> updateProvidencia(@RequestBody ProvidenciaDTO providenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Providencia : {}", providenciaDTO);
        if (providenciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProvidenciaDTO result = providenciaService.save(providenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /providencias : get all the providencias.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of providencias in body
     */
    @GetMapping("/providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA + "\")")
    public ResponseEntity<List<ProvidenciaItemListDTO>> getAllProvidencias(Pageable pageable,
                                                                           @RequestParam(required = false, defaultValue = "false") boolean eagerload)
    {
        log.debug("REST request to get a page of Providencias");
        Page<ProvidenciaItemListDTO> page = null;
        if (eagerload) {
//            page = providenciaService.findAllWithEagerRelationships(pageable);
        } else {
            page = providenciaService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/providencias?eagerload=%b", eagerload));



        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /providencias/:id : get the "id" providencia.
     *
     * @param id the id of the providenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the providenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/providencias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> getProvidencia(@PathVariable Long id) {
        log.debug("REST request to get Providencia : {}", id);
        Optional<ProvidenciaDTO> providenciaDTO = providenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(providenciaDTO);
    }

    /**
     * DELETE  /providencias/:id : delete the "id" providencia.
     *
     * @param id the id of the providenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/providencias/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteProvidencia(@PathVariable Long id) {
        log.debug("REST request to delete Providencia : {}", id);
        providenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/providencias/reply")
    @Timed
    public ResponseEntity<Void> reply(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO)
    {
        this.providenciaService.reply(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/providencias/aceptar")
    @Timed
    public ResponseEntity<Void> aceptar(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO)
    {
        this.providenciaService.aceptar(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/providencias/rechazar")
    @Timed
    public ResponseEntity<Void> rechazar(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO)
    {
        this.providenciaService.rechazar(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/comeback")
    @Timed
    public ResponseEntity<Void> goBackwards(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO)
    {
        this.providenciaService.goBackwards(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/actions")
    @Timed
    public ResponseEntity<HashMap<String, Boolean>> getActionsPermitted(@RequestBody ProvidenciaDTO providenciaDTO)
    {
        HashMap<String, Boolean> actionsPermitted = this.providenciaService.getActionsPermitted(providenciaDTO);
        return new ResponseEntity<>(actionsPermitted, HttpStatus.OK);
    }

    @PostMapping("/providencias/templates")
    @Timed
    public ResponseEntity<List<PlantillaDTO>> getPlantillasEnabled(@RequestBody ProvidenciaDTO providenciaDTO)
    {
        List<PlantillaDTO> plantillas = this.providenciaService.getPlantillasEnabled(providenciaDTO);
        return new ResponseEntity<>(plantillas, HttpStatus.OK);
    }

    /**
     * El parámetro providenciaId nos permite evitar que sea retornada la misma providencia que se está visualizando.
     * @param runImplicado
     * @param entidadImplicadaId
     * @return
     */
    @GetMapping("/providencias/related/{runImplicado}/{entidadImplicadaId}/{providenciaId}")
    @Timed
    public ResponseEntity<Set<ProvidenciaDTO>> getAllByRunOrEntidadImplicada(@PathVariable String runImplicado,
                                                                             @PathVariable Long entidadImplicadaId,
                                                                             @PathVariable Long providenciaId)
    {
        Set<ProvidenciaDTO> providenciaDTOs = this.providenciaService.findAllByRunOrEntidadImplicada(runImplicado,
            entidadImplicadaId, providenciaId);

        return new ResponseEntity<>(providenciaDTOs, HttpStatus.OK);
    }

    @PutMapping("/providencias/fiscal")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNombreFiscalAsignado(@RequestBody ProvidenciaDTO providenciaDTO)
    {
        providenciaDTO = this.providenciaService.updateNombreFiscalAsignado(providenciaDTO);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
            .toString())).body(providenciaDTO);
    }

    @GetMapping("/providencias/all")
    @Timed
    public ResponseEntity<Set<ProvidenciaDTO>> getAllWithoutPagination()
    {
        return new ResponseEntity<>(this.providenciaService.findAllWithoutPagination(), HttpStatus.OK);
    }

    @PutMapping("/providencias/madre")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateProvidenciaMadre(@RequestBody ProvidenciaUpdateMadreDTO
                                                                     providenciaUpdateMadreDTO)
    {
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateProvidenciaMadre(providenciaUpdateMadreDTO);

        if (providenciaDTO != null)
        {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
                .toString())).body(providenciaDTO);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("No se pudo crear la relación entre las dos providencias",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/nroReferencia")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNroReferencia(@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO)
    {
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateNumeroReferencia(providenciaUpdateNumeroReferenciaDTO);

        if (providenciaDTO != null)
        {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
                .toString())).body(providenciaDTO);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("No se pudo crear la relación entre las dos providencias",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/tipoSolicitud")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateTipoSolicitud(@RequestBody ProvidenciaUpdateTipoSolicitudDTO providenciaUpdateTipoSolicitudDTO)
    {
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateTipoSolicitud(providenciaUpdateTipoSolicitudDTO);

        if (providenciaDTO != null)
        {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
                .toString())).body(providenciaDTO);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("No se pudo agregar el tipo de solicitud a la providencia",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

        // Crea providencia Seleccion Fiscal
    @PostMapping("/providencias/crearSeleccionFiscal")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> crearSeleccionFiscal(@RequestBody (required=false) ProvidenciaDTO providenciaDTO,
                                                               @RequestParam Long numeroReferencia) throws URISyntaxException {
        String tipoProvidencia = "SeleccionFiscal";
        // Busca la providencia madre
        Providencia providenciaMadre = providenciaService.getProvidenciaNumeroReferencia(numeroReferencia, tipoProvidencia);

        //Crear una providencia nueva a partir de la madre
        ProvidenciaDTO result = providenciaService.createdProvidenciaForType(providenciaDTO, providenciaMadre, null);

        return ResponseEntity.created(new URI("/api/providencias/" + result.getId())).body(result);
    }

    // Crea providencia tipo Orden Juridico
    @PostMapping("/providencias/crearProvidenciaOrdenJuridico")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> crearProvidenciaOrdenJuridico (@RequestBody (required=false) ProvidenciaDTO providenciaDTO,
                                                                         @RequestParam Long numeroReferencia,
                                                                         OrdenJuridico tipoOrdeJuridico) throws URISyntaxException {
        String tipoProvidencia = "ordenJuridico";
        // Primero buscar la providencia madre
        Providencia providenciaMadre = providenciaService.getProvidenciaNumeroReferencia(numeroReferencia, tipoProvidencia);

        //Crear una providencia nueva a partir de la madre y segun el Tipo Seleccionado (Absolver, Sobreceder, Reabrir, Sancionar)
        ProvidenciaDTO result = null;
        if (providenciaMadre.getRequisito() == EstadoProvidencia.SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO) {
            result = providenciaService.createdProvidenciaForType(providenciaDTO, providenciaMadre, tipoOrdeJuridico);
        }else{
            log.debug("No se puede Crear una Providencia de tipo Orden Juridico " + providenciaMadre.getId());
        }
        return ResponseEntity.created(new URI("/api/providencias/" + result.getId())).body(result);
    }

    // Crea providencia tipo Orden Juridico
    @PostMapping("/providencias/crearProvidenciaSeleccionApelacion")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA + "\")")
    public ResponseEntity<ProvidenciaDTO> crearProvidenciaSeleccionApelacion(@RequestBody (required=false) ProvidenciaDTO providenciaDTO,
                                                                             @RequestParam Long numeroReferencia,
                                                                             Apelacion seleccionApelacion) throws URISyntaxException {
        String tipoProvidencia = "seleccionApelacion";
        // Primero buscar la providencia madre
        Providencia providenciaMadre = providenciaService.getProvidenciaNumeroReferencia(numeroReferencia, tipoProvidencia);

        ProvidenciaDTO result = null;
        if (providenciaMadre.getEtapa() == EstadoProvidencia.IJ_PROVIDENCIA_SANCIONAR) {
            result = providenciaService.createdProvidenciaSancion(providenciaDTO, providenciaMadre, seleccionApelacion);
        }else{
            log.debug("No se puede Crear una Providencia de tipo Sancion Apelo o No " + providenciaMadre.getId());
        }
        return ResponseEntity.created(new URI("/api/providencias/" + result.getId())).body(result);
    }

      // Metodo permite obtener todas las providencias asociadas a un Numero de Referencia
    @GetMapping("/provi/{nroReferencia}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA + "\")")
    public ResponseEntity<List<NroReferenciaDTO>> getnroReferencia(@PathVariable Long nroReferencia) {
        log.debug("REST request to get Providencia hola: {}", nroReferencia);
        List<NroReferenciaDTO> nroReferenciaDTOList = providenciaService.findAllNro(nroReferencia);

      //    return new ResponseEntity<>(nroReferenciaDTOList, HttpStatus.ACCEPTED );
       //
        return  ResponseEntity.ok (nroReferenciaDTOList);
    }
}
