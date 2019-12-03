import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {IInstrucciones} from 'app/shared/model/Instrucciones.model';
import {InstruccionesService} from 'app/entities/instrucciones/instrucciones.service';
import {IEntidad} from 'app/shared/model/entidad.model';
import {EntidadService} from 'app/entities/entidad';

@Component({
    selector: 'jhi-instrucciones-update',
    templateUrl: './instrucciones-update.component.html'
})
export class InstruccionesUpdateComponent implements OnInit {
    private _instrucciones: IInstrucciones;
    isSaving: boolean;

    constructor(private instruccionesService: InstruccionesService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ instrucciones }) => {
            this.instrucciones = instrucciones;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        console.log('guardando la instrucción', this.instrucciones);
        this.isSaving = true;
        if (this.instrucciones.id !== undefined) {
            this.subscribeToSaveResponse(this.instruccionesService.update(this.instrucciones));
        } else {
            this.subscribeToSaveResponse(this.instruccionesService.create(this.instrucciones));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IInstrucciones>>) {
        result.subscribe((res: HttpResponse<IInstrucciones>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get instrucciones() {
        return this._instrucciones;
    }

    set instrucciones(instrucciones: IInstrucciones) {
        this._instrucciones = instrucciones;
    }
}
