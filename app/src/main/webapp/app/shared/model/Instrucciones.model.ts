
export interface IInstrucciones {
    id?: number;
    nombre?: string;
}

export class Instrucciones implements IInstrucciones {
    constructor(
        public id?: number,
        public nombre?: string
    ) {}

}
