package com.gruposolux.rcivil.pdisciplinario.domain;

import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.FileUploadStatus;
import com.gruposolux.rcivil.pdisciplinario.domain.enumeration.TipoAdjunto;
import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adjunto.class)
public abstract class Adjunto_ {

	public static volatile SingularAttribute<Adjunto, String> descripcion;
	public static volatile SingularAttribute<Adjunto, Providencia> providencia;
	public static volatile SingularAttribute<Adjunto, LocalDate> fechaCreado;
	public static volatile SingularAttribute<Adjunto, FileUploadStatus> estado;
	public static volatile SingularAttribute<Adjunto, String> alfrescoNodePath;
	public static volatile SingularAttribute<Adjunto, byte[]> archivo;
	public static volatile SingularAttribute<Adjunto, String> archivoMimeType;
	public static volatile SingularAttribute<Adjunto, String> nombre;
	public static volatile SingularAttribute<Adjunto, Derivacion> derivacion;
	public static volatile SingularAttribute<Adjunto, String> archivoNombre;
	public static volatile SingularAttribute<Adjunto, String> archivoContentType;
	public static volatile SingularAttribute<Adjunto, Instant> fechaSubido;
	public static volatile SingularAttribute<Adjunto, Long> archivoSize;
	public static volatile SingularAttribute<Adjunto, MovimientoProvidencia> movimientoProvidencia;
	public static volatile SingularAttribute<Adjunto, String> localPath;
	public static volatile SingularAttribute<Adjunto, String> alfrescoNodeId;
	public static volatile SingularAttribute<Adjunto, Long> id;
	public static volatile SingularAttribute<Adjunto, Respuesta> respuesta;
	public static volatile SingularAttribute<Adjunto, TipoAdjunto> tipoAdjunto;
	public static volatile SingularAttribute<Adjunto, String> hash;

}

