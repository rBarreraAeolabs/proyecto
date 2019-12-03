import { IGrupo } from 'app/shared/model//grupo.model';
import {Moment} from "moment";

export interface IPerfil {
    id?: number;
    nombre?: string;
    grupo?: IGrupo;
    authorities?: any[];
    f_nacimiento?: Moment;
}

export class Perfil implements IPerfil {
    constructor(
        public id?: number,
        public nombre?: string,
        public authorities?: any[],
        public grupo?: IGrupo
    ) {}
}
