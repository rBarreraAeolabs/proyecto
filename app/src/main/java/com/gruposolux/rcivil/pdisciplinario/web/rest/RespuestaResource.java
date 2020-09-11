package com.gruposolux.rcivil.pdisciplinario.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gruposolux.rcivil.pdisciplinario.service.RespuestaService;
import com.gruposolux.rcivil.pdisciplinario.service.dto.ProvidenciaDTO;
import com.gruposolux.rcivil.pdisciplinario.service.dto.RespuestaDTO;
import com.gruposolux.rcivil.pdisciplinario.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.checkerframework.checker.units.qual.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by sneiraillanes on 22-04-2019.
 */
@RestController
@RequestMapping("/api")
public class RespuestaResource
{
    private final Logger log = LoggerFactory.getLogger(RespuestaResource.class);
    private static final String ENTITY_NAME = "respuesta";
    private final RespuestaService respuestaService;

    public RespuestaResource(RespuestaService respuestaService)
    {
        this.respuestaService = respuestaService;
    }

    @PostMapping("/respuestas")
    @Timed
    public ResponseEntity<RespuestaDTO> save(@RequestBody RespuestaDTO respuestaDTO)
    {
        respuestaDTO = this.respuestaService.save(respuestaDTO);
        return new ResponseEntity<>(respuestaDTO, HttpStatus.OK);
    }

    @PutMapping("/respuestas")
    @Timed
    public ResponseEntity<RespuestaDTO> update(@RequestBody RespuestaDTO respuestaDTO)
    {
        respuestaDTO = this.respuestaService.save(respuestaDTO);
        return new ResponseEntity<>(respuestaDTO, HttpStatus.OK);
    }

    @GetMapping("/respuestas/{id}")
    @Timed
    public ResponseEntity<RespuestaDTO> getOne(@PathVariable Long id)
    {
        Optional<RespuestaDTO> respuestaDTO = this.respuestaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(respuestaDTO);
    }

    @DeleteMapping("/respuestas/{id}")
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        this.respuestaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/respuestas/all")
    @Timed
    public ResponseEntity<List<RespuestaDTO>> getAll()
    {
        List<RespuestaDTO> respuestas = this.respuestaService.getAll();
        return new ResponseEntity<>(respuestas, HttpStatus.OK);
    }

    @PostMapping("/respuestas/providencia")
    @Timed
    public ResponseEntity<RespuestaDTO> getRespuestaByProvidencia(@RequestBody ProvidenciaDTO providenciaDTO)
    {
        Optional<RespuestaDTO> respuestaDTO = this.respuestaService.findOneByProvidencia(providenciaDTO);
        if (respuestaDTO.isPresent())
        {
            return new ResponseEntity<>(respuestaDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new RespuestaDTO(), HttpStatus.OK);
    }

    @GetMapping("/respuestas/movimiento-providencia/{movimientoProvidenciaId}")
    @Timed
    public ResponseEntity<RespuestaDTO> findByMovimientoProvidencia(@PathVariable Long movimientoProvidenciaId)
    {
        Optional<RespuestaDTO> respuestaDTO = Optional.of(this.respuestaService.findByMovimientoProvidencia(movimientoProvidenciaId));
        return ResponseUtil.wrapOrNotFound(respuestaDTO);
    }

}
