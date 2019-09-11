import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IMovimientoProvidencia} from 'app/shared/model/movimiento-providencia.model';

@Component({
    selector: 'jhi-movimiento-providencia-detail',
    templateUrl: './movimiento-providencia-detail.component.html'
})
export class MovimientoProvidenciaDetailComponent implements OnInit {
    movimientoProvidencia: IMovimientoProvidencia;

    constructor(private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({movimientoProvidencia}) => {
            this.movimientoProvidencia = movimientoProvidencia;
        });
    }

    previousState() {
        window.history.back();
    }
}
