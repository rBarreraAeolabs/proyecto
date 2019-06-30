package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.NotificacionInBrowser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NotificacionInBrowser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificacionInBrowserRepository extends JpaRepository<NotificacionInBrowser, Long> {

}
