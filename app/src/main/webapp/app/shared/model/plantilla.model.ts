import { EstadoProvidencia } from 'app/shared/model/providencia.model';

export const enum TipoPlantilla {
    MEMORANDUM = 'MEMORANDUM',
    RESOLUCION = 'RESOLUCION',
    CERTIFICACION = 'CERTIFICACION',
    EXPEDIENTE = 'EXPEDIENTE',
    NOTIFICACION = 'NOTIFICACION',
    DEFECTO = 'DEFECTO'
}

export interface IPlantilla {
    id?: number;
    nombre?: string;
    contenido?: string;
    tipo?: TipoPlantilla;
    estado?: EstadoProvidencia;
}

export class Plantilla implements IPlantilla {
    constructor(
        public id?: number,
        public nombre?: string,
        public contenido?: string,
        public tipo?: TipoPlantilla,
        public estado?: EstadoProvidencia
    ) {}
}
