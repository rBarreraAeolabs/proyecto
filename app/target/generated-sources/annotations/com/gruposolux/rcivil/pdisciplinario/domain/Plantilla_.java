package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Plantilla.class)
public abstract class Plantilla_ {

	public static volatile SingularAttribute<Plantilla, String> contenido;
	public static volatile SingularAttribute<Plantilla, TipoPlantilla> tipo;
	public static volatile SingularAttribute<Plantilla, EstadoProvidencia> estado;
	public static volatile SingularAttribute<Plantilla, Long> id;
	public static volatile SingularAttribute<Plantilla, String> nombre;

}

