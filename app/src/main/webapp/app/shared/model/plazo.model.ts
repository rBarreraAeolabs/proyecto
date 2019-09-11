import {IMovimientoProvidencia} from 'app/shared/model//movimiento-providencia.model';

export interface IPlazo {
    id?: number;
    nombre?: string;
    dias?: number;
    movimientosProvidencis?: IMovimientoProvidencia[];
}

export class Plazo implements IPlazo {
    constructor(
        public id?: number,
        public nombre?: string,
        public dias?: number,
        public movimientosProvidencis?: IMovimientoProvidencia[]
    ) {
    }
}
