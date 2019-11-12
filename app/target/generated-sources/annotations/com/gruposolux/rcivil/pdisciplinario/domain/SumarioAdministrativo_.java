package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SumarioAdministrativo.class)
public abstract class SumarioAdministrativo_ {

	public static volatile SingularAttribute<SumarioAdministrativo, InvestigacionSumaria> investigacionSumaria;
	public static volatile SetAttribute<SumarioAdministrativo, Providencia> providencias;
	public static volatile SingularAttribute<SumarioAdministrativo, String> campoUno;
	public static volatile SingularAttribute<SumarioAdministrativo, Long> id;
	public static volatile SetAttribute<SumarioAdministrativo, MovimientoSumarioAdministrativo> movimientosSumarioAdmins;

}

