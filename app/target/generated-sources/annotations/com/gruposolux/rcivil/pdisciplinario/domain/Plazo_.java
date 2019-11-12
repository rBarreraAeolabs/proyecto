package com.gruposolux.rcivil.pdisciplinario.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Plazo.class)
public abstract class Plazo_ {

	public static volatile SetAttribute<Plazo, MovimientoProvidencia> movimientosProvidencis;
	public static volatile SingularAttribute<Plazo, Integer> dias;
	public static volatile SingularAttribute<Plazo, Long> id;
	public static volatile SingularAttribute<Plazo, String> nombre;

}

