import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ISumarioAdministrativo } from 'app/shared/model/sumario-administrativo.model';

@Component({
    selector: 'jhi-sumario-administrativo-detail',
    templateUrl: './sumario-administrativo-detail.component.html'
})
export class SumarioAdministrativoDetailComponent implements OnInit {
    sumarioAdministrativo: ISumarioAdministrativo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sumarioAdministrativo }) => {
            this.sumarioAdministrativo = sumarioAdministrativo;
        });
    }

    previousState() {
        window.history.back();
    }
}
