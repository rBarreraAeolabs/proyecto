import {Moment} from 'moment';
import {IDocumento} from 'app/shared/model//documento.model';
import {IAdjunto} from './adjunto.model';

export const enum EstadoDerivacion {
    LEIDO = 'LEIDO',
    NO_LEIDO = 'NO_LEIDO'
}

export const enum TipoDerivacion {
    TIPO_UNO = 'TIPO_UNO',
    TIPO_DOS = 'TIPO_DOS'
}

export interface IDerivacion {
    id?: number;
    observacion?: string;
    fechaDerivacion?: Moment;
    estado?: EstadoDerivacion;
    tipo?: TipoDerivacion;
    providenciaId?: number;
    documentosDtos?: IDocumento[];
    adjuntosDtos?: IAdjunto[];
}

export class Derivacion implements IDerivacion {
    constructor(
        public id?: number,
        public observacion?: string,
        public fechaDerivacion?: Moment,
        public estado?: EstadoDerivacion,
        public tipo?: TipoDerivacion,
        public providenciaId?: number,
        public documentosDtos?: IDocumento[],
        public adjuntosDtos?: IAdjunto[]
    ) {
    }
}
