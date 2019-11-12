package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(FichaIngresoSdj.class)
public abstract class FichaIngresoSdj_ {

	public static volatile CollectionAttribute<FichaIngresoSdj, InstruccionesProvidencia> instrucciones;
	public static volatile SingularAttribute<FichaIngresoSdj, Instant> fechaHasta;
	public static volatile SingularAttribute<FichaIngresoSdj, Instant> fechaInicio;
	public static volatile SingularAttribute<FichaIngresoSdj, Integer> plazo;
	public static volatile SingularAttribute<FichaIngresoSdj, Long> numeroReferencia;
	public static volatile SingularAttribute<FichaIngresoSdj, String> tipoSolicitud;
	public static volatile SingularAttribute<FichaIngresoSdj, String> atentamente;
	public static volatile SingularAttribute<FichaIngresoSdj, Long> id;
	public static volatile SingularAttribute<FichaIngresoSdj, String> observacion;
	public static volatile SingularAttribute<FichaIngresoSdj, Long> numeroProvidencia;

}

