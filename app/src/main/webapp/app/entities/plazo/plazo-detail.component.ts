import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlazo } from 'app/shared/model/plazo.model';

@Component({
    selector: 'jhi-plazo-detail',
    templateUrl: './plazo-detail.component.html'
})
export class PlazoDetailComponent implements OnInit {
    plazo: IPlazo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plazo }) => {
            this.plazo = plazo;
        });
    }

    previousState() {
        window.history.back();
    }
}
