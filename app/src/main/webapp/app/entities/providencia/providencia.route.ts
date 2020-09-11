import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { IProvidencia, Providencia } from 'app/shared/model/providencia.model';
import { ProvidenciaService } from './providencia.service';
import { ProvidenciaComponent } from './providencia.component';
import { ProvidenciaUpdatePopupComponent } from './providencia-update.component';
import { ProvidenciaDeletePopupComponent } from './providencia-delete-dialog.component';
import { ProvidenciaResponderPopupComponent } from 'app/entities/providencia/providencia-responder-dialog.component';
import { ProvidenciaDevolverPopupComponent } from 'app/entities/providencia/providencia-devolver-dialog.component';
import { ProvidenciaDetailComponent } from 'app/entities/providencia/providencia-detail.component';
import { ProvidenciaDetailDgdpComponent } from './providencia-detail-dgdp.component';
import { ProvidenciaAsignarNumeroResolucionPopupComponent } from 'app/entities/providencia/providencia-asignar-numero-resolucion.component';
import { ProvidenciaAsignarFiscalPopupComponent } from 'app/entities/providencia/providencia-asignar-fiscal.component';
import { ProvidenciaRelacionarPopupComponent } from './providencia-relacionar-dialog.component';
import { ProvidenciaAsignarNumeroReferenciaPopupComponent } from 'app/entities/providencia/providencia-asignar-numero-referencia.component';
import { ProvidenciaAsignarTipoSolicitudPopupComponent } from 'app/entities/providencia/providencia-asignar-tipo-solicitud.component';
import { ProvidenciaFiscalAceptaPopupComponent } from 'app/entities/providencia/providencia-fiscal-acepta.component';
import { ProvidenciaFiscalRechazaPopupComponent } from 'app/entities/providencia/providencia-fiscal-rechaza.component';
import { ProvidenciaFiscalProrrogaPopupComponent } from 'app/entities/providencia/providencia-fiscal-prorroga.component';
import { ProvidenciaApelaPopupComponent } from 'app/entities/providencia/providencia-apela.component';
import { ProvidenciaNoApelaPopupComponent } from 'app/entities/providencia/providencia-no-apela.component';
import { ProvidenciaFiscalNotificaCierrePopupComponent } from 'app/entities/providencia/providencia-fiscal-notifica-cierre-investigacion.component';
import {ProvidenciaInculpadoEnviaMemoPopupComponent} from 'app/entities/providencia/providencia-inculpado-envia-memo.component';
import {ProvidenciaInculpadoNoEnviaMemoPopupComponent} from 'app/entities/providencia/providencia-inculpado-no-envia-memo.component';
import {ProvidenciaFiscalFormulaCargosPopupComponent} from 'app/entities/providencia/providencia-fiscal-formula-cargos.component';
import {ProvidenciaFiscalRemiteExpedientePopupComponent} from 'app/entities/providencia/providencia-fiscal-remite-expediente.component';
import {ProvidenciaTerminoProbatorioPopupComponent} from 'app/entities/providencia/providencia-termino-probatorio.component';
import {ProvidenciaSiDeAcuerdoPopupComponent} from 'app/entities/providencia/providencia-si-de-acuerdo.component';
import {ProvidenciaNoReabroPopupComponent} from 'app/entities/providencia/providencia-no-reabro.component';
import {ProvidenciaNoProponePopupComponent} from 'app/entities/providencia/providencia-no-propone.component';
import {ProvidenciaUpdNotificaInculpadoPopupComponent} from 'app/entities/providencia/providencia-upd-notifica-inculpado.component';
import { ProvidenciaAsignarNumeroFolioPopupComponent } from 'app/entities/providencia/providencia-asignar-numero-folio.component';
import {ProvidenciaTomaRazonPopupComponent} from 'app/entities/providencia/providencia-toma-razon.component';
import {ProvidenciaRegistraPopupComponent} from 'app/entities/providencia/providencia-registra.component';
import {ProvidenciaRepresentaPopupComponent} from 'app/entities/providencia/providencia-representa.component';
import {ProvidenciaMemoConductorPopupComponent} from 'app/entities/providencia/providencia-memo-conductor.component';
import {ProvidenciaExamenLegalidadPopupComponent} from 'app/entities/providencia/providencia-examen-legalidad.component';
import {ProvidenciaAlcancePopupComponent} from 'app/entities/providencia/providencia-alcance.component';
import {ProvidenciaResolucionPopupComponent} from 'app/entities/providencia/providencia-resolucion.component';
import {ProvidenciaSinResolucionPopupComponent} from 'app/entities/providencia/providencia-sin-resolucion.component';
import {ProvidenciaRealizoResolucionPopupComponent} from 'app/entities/providencia/providencia-realizo-resolucion.component';
import {ProvidenciaDgdpNotificaInculpadoPopupComponent} from 'app/entities/providencia/providencia-dgdp-notifica-inculpado.component';
import {ProvidenciaMultaPopupComponent} from 'app/entities/providencia/providencia-multa.component';
import {ProvidenciaSuspensionPopupComponent} from 'app/entities/providencia/providencia-suspension.component';
import {ProvidenciaSensuraPopupComponent} from 'app/entities/providencia/providencia-sensura.component';
import {ProvidenciaDestitucionPopupComponent} from 'app/entities/providencia/providencia-destitucion.component';
import {ProvidenciaNotificaRemuneracionPopupComponent} from 'app/entities/providencia/providencia-notifica-remuneracion.component';
import {ProvidenciaNotificaDenunciantePopupComponent} from 'app/entities/providencia/providencia-notifica-denunciante.component';
import {ProvidenciaNotificaDenunciadoPopupComponent} from 'app/entities/providencia/providencia-notifica-denunciado.component';
import {ProvidenciaAsignarNumeroDgdPopupComponent} from 'app/entities/providencia/providencia-asignar-numero-dgd.component';
import {ProvidenciaAsignarNumeroProvidenciaPopupComponent} from 'app/entities/providencia/providencia-asignar-numero-providencia.component';
import {ProvidenciaEmitePopupComponent} from 'app/entities/providencia/providencia-emite.component';
import {ProvidenciaAsignarNumeroDgdpPopupComponent} from 'app/entities/providencia/providencia-asignar-numero-dgdp.component';
import {ProvidenciaAsignarAbogadoPopupComponent} from 'app/entities/providencia/providencia-asignar-abogado.component';
import {ProvidenciaAsignarNumeroIngresoPopupComponent} from 'app/entities/providencia/providencia-asignar-numero-ingreso.component';
import {ProvidenciaAsignarAUpdPopupComponent} from 'app/entities/providencia/providencia-asignar-a-upd.component';
import {ProvidenciaSinDenunciantePopupComponent} from 'app/entities/providencia/providencia-sin-denunciante.component';
import {ProvidenciaAsignarNumeroDespachoPopupComponent} from 'app/entities/providencia/providencia-asignar-numero-despacho.component';
import {ProvidenciaEnviarInformePopupComponent} from 'app/entities/providencia/providencia-enviar-informe.component';
import {ProvidenciaEnviaVistaFiscalPopupComponent} from 'app/entities/providencia/providencia-envia-vista-fiscal.component';
import {ProvidenciaNotificaResolucionPopupComponent} from 'app/entities/providencia/providencia-notifica-resolucion.component';
import {ProvidenciaAcogePopupComponent} from 'app/entities/providencia/providencia-acoge.component';
import {ProvidenciaAcogeFiscalPopupComponent} from 'app/entities/providencia/providencia-acoge-fiscal.component';
import {ProvidenciaNoAcogeFiscalPopupComponent} from 'app/entities/providencia/providencia-no-acoge-fiscal.component';

