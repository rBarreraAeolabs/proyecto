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
// import { ProvidenciaFiscalCierreComponent } from './providencia-fiscal-cierre.component';
import { Ng2Rut } from 'ng2-rut';

import {
    ProvidenciaRelacionarDialogComponent,
    ProvidenciaRelacionarPopupComponent
} from './providencia-relacionar-dialog.component';

import {
    ProvidenciaFiscalCierreComponent,
    ProvidenciaFiscalCierrePopupComponent
} from './providencia-fiscal-cierre.component';

import {
    ProvidenciaAsignarFiscalComponent,
    ProvidenciaAsignarFiscalPopupComponent
} from './providencia-asignar-fiscal.component';
import {
    ProvidenciaAsignarNumeroReferenciaComponent,
    ProvidenciaAsignarNumeroReferenciaPopupComponent
} from 'app/entities/providencia/providencia-asignar-numero-referencia.component';
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
    ProvidenciaNoApelaComponent,
    ProvidenciaNoApelaPopupComponent
} from 'app/entities/providencia/providencia-no-apela.component';

const ENTITY_STATES = [...providenciaRoute, ...providenciaPopupRoute];

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
        ProvidenciaFiscalCierreComponent

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
        ProvidenciaFiscalCierreComponent

    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioProvidenciaModule {}
