import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFichaIngresoSdj } from 'app/shared/model/ficha-ingreso-sdj.model';
import { FichaIngresoSdjService } from './ficha-ingreso-sdj.service';
import { InstruccionesProvidencia, IProvidencia} from 'app/shared/model/providencia.model';
import { ProvidenciaService } from 'app/entities/providencia';
import { PlazoService } from '../plazo/plazo.service';
import { IPlazo } from '../../shared/model/plazo.model';

@Component({
    selector: 'jhi-ficha-ingreso-sdj-update',
    templateUrl: './ficha-ingreso-sdj-update.component.html'
})
export class FichaIngresoSdjUpdateComponent implements OnInit {
    private _fichaIngresoSdj: IFichaIngresoSdj;
    isSaving: boolean;

    // providencias: IProvidencia[];
    fechaInicio: string;
    fechaHasta: string;
    numeroReferencia: number;
    atentamente: string;
    // Variables para el multiselect
    dropdownListAcciones = [];
    selectedAcciones = [];
    dropdownSettingsAcciones = {};

    constructor(
        private jhiAlertService: JhiAlertService,
        private fichaIngresoSdjService: FichaIngresoSdjService,
        private providenciaService: ProvidenciaService,
        private activatedRoute: ActivatedRoute,
        private plazoService: PlazoService
    ) {}

    ngOnInit() {
        this.isSaving = false;

        this.cargarConfigMultiSelectAcciones();

        this.activatedRoute.data.subscribe(({ fichaIngresoSdj }) => {
            this.fichaIngresoSdj = fichaIngresoSdj;

            if (this.fichaIngresoSdj.id !== null && typeof this.fichaIngresoSdj.id !== 'undefined') {
                this.cargarAcciones(this.fichaIngresoSdj.acciones);
            }
        });

        this.providenciaService.query().subscribe(
            (res: HttpResponse<IProvidencia[]>) => {
                // this.providencias = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.plazoService.getAll().subscribe(
            (res: HttpResponse<IPlazo[]>) => {
                console.log('plazos', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.fichaIngresoSdj.fechaInicio = moment(this.fechaInicio, DATE_TIME_FORMAT);
        this.fichaIngresoSdj.fechaHasta = moment(this.fechaHasta, DATE_TIME_FORMAT);
        this.fichaIngresoSdj.numeroReferencia = this.numeroReferencia;
        this.fichaIngresoSdj.acciones = this.selectedAcciones.map(item => {
            return item.itemName.split(' ').join('_');
        });

        this.fichaIngresoSdj.atentamente = this.atentamente;

        console.log(this.fichaIngresoSdj);

        if (this.fichaIngresoSdj.id !== undefined) {
            this.subscribeToSaveResponse(this.fichaIngresoSdjService.update(this.fichaIngresoSdj));
        } else {
            this.subscribeToSaveResponse(this.fichaIngresoSdjService.create(this.fichaIngresoSdj));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFichaIngresoSdj>>) {
        result.subscribe((res: HttpResponse<IFichaIngresoSdj>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get fichaIngresoSdj() {
        return this._fichaIngresoSdj;
    }

    set fichaIngresoSdj(fichaIngresoSdj: IFichaIngresoSdj) {
        this._fichaIngresoSdj = fichaIngresoSdj;
        this.fechaInicio = moment(fichaIngresoSdj.fechaInicio).format(DATE_TIME_FORMAT);
        this.fechaHasta = moment(fichaIngresoSdj.fechaHasta).format(DATE_TIME_FORMAT);
        this.numeroReferencia = fichaIngresoSdj.numeroReferencia;
        this.atentamente = fichaIngresoSdj.atentamente;
    }
    // Funciones del multiselect

    cargarConfigMultiSelectAcciones() {
        this.dropdownListAcciones = [
            { id: 1, itemName: InstruccionesProvidencia.TOMAR_CONOCIMIENTO.split('_').join(' ') },
            { id: 2, itemName: InstruccionesProvidencia.PROPONER_RESPUESTA_AL_DIRECTOR.split('_').join(' ') },
            { id: 3, itemName: InstruccionesProvidencia.PROPONER_DECRETO_O_RESOLUCION.split('_').join(' ') },
            { id: 4, itemName: InstruccionesProvidencia.ESTUDIAR_ANTECEDENTES_Y_PROCEDER_CONFORME_A_DERECHO.split('_').join(' ') },
            { id: 5, itemName: InstruccionesProvidencia.CONVERSAR_CONMIGO.split('_').join(' ') },
            { id: 6, itemName: InstruccionesProvidencia.PROVIDENCIA_ARCHIVO.split('_').join(' ') }
        ];

        this.dropdownSettingsAcciones = {
            singleSelection: false,
            text: 'Seleccionar',
            selectAllText: 'Seleccionar todas',
            unSelectAllText: 'Deseleccionar todas',
            enableSearchFilter: true,
            classes: 'myclass custom-class'
        };
    }

    /**
     * Método que permite cargar las acciones de una ficha de ingreso que se va a editar.
     */
    cargarAcciones(acciones: string[]) {
        acciones.map(accion => {
            this.dropdownListAcciones.map(a => {
                if (a.itemName === accion.split('_').join(' ')) {
                    this.selectedAcciones.push({ id: a.id, itemName: accion.split('_').join(' ') });
                }
            });
        });
    }

    onItemSelect(item: any) {}

    OnItemDeSelect(item: any) {}
    onSelectAll(items: any) {
        console.log(items);
    }
    onDeSelectAll(items: any) {
        console.log(items);
    }
}