import {ProvidenciaAcogeParcialPopupComponent} from 'app/entities/providencia/providencia-acoge-parcial.component';
import {ProvidenciaRechazaPopupComponent} from 'app/entities/providencia/providencia-rechaza.component';
import {ProvidenciaNotificaDgdpPopupComponent} from 'app/entities/providencia/providencia-notifica-dgdp.component';
import {ProvidenciaCierreInvestigacionSumariaPopupComponent} from 'app/entities/providencia/providencia-cierre-investigacion-sumaria.component';
import {ProvidenciaElevarsumarioAdministrativoPopupComponent} from 'app/entities/providencia/providencia-elevar-sumario-administrativo.component';
import {ProvidenciaApelaInvestigacionPopupComponent} from 'app/entities/providencia/providencia-apela-investigacion.component';
import {ProvidenciaAcogeInvestigacionApelaPopupComponent} from 'app/entities/providencia/providencia-acoge-investigacion-apela.component';
import {ProvidenciaAceptaInvestigadorNoAceptaPopupComponent} from 'app/entities/providencia/providencia-acepta-investigador-no-acepta.component';
// import {ProvidenciaAcogeParcialInvestigacionApelaPopupComponent} from 'app/entities/providencia/providencia-acoge-parcial-investigacion-apela.component';
// import {ProvidenciaRechazaInvestigacionApelaPopupComponent} from 'app/entities/providencia/providencia-rechaza-investigacion-apela.component';
// import {ProvidenciaAcogeInvestigacionNoApelaPopupComponent} from 'app/entities/providencia/providencia-acoge-investigacion-no-apela.component';
// import {ProvidenciaAcogeParcialInvestigacionNoApelaPopupComponent} from 'app/entities/providencia/providencia-acoge-parcial-investigacion-no-apela.component';
// import {ProvidenciaRechazaInvestigacionNoApelaPopupComponent} from 'app/entities/providencia/providencia-rechaza-investigacion-no-apela.component';
// import {ProvidenciaSiDeAcuerdoInvestigacionPopupComponent} from 'app/entities/providencia/providencia-si-de-acuerdo-investigacion.component';
// import {ProvidenciaNoReabroInvestigacionPopupComponent} from 'app/entities/providencia/providencia-no-reabro-investigacion.component';
// import {ProvidenciaNoProponeInvestigacionPopupComponent} from 'app/entities/providencia/providencia-no-propone-investigacion.component';
// import {ProvidenciaNoAceptaInvestigadorNoAceptaPopupComponent} from 'app/entities/providencia/providencia-no-acepta-investigador-no-acepta.component';
// import {ProvidenciaNoAcogeInvestigacionNoApelaPopupComponent} from 'app/entities/providencia/providencia-no-acoge-investigacion-no-apela.component';


