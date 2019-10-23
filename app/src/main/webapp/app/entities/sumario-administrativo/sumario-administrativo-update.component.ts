import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISumarioAdministrativo } from 'app/shared/model/sumario-administrativo.model';
import { SumarioAdministrativoService } from './sumario-administrativo.service';
import { IInvestigacionSumaria } from 'app/shared/model/investigacion-sumaria.model';
import { InvestigacionSumariaService } from 'app/entities/investigacion-sumaria';

@Component({
    selector: 'jhi-sumario-administrativo-update',
    templateUrl: './sumario-administrativo-update.component.html'
})
export class SumarioAdministrativoUpdateComponent implements OnInit {
    private _sumarioAdministrativo: ISumarioAdministrativo;
    isSaving: boolean;

    investigacionsumarias: IInvestigacionSumaria[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private sumarioAdministrativoService: SumarioAdministrativoService,
        private investigacionSumariaService: InvestigacionSumariaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sumarioAdministrativo }) => {
            this.sumarioAdministrativo = sumarioAdministrativo;
        });
        this.investigacionSumariaService.query({ filter: 'sumarioadministrativo-is-null' }).subscribe(
            (res: HttpResponse<IInvestigacionSumaria[]>) => {
                if (!this.sumarioAdministrativo.investigacionSumariaId) {
                    this.investigacionsumarias = res.body;
                } else {
                    this.investigacionSumariaService.find(this.sumarioAdministrativo.investigacionSumariaId).subscribe(
                        (subRes: HttpResponse<IInvestigacionSumaria>) => {
                            this.investigacionsumarias = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sumarioAdministrativo.id !== undefined) {
            this.subscribeToSaveResponse(this.sumarioAdministrativoService.update(this.sumarioAdministrativo));
        } else {
            this.subscribeToSaveResponse(this.sumarioAdministrativoService.create(this.sumarioAdministrativo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISumarioAdministrativo>>) {
        result.subscribe(
            (res: HttpResponse<ISumarioAdministrativo>) => this.onSaveSuccess(),
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
    get sumarioAdministrativo() {
        return this._sumarioAdministrativo;
    }

    set sumarioAdministrativo(sumarioAdministrativo: ISumarioAdministrativo) {
        this._sumarioAdministrativo = sumarioAdministrativo;
    }
}
