import { Moment } from 'moment';
import { IDerivacion } from 'app/shared/model//derivacion.model';
import { IDocumento } from 'app/shared/model//documento.model';
import { IAdjunto } from 'app/shared/model//adjunto.model';
import { IMovimientoProvidencia } from 'app/shared/model//movimiento-providencia.model';
import { IGrupo } from 'app/shared/model//grupo.model';
import {IEntidad} from './entidad.model';

export const enum EstadoProvidencia {
    NUEVA_PROVIDENCIA= 'NUEVA_PROVIDENCIA',
    RESOLUCION_Y_MEMO= 'RESOLUCION_Y_MEMO',
    FIRMA_RESOLUCION= 'FIRMA_RESOLUCION',
    DGDP_ASIGNANDO_NUMERO= 'DGDP_ASIGNANDO_NUMERO',
    NUMERO_DGDP= 'NUMERO_DGDP',
    ELABORACION_NOTIFICACION_FISCAL= 'ELABORACION_NOTIFICACION_FISCAL',
    NOTIFICACION_FISCAL= 'NOTIFICACION_FISCAL',
    INVESTIGACION= 'INVESTIGACION',
    NOTIFICACION_INCULPADO= 'NOTIFICACION_INCULPADO',
    VISTA_FISCAL= 'VISTA_FISCAL',
    INFORME_JURIDICO= 'INFORME_JURIDICO',
    ENVIAR_A_SUB_DIRRECION_JURIDICA= 'ENVIAR_A_SUB_DIRRECION_JURIDICA',
    PROVIDENCIA_CREADA= 'PROVIDENCIA_CREADA',
    ENVIADO_A_SUBDIRECCION_JURIDICA= 'ENVIADO_A_SUBDIRECCION_JURIDICA',
    SUB_DIRECCION_RECIBE_PROVIDENCIA= 'SUB_DIRECCION_RECIBE_PROVIDENCIA',
    UPD_REDACTA_RESOLUCION_Y_MEMO= 'UPD_REDACTA_RESOLUCION_Y_MEMO',
    ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION= 'ESPERANDO_FIRMA_VISA_DE_SUBDIRECCION',
    ENVIAR_A_DGD= 'ENVIAR_A_DGD',
    ENVIAR_A_DN= 'ENVIAR_A_DN',
    ESPERANDO_FIRMA_DEL_DN= 'ESPERANDO_FIRMA_DEL_DN',
    ENVIAR_A_DGDP= 'ENVIAR_A_DGDP',
    DGDP_DIO_NUMERO= 'DGDP_DIO_NUMERO',
    RECEPCION_DGD= 'RECEPCION_DGD',
    UPD_ELABORA_NOTIFICACION_VISTA_FISCAL= 'UPD_ELABORA_NOTIFICACION_VISTA_FISCAL',
    UPD_ELABORO_NOTIFICACION_VISTA_FISCAL= 'UPD_ELABORO_NOTIFICACION_VISTA_FISCAL',
    ENVIAR_A_UPD= 'ENVIAR_A_UPD',
    UPD_NOTIFICA_FISCAL= 'UPD_NOTIFICA_FISCAL',
    FISCAL_NOTIFICADO= 'FISCAL_NOTIFICADO',
    FISCAL_ACEPTO= 'FISCAL_ACEPTO',
    FISCAL_RECHAZO= 'FISCAL_RECHAZO',
    DGDP_ASIGNA_TIPO_SOLICITUD= 'DGDP_ASIGNA_TIPO_SOLICITUD',
    GESTOR_DOCUMENTAL_ASIGNA_NUMERO= 'GESTOR_DOCUMENTAL_ASIGNA_NUMERO',
    SUB_DIRECCION_DEBE_ASIGNAR= 'SUB_DIRECCION_DEBE_ASIGNAR',
    FISCAL_REDACTA_MEMO= 'FISCAL_REDACTA_MEMO',
    UPD_DA_INICIO= 'UPD_DA_INICIO',
    FISCAL_COMIENZA_INVESTIGACION= 'FISCAL_COMIENZA_INVESTIGACION',
    FISCAL_ENVIA_A_UPD_MEMO_CIERRE= 'FISCAL_ENVIA_A_UPD_MEMO_CIERRE',
    UPD_REGISTRA_CIERRE= 'UPD_REGISTRA_CIERRE',
    FISCAL_NOTIFICA_A_INCULPADO= 'FISCAL_NOTIFICA_A_INCULPADO',
    INCULPADO_ENVIA_MEMO= 'INCULPADO_ENVIA_MEMO',
    FISCAL_FORMULA_CARGOS= 'FISCAL_FORMULA_CARGOS',
    TERMINO_PROBATORIO= 'TERMINO_PROBATORIO',
    FISCAL_REMITE_EXPEDIENTE= 'FISCAL_REMITE_EXPEDIENTE',
    DN_RECIBE_SUMARIO_COMPLETO= 'DN_RECIBE_SUMARIO_COMPLETO',
    SUB_DIRECCION_ASIGNA_ABOGADO= 'SUB_DIRECCION_ASIGNA_ABOGADO',
    ABOGADO_ELABORA_INFORME= 'ABOGADO_ELABORA_INFORME',
    SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO= 'SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO',
    PROVIDENCIA_INFORME_JURIDICO= 'PROVIDENCIA_INFORME_JURIDICO',
    PROVIDENCIA_SELECCION_FISCAL= 'PROVIDENCIA_SELECCION_FISCAL',
    SELECCION_PROVI_SOBRECEDER= 'SELECCION_PROVI_SOBRECEDER',
    SELECCION_PROVI_ABSORVER= 'SELECCION_PROVI_ABSORVER',
    SELECCION_PROVI_REABRIR= 'SELECCION_PROVI_REABRIR',
    SELECCION_PROVI_SANCIONAR= 'SELECCION_PROVI_SANCIONAR',
    CREADA_PROVIDENCIA_SOBRECEDER= 'CREADA_PROVIDENCIA_SOBRECEDER',
    CREADA_PROVIDENCIA_ABSORVER= 'CREADA_PROVIDENCIA_ABSORVER',
    CREADA_PROVIDENCIA_REABRIR= 'CREADA_PROVIDENCIA_REABRIR',
    CREADA_PROVIDENCIA_SANCIONAR= 'CREADA_PROVIDENCIA_SANCIONAR',
    IJ_PROVIDENCIA_SOBRECEDER= 'IJ_PROVIDENCIA_SOBRECEDER',
    IJ_PROVIDENCIA_ABSORVER= 'IJ_PROVIDENCIA_ABSORVER',
    IJ_PROVIDENCIA_REABRIR= 'IJ_PROVIDENCIA_REABRIR',
    IJ_PROVIDENCIA_SANCIONAR= 'IJ_PROVIDENCIA_SANCIONAR',
  DA_INICIO='DA INICIO'

}

