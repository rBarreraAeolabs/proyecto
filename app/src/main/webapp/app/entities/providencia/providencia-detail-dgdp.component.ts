import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IProvidencia} from 'app/shared/model/providencia.model';

import {JhiAlertService} from 'ng-jhipster';
import {IMovimientoProvidencia} from 'app/shared/model/movimiento-providencia.model';
import {ProvidenciaService} from './providencia.service';

@Component({
    selector: 'jhi-providencia-detail-dgdp',
    templateUrl: './providencia-detail-dgdp.component.html'
})
export class ProvidenciaDetailDgdpComponent implements OnInit {
    providencia: IProvidencia;
    movimientosProvidencia: IMovimientoProvidencia[];
    actionsPermitted: any = {reply: false, goBackwards: false};

    constructor(
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private providenciaService: ProvidenciaService
    ) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({providencia}) => {
            this.providencia = providencia;

            this.providenciaService.getActionsPermitted(this.providencia).subscribe(response => {
                this.actionsPermitted = response.body;
            });
        });
    }

    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

}
