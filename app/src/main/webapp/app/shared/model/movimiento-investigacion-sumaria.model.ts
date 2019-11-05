import { Moment } from 'moment';

export const enum EstadoInvestigacionSumaria {
    ESTADO_UNO = 'ESTADO_UNO',
    ESTADO_DOS = 'ESTADO_DOS',
    ELEVADO_A_SUMARIO = 'ELEVADO_A_SUMARIO'
}

export interface IMovimientoInvestigacionSumaria {
    id?: number;
    estadoAnterior?: EstadoInvestigacionSumaria;
    estadoNuevo?: EstadoInvestigacionSumaria;
    fecha?: Moment;
    investigacionSumariaId?: number;
}

export class MovimientoInvestigacionSumaria implements IMovimientoInvestigacionSumaria {
    constructor(
        public id?: number,
        public estadoAnterior?: EstadoInvestigacionSumaria,
        public estadoNuevo?: EstadoInvestigacionSumaria,
        public fecha?: Moment,
        public investigacionSumariaId?: number
    ) {}
}
