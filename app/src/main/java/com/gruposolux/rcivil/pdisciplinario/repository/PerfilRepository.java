package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Perfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    @Query(value = "select distinct perfil from Perfil perfil",
        countQuery = "select count(distinct perfil) from Perfil perfil")
    Page<Perfil> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct perfil from Perfil perfil")
    List<Perfil> findAllWithEagerRelationships();

    @Query("select perfil from Perfil perfil where perfil.id =:id")
    Optional<Perfil> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("SELECT g FROM Perfil g WHERE " +
        "UPPER(g.nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    Page<Perfil> FindOneByPerfilName(Pageable pageable, @Param("nombre") String nombre);

}