export const enum EtapaProvidencia {
    NUEVA_PROVIDENCIA = 'NUEVA_PROVIDENCIA',
    PROVIDENCIA_SELECCION_FISCAL = 'PROVIDENCIA_SELECCION_FISCAL',
    PROVIDENCIA_PRORROGA = 'PROVIDENCIA_PRORROGA',
    PROVIDENCIA_SEGUNDA_PRORROGA = 'PROVIDENCIA_SEGUNDA_PRORROGA',
    PROVIDENCIA_INFORME_JURIDICO = 'PROVIDENCIA_INFORME_JURIDICO',
    PROVIDENCIA_SOBRECEER = 'PROVIDENCIA_SOBRECEER',
    PROVIDENCIA_ABSOLVER = 'PROVIDENCIA_ABSOLVER',
    PROVIDENCIA_SANCIONAR = 'PROVIDENCIA_SANCIONAR',
    PROVIDENCIA_SANCIONAR_INCULPADO_APELA = 'PROVIDENCIA_SANCIONAR_INCULPADO_APELA',
    PROVIDENCIA_SANCIONAR_INCULPADO_NO_APELA = 'PROVIDENCIA_SANCIONAR_INCULPADO_NO_APELA',
    PROVIDENCIA_REABRE = 'PROVIDENCIA_REABRE'
}

