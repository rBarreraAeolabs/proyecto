import { Component , OnDestroy , OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';

import { IProvidencia} from 'app/shared/model/providencia.model';

import { JhiAlertService } from 'ng-jhipster';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { ProvidenciaService } from './providencia.service';
import { IRespuesta } from '../../shared/model/respuesta.model';
import { IPlantilla } from 'app/shared/model/plantilla.model';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        tipoSolicitud: false,
        prorroga: false,
        apela: false,
        noApela: false,
        fiscalNotificaCierre: false,
        inculpadoEnviaMemo: false,
        inculpadoNoEnviaMemo: false,
        folio: false,
        asignarNumeroDespacho: false,

    };
    refresh = false;
    respuesta: IRespuesta;
    isResponder = true;
    isDevolver = true;
    isRechazar = true;
    isProrroga = true;
    isNotificaCierre = true;
    disableResponder = false;
    navigationSubscription;
    excel = [];

    constructor(
        private activatedRoute: ActivatedRoute,
        private jhiAlertService: JhiAlertService,
        private providenciaService: ProvidenciaService,
        private router: Router,
        private http: HttpClient
    ) {
        // trabajando con descarga en formato excell
        // this.providenciaService.query().subscribe(data => {
        //     console.log('esta es la data: ', data);
        //     data.body.forEach(row => {
        //         this.excel.push(row);
        //     });
        // });
        // this.actionsPermitted.aceptar = true;
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

            this.providenciaService.getActionsPermitted(this.providencia).subscribe(response => {
                this.actionsPermitted = response.body;
                console.log('ng onit ' +  this.actionsPermitted );

                switch (this.providencia.estadoActual) {
                    case 'RESOLUCION_Y_MEMO': {
                        if (this.plantillaUtilizada.contenido === null) {
                            this.disableResponder = true;
                            this.refresh = true;
                        }

                        break;
                    }
                }
                if (this.providencia.etapa === 'INVESTIGACION_PRORROGA_1' ) {
                    this.isProrroga = false;

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

    // complemento para descargar excel
    exportAsXLSX(): void {
        this.providenciaService.exportAsExcelFile(this.excel, 'sample');
    }

    public getJSON(): Observable<any> {
        return this.http.get('https://api.myjson.com/bins/zg8of');
    }

    // metodo que refresca el detalle
    refreshDetail() {

        this.providenciaService.find(this.providencia.id).subscribe(response => {
            this.providencia = response.body;
            this.refrescarPermisos(response.body);
        });
    }

    refrescarPermisos( provi: IProvidencia ) {

        console.log('ng refresh  recargado ' + provi.requisito);
        // this.providenciaService.find(provi.id).subscribe(response => {
        //     const provide = response.body;
        // });
        console.log('ng refresh  recargado ' + provi.requisito);
        console.log('ng refesh permite recargado ' +  this.actionsPermitted.apela + ' '  + this.actionsPermitted.noApela);
        this.providenciaService.getActionsPermitted(provi).subscribe(resp => {
            this.actionsPermitted = resp.body;
            console.log('ng refesh permite recargado ' +  this.actionsPermitted.apela + ' '  + this.actionsPermitted.noApela);

        });
        return this.actionsPermitted = this.actionsPermitted ;
    }
    // metodo para volver atras
    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    getRespuesta($event) {
        this.respuesta = $event;
    }

    ngOnDestroy() {
        if (this.navigationSubscription) {
            this.navigationSubscription.unsubscribe();
        }
    }

}
