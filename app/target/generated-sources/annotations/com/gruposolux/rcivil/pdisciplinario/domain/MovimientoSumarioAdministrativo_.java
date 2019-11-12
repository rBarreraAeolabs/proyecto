package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoSumarioAdministrativo;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MovimientoSumarioAdministrativo.class)
public abstract class MovimientoSumarioAdministrativo_ {

	public static volatile SingularAttribute<MovimientoSumarioAdministrativo, SumarioAdministrativo> sumarioAdministrativo;
	public static volatile SingularAttribute<MovimientoSumarioAdministrativo, Instant> fecha;
	public static volatile SingularAttribute<MovimientoSumarioAdministrativo, EstadoSumarioAdministrativo> estadoNuevo;
	public static volatile SingularAttribute<MovimientoSumarioAdministrativo, Long> id;
	public static volatile SingularAttribute<MovimientoSumarioAdministrativo, EstadoSumarioAdministrativo> estadoAnterior;

}

