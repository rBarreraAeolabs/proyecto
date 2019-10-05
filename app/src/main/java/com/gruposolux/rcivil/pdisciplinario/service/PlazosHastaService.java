/*
 *
 * este codigo cuenta con la participacion de Rubén Hernan Barrera Chavez
 *
 */

package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.Providencia;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

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
//        providencia.setFechaCreacion(Instant.now());
        Instant fechaHoy = Instant.now();
        log.debug("resultado qlo: "+providencia.getFechaCreacion() );
        log.debug("fecha hoy: "+ providencia.getFechaSolicitud());
        Date diahoy = Date.from(fechaHoy);
        log.debug("fechad e hoy: "+diahoy);
        fechasService.sumarRestarDias(diahoy,dias);

        log.debug("resultado qlo: "+ fechasService.sumarRestarDias(diahoy,dias)+".");

        Date fechaqla =fechasService.sumarRestarDias(diahoy,dias);
        Instant fechadehoy =   fechaqla.toInstant();
        providencia.setFechaHasta(fechadehoy);

        providenciaRepository.save(providencia);



    }
}
