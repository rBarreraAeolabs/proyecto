import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    SumarioAdministrativoComponent,
    SumarioAdministrativoDetailComponent,
    SumarioAdministrativoUpdateComponent,
    SumarioAdministrativoDeletePopupComponent,
    SumarioAdministrativoDeleteDialogComponent,
    sumarioAdministrativoRoute,
    sumarioAdministrativoPopupRoute
} from './';

const ENTITY_STATES = [...sumarioAdministrativoRoute, ...sumarioAdministrativoPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SumarioAdministrativoComponent,
        SumarioAdministrativoDetailComponent,
        SumarioAdministrativoUpdateComponent,
        SumarioAdministrativoDeleteDialogComponent,
        SumarioAdministrativoDeletePopupComponent
    ],
    entryComponents: [
        SumarioAdministrativoComponent,
        SumarioAdministrativoUpdateComponent,
        SumarioAdministrativoDeleteDialogComponent,
        SumarioAdministrativoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioSumarioAdministrativoModule {}
