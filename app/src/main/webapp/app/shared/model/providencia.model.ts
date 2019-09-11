import {Moment} from 'moment';
import {IDerivacion} from 'app/shared/model//derivacion.model';
import {IDocumento} from 'app/shared/model//documento.model';
import {IAdjunto} from 'app/shared/model//adjunto.model';
import {IMovimientoProvidencia} from 'app/shared/model//movimiento-providencia.model';
import {IGrupo} from 'app/shared/model//grupo.model';
import {IEntidad} from './entidad.model';

export const enum EstadoProvidencia {
    ESTADO_1 = 'ESTADO 1',
    ESTADO_2 = 'ESTADO 2',
    ESTADO_3 = 'ESTADO 3',
    ESTADO_4 = 'ESTADO 4',
    ESTADO_5 = 'ESTADO 5',
    ESTADO_6 = 'ESTADO 6',
    ESTADO_7 = 'ESTADO 7',
    ESTADO_8 = 'ESTADO 8',
    ESTADO_9 = 'ESTADO 9',
    ESTADO_10 = 'ESTADO 10',
    ESTADO_11 = 'ESTADO 11',
    ESTADO_12 = 'ESTADO 12',
    ESTADO_13 = 'ESTADO 13',
    ESTADO_14 = 'ESTADO 14',
    ESTADO_15 = 'ESTADO 15',
    ESTADO_16 = 'ESTADO 16',
    ESTADO_17 = 'ESTADO 17',
    ESTADO_18 = 'ESTADO 18',
    ESTADO_19 = 'ESTADO 19',
    ESTADO_20 = 'ESTADO 20',
    ESTADO_21 = 'ESTADO 21',

}

export const enum TipoProvidencia {
    SUMARIO_ADMINISTRATIVO = 'SUMARIO_ADMINISTRATIVO',
    INVESTIGACION_SUMARIA = 'INVESTIGACION_SUMARIA'
}

export const enum Caracter {
    RESERVADO = 'RESERVADO',
    URGENTE = 'URGENTE',
    REITERO = 'REITERO'
}

export const enum Accion {
    TOMAR_CONOCIMIENTO = 'TOMAR_CONOCIMIENTO',
    PROPONER_RESPUESTA_AL_DIRECTOR = 'PROPONER_RESPUESTA_AL_DIRECTOR',
    PROPONER_DECRETO_O_RESOLUCION = 'PROPONER_DECRETO_O_RESOLUCION',
    ESTUDIAR_ANTECEDENTES_Y_PROCEDER_CONFORME_A_DERECHO = 'ESTUDIAR_ANTECEDENTES_Y_PROCEDER_CONFORME_A_DERECHO',
    CONVERSAR_CONMIGO = 'CONVERSAR_CONMIGO',
    PROVIDENCIA_ARCHIVO = 'PROVIDENCIA_ARCHIVO'
}

export interface IProvidencia {
    id?: number;
    numero?: number;
    estadoActual?: EstadoProvidencia;
    tipo?: TipoProvidencia;
    comentario?: Caracter;
    fechaSolicitud?: Moment;
    fechaCreacion?: Moment;
    acciones?: string[];
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
}

export class Providencia implements IProvidencia {
    constructor(
        public id?: number,
        public numero?: number,
        public estadoActual?: EstadoProvidencia,
        public tipo?: TipoProvidencia,
        public comentario?: Caracter,
        public fechaSolicitud?: Moment,
        public fechaCreacion?: Moment,
        public acciones?: string[],
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
        public nombreFiscalAsignado?: string
    ) {
    }
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


}

export interface IProvidenciaUpdateMadre {
    providenciaId?: number;
    providenciaMadreId?: number;
}
