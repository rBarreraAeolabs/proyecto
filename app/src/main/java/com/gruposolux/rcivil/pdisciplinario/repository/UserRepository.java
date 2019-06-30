package com.gruposolux.rcivil.pdisciplinario.repository;

import com.gruposolux.rcivil.pdisciplinario.domain.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
import java.util.Set;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String USERS_BY_LOGIN_CACHE = "usersByLogin";

    String USERS_BY_EMAIL_CACHE = "usersByEmail";

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_LOGIN_CACHE)
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<User> findOneWithAuthoritiesByEmail(String email);

    @Query("select u from User u where u.perfil.id = :perfilId order by u.id desc ")
    List<User> findByPerfilId(@Param("perfilId") Long perfilId);

    @Query("SELECT u FROM User u WHERE CONCAT(LOWER(u.firstName), ' ', LOWER(u.lastName)) = :fullname")
    Optional<User> findByFullName(@Param("fullname") String fullname);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    @Modifying
    @Query("UPDATE User u SET u.activated = :newStatus, u.lastModifiedDate = :modifyDate, u.lastModifiedBy = :lastModifiedBy WHERE u.id = :idUser")
    void updateActivationStatus(@Param("newStatus") Boolean newStatus, @Param("modifyDate") Instant modifyDate,
                                @Param("lastModifiedBy") String lastModifiedBy ,@Param("idUser") Long idUser);

//    @Query(value = "select distinct usuario from User usuario left join fetch usuario.grupos")
//    Set<User> findAllUsersByGroup();
}

