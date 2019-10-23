/*
 *
 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 *
 */

package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PlazosHastaService {
    private final ProvidenciaRepository providenciaRepository;
    private final Logger log = LoggerFactory.getLogger(ProvidenciaService.class);
    public PlazosHastaService(ProvidenciaRepository providenciaRepository) {
        this.providenciaRepository = providenciaRepository;
    }
    @Autowired
    @Qualifier("fechasService")
    private FechasService fechasService;

    public void dias(Long id, Integer dias){
        Providencia providencia = providenciaRepository.findOne(id);
        Instant fechaHoy = Instant.now();
        Date diahoy = Date.from(fechaHoy);
        log.debug("fecha de hoy: "+diahoy);
        List<Date> rubenFechasNoLaborables= Collections.emptyList();
       Date diasHabiles= fechasService.calcularFecha(diahoy,dias,rubenFechasNoLaborables);
        log.debug("dias habiles: "+ diasHabiles+".");
        Instant fechaFinal =   diasHabiles.toInstant();
        //  log.debug("ruben resultado cond dias habiles: "+ fechasService.diasHabiles(rubenHoy,rubenHasta,rubenFechasNoLaborables));
        providencia.setFechaHasta(fechaFinal);
        providenciaRepository.save(providencia);
    }

}
