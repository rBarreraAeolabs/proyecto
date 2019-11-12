package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoInvestigacionSumaria;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MovimientoInvestigacionSumaria.class)
public abstract class MovimientoInvestigacionSumaria_ {

	public static volatile SingularAttribute<MovimientoInvestigacionSumaria, Instant> fecha;
	public static volatile SingularAttribute<MovimientoInvestigacionSumaria, InvestigacionSumaria> investigacionSumaria;
	public static volatile SingularAttribute<MovimientoInvestigacionSumaria, EstadoInvestigacionSumaria> estadoNuevo;
	public static volatile SingularAttribute<MovimientoInvestigacionSumaria, Long> id;
	public static volatile SingularAttribute<MovimientoInvestigacionSumaria, EstadoInvestigacionSumaria> estadoAnterior;

}

