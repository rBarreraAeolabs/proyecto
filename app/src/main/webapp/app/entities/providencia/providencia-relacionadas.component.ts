/**
 * Created by sneiraillanes on 02-05-2019.
 */
import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IProvidencia} from 'app/shared/model/providencia.model';

import {JhiAlertService} from 'ng-jhipster';
import {ProvidenciaService} from './providencia.service';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-providencia-relacionadas',
    templateUrl: './providencia-relacionadas.component.html'
})
export class ProvidenciaRelacionadasComponent implements OnInit, OnChanges {
    @Input() providencia: IProvidencia;
    providencias: IProvidencia[];

    constructor(
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private providenciaService: ProvidenciaService
    ) {
    }

    ngOnInit() {

        const runImplicado: string = this.providencia.runImplicado !== null ? this.providencia.runImplicado : null;
        const entidadImplicadaId: number = this.providencia.entidadImplicada !== null ? this.providencia.entidadImplicada.id : 0;

        this.providenciaService.getAllByRunOrEntidadImplicada(runImplicado, entidadImplicadaId, this.providencia.id)
            .subscribe(
                (req: HttpResponse<IProvidencia[]>) => {
                    this.providencias = req.body;
                },
                (req: HttpErrorResponse) => {
                    console.log('error ', req.message);
                }
            );
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.ngOnInit();
    }

    trackId(index: number, item: IProvidencia) {
        return item.id;
    }

    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

}
