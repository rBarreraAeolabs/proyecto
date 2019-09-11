import {Component, OnChanges, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {EstadoProvidencia, IProvidencia} from 'app/shared/model/providencia.model';

import {JhiAlertService} from 'ng-jhipster';
import {IMovimientoProvidencia} from 'app/shared/model/movimiento-providencia.model';
import {ProvidenciaService} from './providencia.service';
import {IRespuesta} from '../../shared/model/respuesta.model';

@Component({
    selector: 'jhi-providencia-detail',
    templateUrl: './providencia-detail.component.html'
})
export class ProvidenciaDetailComponent implements OnInit {
    providencia: IProvidencia;
    movimientosProvidencia: IMovimientoProvidencia[];
    actionsPermitted: any = {
        reply: false,
        goBackwards: false,
        watchTabRespuesta: true,
        relacionarProvidencia: false,
        asignarFiscal: false
    };

    respuesta: IRespuesta;
    isResponder = true;
    isDevolver = true;
    disableResponder = false;


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

            if (this.providencia.estadoActual === EstadoProvidencia.ESTADO_6 || this.providencia.estadoActual === EstadoProvidencia.ESTADO_14) {
                this.disableResponder = true;
            }

            if (this.providencia.estadoActual === EstadoProvidencia.ESTADO_21) {
                this.isResponder = false;
                this.isDevolver = false;
            }
            if (this.providencia.estadoActual === EstadoProvidencia.ESTADO_19) {
                this.isResponder = false;
                this.isDevolver = false;
            }
        });

    }

    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    getRespuesta($event) {
        this.respuesta = $event;

        console.log('asdasd', this.respuesta);

        if (this.providencia.estadoActual === EstadoProvidencia.ESTADO_6) {

            if (this.respuesta.documentos != null && this.respuesta.documentos.length >= 2) {

                let countMemos = 0;
                let countResoluciones = 0;

                this.respuesta.documentos.forEach(doc => {
                    if (doc.tipoPlantilla === 'MEMORANDUM') {
                        countMemos += 1;
                    } else if (doc.tipoPlantilla === 'RESOLUCION') {
                        countResoluciones += 1;
                    }
                });

                if (countMemos > 0 && countResoluciones > 0) {
                    this.disableResponder = false;
                }
            } else {
                this.disableResponder = true;
            }

        } else if (this.providencia.estadoActual === EstadoProvidencia.ESTADO_14) {

            if (this.respuesta.documentos != null && this.respuesta.documentos.length > 0) {

                let countNotificaciones = 0;

                this.respuesta.documentos.forEach(doc => {
                    if (doc.tipoPlantilla === 'NOTIFICACION') {
                        countNotificaciones += 1;
                    }
                });

                if (countNotificaciones >= 1) {
                    this.disableResponder = false;
                }
            } else {
                this.disableResponder = true;
            }
        }
    }

}
