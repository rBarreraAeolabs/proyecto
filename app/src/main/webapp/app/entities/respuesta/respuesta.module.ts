import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PdisciplinarioSharedModule } from 'app/shared';
import {AngularMultiSelectModule} from 'angular2-multiselect-dropdown';
import {FormsModule} from '@angular/forms';
import {CKEditorModule} from 'ng2-ckeditor';
import {CommonModule} from '@angular/common';
import {BrowserModule} from '@angular/platform-browser';

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
