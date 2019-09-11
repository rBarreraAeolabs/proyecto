import {IProvidencia} from 'app/shared/model/providencia.model';
import {IFiltroMovimientoProvidencia} from 'app/shared/model/filtroMovPro.model';

export interface IFiltroMovPro {
    providencia?: IProvidencia;
    filtroMovimientoProvidencia?: IFiltroMovimientoProvidencia;
}

export class FiltroMovPro implements IFiltroMovPro {
    constructor(
        public providencia?: IProvidencia,
        public filtroMovimientoProvidencia?: IFiltroMovimientoProvidencia
    ) {
    }
}
