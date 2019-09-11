import {IPerfil} from 'app/shared/model//perfil.model';
import {IProvidencia} from 'app/shared/model//providencia.model';

export interface IGrupo {
    id?: number;
    nombre?: string;
    perfiles?: IPerfil[];
    providencias?: IProvidencia[];
}

export class Grupo implements IGrupo {
    constructor(
        public id?: number,
        public nombre?: string,
        public perfiles?: IPerfil[],
        public providencias?: IProvidencia[]
    ) {
    }
}
