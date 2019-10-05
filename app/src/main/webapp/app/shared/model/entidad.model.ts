
export interface IEntidad {
    id?: number;
    nombre?: string;
}

export class Entidad implements IEntidad {
    constructor(
        public id?: number,
        public nombre?: string
    ) {}
}
