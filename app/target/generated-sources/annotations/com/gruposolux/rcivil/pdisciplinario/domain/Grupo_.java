package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Grupo.class)
public abstract class Grupo_ {

	public static volatile SetAttribute<Grupo, NotificacionInBrowser> notificacionInBrowser;
	public static volatile SingularAttribute<Grupo, Long> id;
	public static volatile SingularAttribute<Grupo, String> nombre;
	public static volatile SetAttribute<Grupo, User> usuariosEnGrupo;
	public static volatile SetAttribute<Grupo, Derivacion> derivacion;

}

