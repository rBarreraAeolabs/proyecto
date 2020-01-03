import { Moment } from 'moment';
import { IDocumento } from 'app/shared/model//documento.model';
import { IAdjunto } from 'app/shared/model//adjunto.model';
import { EstadoProvidencia } from 'app/shared/model/providencia.model';

export interface IMovimientoProvidencia {
    id?: number;
    estadoAnterior?: EstadoProvidencia;
    estadoNuevo?: EstadoProvidencia;
    fecha?: Moment;
    accion?: string;
    documentos?: IDocumento[];
    adjuntos?: IAdjunto[];
    providenciaId?: number;
    plazoId?: number;
    plazoDias?: number;
    comentario?: string;
    userId?: number;
    userFirstName?: string;
    userLastName?: string;
    numero_dgd?: number;
    numero_dgdp?: number;
}

export class MovimientoProvidencia implements IMovimientoProvidencia {
    constructor(
        public id?: number,
        public estadoAnterior?: EstadoProvidencia,
        public estadoNuevo?: EstadoProvidencia,
        public fecha?: Moment,
        public accion?: string,
        public documentos?: IDocumento[],
        public adjuntos?: IAdjunto[],
        public providenciaId?: number,
        public plazoId?: number,
        public plazoDias?: number,
        public comentario?: string,
        public userId?: number,
        public userFirstName?: string,
        public userLastName?: string,
        public numero_dgd?: number,
        public numero_dgdp?: number,
    ) {}
}
