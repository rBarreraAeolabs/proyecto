import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';

import {IDocumento} from 'app/shared/model/documento.model';
import {DocumentoService} from './documento.service';
import {IProvidencia} from 'app/shared/model/providencia.model';
import {ProvidenciaService} from 'app/entities/providencia';
import {IDerivacion} from 'app/shared/model/derivacion.model';
import {DerivacionService} from 'app/entities/derivacion';
import {IMovimientoProvidencia} from 'app/shared/model/movimiento-providencia.model';
import {MovimientoProvidenciaService} from 'app/entities/movimiento-providencia';

@Component({
    selector: 'jhi-documento-update',
    templateUrl: './documento-update.component.html'
})
export class DocumentoUpdateComponent implements OnInit {
    private _documento: IDocumento;
    isSaving: boolean;

    providencias: IProvidencia[];

    derivacions: IDerivacion[];

    movimientoprovidencias: IMovimientoProvidencia[];
    fechaCreadoDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private documentoService: DocumentoService,
        private providenciaService: ProvidenciaService,
        private derivacionService: DerivacionService,
        private movimientoProvidenciaService: MovimientoProvidenciaService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({documento}) => {
            this.documento = documento;
        });
        this.providenciaService.query().subscribe(
            (res: HttpResponse<IProvidencia[]>) => {
                this.providencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.derivacionService.query().subscribe(
            (res: HttpResponse<IDerivacion[]>) => {
                this.derivacions = res.body;
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
        if (this.documento.id !== undefined) {
            this.subscribeToSaveResponse(this.documentoService.update(this.documento));
        } else {
            this.subscribeToSaveResponse(this.documentoService.create(this.documento));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDocumento>>) {
        result.subscribe((res: HttpResponse<IDocumento>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDerivacionById(index: number, item: IDerivacion) {
        return item.id;
    }

    trackMovimientoProvidenciaById(index: number, item: IMovimientoProvidencia) {
        return item.id;
    }

    get documento() {
        return this._documento;
    }

    set documento(documento: IDocumento) {
        this._documento = documento;
    }
}
