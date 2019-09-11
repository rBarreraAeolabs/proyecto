import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {IInvestigacionSumaria} from 'app/shared/model/investigacion-sumaria.model';
import {InvestigacionSumariaService} from './investigacion-sumaria.service';

@Component({
    selector: 'jhi-investigacion-sumaria-update',
    templateUrl: './investigacion-sumaria-update.component.html'
})
export class InvestigacionSumariaUpdateComponent implements OnInit {
    private _investigacionSumaria: IInvestigacionSumaria;
    isSaving: boolean;

    constructor(private investigacionSumariaService: InvestigacionSumariaService, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({investigacionSumaria}) => {
            this.investigacionSumaria = investigacionSumaria;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.investigacionSumaria.id !== undefined) {
            this.subscribeToSaveResponse(this.investigacionSumariaService.update(this.investigacionSumaria));
        } else {
            this.subscribeToSaveResponse(this.investigacionSumariaService.create(this.investigacionSumaria));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IInvestigacionSumaria>>) {
        result.subscribe(
            (res: HttpResponse<IInvestigacionSumaria>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    get investigacionSumaria() {
        return this._investigacionSumaria;
    }

    set investigacionSumaria(investigacionSumaria: IInvestigacionSumaria) {
        this._investigacionSumaria = investigacionSumaria;
    }
}
