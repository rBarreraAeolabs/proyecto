import { IPerfil } from 'app/shared/model//perfil.model';
import { IProvidencia } from 'app/shared/model//providencia.model';
import {Moment} from "moment";

export interface IGrupo {
    id?: number;
    nombre?: string;
    perfiles?: IPerfil[];
    providencias?: IProvidencia[];
    f_nacimiento?: Moment;
}

export class Grupo implements IGrupo {
    constructor(
        public id?: number,
        public nombre?: string,
        public perfiles?: IPerfil[],
        public providencias?: IProvidencia[]
    ) {}
}
