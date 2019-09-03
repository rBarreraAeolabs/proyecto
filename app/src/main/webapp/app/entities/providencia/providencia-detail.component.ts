import {Component, OnChanges, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationEnd, NavigationStart, Router} from '@angular/router';

import {EstadoProvidencia, IProvidencia} from 'app/shared/model/providencia.model';

import { JhiAlertService } from 'ng-jhipster';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import {ProvidenciaService} from './providencia.service';
import {IRespuesta} from '../../shared/model/respuesta.model';
import {IPlantilla} from 'app/shared/model/plantilla.model';

@Component({
    selector: 'jhi-providencia-detail',
    templateUrl: './providencia-detail.component.html'
})
export class ProvidenciaDetailComponent implements OnInit, OnDestroy {
    providencia: IProvidencia;
    plantillaUtilizada: IPlantilla;
    movimientosProvidencia: IMovimientoProvidencia[];
    actionsPermitted: any = {
        aceptar: false,
        rechazar: false,
        reply: false,
        goBackwards: false,
        watchTabRespuesta: true,
        relacionarProvidencia: false,
        asignarFiscal: false,
        numerarReferencia: false,
        tipoSolicitud: false
    };
    refresh = false;
    respuesta: IRespuesta;
    isResponder = true;
    isDevolver = true;
    disableResponder = false;
    navigationSubscription;

    constructor(
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private providenciaService: ProvidenciaService,
        private router: Router
    ) {
        this.navigationSubscription = this.router.events.subscribe ((e: any) => {
            // Si es un evento NavigationEnd, volver a inicializar el componente
            if (e instanceof NavigationEnd) {
                if (this.refresh) {
                    this.refreshDetail();
                }
                this.refresh = true;
            }
        });
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            this.providencia = providencia;
            console.log('permisos para continuar', this.providencia.documentos);

            this.providencia.documentos.forEach(documento => {
                if (documento.tipoPlantilla === 'RESOLUCION') {
                    console.log('puede continuar porque tiene el archivo correcto');
                }
            });
            this.providenciaService.getActionsPermitted(this.providencia).subscribe(response => {
                this.actionsPermitted = response.body;

                // condicion para que en el estado 6 condiciona la vista del btn asignar numero referencia
                if (this.providencia.estadoActual === 'ESTADO_3') {
                    if (this.providencia.numeroReferencia !== null && typeof this.providencia.numeroReferencia !== 'undefined') {
                        this.actionsPermitted.numerarReferencia = false;
                    } else {
                        this.actionsPermitted.numerarReferencia = true;
                    }
                }
                // condicion para que en el estado 6 condiciona la vista del boton asignar tipo solicitud
                if (this.providencia.estadoActual === 'ESTADO_6') {
                    if (this.providencia.tipo !== null && typeof this.providencia.tipo !== 'undefined') {
                        this.actionsPermitted.tipoSolicitud = false;
                    } else {
                        this.actionsPermitted.tipoSolicitud = true;
                    }
                }

                // condicion para que en el estado actual haga una accion
                // 6, condiciona que si en contenido de la plantilla es nulo desactive el boton continuar
                // 11, condiciona si el nombre del fiscal es nulo o indefinido desactive el boton continuar
                // 14 condiciona la vista del btn continuar
                switch (this.providencia.estadoActual) {
                    case 'ESTADO_6': {
                        if (this.plantillaUtilizada.contenido === null) {
                            this.disableResponder = true;
                            this.refresh = true;
                        }

                        break;
                    }
                    case 'ESTADO_11': {
                        if (this.providencia.nombreFiscalAsignado === null || typeof this.providencia.nombreFiscalAsignado === 'undefined') {
                            this.disableResponder = true;
                        } else {
                            this.actionsPermitted.asignarFiscal = false;
                        }
                        break;
                    }
                    case 'ESTADO_14': {
                        this.disableResponder = true;
                        break;
                    }
                }

            });
            // condicion para cuando se valla a continuar=(true) o aceptar=(false)
            if (this.providencia.requisito === 'FISCAL_NOTIFICADO' && this.providencia.etapa === 'NUEVA_PROVIDENCIA') {
                this.isResponder = false;

            }

            // condicion para cuando se valla a devolver=(true) o rechazo=(false)

            if (this.providencia.requisito === 'FISCAL_NOTIFICADO' && this.providencia.etapa === 'NUEVA_PROVIDENCIA') {
                this.isDevolver = false;
            }

        });

    }

    refreshDetail() {
        this.providenciaService.find(this.providencia.id).subscribe(response => {
            this.providencia = response.body;
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

        console.log('Respondiendo', this.respuesta);

        if (this.providencia.estadoActual === 'ESTADO_6') {

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

        } else if (this.providencia.estadoActual === 'ESTADO_16') {

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

    ngOnDestroy() {
        if (this.navigationSubscription) {
            this.navigationSubscription.unsubscribe();
        }
    }
}
