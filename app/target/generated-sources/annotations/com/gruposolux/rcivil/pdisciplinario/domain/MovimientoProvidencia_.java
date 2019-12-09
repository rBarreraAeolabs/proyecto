package com.gruposolux.rcivil.pdisciplinario.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MovimientoProvidencia.class)
public abstract class MovimientoProvidencia_ {

	public static volatile SingularAttribute<MovimientoProvidencia, String> accion;
	public static volatile SingularAttribute<MovimientoProvidencia, Instant> fecha;
	public static volatile SingularAttribute<MovimientoProvidencia, Providencia> providencia;
	public static volatile SetAttribute<MovimientoProvidencia, Documento> documentos;
	public static volatile SingularAttribute<MovimientoProvidencia, Integer> numero_dgd;
	public static volatile SingularAttribute<MovimientoProvidencia, String> estadoNuevo;
	public static volatile SingularAttribute<MovimientoProvidencia, Plazo> plazo;
	public static volatile SingularAttribute<MovimientoProvidencia, Long> id;
	public static volatile SingularAttribute<MovimientoProvidencia, String> comentario;
	public static volatile SingularAttribute<MovimientoProvidencia, User> user;
	public static volatile SingularAttribute<MovimientoProvidencia, String> estadoAnterior;
	public static volatile SetAttribute<MovimientoProvidencia, Adjunto> adjuntos;

}

