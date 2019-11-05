import { IProvidencia } from 'app/shared/model//providencia.model';
import { IMovimientoInvestigacionSumaria } from 'app/shared/model//movimiento-investigacion-sumaria.model';

export interface IInvestigacionSumaria {
    id?: number;
    compoTres?: string;
    providencias?: IProvidencia[];
    movimientoInvestSumarias?: IMovimientoInvestigacionSumaria[];
}

export class InvestigacionSumaria implements IInvestigacionSumaria {
    constructor(
        public id?: number,
        public compoTres?: string,
        public providencias?: IProvidencia[],
        public movimientoInvestSumarias?: IMovimientoInvestigacionSumaria[]
    ) {}
}
