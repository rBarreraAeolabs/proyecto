package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.EstadoProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.InstruccionesProvidencia;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoProvidencia;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Providencia.class)
public abstract class Providencia_ {

	public static volatile SingularAttribute<Providencia, SumarioAdministrativo> sumarioAdministrativo;
	public static volatile CollectionAttribute<Providencia, InstruccionesProvidencia> instrucciones;
	public static volatile SetAttribute<Providencia, Derivacion> derivaciones;
	public static volatile SingularAttribute<Providencia, TipoProvidencia> tipo;
	public static volatile SingularAttribute<Providencia, Instant> fechaHasta;
	public static volatile SingularAttribute<Providencia, Long> numeroDgdp;
	public static volatile SingularAttribute<Providencia, String> nombreImplicado;
	public static volatile SingularAttribute<Providencia, Boolean> standby;
	public static volatile SingularAttribute<Providencia, EstadoProvidencia> etapa;
	public static volatile SingularAttribute<Providencia, Entidad> entidadSolicitante;
	public static volatile SingularAttribute<Providencia, Long> providencia_madre_id;
	public static volatile SingularAttribute<Providencia, String> runSolicitante;
	public static volatile SetAttribute<Providencia, Documento> documentos;
	public static volatile SingularAttribute<Providencia, Long> numeroReferencia;
	public static volatile SingularAttribute<Providencia, EstadoProvidencia> requisito;
	public static volatile SingularAttribute<Providencia, String> nombreSolicitante;
	public static volatile SingularAttribute<Providencia, Entidad> entidadImplicada;
	public static volatile SingularAttribute<Providencia, String> nombreFiscalAsignado;
	public static volatile SingularAttribute<Providencia, Long> id;
	public static volatile SingularAttribute<Providencia, Long> numeroProvidencia;
	public static volatile SetAttribute<Providencia, Adjunto> adjuntos;
	public static volatile SingularAttribute<Providencia, InvestigacionSumaria> investigacionSumaria;
	public static volatile SingularAttribute<Providencia, Instant> fechaSolicitud;
	public static volatile SingularAttribute<Providencia, String> runImplicado;
	public static volatile SetAttribute<Providencia, MovimientoProvidencia> movimientos;
	public static volatile SingularAttribute<Providencia, String> comentario;
	public static volatile SingularAttribute<Providencia, Long> numeroDgd;
	public static volatile SingularAttribute<Providencia, String> estadoActual;
	public static volatile SingularAttribute<Providencia, Long> folio;
	public static volatile SingularAttribute<Providencia, Instant> fechaCreacion;
	public static volatile SingularAttribute<Providencia, EstadoProvidencia> subEtapa;

}

