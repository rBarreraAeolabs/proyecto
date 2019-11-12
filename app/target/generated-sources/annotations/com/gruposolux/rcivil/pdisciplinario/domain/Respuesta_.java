package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Respuesta.class)
public abstract class Respuesta_ {

	public static volatile SingularAttribute<Respuesta, Providencia> providencia;
	public static volatile SetAttribute<Respuesta, Documento> documentos;
	public static volatile SingularAttribute<Respuesta, Boolean> guardada;
	public static volatile SingularAttribute<Respuesta, String> estadoProvidencia;
	public static volatile SingularAttribute<Respuesta, MovimientoProvidencia> movimientoProvidencia;
	public static volatile SingularAttribute<Respuesta, Long> id;
	public static volatile SingularAttribute<Respuesta, String> comentario;
	public static volatile SingularAttribute<Respuesta, User> user;
	public static volatile SetAttribute<Respuesta, Adjunto> adjuntos;

}

