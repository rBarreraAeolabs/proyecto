/**
 * Created by sneiraillanes on 22-04-2019.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PdisciplinarioSharedModule } from 'app/shared';
import {AngularMultiSelectModule} from 'angular2-multiselect-dropdown';
import {FormsModule} from '@angular/forms';
import {CKEditorModule} from 'ng2-ckeditor';
import {RespuestaUpdateComponent} from './respuesta-update.component';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';
import {RespuestaBitacoraModalComponent} from 'app/entities/respuesta/respuesta-bitacora-modal.componente';

@NgModule({
    imports: [
        AngularMultiSelectModule,
        FormsModule,
        CKEditorModule,
        BrowserModule,
        CommonModule,
        PdisciplinarioSharedModule
    ],
    exports: [
    ],
    declarations: [
    ],
    entryComponents: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioRespuestaModule {}
