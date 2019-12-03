import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInstrucciones } from 'app/shared/model/Instrucciones.model';

@Component({
    selector: 'jhi-instrucciones-detail',
    templateUrl: './instrucciones-detail.component.html'
})
export class InstruccionesDetailComponent implements OnInit {
    instrucciones: IInstrucciones;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instrucciones }) => {
            this.instrucciones = instrucciones;
        });
    }

    previousState() {
        window.history.back();
    }
}
