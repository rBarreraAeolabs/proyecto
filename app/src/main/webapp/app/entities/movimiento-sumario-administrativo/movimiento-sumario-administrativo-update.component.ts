import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService} from 'ng-jhipster';

import {IMovimientoSumarioAdministrativo} from 'app/shared/model/movimiento-sumario-administrativo.model';
import {MovimientoSumarioAdministrativoService} from './movimiento-sumario-administrativo.service';
import {ISumarioAdministrativo} from 'app/shared/model/sumario-administrativo.model';
import {SumarioAdministrativoService} from 'app/entities/sumario-administrativo';

@Component({
    selector: 'jhi-movimiento-sumario-administrativo-update',
    templateUrl: './movimiento-sumario-administrativo-update.component.html'
})
export class MovimientoSumarioAdministrativoUpdateComponent implements OnInit {
    private _movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo;
    isSaving: boolean;

    sumarioadministrativos: ISumarioAdministrativo[];
    fecha: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private movimientoSumarioAdministrativoService: MovimientoSumarioAdministrativoService,
        private sumarioAdministrativoService: SumarioAdministrativoService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({movimientoSumarioAdministrativo}) => {
            this.movimientoSumarioAdministrativo = movimientoSumarioAdministrativo;
        });
        this.sumarioAdministrativoService.query().subscribe(
            (res: HttpResponse<ISumarioAdministrativo[]>) => {
                this.sumarioadministrativos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.movimientoSumarioAdministrativo.fecha = moment(this.fecha, DATE_TIME_FORMAT);
        if (this.movimientoSumarioAdministrativo.id !== undefined) {
            this.subscribeToSaveResponse(this.movimientoSumarioAdministrativoService.update(this.movimientoSumarioAdministrativo));
        } else {
            this.subscribeToSaveResponse(this.movimientoSumarioAdministrativoService.create(this.movimientoSumarioAdministrativo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoSumarioAdministrativo>>) {
        result.subscribe(
            (res: HttpResponse<IMovimientoSumarioAdministrativo>) => this.onSaveSuccess(),
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

    trackSumarioAdministrativoById(index: number, item: ISumarioAdministrativo) {
        return item.id;
    }

    get movimientoSumarioAdministrativo() {
        return this._movimientoSumarioAdministrativo;
    }

    set movimientoSumarioAdministrativo(movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo) {
        this._movimientoSumarioAdministrativo = movimientoSumarioAdministrativo;
        this.fecha = moment(movimientoSumarioAdministrativo.fecha).format(DATE_TIME_FORMAT);
    }
}
