import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    ProvidenciaComponent,
    ProvidenciaDetailComponent,
    ProvidenciaUpdateComponent,
    ProvidenciaDeletePopupComponent,
    ProvidenciaDeleteDialogComponent,
    providenciaRoute,
    providenciaPopupRoute
} from './';

import { FormsModule } from '@angular/forms';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
import { MovimientoProvidenciaComponent } from 'app/entities/movimiento-providencia';
import { ProvidenciaResponderDialogComponent, ProvidenciaResponderPopupComponent } from 'app/entities/providencia/providencia-responder-dialog.component';
import { ProvidenciaDevolverDialogComponent, ProvidenciaDevolverPopupComponent } from 'app/entities/providencia/providencia-devolver-dialog.component';
import { DerivacionComponent } from '../derivacion/derivacion.component';
import { AdjuntoShowListPopupComponent } from '../adjunto/adjunto-show-list-dialog.component';
import { CKEditorModule } from 'ng2-ckeditor';
import { ProvidenciaDetailDgdpComponent } from 'app/entities/providencia/providencia-detail-dgdp.component';
import {
    ProvidenciaAsignarNumeroResolucionComponent,
    ProvidenciaAsignarNumeroResolucionPopupComponent
} from 'app/entities/providencia/providencia-asignar-numero-resolucion.component';
import { RespuestaUpdateComponent } from '../respuesta/respuesta-update.component';
import { ComentarioBitacoraModalComponent } from 'app/entities/movimiento-providencia/comentario-bitacora-modal.component';
import { RespuestaBitacoraModalComponent } from 'app/entities/respuesta/respuesta-bitacora-modal.componente';
import { ProvidenciaRelacionadasComponent } from './providencia-relacionadas.component';

import { Ng2Rut } from 'ng2-rut';

import {
    ProvidenciaRelacionarDialogComponent,
    ProvidenciaRelacionarPopupComponent
} from './providencia-relacionar-dialog.component';

import {
    ProvidenciaTerminoProbatorioComponent,
    ProvidenciaTerminoProbatorioPopupComponent
} from './providencia-termino-probatorio.component';

import {
    ProvidenciaSiDeAcuerdoComponent,
    ProvidenciaSiDeAcuerdoPopupComponent
} from './providencia-si-de-acuerdo.component';

import {
    ProvidenciaAsignarFiscalComponent,
    ProvidenciaAsignarFiscalPopupComponent
} from './providencia-asignar-fiscal.component';
import {
    ProvidenciaAsignarNumeroReferenciaComponent,
    ProvidenciaAsignarNumeroReferenciaPopupComponent
} from 'app/entities/providencia/providencia-asignar-numero-referencia.component';
import {
    ProvidenciaAsignarNumeroFolioComponent,
    ProvidenciaAsignarNumeroFolioPopupComponent
} from 'app/entities/providencia/providencia-asignar-numero-folio.component';

import {
    ProvidenciaAsignarTipoSolicitudComponent,
    ProvidenciaAsignarTipoSolicitudPopupComponent
} from 'app/entities/providencia/providencia-asignar-tipo-solicitud.component';
import {
    ProvidenciaFiscalAceptaComponent,
    ProvidenciaFiscalAceptaPopupComponent
} from 'app/entities/providencia/providencia-fiscal-acepta.component';
import {
    ProvidenciaFiscalRechazaComponent,
    ProvidenciaFiscalRechazaPopupComponent
} from 'app/entities/providencia/providencia-fiscal-rechaza.component';
import {
    ProvidenciaFiscalProrrogaComponent,
    ProvidenciaFiscalProrrogaPopupComponent
} from 'app/entities/providencia/providencia-fiscal-prorroga.component';
import {
    ProvidenciaApelaComponent,
    ProvidenciaApelaPopupComponent
} from 'app/entities/providencia/providencia-apela.component';
import {
    ProvidenciaNoProponeComponent,
    ProvidenciaNoProponePopupComponent
} from 'app/entities/providencia/providencia-no-propone.component';

import {

    ProvidenciaNoReabroComponent,
    ProvidenciaNoReabroPopupComponent
}  from 'app/entities/providencia/providencia-no-reabro.component';