export const enum AccionesProvidencia {
    CREAR_PROVIDENCIA = 'SUMARIO_ADMINISTRATIVO',
    DGD_ASIGNA_NUMERO_REFERENCIA = 'DGD_ASIGNA_NUMERO_REFERENCIA',
    SUB_DIRECCION_RECIBE_PROVIDENCIA = 'SUB_DIRECCION_RECIBE_PROVIDENCIA',
    SUB_DIRECCION_ASIGNA = 'SUB_DIRECCION_ASIGNA',
    SUB_DIRECCION_RECIBE = 'SUB_DIRECCION_RECIBE',
    UPD_REDACTA_RESOLUCION_Y_MEMO = 'UPD_REDACTA_RESOLUCION_Y_MEMO',
    ENVIA_A_SUB_DIRECCION = 'ENVIA_A_SUB_DIRECCION',
    SUB_DIRECCION_FIRMA_VISA = 'SUB_DIRECCION_FIRMA_VISA',
    ENVIADA_A_DGD = 'ENVIADA_A_DGD',
    DGD_REGISTRA  = 'DGD_REGISTRA',
    ENVIADA_A_DN = 'ENVIADA_A_DN',
    DN_FIRMA_RESOLUCION = 'DN_FIRMA_RESOLUCION',
    ENVIADA_A_DGDP = 'ENVIADA_A_DGDP',
    DGDP_ASIGNA_NUMERO = 'DGDP_ASIGNA_NUMERO',
    ENVIADA_A_UPD = 'ENVIADA_A_UPD',
    UPD_ELABORA_NOTIFICACION_VISTA_FISCAL = 'UPD_ELABORA_NOTIFICACION_VISTA_FISCAL',
    ENVIA_A_UPD = 'ENVIA_A_UPD',
    UPD_NOTIFICA_A_FISCAL = 'UPD_NOTIFICA_A_FISCAL',
    FISCAL_RECIBE_NOTIFICACION = 'FISCAL_RECIBE_NOTIFICACION',
    FISCAL_ACEPTA = 'FISCAL_ACEPTA',
    FISCAL_ENVIA_MEMO = 'FISCAL_ENVIA_MEMO',
    UPD_INICIA = 'UPD_INICIA',
    FISCAL_COMIENZA_INVESTIGACION = 'FISCAL_COMIENZA_INVESTIGACION',
    FISCAL_ENVIA_MEMO_CIERRE_A_UPD = 'FISCAL_ENVIA_MEMO_CIERRE_A_UPD',
    UPD_REGISTRA_CIERRE = 'UPD_REGISTRA_CIERRE',
    FISCAL_NOTIFICA_A_INCULPADO = 'FISCAL_NOTIFICA_A_INCULPADO',
    INCULPADO_ENVIA_MEMO = 'INCULPADO_ENVIA_MEMO',
    FISCAL_FORMULA_CARGOS = 'FISCAL_FORMULA_CARGOS',
    TERMINO_PROBATORIO = 'TERMINO_PROBATORIO',
    REMITE_EXPEDIENTE_FISCAL = 'REMITE_EXPEDIENTE_FISCAL',
    ENVIA_A_DN = 'ENVIA_A_DN',
    DN_RECIBE_SUMARIO_COMPLETO = 'DN_RECIBE_SUMARIO_COMPLETO',
    SUB_DIRECCION_ASIGNA_ABOGADO = 'SUB_DIRECCION_ASIGNA_ABOGADO',
    ABOGADO_ELABORA_INFORME = 'ABOGADO_ELABORA_INFORME',
    ENVIA_INFORME_A_SUB_DIRECCION = 'ENVIA_INFORME_A_SUB_DIRECCION',
    SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO  = 'SUB_DIRECCION_ENVIA_A_DN_INFORME_JURIDICO',
    FISCAL_NOTIFICADO = 'FISCAL_NOTIFICADO',
    FISCAL_REDACTA_MEMO= 'FISCAL_REDACTA_MEMO',
    SECRETARIA_DESPACHA_A_DGD= 'SECRETARIA_DESPACHA_A_DGD',
    FISCAL_ACEPTO_Y_DA_INICIO= 'FISCAL_ACEPTO_Y_DA_INICIO'
}

export const enum TipoProvidencia {
    SUMARIO_ADMINISTRATIVO = 'SUMARIO_ADMINISTRATIVO',
    INVESTIGACION_SUMARIA = 'INVESTIGACION_SUMARIA'
}
export const enum OrdenJuridico {
    ABSOLVER = 'RESERVADO',
    SOBRECEDER = 'RESERVADO',
    SANCIONAR = 'RESERVADO',
    REABRIR = 'RESERVADO'
}

