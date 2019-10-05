import { Moment } from 'moment';

export const enum EstadoSumarioAdministrativo {
    ESTADO_UNO = 'ESTADO_UNO',
    ESTADO_DOS = 'ESTADO_DOS'
}

export interface IMovimientoSumarioAdministrativo {
    id?: number;
    estadoAnterior?: EstadoSumarioAdministrativo;
    estadoNuevo?: EstadoSumarioAdministrativo;
    fecha?: Moment;
    sumarioAdministrativoId?: number;
}

export class MovimientoSumarioAdministrativo implements IMovimientoSumarioAdministrativo {
    constructor(
        public id?: number,
        public estadoAnterior?: EstadoSumarioAdministrativo,
        public estadoNuevo?: EstadoSumarioAdministrativo,
        public fecha?: Moment,
        public sumarioAdministrativoId?: number
    ) {}
}
