package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
import com.gruposolux.rcivil.pdisciplinario.service.dto.NotificacionInBrowserDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NotificacionInBrowser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificacionInBrowserRepository extends JpaRepository<NotificacionInBrowser, Long> {

//    @Query("SELECT n FROM NotificacionInBrowser n WHERE n.user.id = :userId")
    @Query(value="SELECT * FROM notificacion_in_browser n  WHERE n.user_id =:userId and  n.visto=false  order by n.created_at desc limit 5",nativeQuery = true)
    List<NotificacionInBrowser> TraerNotificacionesPorUsuario(@Param("userId") Long userId);

    @Query("SELECT n FROM NotificacionInBrowser n where n.id=:notiId")
    Optional<NotificacionInBrowser>selectNotificacion(@Param("notiId")Long notiId);

    @Modifying
    @Query("update NotificacionInBrowser n SET n.visto = true WHERE n.id = :id")
    Integer Updatenotificacion(@Param("id")Long id);

    @Modifying
    @Query(value = "UPDATE notificacion_in_browser SET visto = true where id =:id ",nativeQuery = true)
    void  updatenotifi(@Param("id") Long id);
}