import {
    ProvidenciaNoApelaComponent,
    ProvidenciaNoApelaPopupComponent
} from 'app/entities/providencia/providencia-no-apela.component';
import {
    ProvidenciaFiscalNotificaCierreComponent,
    ProvidenciaFiscalNotificaCierrePopupComponent
} from 'app/entities/providencia/providencia-fiscal-notifica-cierre-investigacion.component';
import {
    ProvidenciaInculpadoNoEnviaMemoComponent,
    ProvidenciaInculpadoNoEnviaMemoPopupComponent
} from 'app/entities/providencia/providencia-inculpado-no-envia-memo.component';
import {
    ProvidenciaInculpadoEnviaMemoComponent,
    ProvidenciaInculpadoEnviaMemoPopupComponent
} from 'app/entities/providencia/providencia-inculpado-envia-memo.component';
import {
    ProvidenciaFiscalFormulaCargosComponent,
    ProvidenciaFiscalFormulaCargosPopupComponent
} from 'app/entities/providencia/providencia-fiscal-formula-cargos.component';
import {
    ProvidenciaFiscalRemiteExpedienteComponent,
    ProvidenciaFiscalRemiteExpedientePopupComponent
} from 'app/entities/providencia/providencia-fiscal-remite-expediente.component';

import {
    ProvidenciaUpdNotificaInculpadoComponent,
    ProvidenciaUpdNotificaInculpadoPopupComponent
} from 'app/entities/providencia/providencia-upd-notifica-inculpado.component';
// directive
import {
    ReadMoreDirective,
} from 'app/entities/providencia/read-more.directive';

const ENTITY_STATES = [...providenciaRoute, ...providenciaPopupRoute];

