import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IGrupo } from 'app/shared/model/grupo.model';
import { GrupoService } from './grupo.service';
import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from 'app/entities/perfil';
import { IProvidencia } from 'app/shared/model/providencia.model';
import { ProvidenciaService } from 'app/entities/providencia';

@Component({
    selector: 'jhi-grupo-update',
    templateUrl: './grupo-update.component.html'
})
export class GrupoUpdateComponent implements OnInit {
    private _grupo: IGrupo;
    isSaving: boolean;
    perfils: IPerfil[];
    providencias: IProvidencia[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private grupoService: GrupoService,
        private perfilService: PerfilService,
        private providenciaService: ProvidenciaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ grupo }) => {
            this.grupo = grupo;
        });
        this.perfilService.query().subscribe(
            (res: HttpResponse<IPerfil[]>) => {
                this.perfils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

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
        if (this.grupo.id !== undefined) {
            this.subscribeToSaveResponse(this.grupoService.update(this.grupo));
        } else {
            this.subscribeToSaveResponse(this.grupoService.create(this.grupo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGrupo>>) {
        result.subscribe((res: HttpResponse<IGrupo>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPerfilById(index: number, item: IPerfil) {
        return item.id;
    }

    trackProvidenciaById(index: number, item: IProvidencia) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get grupo() {
        return this._grupo;
    }

    set grupo(grupo: IGrupo) {
        this._grupo = grupo;
    }
}
