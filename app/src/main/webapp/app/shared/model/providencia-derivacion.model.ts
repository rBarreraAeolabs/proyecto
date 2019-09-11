import {IProvidencia} from 'app/shared/model/providencia.model';
import {IDerivacion} from 'app/shared/model/derivacion.model';

export interface IProvidenciaDerivacion {
    providenciaDTO?: IProvidencia;
    derivacionDTO?: IDerivacion;
}

export class ProvidenciaDerivacion implements IProvidenciaDerivacion {
    constructor(public providenciaDTO?: IProvidencia, public derivacionDTO?: IDerivacion) {
    }
}
