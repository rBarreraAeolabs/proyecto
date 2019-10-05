import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAdjunto } from 'app/shared/model/adjunto.model';
import { AdjuntoService } from './adjunto.service';
import { IProvidencia } from 'app/shared/model/providencia.model';
import { ProvidenciaService } from 'app/entities/providencia';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { MovimientoProvidenciaService } from 'app/entities/movimiento-providencia';

@Component({
    selector: 'jhi-adjunto-update',
    templateUrl: './adjunto-update.component.html'
})
export class AdjuntoUpdateComponent implements OnInit {
    private _adjunto: IAdjunto;
    isSaving: boolean;

    providencias: IProvidencia[];

    movimientoprovidencias: IMovimientoProvidencia[];
    fechaCreadoDp: any;
    fechaSubido: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private adjuntoService: AdjuntoService,
        private providenciaService: ProvidenciaService,
        private movimientoProvidenciaService: MovimientoProvidenciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ adjunto }) => {
            this.adjunto = adjunto;
        });
        this.providenciaService.query().subscribe(
            (res: HttpResponse<IProvidencia[]>) => {
                this.providencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.movimientoProvidenciaService.query().subscribe(
            (res: HttpResponse<IMovimientoProvidencia[]>) => {
                this.movimientoprovidencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.adjunto.fechaSubido = moment(this.fechaSubido, DATE_TIME_FORMAT);
        if (this.adjunto.id !== undefined) {
            this.subscribeToSaveResponse(this.adjuntoService.update(this.adjunto));
        } else {
            this.subscribeToSaveResponse(this.adjuntoService.create(this.adjunto));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAdjunto>>) {
        result.subscribe((res: HttpResponse<IAdjunto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackMovimientoProvidenciaById(index: number, item: IMovimientoProvidencia) {
        return item.id;
    }
    get adjunto() {
        return this._adjunto;
    }

    set adjunto(adjunto: IAdjunto) {
        this._adjunto = adjunto;
        this.fechaSubido = moment(adjunto.fechaSubido).format(DATE_TIME_FORMAT);
    }
}
