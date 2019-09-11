import {Moment} from 'moment';
import {TipoPlantilla} from './plantilla.model';

export interface IDocumento {
    id?: number;
    descripcion?: string;
    contenido?: string;
    fechaCreado?: Moment;
    archivoNombre?: string;
    archivoMimeType?: string;
    archivoSize?: number;
    archivo?: any;
    archivoContentType?: string;
    alfrescoNodeId?: string;
    alfrescoNodePath?: string;
    localPath?: string;
    hash?: string;
    providenciaId?: number;
    derivacionId?: number;
    movimientoProvidenciaId?: number;
    numeroResolucion?: number;
    tipoPlantilla?: TipoPlantilla;
    version?: number;
}

export class Documento implements IDocumento {
    constructor(
        public id?: number,
        public descripcion?: string,
        public contenido?: string,
        public fechaCreado?: Moment,
        public archivoNombre?: string,
        public archivoMimeType?: string,
        public archivoSize?: number,
        public archivoContentType?: string,
        public archivo?: any,
        public alfrescoNodeId?: string,
        public alfrescoNodePath?: string,
        public localPath?: string,
        public hash?: string,
        public providenciaId?: number,
        public derivacionId?: number,
        public movimientoProvidenciaId?: number,
        public numeroResolucion?: number,
        public tipoPlantilla?: TipoPlantilla,
        public version?: number
    ) {
    }
}
