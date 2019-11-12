package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoPlantilla;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Documento.class)
public abstract class Documento_ {

	public static volatile SingularAttribute<Documento, String> descripcion;
	public static volatile SingularAttribute<Documento, Providencia> providencia;
	public static volatile SingularAttribute<Documento, String> contenido;
	public static volatile SingularAttribute<Documento, LocalDate> fechaCreado;
	public static volatile SingularAttribute<Documento, String> alfrescoNodePath;
	public static volatile SingularAttribute<Documento, byte[]> archivo;
	public static volatile SingularAttribute<Documento, TipoPlantilla> tipoPlantilla;
	public static volatile SingularAttribute<Documento, String> archivoMimeType;
	public static volatile SingularAttribute<Documento, Derivacion> derivacion;
	public static volatile SingularAttribute<Documento, Integer> version;
	public static volatile SingularAttribute<Documento, String> archivoNombre;
	public static volatile SingularAttribute<Documento, String> archivoContentType;
	public static volatile SingularAttribute<Documento, Long> archivoSize;
	public static volatile SingularAttribute<Documento, MovimientoProvidencia> movimientoProvidencia;
	public static volatile SingularAttribute<Documento, String> localPath;
	public static volatile SingularAttribute<Documento, String> alfrescoNodeId;
	public static volatile SingularAttribute<Documento, Long> id;
	public static volatile SingularAttribute<Documento, Respuesta> respuesta;
	public static volatile SingularAttribute<Documento, String> hash;
	public static volatile SingularAttribute<Documento, Long> numeroResolucion;

}

