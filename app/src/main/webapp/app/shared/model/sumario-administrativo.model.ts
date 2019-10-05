import { IProvidencia } from 'app/shared/model//providencia.model';
import { IMovimientoSumarioAdministrativo } from 'app/shared/model//movimiento-sumario-administrativo.model';
import { Moment } from 'moment';

export interface ISumarioAdministrativo {
    id?: number;
    campoUno?: string;
    fechaDeSolicitud?: Moment;
    plazo?: string;
    estado?: boolean;
    unidad?: string;
    investigacionSumariaId?: number;
    providencias?: IProvidencia[];
    movimientosSumarioAdmins?: IMovimientoSumarioAdministrativo[];
}

export class SumarioAdministrativo implements ISumarioAdministrativo {
    constructor(
        public id?: number,
        public campoUno?: string,
        public fechaDeSolicitud?: Moment,
        public plazo?: string,
        public estado?: boolean,
        public unidad?: string,
        public investigacionSumariaId?: number,
        public providencias?: IProvidencia[],
        public movimientosSumarioAdmins?: IMovimientoSumarioAdministrativo[]
    ) {}
}
