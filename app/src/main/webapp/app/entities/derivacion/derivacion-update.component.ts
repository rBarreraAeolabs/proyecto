import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IDerivacion } from 'app/shared/model/derivacion.model';
import { DerivacionService } from './derivacion.service';
import { IProvidencia } from 'app/shared/model/providencia.model';
import { ProvidenciaService } from 'app/entities/providencia';
import { IProvidenciaDerivacion } from 'app/shared/model/providencia-derivacion.model';

@Component({
    selector: 'jhi-derivacion-update',
    templateUrl: './derivacion-update.component.html'
})
export class DerivacionUpdateComponent implements OnInit {
    private _derivacion: IDerivacion;
    isSaving: boolean;

    providencias: IProvidencia[];
    fechaDerivacion: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private derivacionService: DerivacionService,
        private providenciaService: ProvidenciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ derivacion }) => {
            this.derivacion = derivacion;
        });
        this.providenciaService.query().subscribe(
            (res: HttpResponse<IProvidencia[]>) => {
                this.providencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.derivacion.fechaDerivacion = moment(this.fechaDerivacion, DATE_TIME_FORMAT);
        if (this.derivacion.id !== undefined) {
            this.subscribeToSaveResponseDP(this.derivacionService.update({ providenciaDTO: null, derivacionDTO: this.derivacion }));
        } else {
            this.subscribeToSaveResponseDerivacion(this.derivacionService.create(this.derivacion));
        }
    }

    private subscribeToSaveResponseDerivacion(result: Observable<HttpResponse<IDerivacion>>) {
        result.subscribe((res: HttpResponse<IDerivacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponseDP(result: Observable<HttpResponse<IProvidenciaDerivacion>>) {
        result.subscribe(
            (res: HttpResponse<IProvidenciaDerivacion>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProvidenciaById(index: number, item: IProvidencia) {
        return item.id;
    }
    get derivacion() {
        return this._derivacion;
    }

    set derivacion(derivacion: IDerivacion) {
        this._derivacion = derivacion;
        this.fechaDerivacion = moment(derivacion.fechaDerivacion).format(DATE_TIME_FORMAT);
    }
}
