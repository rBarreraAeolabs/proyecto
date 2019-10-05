import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    // DerivacionComponent,
    DerivacionDetailComponent,
    DerivacionUpdateComponent,
    DerivacionDeletePopupComponent,
    DerivacionDeleteDialogComponent,
    derivacionRoute,
    derivacionPopupRoute
} from './';

const ENTITY_STATES = [...derivacionRoute, ...derivacionPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        // DerivacionComponent,
        DerivacionDetailComponent,
        DerivacionUpdateComponent,
        DerivacionDeleteDialogComponent,
        DerivacionDeletePopupComponent
    ],
    entryComponents: [
        // DerivacionComponent,
        DerivacionUpdateComponent,
        DerivacionDeleteDialogComponent,
        DerivacionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioDerivacionModule {}
