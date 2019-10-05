import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    FichaIngresoSdjComponent,
    FichaIngresoSdjDetailComponent,
    FichaIngresoSdjUpdateComponent,
    FichaIngresoSdjDeletePopupComponent,
    FichaIngresoSdjDeleteDialogComponent,
    fichaIngresoSdjRoute,
    fichaIngresoSdjPopupRoute
} from './';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';

const ENTITY_STATES = [...fichaIngresoSdjRoute, ...fichaIngresoSdjPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES), AngularMultiSelectModule],
    declarations: [
        FichaIngresoSdjComponent,
        FichaIngresoSdjDetailComponent,
        FichaIngresoSdjUpdateComponent,
        FichaIngresoSdjDeleteDialogComponent,
        FichaIngresoSdjDeletePopupComponent
    ],
    entryComponents: [
        FichaIngresoSdjComponent,
        FichaIngresoSdjUpdateComponent,
        FichaIngresoSdjDeleteDialogComponent,
        FichaIngresoSdjDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioFichaIngresoSdjModule {}
