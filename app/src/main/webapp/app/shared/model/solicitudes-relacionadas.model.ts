import {Moment} from 'moment';

export interface ISolicitudesRelacionadas {
    id?: number;
    runSolicitante?: string;
    nombreSolicitante?: string;
    entidadSolicitante?: string;
    idProvidencia?: string;
    estadoActual?: string;
    fechaSolicitud?: Moment;
    tipo?: string;

}

export class SolicitudesRelacionadas implements ISolicitudesRelacionadas {
    constructor(
        public id?: number,
        public runSolicitante?: string,
        public nombreSolicitante?: string,
        public entidadSolicitante?: string,
        public idProvidencia?: string,
        public estadoActual?: string,
        public fechaSolicitud?: Moment,
        public tipo?: string,
    ) {
    }
}
