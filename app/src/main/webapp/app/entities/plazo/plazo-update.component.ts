import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPlazo } from 'app/shared/model/plazo.model';
import { PlazoService } from './plazo.service';

@Component({
    selector: 'jhi-plazo-update',
    templateUrl: './plazo-update.component.html'
})
export class PlazoUpdateComponent implements OnInit {
    private _plazo: IPlazo;
    isSaving: boolean;

    constructor(private plazoService: PlazoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plazo }) => {
            this.plazo = plazo;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plazo.id !== undefined) {
            this.subscribeToSaveResponse(this.plazoService.update(this.plazo));
        } else {
            this.subscribeToSaveResponse(this.plazoService.create(this.plazo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlazo>>) {
        result.subscribe((res: HttpResponse<IPlazo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get plazo() {
        return this._plazo;
    }

    set plazo(plazo: IPlazo) {
        this._plazo = plazo;
    }
}
