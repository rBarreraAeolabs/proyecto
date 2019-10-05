import { IGrupo } from 'app/shared/model//grupo.model';

export interface IPerfil {
    id?: number;
    nombre?: string;
    grupo?: IGrupo;
    authorities?: any[];
}

export class Perfil implements IPerfil {
    constructor(
        public id?: number,
        public nombre?: string,
        public authorities?: any[],
        public grupo?: IGrupo
    ) {}
}
