import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {IEntidad} from 'app/shared/model/entidad.model';
import {EntidadService} from 'app/entities/entidad/entidad.service';

@Component({
    selector: 'jhi-entidad-update',
    templateUrl: './entidad-update.component.html'
})
export class EntidadUpdateComponent implements OnInit {
    private _entidad: IEntidad;
    isSaving: boolean;

    constructor(private entidadService: EntidadService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ entidad }) => {
            this.entidad = entidad;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        console.log('guardando la de entidad', this.entidad);
        this.isSaving = true;
        if (this.entidad.id !== undefined) {
            this.subscribeToSaveResponse(this.entidadService.update(this.entidad));
        } else {
            this.subscribeToSaveResponse(this.entidadService.create(this.entidad));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IEntidad>>) {
        result.subscribe((res: HttpResponse<IEntidad>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get entidad() {
        return this._entidad;
    }

    set entidad(entidad: IEntidad) {
        this._entidad = entidad;
    }
}
