package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**sw
 * Spring Data  repository for the Grupo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query("select g from Grupo g where g.nombre = :nombre")
    Grupo FindOneByName(@Param("nombre") String nombre);

    @Query("select grupo from Grupo grupo where grupo.nombre = :nombre")
    Optional<Grupo> findOneByGroupName(@Param("nombre") String nombre);


    @Query("SELECT g FROM Grupo g WHERE " +
        "UPPER(g.nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    Page<Grupo> FindOneByGroupName(Pageable pageable, @Param("nombre") String nombre);

}

