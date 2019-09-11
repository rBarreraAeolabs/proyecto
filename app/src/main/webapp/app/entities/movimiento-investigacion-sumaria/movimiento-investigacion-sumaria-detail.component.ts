import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IMovimientoInvestigacionSumaria} from 'app/shared/model/movimiento-investigacion-sumaria.model';

@Component({
    selector: 'jhi-movimiento-investigacion-sumaria-detail',
    templateUrl: './movimiento-investigacion-sumaria-detail.component.html'
})
export class MovimientoInvestigacionSumariaDetailComponent implements OnInit {
    movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria;

    constructor(private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({movimientoInvestigacionSumaria}) => {
            this.movimientoInvestigacionSumaria = movimientoInvestigacionSumaria;
        });
    }

    previousState() {
        window.history.back();
    }
}
