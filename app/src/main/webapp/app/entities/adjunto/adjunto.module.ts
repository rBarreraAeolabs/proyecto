import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    AdjuntoComponent,
    AdjuntoDetailComponent,
    AdjuntoUpdateComponent,
    AdjuntoDeletePopupComponent,
    AdjuntoDeleteDialogComponent,
    adjuntoRoute,
    adjuntoPopupRoute
} from './';

const ENTITY_STATES = [...adjuntoRoute, ...adjuntoPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AdjuntoComponent,
        AdjuntoDetailComponent,
        AdjuntoUpdateComponent,
        AdjuntoDeleteDialogComponent,
        AdjuntoDeletePopupComponent
    ],
    entryComponents: [AdjuntoComponent, AdjuntoUpdateComponent, AdjuntoDeleteDialogComponent, AdjuntoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioAdjuntoModule {}