export const enum Apelacion {
    APELO = 'APELO',
    NOAPELO = 'NO_APELO'
}
export const enum Caracter {
    RESERVADO = 'RESERVADO',
    URGENTE = 'URGENTE',
    REITERO = 'REITERO'
}

export const enum InstruccionesProvidencia {
    TOMAR_CONOCIMIENTO = 'TOMAR_CONOCIMIENTO',
    PROPONER_RESPUESTA_AL_DIRECTOR = 'PROPONER_RESPUESTA_AL_DIRECTOR',
    PROPONER_DECRETO_O_RESOLUCION = 'PROPONER_DECRETO_O_RESOLUCION',
    ESTUDIAR_ANTECEDENTES_Y_PROCEDER_CONFORME_A_DERECHO = 'ESTUDIAR_ANTECEDENTES_Y_PROCEDER_CONFORME_A_DERECHO',
    CONVERSAR_CONMIGO = 'CONVERSAR_CONMIGO',
    PROVIDENCIA_ARCHIVO = 'PROVIDENCIA_ARCHIVO'
}

export interface IProvidencia {
    id?: number;
    numeroReferencia?: number;
    numeroProvidencia?: number;
    estadoActual?: EstadoProvidencia;
    etapa?: EtapaProvidencia;
    subEtapa?: EstadoProvidencia;
    requisito?: AccionesProvidencia;
    tipo?: TipoProvidencia;
    comentario?: String;
    fechaSolicitud?: Moment;
    fechaCreacion?: Moment;
    instrucciones?: string[];
    adjuntos?: IAdjunto[];
    documentos?: IDocumento[];
    movimientos?: IMovimientoProvidencia[];
    sumarioAdministrativoId?: number;
    investigacionSumariaId?: number;
    fechaHasta?: Moment;
    runSolicitante?: string;
    nombreSolicitante?: string;
    runImplicado?: string;
    nombreImplicado?: string;
    entidadSolicitante?: IEntidad;
    entidadImplicada?: IEntidad;
    nombreFiscalAsignado?: string;
    ordenJuridico?: OrdenJuridico;
    apelacion?: Apelacion;
    standby?: boolean;
}

export class Providencia implements IProvidencia {
    constructor(
        public id?: number,
        public numeroReferencia?: number,
        public numeroProvidencia?: number,
        public estadoActual?: EstadoProvidencia,
        public tipo?: TipoProvidencia,
        public comentario?: Caracter,
        public fechaSolicitud?: Moment,
        public fechaCreacion?: Moment,
        public instrucciones?: string[],
        public adjuntos?: IAdjunto[],
        public documentos?: IDocumento[],
        public movimientos?: IMovimientoProvidencia[],
        public sumarioAdministrativoId?: number,
        public investigacionSumariaId?: number,
        public fechaHasta?: Moment,
        public runSolicitante?: string,
        public nombreSolicitante?: string,
        public runImplicado?: string,
        public nombreImplicado?: string,
        public entidadSolicitante?: IEntidad,
        public entidadImplicada?: IEntidad,
        public nombreFiscalAsignado?: string,
        public ordenJuridico?: OrdenJuridico,
        public apelacion?: Apelacion,
        public standby?: boolean,
) {}
}

export class IProvidenciaResponse {
    providenciaId?: number;
    estadoActual?: EstadoProvidencia;
    adjuntosDTOs?: IAdjunto[];
    documentosDTOs?: IDocumento[];
    observacion?: string;
}

export interface IProvidenciaItemList {
    id?: number;
    fechaCreacion?: Moment;
    estadoProvidencia?: EstadoProvidencia;
    nombreGrupo?: string;
    diasDesdeCreacion?: number;
    diasUltimoTramite?: number;
    fechaVencimiento?: Moment;
    standby?: boolean;
}

export interface IProvidenciaUpdateForType {
    numeroReferencia?: number;
    providenciaId?: number;
    providenciaMadreId?: number;
    providenciaMadreNumeroReferencia?: number;
    ordenJuridico?: string;
    apelacion?: string;

}

export interface IProvidenciaUpdateNroReferencia {
    providenciaId?: number;
    numeroReferencia?: number;
}
export interface IProvidenciaUpdateTipoSolicitud {
    providenciaId?: number;
    tipoSolicitud?: string;
}
