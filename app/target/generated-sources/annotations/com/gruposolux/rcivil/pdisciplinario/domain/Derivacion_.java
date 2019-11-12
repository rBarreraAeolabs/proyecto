package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoDerivacion;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoDerivacion;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Derivacion.class)
public abstract class Derivacion_ {

	public static volatile SingularAttribute<Derivacion, Providencia> providencia;
	public static volatile SingularAttribute<Derivacion, Grupo> derivadoAGrupo;
	public static volatile SingularAttribute<Derivacion, EstadoDerivacion> estado;
	public static volatile SingularAttribute<Derivacion, TipoDerivacion> tipo;
	public static volatile SetAttribute<Derivacion, Documento> documentos;
	public static volatile SingularAttribute<Derivacion, User> derivadoAUsuario;
	public static volatile SingularAttribute<Derivacion, Instant> fechaDerivacion;
	public static volatile SingularAttribute<Derivacion, Long> id;
	public static volatile SingularAttribute<Derivacion, User> derivadoPorUsuario;
	public static volatile SingularAttribute<Derivacion, String> observacion;
	public static volatile SingularAttribute<Derivacion, Grupo> derivadoPorGrupo;
	public static volatile SetAttribute<Derivacion, Adjunto> adjuntos;

}

