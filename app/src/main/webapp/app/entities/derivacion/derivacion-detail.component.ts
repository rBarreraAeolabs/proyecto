import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IDerivacion} from 'app/shared/model/derivacion.model';

@Component({
    selector: 'jhi-derivacion-detail',
    templateUrl: './derivacion-detail.component.html'
})
export class DerivacionDetailComponent implements OnInit {
    derivacion: IDerivacion;

    constructor(private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({derivacion}) => {
            this.derivacion = derivacion;
        });
    }

    previousState() {
        window.history.back();
    }
}
