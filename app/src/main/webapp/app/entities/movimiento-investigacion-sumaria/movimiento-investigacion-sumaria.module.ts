import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    MovimientoInvestigacionSumariaComponent,
    MovimientoInvestigacionSumariaDetailComponent,
    MovimientoInvestigacionSumariaUpdateComponent,
    MovimientoInvestigacionSumariaDeletePopupComponent,
    MovimientoInvestigacionSumariaDeleteDialogComponent,
    movimientoInvestigacionSumariaRoute,
    movimientoInvestigacionSumariaPopupRoute
} from './';

const ENTITY_STATES = [...movimientoInvestigacionSumariaRoute, ...movimientoInvestigacionSumariaPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MovimientoInvestigacionSumariaComponent,
        MovimientoInvestigacionSumariaDetailComponent,
        MovimientoInvestigacionSumariaUpdateComponent,
        MovimientoInvestigacionSumariaDeleteDialogComponent,
        MovimientoInvestigacionSumariaDeletePopupComponent
    ],
    entryComponents: [
        MovimientoInvestigacionSumariaComponent,
        MovimientoInvestigacionSumariaUpdateComponent,
        MovimientoInvestigacionSumariaDeleteDialogComponent,
        MovimientoInvestigacionSumariaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioMovimientoInvestigacionSumariaModule {}
