package com.gruposolux.rcivil.pdisciplinario.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NotificacionInBrowser.class)
public abstract class NotificacionInBrowser_ {

	public static volatile SingularAttribute<NotificacionInBrowser, Instant> createdAt;
	public static volatile SingularAttribute<NotificacionInBrowser, String> contenido;
	public static volatile SingularAttribute<NotificacionInBrowser, Grupo> grupo;
	public static volatile SingularAttribute<NotificacionInBrowser, Long> id;
	public static volatile SingularAttribute<NotificacionInBrowser, Boolean> visto;
	public static volatile SingularAttribute<NotificacionInBrowser, User> user;

}

