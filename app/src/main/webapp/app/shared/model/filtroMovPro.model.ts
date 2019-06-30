import { EstadoProvidencia } from 'app/shared/model/providencia.model';
import { TipoAdjunto } from 'app/shared/model/adjunto.model';

export interface IFiltroMovimientoProvidencia {
    accion?: string;
    estadoProvidencia?: EstadoProvidencia;
    tipoAdjunto?: TipoAdjunto

}

export class FiltroMovimientoProvidencia implements IFiltroMovimientoProvidencia {
    constructor(
        public accion?: string,
        public estadoProvidencia?: EstadoProvidencia,
        public tipoAdjunto?: TipoAdjunto
    ) {}
}
