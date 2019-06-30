import {EstadoProvidencia, IProvidencia} from './providencia.model';
import {IMovimientoProvidencia} from './movimiento-providencia.model';
import {IAdjunto} from './adjunto.model';
import {IDocumento} from './documento.model';

/**
 * Created by sneiraillanes on 22-04-2019.
 */
export interface IRespuesta {
    id?: number;
    comentario?: string;
    providencia?: IProvidencia;
    movimientoProvidenciaDTO?: IMovimientoProvidencia;
    adjuntos?: IAdjunto[];
    documentos?: IDocumento[];
    guardada?: boolean;
    userName?: string;
    userLastname?: string;
    userId?: number;
    estadoProvidencia?: EstadoProvidencia;
}

export class Respuesta implements IRespuesta {
    constructor(
        public id?: number,
        public comentario?: string,
        public providencia?: IProvidencia,
        public movimientoProvidenciaDTO?: IMovimientoProvidencia,
        public adjuntos?: IAdjunto[],
        public documentos?: IDocumento[],
        public guardada?: boolean,
        public userName?: string,
        public userLastname?: string,
        public userId?: number,
        public estadoProvidencia?: EstadoProvidencia
    ) {}
}
