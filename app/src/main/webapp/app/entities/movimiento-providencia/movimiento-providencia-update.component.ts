import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService} from 'ng-jhipster';

import {IMovimientoProvidencia} from 'app/shared/model/movimiento-providencia.model';
import {MovimientoProvidenciaService} from './movimiento-providencia.service';
import {IProvidencia} from 'app/shared/model/providencia.model';
import {ProvidenciaService} from 'app/entities/providencia';
import {IPlazo} from 'app/shared/model/plazo.model';
import {PlazoService} from 'app/entities/plazo';

@Component({
    selector: 'jhi-movimiento-providencia-update',
    templateUrl: './movimiento-providencia-update.component.html'
})
export class MovimientoProvidenciaUpdateComponent implements OnInit {
    private _movimientoProvidencia: IMovimientoProvidencia;
    isSaving: boolean;

    providencias: IProvidencia[];

    plazos: IPlazo[];
    fecha: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private movimientoProvidenciaService: MovimientoProvidenciaService,
        private providenciaService: ProvidenciaService,
        private plazoService: PlazoService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({movimientoProvidencia}) => {
            this.movimientoProvidencia = movimientoProvidencia;
        });
        this.providenciaService.query().subscribe(
            (res: HttpResponse<IProvidencia[]>) => {
                this.providencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.plazoService.query().subscribe(
            (res: HttpResponse<IPlazo[]>) => {
                this.plazos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.movimientoProvidencia.fecha = moment(this.fecha, DATE_TIME_FORMAT);
        if (this.movimientoProvidencia.id !== undefined) {
            this.subscribeToSaveResponse(this.movimientoProvidenciaService.update(this.movimientoProvidencia));
        } else {
            this.subscribeToSaveResponse(this.movimientoProvidenciaService.create(this.movimientoProvidencia));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoProvidencia>>) {
        result.subscribe(
            (res: HttpResponse<IMovimientoProvidencia>) => this.onSaveSuccess(),
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

    trackPlazoById(index: number, item: IPlazo) {
        return item.id;
    }

    get movimientoProvidencia() {
        return this._movimientoProvidencia;
    }

    set movimientoProvidencia(movimientoProvidencia: IMovimientoProvidencia) {
        this._movimientoProvidencia = movimientoProvidencia;
        this.fecha = moment(movimientoProvidencia.fecha).format(DATE_TIME_FORMAT);
    }
}
