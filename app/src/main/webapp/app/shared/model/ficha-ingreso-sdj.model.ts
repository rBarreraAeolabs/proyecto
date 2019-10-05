import { Moment } from 'moment';

export interface IFichaIngresoSdj {
    id?: number;
    fechaInicio?: Moment;
    observacion?: string;
    plazo?: number;
    fechaHasta?: Moment;
    providenciaId?: number;
    numeroReferencia?: number;
    acciones?: string[];
    atentamente?: string;
}

export class FichaIngresoSdj implements IFichaIngresoSdj {
    constructor(
        public id?: number,
        public fechaInicio?: Moment,
        public observacion?: string,
        public plazo?: number,
        public fechaHasta?: Moment,
        public providenciaId?: number,
        public numeroReferencia?: number,
        public acciones?: string[],
        public atentamente?: string
    ) {}
}
