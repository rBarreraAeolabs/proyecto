package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvestigacionSumaria.class)
public abstract class InvestigacionSumaria_ {

	public static volatile SetAttribute<InvestigacionSumaria, MovimientoInvestigacionSumaria> movimientoInvestSumarias;
	public static volatile SetAttribute<InvestigacionSumaria, Providencia> providencias;
	public static volatile SingularAttribute<InvestigacionSumaria, Long> id;
	public static volatile SingularAttribute<InvestigacionSumaria, String> compoTres;

}