// @ts-ignore
@NgModule({
    imports: [
        Ng2Rut,
        PdisciplinarioSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        // ,{onSameUrlNavigation: 'reload'}
        AngularMultiSelectModule,
        FormsModule,
        CKEditorModule
    ],
    declarations: [
        ProvidenciaComponent,
        ProvidenciaDetailComponent,
        ProvidenciaDetailDgdpComponent,
        ProvidenciaUpdateComponent,
        ProvidenciaDeleteDialogComponent,
        ProvidenciaResponderDialogComponent,
        ProvidenciaResponderPopupComponent,
        ProvidenciaFiscalAceptaComponent,
        ProvidenciaFiscalAceptaPopupComponent,
        ProvidenciaDeletePopupComponent,
        ProvidenciaDevolverDialogComponent,
        ProvidenciaDevolverPopupComponent,
        MovimientoProvidenciaComponent,
        DerivacionComponent,
        AdjuntoShowListPopupComponent,
        RespuestaBitacoraModalComponent,
        ComentarioBitacoraModalComponent,
        ProvidenciaAsignarNumeroResolucionComponent,
        ProvidenciaAsignarNumeroResolucionPopupComponent,
        ProvidenciaAsignarNumeroReferenciaComponent,
        ProvidenciaAsignarNumeroReferenciaPopupComponent,
        RespuestaUpdateComponent,
        ProvidenciaRelacionadasComponent,
        ProvidenciaRelacionarPopupComponent,
        ProvidenciaRelacionarDialogComponent,
        ProvidenciaAsignarFiscalComponent,
        ProvidenciaAsignarFiscalPopupComponent,
        ProvidenciaAsignarTipoSolicitudComponent,
        ProvidenciaAsignarTipoSolicitudPopupComponent,
        ProvidenciaFiscalRechazaComponent,
        ProvidenciaFiscalRechazaPopupComponent,
        ProvidenciaFiscalProrrogaComponent,
        ProvidenciaFiscalProrrogaPopupComponent,
        ProvidenciaApelaPopupComponent,
        ProvidenciaApelaComponent,
        ProvidenciaNoApelaPopupComponent,
        ProvidenciaNoApelaComponent,
        ProvidenciaFiscalNotificaCierreComponent,
        ProvidenciaFiscalNotificaCierrePopupComponent,
        ProvidenciaInculpadoNoEnviaMemoComponent,
        ProvidenciaInculpadoNoEnviaMemoPopupComponent,
        ProvidenciaInculpadoEnviaMemoComponent,
        ProvidenciaInculpadoEnviaMemoPopupComponent,
        ProvidenciaFiscalRemiteExpedienteComponent,
        ProvidenciaFiscalRemiteExpedientePopupComponent,
        ProvidenciaFiscalFormulaCargosComponent,
        ProvidenciaFiscalFormulaCargosPopupComponent,
        ProvidenciaTerminoProbatorioComponent,
        ProvidenciaTerminoProbatorioPopupComponent,
        ProvidenciaSiDeAcuerdoComponent,
        ProvidenciaSiDeAcuerdoPopupComponent,
        ProvidenciaNoProponeComponent,
        ProvidenciaNoProponePopupComponent,
        ProvidenciaNoReabroComponent,
        ProvidenciaNoReabroPopupComponent,
        ReadMoreDirective,
        ProvidenciaAsignarNumeroFolioComponent,
        ProvidenciaAsignarNumeroFolioPopupComponent,
        ProvidenciaUpdNotificaInculpadoComponent,
        ProvidenciaUpdNotificaInculpadoPopupComponent

    ],
    entryComponents: [
        ProvidenciaComponent,
        ProvidenciaUpdateComponent,
        ProvidenciaDeleteDialogComponent,
        ProvidenciaResponderDialogComponent,
        ProvidenciaResponderPopupComponent,
        ProvidenciaFiscalAceptaComponent,
        ProvidenciaFiscalAceptaPopupComponent,
        ProvidenciaDeletePopupComponent,
        ProvidenciaDevolverDialogComponent,
        ProvidenciaDevolverPopupComponent,
        AdjuntoShowListPopupComponent,
        RespuestaBitacoraModalComponent,
        ComentarioBitacoraModalComponent,
        ProvidenciaAsignarNumeroResolucionComponent,
        ProvidenciaAsignarNumeroResolucionPopupComponent,
        ProvidenciaAsignarNumeroReferenciaComponent,
        ProvidenciaAsignarNumeroReferenciaPopupComponent,
        RespuestaUpdateComponent,
        ProvidenciaRelacionadasComponent,
        ProvidenciaRelacionarPopupComponent,
        ProvidenciaRelacionarDialogComponent,
        ProvidenciaAsignarFiscalComponent,
        ProvidenciaAsignarFiscalPopupComponent,
        ProvidenciaAsignarTipoSolicitudComponent,
        ProvidenciaAsignarTipoSolicitudPopupComponent,
        ProvidenciaFiscalRechazaComponent,
        ProvidenciaFiscalRechazaPopupComponent,
        ProvidenciaFiscalProrrogaComponent,
        ProvidenciaFiscalProrrogaPopupComponent,
        ProvidenciaApelaPopupComponent,
        ProvidenciaApelaComponent,
        ProvidenciaNoApelaPopupComponent,
        ProvidenciaNoApelaComponent,
        ProvidenciaFiscalNotificaCierreComponent,
        ProvidenciaFiscalNotificaCierrePopupComponent,
        ProvidenciaInculpadoNoEnviaMemoComponent,
        ProvidenciaInculpadoNoEnviaMemoPopupComponent,
        ProvidenciaInculpadoEnviaMemoComponent,
        ProvidenciaInculpadoEnviaMemoPopupComponent,
        ProvidenciaFiscalRemiteExpedienteComponent,
        ProvidenciaFiscalRemiteExpedientePopupComponent,
        ProvidenciaFiscalFormulaCargosComponent,
        ProvidenciaFiscalFormulaCargosPopupComponent,
        ProvidenciaTerminoProbatorioComponent,
        ProvidenciaTerminoProbatorioPopupComponent,
        ProvidenciaSiDeAcuerdoComponent,
        ProvidenciaSiDeAcuerdoPopupComponent,
        ProvidenciaNoProponeComponent,
        ProvidenciaNoProponePopupComponent,
        ProvidenciaNoReabroComponent,
        ProvidenciaNoReabroPopupComponent,
        ProvidenciaAsignarNumeroFolioComponent,
        ProvidenciaAsignarNumeroFolioPopupComponent,
        ProvidenciaUpdNotificaInculpadoComponent,
        ProvidenciaUpdNotificaInculpadoPopupComponent

    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioProvidenciaModule {}
