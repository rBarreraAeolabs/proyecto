import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {
    MovimientoSumarioAdministrativoComponent,
    MovimientoSumarioAdministrativoDetailComponent,
    MovimientoSumarioAdministrativoUpdateComponent,
    MovimientoSumarioAdministrativoDeletePopupComponent,
    MovimientoSumarioAdministrativoDeleteDialogComponent,
    movimientoSumarioAdministrativoRoute,
    movimientoSumarioAdministrativoPopupRoute
} from './';

const ENTITY_STATES = [...movimientoSumarioAdministrativoRoute, ...movimientoSumarioAdministrativoPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MovimientoSumarioAdministrativoComponent,
        MovimientoSumarioAdministrativoDetailComponent,
        MovimientoSumarioAdministrativoUpdateComponent,
        MovimientoSumarioAdministrativoDeleteDialogComponent,
        MovimientoSumarioAdministrativoDeletePopupComponent
    ],
    entryComponents: [
        MovimientoSumarioAdministrativoComponent,
        MovimientoSumarioAdministrativoUpdateComponent,
        MovimientoSumarioAdministrativoDeleteDialogComponent,
        MovimientoSumarioAdministrativoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioMovimientoSumarioAdministrativoModule {
}
