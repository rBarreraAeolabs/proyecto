package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.security.AuthoritiesConstants;
import com.gruposolux.rcivil.pdisciplinario.service.ProvidenciaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.*;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
    private final ProvidenciaRepository providenciaRepository;

    public ProvidenciaResource(ProvidenciaService providenciaService, ProvidenciaRepository providenciaRepository) {
        this.providenciaService = providenciaService;
        this.providenciaRepository = providenciaRepository;
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
     * optimizar para el futuro
     */
    @GetMapping("/providencias")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA + "\")")
    public ResponseEntity<List<ProvidenciaItemListDTO>> getAllProvidencias(Pageable pageable,
                                                                           @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
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
        Optional<ProvidenciaDTO> ProvidenciaDTO = providenciaService.findOne(id);
        log.debug("devuelve del endpoint provi id: {}", ProvidenciaDTO);
        return ResponseUtil.wrapOrNotFound(ProvidenciaDTO);
    }

    @GetMapping("/providencias/detalle/{id}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.VISUALIZAR_PROVIDENCIA + "\")")
    public ResponseEntity<DetalleProvidenciaDTO> detalleProvidencia(@PathVariable Long id) {
        log.debug("REST request to get Providencia : {}", id);
        Optional<DetalleProvidenciaDTO> DetalleProvidenciaDTO = providenciaService.findOneDetalle(id);
        log.debug("devuelve del endpoint detalle: {}", DetalleProvidenciaDTO);
        return ResponseUtil.wrapOrNotFound(DetalleProvidenciaDTO);
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
    public ResponseEntity<Void> reply(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO AL REPLY");
        this.providenciaService.reply(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/fiscalNotificaCierre")
    @Timed
    public ResponseEntity<Void> fiscalNotificaCierre (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO AL fiscal: ");
        this.providenciaService.fiscalNotificaCierre(providenciaResponseDTO);
           return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/formularCargos")
    @Timed
    public ResponseEntity<Void> formularCargos (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO AL formularCargos: ");
        this.providenciaService.formularCargos(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/terminoProbatorio")
    @Timed
    public ResponseEntity<Void> terminoProbatorio (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO a terminoProbatorio: ");
        this.providenciaService.terminoProbatorio(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/siDeAcuerdo")
    @Timed
    public ResponseEntity<Void> siDeAcuerdo (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO a deAcuerdo: ");
        this.providenciaService.siDeAcuerdo(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/noReabro")
    @Timed
    public ResponseEntity<Void> noReabro (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO a noReabro: ");
        this.providenciaService.noReabro(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/noPropone")
    @Timed
    public ResponseEntity<Void> noPropone (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO a noPropone: ");
        this.providenciaService.noPropone(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PostMapping("/providencias/remiteExpediente")
    @Timed
    public ResponseEntity<Void> remiteExpediente (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO AL fiscal: ");
        this.providenciaService.remiteExpediente(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/aceptar")
    @Timed
    public ResponseEntity<Void> aceptar(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("ENTRO AL ACEPTAR");
        this.providenciaService.aceptar(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/rechazar")
    @Timed
    public ResponseEntity<Void> rechazar(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        this.providenciaService.rechazar(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/prorroga")
    @Timed
    public ResponseEntity<Void> prorroga(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton prorroga");

        Long iDMadre = providenciaRepository.findIDMadre(providenciaResponseDTO.getProvidenciaId());
        log.debug("paso por alquiii");
        log.debug("el ID de la madre es" + iDMadre);
        EstadoProvidencia requisitoMadre = null;

        if(iDMadre != null) {
            requisitoMadre = providenciaRepository.findRequisitoByIdMadre(iDMadre);
        }
        log.debug("el requisito de la madre es" + requisitoMadre);

        if (requisitoMadre != EstadoProvidencia.PETICION_PRORROGA_2 || iDMadre == null){
            this.providenciaService.prorroga(providenciaResponseDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            throw new BadRequestAlertException("Ya alcanzo el limite de Prorrogas Para Esta Providencia", null, null);
        }
    }

    @PostMapping("/providencias/inculpadoEnviaMemo")
    @Timed
    public ResponseEntity<Void> inculpadoEnviaMemo(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton noApela");
        this.providenciaService.inculpadoEnviaMemo(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping("/providencias/inculpadoNoEnviaMemo")
    @Timed
    public ResponseEntity<Void> inculpadoNoEnviaMemo(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton noApela");
        this.providenciaService.inculpadoNoEnviaMemo(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/comeback")
    @Timed
    public ResponseEntity<Void> goBackwards(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        this.providenciaService.goBackwards(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/notificaDemandado")
    @Timed
    public ResponseEntity<Void> notificaDemandado(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        this.providenciaService.notificaDemandado(providenciaResponseDTO);
        Providencia providencia = providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId()).get();

        if (providencia.getRequisito() == EstadoProvidencia.SE_NOTIFICO_INCULPADO) {
            return ResponseEntity.ok().headers(HeaderUtil.createAlert("Se Notifico al Inculpado!",ENTITY_NAME)).body(null);
        } else {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @PostMapping("/providencias/apela")
    @Timed
    public ResponseEntity<Void> apela(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton apela");
        this.providenciaService.apela(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/noApela")
    @Timed
    public ResponseEntity<Void> noApela(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton noApela");
        this.providenciaService.noApela(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/tomaRazon")
    @Timed
    public ResponseEntity<Void> tomaRazon(@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton tomaRazon");
        this.providenciaService.tomaRazon(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/registra")
    @Timed
    public ResponseEntity<Void> registra (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton registra");
        this.providenciaService.registra(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/representa")
    @Timed
    public ResponseEntity<Void> representa (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton representa");
        this.providenciaService.representa(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/memoConductor")
    @Timed
    public ResponseEntity<Void> memoConductor (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton memoConductor");
        this.providenciaService.memoConductor(providenciaResponseDTO);
        Providencia providencia = providenciaRepository.findById(providenciaResponseDTO.getProvidenciaId()).get();

        if (providencia.getRequisito() == EstadoProvidencia.REALIZADO_MEMO_CONDUCTOR) {
            return ResponseEntity.ok().headers(HeaderUtil.createAlert("Se ha realizado Memo Conductor!",ENTITY_NAME)).body(null);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/examenLegalidad")
    @Timed
    public ResponseEntity<Void> examenLegalidad (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton examenLegalidad");
        this.providenciaService.examenLegalidad(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Adjuntado Examen de Legalidad!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/emiteProvidencia")
    @Timed
    public ResponseEntity<Void> emiteProvidencia (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton emiteProvidencia");
        this.providenciaService.emiteProvidencia(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("DN ha emitido Providencia!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/alcance")
    @Timed
    public ResponseEntity<Void> alcance (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton alcance");
        this.providenciaService.alcance(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Alcance!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/alcanceConResolucion")
    @Timed
    public ResponseEntity<Void> alcanceConResolucion (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton alcanceConResolucion");
        this.providenciaService.alcanceConResolucion(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);    }

    @PostMapping("/providencias/alcanceSinResolucion")
    @Timed
    public ResponseEntity<Void> alcanceSinResolucion (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton alcanceSinResolucion");
        this.providenciaService.alcanceSinResolucion(providenciaResponseDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/providencias/resolucion")
    @Timed
    public ResponseEntity<Void> resolucion (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton resolucion");
        this.providenciaService.resolucion(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Resolución!",ENTITY_NAME)).body(null);    }

    @PostMapping("/providencias/notificarDGDP")
    @Timed
    public ResponseEntity<Void> notificarDGDP (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton notificarDGDP");
        this.providenciaService.notificarDGDP(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Se ha notificado al DGDP!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/suspension")
    @Timed
    public ResponseEntity<Void> suspension (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton suspension");
        this.providenciaService.suspension(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Suspension!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/multa")
    @Timed
    public ResponseEntity<Void> multa (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton multa");
        this.providenciaService.multa(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Multa!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/sensura")
    @Timed
    public ResponseEntity<Void> sensura (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton sensura");
        this.providenciaService.sensura(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Sensura!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/destitucion")
    @Timed
    public ResponseEntity<Void> destitucion (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton destitucion");
        this.providenciaService.destitucion(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Ha Seleccionado Destitución!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/notificaRemuneracion")
    @Timed
    public ResponseEntity<Void> notificaRemuneracion (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton notificaRemuneracion");
        this.providenciaService.notificaRemuneracion(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Se ha notificado a Remuneración!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/notificaDenunciante")
    @Timed
    public ResponseEntity<Void> notificaDenunciante (@RequestBody ProvidenciaResponseDTO providenciaResponseDTO) {
        log.debug("endpoint botton notificaDenunciante");
        this.providenciaService.notificaDenunciante(providenciaResponseDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Se ha notificado al Denunciante!",ENTITY_NAME)).body(null);
    }

    @PostMapping("/providencias/actions")
    @Timed
    public ResponseEntity<HashMap<String, Boolean>> getActionsPermitted(@RequestBody ProvidenciaResponseDTO providenciaDTO) {
        HashMap<String, Boolean> actionsPermitted = this.providenciaService.getActionsPermitted(providenciaDTO);
        return new ResponseEntity<>(actionsPermitted, HttpStatus.OK);
    }

    @PostMapping("/providencias/templates")
    @Timed
    public ResponseEntity<List<PlantillaDTO>> getPlantillasEnabled(@RequestBody ProvidenciaDTO providenciaDTO) {
        List<PlantillaDTO> plantillas = this.providenciaService.getPlantillasEnabled(providenciaDTO);
        return new ResponseEntity<>(plantillas, HttpStatus.OK);
    }

    /**
     * El parámetro providenciaId nos permite evitar que sea retornada la misma providencia que se está visualizando.
     *
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

    @PutMapping("/providencias/updateNumeroDGD")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNumeroDGD(@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {

        log.debug(" Entro al updateNumeroDGD con el DTO : {} ");

        ProvidenciaDTO providenciaDTO = this.providenciaService.updateNumeroDGD(providenciaUpdateNumeroReferenciaDTO);

        if (providenciaDTO.getNumeroDgd() == null) {
            return ResponseEntity.ok().headers(HeaderUtil.message("No se pudo agregar el N° DGD",ENTITY_NAME)).body(null);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("El N° DGD se agrego Correctamente!",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/updateNumeroDGDP")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNumeroDGDP(@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {

        log.debug(" Entro al updateNumeroDGDP con el DTO : {} ");

        ProvidenciaDTO providenciaDTO = this.providenciaService.updateNumeroDGDP(providenciaUpdateNumeroReferenciaDTO);

        if (providenciaDTO.getNumeroDgd() == null) {
            return ResponseEntity.ok().headers(HeaderUtil.message("No se pudo agregar el N° DGDP",ENTITY_NAME)).body(null);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("El N° DGDP se agrego Correctamente!",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/updateNumeroProvidencia")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNumeroProvidencia (@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {

        log.debug(" Entro al updateNumeroProvidencia con el DTO : {} ");

        ProvidenciaDTO providenciaDTO = this.providenciaService.updateNumeroProvidencia(providenciaUpdateNumeroReferenciaDTO);

        if (providenciaDTO.getNumeroProvidencia() == null) {
            return ResponseEntity.ok().headers(HeaderUtil.message("No se pudo agregar el N° DGD",ENTITY_NAME)).body(null);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("El N° DGD se agrego Correctamente!",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/updateFolio")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateFolio(@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {

        log.debug(" Entro al updateFolio con el DTO : {} ");

        ProvidenciaDTO providenciaDTO = this.providenciaService.updateFolio(providenciaUpdateNumeroReferenciaDTO);

        if (providenciaDTO.getFolio() == null) {
            return ResponseEntity.ok().headers(HeaderUtil.message("No se pudo agregar el N° de Folio",ENTITY_NAME)).body(null);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("El N° de Folio se agrego Correctamente!",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/nroReferencia")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateNroReferencia(@RequestBody ProvidenciaUpdateNumeroReferenciaDTO providenciaUpdateNumeroReferenciaDTO) {

        Long numeroReferencia =  providenciaUpdateNumeroReferenciaDTO.getNumeroReferencia();
        log.debug(" Entro al updateNumeroReferencia con el numero: {} ", numeroReferencia);
        Long numeroReferenciaNoUsado = this.providenciaRepository.existeNumeroReferencia(numeroReferencia);

        if (numeroReferenciaNoUsado != null) {
            throw new BadRequestAlertException("El N° que ingreso ya esta siendo utilizado, por favor ingrese otro numero!", null, null);
        }
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateNumeroReferencia(providenciaUpdateNumeroReferenciaDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("El N° de Referencia se agrego Correctamente!",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    @PutMapping("/providencias/tipoSolicitud")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateTipoSolicitud(@RequestBody ProvidenciaUpdateTipoSolicitudDTO providenciaUpdateTipoSolicitudDTO) {
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateTipoSolicitud(providenciaUpdateTipoSolicitudDTO);

        if (providenciaDTO != null) {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
                .toString())).body(providenciaDTO);
        }

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("No se pudo agregar el tipo de solicitud a la providencia",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

    // Metodo permite obtener todas las providencias asociadas a un Numero de Referencia
    /**
     * el metodo  @GetMapping es para en viar datos al front sin que el nos envie nada
      */

    @GetMapping("/providencias/buscarNroReferencia")
    @Timed
    public ResponseEntity<Set<ProvidenciaDTO>> getnroReferencia() {
        log.debug("ruben te da el numero de referencia hola: {}");
        Set<ProvidenciaDTO> providenciaDTOList = providenciaService.findAllNro();
            log.debug("ruben te da la lista"+providenciaDTOList);
        return new ResponseEntity<>(this.providenciaService.findAllNro(), HttpStatus.OK);
  }

    /**
     *  el metodo @PostMapping nos permite recibir un dato del front y tambien devolver algo el put hace lo mismo pero es una mala practica
     *
     *
     */
    //crear prrorroga
//    @PostMapping("/providencias/prorroga/{idMadre}")
//    @Timed
//    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\") or hasAuthority(\"" + AuthoritiesConstants.CREAR_PROVIDENCIA + "\")")
//    public ResponseEntity<ProvidenciaDTO> crearProrroga( @RequestParam Long idMadre
//                                                       ) throws URISyntaxException {
//        log.debug("recibo el id madre: {}", idMadre);
//        // Busca la providencia madre
//        Providencia providenciaMadre = providenciaService.getProvidenciaMadreid(idMadre );
//
//         //Crear una providencia nueva a partir de la madre
//            ProvidenciaDTO result = null;
//            result = providenciaService.createdProvidenciProrroga( providenciaMadre );
//      return ResponseEntity.created(new URI("/api/providencias/" + result.getId())).body(result);
//    }

    @PutMapping("/providencias/updateProvidenciaForType")
    @Timed
    public ResponseEntity<ProvidenciaDTO> updateProvidenciaForType(@RequestBody ProvidenciaUpdateForTypeDTO providenciaUpdateForTypeDTO)
        throws URISyntaxException {

        EstadoProvidencia requisitoMadre = providenciaRepository.findRequisitoByNumberRefer(providenciaUpdateForTypeDTO.getNumeroReferencia());
        log.debug("REFERENCIA MADRE: "+providenciaUpdateForTypeDTO.getNumeroReferencia() + " y RequisitoMadre es: "+ requisitoMadre);

        if(requisitoMadre == EstadoProvidencia.DGD_DESPACHA_SUMARIO_COMPLETO &&
            providenciaUpdateForTypeDTO.getOrdenJuridico()== null){
           throw new BadRequestAlertException("Debe seleccionar el tipo de Orden Juridica", null, null);
        }
        ProvidenciaDTO providenciaDTO = this.providenciaService.updateProvidenciaForType(providenciaUpdateForTypeDTO);

        if (providenciaDTO != null)
        {
            return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, providenciaDTO.getId()
                .toString())).body(providenciaDTO);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("No se pudo crear la relación entre las dos providencias",
            providenciaDTO.getId().toString())).body(providenciaDTO);
    }

}
