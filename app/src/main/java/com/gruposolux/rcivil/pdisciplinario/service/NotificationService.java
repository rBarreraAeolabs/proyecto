package com.gruposolux.rcivil.pdisciplinario.service;

import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
import com.gruposolux.rcivil.pdisciplinario.repository.NotificacionInBrowserRepository;
import com.gruposolux.rcivil.pdisciplinario.repository.ProvidenciaRepository;
import com.gruposolux.rcivil.pdisciplinario.service.dto.NotificacionInBrowserDTO;
import com.gruposolux.rcivil.pdisciplinario.service.mapper.NotificacionInBrowserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificacionInBrowserRepository notificacionInBrowserRepository;
    private final ProvidenciaRepository providenciaRepository;
    private final NotificacionInBrowserMapper notificacionInBrowserMapper;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificacionInBrowserRepository notificacionInBrowserRepository, ProvidenciaRepository providenciaRepository, NotificacionInBrowserMapper notificacionInBrowserMapper) {
        this.notificacionInBrowserRepository = notificacionInBrowserRepository;
        this.providenciaRepository = providenciaRepository;
        this.notificacionInBrowserMapper = notificacionInBrowserMapper;
    }

    public void notify(Notification notification, String username) {
        messagingTemplate.convertAndSendToUser(
            username,
            "/queue/notify",
            notification
        );


    }

    @Transactional(readOnly = true)
    public List<NotificacionInBrowserDTO> BuscarNotificaciones(Long userId) {

        log.debug("id del usuario recibido en notificacionService : {}", userId);

        List notificacion =  notificacionInBrowserRepository.TraerNotificacionesPorUsuario(userId);
        log.debug("lista de notificaciones : {}", notificacion);
        return notificacion;
    }

@Transactional (readOnly = false)
    public Optional<NotificacionInBrowserDTO> UpdateNotificacion(Long id) {

    log.debug("id de la notificacion que recibo es: : {}", id);
    notificacionInBrowserRepository.Updatenotificacion(id);

    return notificacionInBrowserRepository.selectNotificacion(id).map(this.notificacionInBrowserMapper::toDto);
}
//=======
//    public Optional <NotificacionInBrowser> UpdateNotificacion(Long id) {
//
//        log.debug("id de la notificacion que recibo es: : {}", id);
//        notificacionInBrowserRepository.Updatenotificacion(id);
//        return notificacionInBrowserRepository.findById(id);
//>>>>>>> origin/fabio-24-09-2019
//    }



}
