import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_TIME_FORMAT} from 'app/shared/constants/input.constants';
import {JhiAlertService} from 'ng-jhipster';

import {IMovimientoInvestigacionSumaria} from 'app/shared/model/movimiento-investigacion-sumaria.model';
import {MovimientoInvestigacionSumariaService} from './movimiento-investigacion-sumaria.service';
import {IInvestigacionSumaria} from 'app/shared/model/investigacion-sumaria.model';
import {InvestigacionSumariaService} from 'app/entities/investigacion-sumaria';

@Component({
    selector: 'jhi-movimiento-investigacion-sumaria-update',
    templateUrl: './movimiento-investigacion-sumaria-update.component.html'
})
export class MovimientoInvestigacionSumariaUpdateComponent implements OnInit {
    private _movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria;
    isSaving: boolean;

    investigacionsumarias: IInvestigacionSumaria[];
    fecha: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private movimientoInvestigacionSumariaService: MovimientoInvestigacionSumariaService,
        private investigacionSumariaService: InvestigacionSumariaService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({movimientoInvestigacionSumaria}) => {
            this.movimientoInvestigacionSumaria = movimientoInvestigacionSumaria;
        });
        this.investigacionSumariaService.query().subscribe(
            (res: HttpResponse<IInvestigacionSumaria[]>) => {
                this.investigacionsumarias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.movimientoInvestigacionSumaria.fecha = moment(this.fecha, DATE_TIME_FORMAT);
        if (this.movimientoInvestigacionSumaria.id !== undefined) {
            this.subscribeToSaveResponse(this.movimientoInvestigacionSumariaService.update(this.movimientoInvestigacionSumaria));
        } else {
            this.subscribeToSaveResponse(this.movimientoInvestigacionSumariaService.create(this.movimientoInvestigacionSumaria));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMovimientoInvestigacionSumaria>>) {
        result.subscribe(
            (res: HttpResponse<IMovimientoInvestigacionSumaria>) => this.onSaveSuccess(),
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

    trackInvestigacionSumariaById(index: number, item: IInvestigacionSumaria) {
        return item.id;
    }

    get movimientoInvestigacionSumaria() {
        return this._movimientoInvestigacionSumaria;
    }

    set movimientoInvestigacionSumaria(movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria) {
        this._movimientoInvestigacionSumaria = movimientoInvestigacionSumaria;
        this.fecha = moment(movimientoInvestigacionSumaria.fecha).format(DATE_TIME_FORMAT);
    }
}