@Injectable({ providedIn: 'root' })
@Injectable({ providedIn: 'root' })
export class ProvidenciaResolve implements Resolve<IProvidencia> {
    constructor(private service: ProvidenciaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((providencia: HttpResponse<Providencia>) => providencia.body));
        }
        return of(new Providencia());
    }
}

export const providenciaRoute: Routes = [
    {
        path: 'providencia',
        component: ProvidenciaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PROVIDENCIA'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'providencia/:id/view',
        component: ProvidenciaDetailComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PROVIDENCIA'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'providencia/:id/viewDgdp',
        component: ProvidenciaDetailDgdpComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
    // {
    //     path: 'providencia/new',
    //     component: ProvidenciaUpdateComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'CREAR_PROVIDENCIA'],
    //         pageTitle: 'pdisciplinarioApp.providencia.home.title'
    //     },
    //     canActivate: [UserRouteAccessService]
    // },
    // {
    //     path: 'providencia/:id/edit',
    //     component: ProvidenciaUpdateComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'EDITAR_PROVIDENCIA'],
    //         pageTitle: 'pdisciplinarioApp.providencia.home.title'
    //     },
    //     canActivate: [UserRouteAccessService]
    // }
];
export const providenciaPopupRoute: Routes = [
    {
        path: 'providencia/new',
        component: ProvidenciaUpdatePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/edit',
        component: ProvidenciaUpdatePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'EDITAR_PROVIDENCIA'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/delete',
        component: ProvidenciaDeletePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/siDeAcuerdo',
        component: ProvidenciaSiDeAcuerdoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/noReabro',
        component: ProvidenciaNoReabroPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/noPropone',
        component: ProvidenciaNoProponePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    // {
    //     path: 'providencia/:id/siDeAcuerdoInvestigacion',
    //     component: ProvidenciaSiDeAcuerdoInvestigacionPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'pdisciplinarioApp.providencia.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    // {
    //     path: 'providencia/:id/noReabroInvestigacion',
    //     component: ProvidenciaNoReabroInvestigacionPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'pdisciplinarioApp.providencia.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    // {
    //     path: 'providencia/:id/noProponeInvestigacion',
    //     component: ProvidenciaNoProponeInvestigacionPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER'],
    //         pageTitle: 'pdisciplinarioApp.providencia.home.title'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'providencia/:id/terminoProbatorio',
        component: ProvidenciaTerminoProbatorioPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },

    {
        path: 'providencia/:id/responder',
        component: ProvidenciaResponderPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Responder providencia',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/resolucion',
        component: ProvidenciaResolucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Responder resolucion',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/sinResolucion',
        component: ProvidenciaSinResolucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Responder sin resolucion',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },   {
        path: 'providencia/:id/realizoResolucion',
        component: ProvidenciaRealizoResolucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Responder sin resolucion',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/alcance',
        component: ProvidenciaAlcancePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Alcance'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/examenLegalidad',
        component: ProvidenciaExamenLegalidadPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Examen legadidad providencia',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/tomaRazon',
        component: ProvidenciaTomaRazonPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Tomar Razon providencia',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/registra',
        component: ProvidenciaRegistraPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'registra providencia',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/representa',
        component: ProvidenciaRepresentaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Representa providencia',
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/fiscalAcepta',
        component: ProvidenciaFiscalAceptaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Aceptar providencia'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },  {
        path: 'providencia/:id/aceptaInvestigadorNoAcepta',
        component: ProvidenciaAceptaInvestigadorNoAceptaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Aceptar providencia'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    // {
    //     path: 'providencia/:id/NoAceptaInvestigadorNoAcepta',
    //     component: ProvidenciaNoAceptaInvestigadorNoAceptaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Aceptar providencia'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'providencia/:id/fiscalRechaza',
        component: ProvidenciaFiscalRechazaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Rechazar providencia'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    // {
    //     path: 'providencia/:id/rechazaInvestigacionApela',
    //     component: ProvidenciaRechazaInvestigacionApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Rechazar providencia'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // }, {
    //     path: 'providencia/:id/rechazaInvestigacionNoApela',
    //     component: ProvidenciaRechazaInvestigacionNoApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Rechazar providencia'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'providencia/:id/prorroga',
        component: ProvidenciaFiscalProrrogaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'prorroga'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificaCierre',
        component: ProvidenciaFiscalNotificaCierrePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'NotificaCierre'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/CierreIvestigacionSumaria',
        component: ProvidenciaCierreInvestigacionSumariaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'CierreIvestigacionSumaria'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/elevarSumarioAdministrativo',
        component: ProvidenciaElevarsumarioAdministrativoPopupComponent
        ,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'CierreIvestigacionSumaria'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/enviaVistaFiscal',
        component: ProvidenciaEnviaVistaFiscalPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'enviaVistaFiscal'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificaResolucion',
        component: ProvidenciaNotificaResolucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'notifica Resolucion'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/acoge',
        component: ProvidenciaAcogePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Acoge'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/acogeInvestigacionApela',
        component: ProvidenciaAcogeInvestigacionApelaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Acoge'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    // {
    //     path: 'providencia/:id/acogeInvestigacionNoApela',
    //     component: ProvidenciaAcogeInvestigacionNoApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Acoge'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // }, {
    //     path: 'providencia/:id/noAcogeInvestigacionNoApela',
    //     component: ProvidenciaNoAcogeInvestigacionNoApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Acoge'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    // {
    //     path: 'providencia/:id/acogeParcialInvestacionApela',
    //     component: ProvidenciaAcogeParcialInvestigacionApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Acoge'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // }, {
    //     path: 'providencia/:id/acogeParcialInvestacionNoApela',
    //     component: ProvidenciaAcogeParcialInvestigacionNoApelaPopupComponent,
    //     resolve: {
    //         providencia: ProvidenciaResolve
    //     },
    //     data: {
    //         authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
    //         pageTitle: 'Acoge'
    //     },
    //     canActivate: [UserRouteAccessService],
    //     outlet: 'popup'
    // },
    {
        path: 'providencia/:id/acogeFiscal',
        component: ProvidenciaAcogeFiscalPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Acoge'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/noAcogeFiscal',
        component: ProvidenciaNoAcogeFiscalPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Acoge'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/acogeParcial',
        component: ProvidenciaAcogeParcialPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Acoge'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/rechaza',
        component: ProvidenciaRechazaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Rechaza'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/apela',
        component: ProvidenciaApelaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Apela'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/noApela',
        component: ProvidenciaNoApelaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'NO Apela'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/apelaInvestigacion',
        component: ProvidenciaApelaInvestigacionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'ApelaInvestigacion'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/noApelaInvestigacion',
        component: ProvidenciaNoApelaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'NO Apela investigacion'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/inculpadoEnviaMemo',
        component: ProvidenciaInculpadoEnviaMemoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Envia Memo'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }, {
        path: 'providencia/:id/enviarInforme',
        component: ProvidenciaEnviarInformePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Envia Memo'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/inculpadoNoEnviaMemo',
        component: ProvidenciaInculpadoNoEnviaMemoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'NO Envia Memo'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/updNotificaInculpado',
        component: ProvidenciaUpdNotificaInculpadoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificarDGDP',
        component: ProvidenciaDgdpNotificaInculpadoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/emiteProvidencia ',
        component: ProvidenciaEmitePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Apela'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/multa',
        component: ProvidenciaMultaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },  {
        path: 'providencia/:id/suspension',
        component: ProvidenciaSuspensionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/censura',
        component: ProvidenciaSensuraPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/destitucion',
        component: ProvidenciaDestitucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificaRemuneracion',
        component: ProvidenciaNotificaRemuneracionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificaDenunciante',
        component: ProvidenciaNotificaDenunciantePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/notificaDenunciado',
        component: ProvidenciaNotificaDenunciadoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'notificar denunciado'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },   {
        path: 'providencia/:id/notificaDgdp',
        component: ProvidenciaNotificaDgdpPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'notificar denunciado'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/memoConductor',
        component: ProvidenciaMemoConductorPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/formulaCargos',
        component: ProvidenciaFiscalFormulaCargosPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'NO Envia Memo'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/remiteExpediente',
        component: ProvidenciaFiscalRemiteExpedientePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'remite expediente'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },  {
        path: 'providencia/:id/sinDenunciante',
        component: ProvidenciaSinDenunciantePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'sin denunciante'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },

    {
        path: 'providencia/:id/devolver',
        component: ProvidenciaDevolverPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Responder providencia'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarFiscal',
        component: ProvidenciaAsignarFiscalPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarAbogado',
        component: ProvidenciaAsignarAbogadoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/numerarResolucion',
        component: ProvidenciaAsignarNumeroResolucionPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/folio',
        component: ProvidenciaAsignarNumeroFolioPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/numerarReferencia',
        component: ProvidenciaAsignarNumeroReferenciaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarNumeroDGD',
        component: ProvidenciaAsignarNumeroDgdPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },  {
        path: 'providencia/:id/asignarNumeroDGDP',
        component: ProvidenciaAsignarNumeroDgdpPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarNumeroIngreso',
        component: ProvidenciaAsignarNumeroIngresoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarNumeroDespacho',
        component: ProvidenciaAsignarNumeroDespachoPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarAUpd',
        component: ProvidenciaAsignarAUpdPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Apela'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/emiteProvidencia ',
        component: ProvidenciaEmitePopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'DERIVAR_PROVIDENCIA'],
            pageTitle: 'Apela'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/asignarNumeroProvidencia',
        component: ProvidenciaAsignarNumeroProvidenciaPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },

    {
        path: 'providencia/:id/tipoSolicitud',
        component: ProvidenciaAsignarTipoSolicitudPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        runGuardsAndResolvers: 'always',
        outlet: 'popup'
    },
    {
        path: 'providencia/:id/relacionar',
        component: ProvidenciaRelacionarPopupComponent,
        resolve: {
            providencia: ProvidenciaResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.providencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
