import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMovimientoSumarioAdministrativo } from 'app/shared/model/movimiento-sumario-administrativo.model';

@Component({
    selector: 'jhi-movimiento-sumario-administrativo-detail',
    templateUrl: './movimiento-sumario-administrativo-detail.component.html'
})
export class MovimientoSumarioAdministrativoDetailComponent implements OnInit {
    movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ movimientoSumarioAdministrativo }) => {
            this.movimientoSumarioAdministrativo = movimientoSumarioAdministrativo;
        });
    }

    previousState() {
        window.history.back();
    }
}
