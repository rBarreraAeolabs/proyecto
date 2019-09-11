import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IFichaIngresoSdj} from 'app/shared/model/ficha-ingreso-sdj.model';

@Component({
    selector: 'jhi-ficha-ingreso-sdj-detail',
    templateUrl: './ficha-ingreso-sdj-detail.component.html'
})
export class FichaIngresoSdjDetailComponent implements OnInit {
    fichaIngresoSdj: IFichaIngresoSdj;

    constructor(private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({fichaIngresoSdj}) => {
            this.fichaIngresoSdj = fichaIngresoSdj;
            console.log(this.fichaIngresoSdj);
        });
    }

    previousState() {
        window.history.back();
    }
}
