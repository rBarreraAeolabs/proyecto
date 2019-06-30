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
import { ProvidenciaResponderDialogComponent, ProvidenciaResponderPopupComponent } from 'app/entities/providencia/providencia-responder-dialog.compoent';
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
    ProvidenciaAsignarFiscalComponent,
    ProvidenciaAsignarFiscalPopupComponent
} from './providencia-asignar-fiscal.component';

const ENTITY_STATES = [...providenciaRoute, ...providenciaPopupRoute];

@NgModule({
    imports: [
        Ng2Rut,
        PdisciplinarioSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        AngularMultiSelectModule,
        FormsModule,
        CKEditorModule
    ],
    declarations: [
        ProvidenciaComponent,
        ProvidenciaDetailComponent,
        ProvidenciaDetailDgdpComponent,
        ProvidenciaAsignarNumeroResolucionComponent,
        ProvidenciaUpdateComponent,
        ProvidenciaDeleteDialogComponent,
        ProvidenciaResponderDialogComponent,
        ProvidenciaResponderPopupComponent,
        ProvidenciaDeletePopupComponent,
        ProvidenciaDevolverDialogComponent,
        ProvidenciaDevolverPopupComponent,
        MovimientoProvidenciaComponent,
        DerivacionComponent,
        AdjuntoShowListPopupComponent,
        RespuestaBitacoraModalComponent,
        ComentarioBitacoraModalComponent,
        ProvidenciaAsignarNumeroResolucionPopupComponent,
        RespuestaUpdateComponent,
        ProvidenciaRelacionadasComponent,
        ProvidenciaRelacionarPopupComponent,
        ProvidenciaRelacionarDialogComponent,
        ProvidenciaAsignarFiscalComponent,
        ProvidenciaAsignarFiscalPopupComponent
    ],
    entryComponents: [
        ProvidenciaComponent,
        ProvidenciaUpdateComponent,
        ProvidenciaDeleteDialogComponent,
        ProvidenciaResponderDialogComponent,
        ProvidenciaResponderPopupComponent,
        ProvidenciaDeletePopupComponent,
        ProvidenciaDevolverDialogComponent,
        ProvidenciaDevolverPopupComponent,
        AdjuntoShowListPopupComponent,
        RespuestaBitacoraModalComponent,
        ComentarioBitacoraModalComponent,
        ProvidenciaAsignarNumeroResolucionComponent,
        ProvidenciaAsignarNumeroResolucionPopupComponent,
        RespuestaUpdateComponent,
        ProvidenciaRelacionadasComponent,
        ProvidenciaRelacionarPopupComponent,
        ProvidenciaRelacionarDialogComponent,
        ProvidenciaAsignarFiscalComponent,
        ProvidenciaAsignarFiscalPopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioProvidenciaModule {}
