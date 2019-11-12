import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvestigacionSumaria } from 'app/shared/model/investigacion-sumaria.model';

@Component({
    selector: 'jhi-investigacion-sumaria-detail',
    templateUrl: './investigacion-sumaria-detail.component.html'
})
export class InvestigacionSumariaDetailComponent implements OnInit {
    investigacionSumaria: IInvestigacionSumaria;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ investigacionSumaria }) => {
            this.investigacionSumaria = investigacionSumaria;
        });
    }

    previousState() {
        window.history.back();
    }
}
