package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
import com.gruposolux.rcivil.pdisciplinario.repository.NotificacionInBrowserRepository;
import com.gruposolux.rcivil.pdisciplinario.service.NotificationService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.NotificacionInBrowserDTO;
import com.gruposolux.rcivil.pdisciplinario.web.rest.errors.BadRequestAlertException;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NotificacionInBrowser.
 */
@RestController
@RequestMapping("/api")
public class NotificacionInBrowserResource {

    private final Logger log = LoggerFactory.getLogger(NotificacionInBrowserResource.class);

    private static final String ENTITY_NAME = "notificacionInBrowser";

    private final NotificacionInBrowserRepository notificacionInBrowserRepository;
    private final NotificationService notificationServiceionService;

    public NotificacionInBrowserResource(NotificacionInBrowserRepository notificacionInBrowserRepository, NotificationService notificationServiceionService) {
        this.notificacionInBrowserRepository = notificacionInBrowserRepository;
        this.notificationServiceionService = notificationServiceionService;
    }

    /**
     * POST  /notificacion-in-browsers : Create a new notificacionInBrowser.
     *
     * @param notificacionInBrowser the notificacionInBrowser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificacionInBrowser, or with status 400 (Bad Request) if the notificacionInBrowser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notificacion-in-browsers")
    @Timed
    public ResponseEntity<NotificacionInBrowser> createNotificacionInBrowser(@Valid @RequestBody NotificacionInBrowser notificacionInBrowser) throws URISyntaxException {
        log.debug("REST request to save NotificacionInBrowser : {}", notificacionInBrowser);
        if (notificacionInBrowser.getId() != null) {
            throw new BadRequestAlertException("A new notificacionInBrowser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificacionInBrowser result = notificacionInBrowserRepository.save(notificacionInBrowser);
        return ResponseEntity.created(new URI("/api/notificacion-in-browsers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

//    /**
//     * PUT  /notificacion-in-browsers : Updates an existing notificacionInBrowser.
//     *
//     * @param notificacionInBrowser the notificacionInBrowser to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated notificacionInBrowser,
//     * or with status 400 (Bad Request) if the notificacionInBrowser is not valid,
//     * or with status 500 (Internal Server Error) if the notificacionInBrowser couldn't be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/notificacion-in-browsers")
//    @Timed
//    public ResponseEntity<NotificacionInBrowser> updateNotificacionInBrowser(@Valid @RequestBody NotificacionInBrowser notificacionInBrowser) throws URISyntaxException {
//        log.debug("REST request to update NotificacionInBrowser : {}", notificacionInBrowser);
//        if (notificacionInBrowser.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        NotificacionInBrowser result = notificacionInBrowserRepository.save(notificacionInBrowser);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificacionInBrowser.getId().toString()))
//            .body(result);
//    }

    @PutMapping("notificacion-in-browsers/noti/{id}")


    public ResponseEntity<Optional< NotificacionInBrowserDTO>>  setNotificacion(@PathVariable Long id) {
        log.debug("ruben te actualiza la notificacion: {}", id);
        Optional<NotificacionInBrowserDTO> notificacionInBrowserDTO = notificationServiceionService.UpdateNotificacion(id);
        return ResponseEntity.ok(notificacionInBrowserDTO);
//=======
//    public ResponseEntity<Void>setNotificacion(@PathVariable Long id) {
//        log.debug("ruben te actualiza la notificacion: {}", id);
//        Optional<NotificacionInBrowser> r = notificationServiceionService.UpdateNotificacion(id);
//
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//>>>>>>> origin/fabio-24-09-2019
    }
    /**
     * GET  /notificacion-in-browsers : get all the notificacionInBrowsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of notificacionInBrowsers in body
     */
    @GetMapping("/notificacion-in-browsers")
    @Timed
    public List<NotificacionInBrowser> getAllNotificacionInBrowsers() {
        log.debug("REST request to get all NotificacionInBrowsers");
        return notificacionInBrowserRepository.findAll();
    }

//    /**
//     * GET  /notificacion-in-browsers/:id : get the "id" notificacionInBrowser.
//     *
//     * @param id the id of the notificacionInBrowser to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the notificacionInBrowser, or with status 404 (Not Found)
//     */
//    @GetMapping("/notificacion-in-browsers/{id}")
//    @Timed
//    public ResponseEntity<NotificacionInBrowser> getNotificacionInBrowser(@PathVariable Long id) {
//        log.debug("REST request to get NotificacionInBrowser : {}", id);
//        Optional<NotificacionInBrowser> notificacionInBrowser = notificacionInBrowserRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(notificacionInBrowser);
//    }

    @GetMapping("notificacion-in-browsers/{userId}")

    public ResponseEntity<List<NotificacionInBrowserDTO>> getNotificacion(@PathVariable Long userId) {
        log.debug("ruben te da la notificacion: {}", userId);
        List<NotificacionInBrowserDTO> notificacionInBrowserDTOList = notificationServiceionService.BuscarNotificaciones(userId);


        return ResponseEntity.ok(notificacionInBrowserDTOList);
    }

    /**
     * DELETE  /notificacion-in-browsers/:id : delete the "id" notificacionInBrowser.
     *
     * @param id the id of the notificacionInBrowser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notificacion-in-browsers/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificacionInBrowser(@PathVariable Long id) {
        log.debug("REST request to delete NotificacionInBrowser : {}", id);

        notificacionInBrowserRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
