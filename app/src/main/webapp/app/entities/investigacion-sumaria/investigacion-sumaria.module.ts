import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import {
    InvestigacionSumariaComponent,
    InvestigacionSumariaDetailComponent,
    InvestigacionSumariaUpdateComponent,
    InvestigacionSumariaDeletePopupComponent,
    InvestigacionSumariaDeleteDialogComponent,
    investigacionSumariaRoute,
    investigacionSumariaPopupRoute
} from './';

const ENTITY_STATES = [...investigacionSumariaRoute, ...investigacionSumariaPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InvestigacionSumariaComponent,
        InvestigacionSumariaDetailComponent,
        InvestigacionSumariaUpdateComponent,
        InvestigacionSumariaDeleteDialogComponent,
        InvestigacionSumariaDeletePopupComponent
    ],
    entryComponents: [
        InvestigacionSumariaComponent,
        InvestigacionSumariaUpdateComponent,
        InvestigacionSumariaDeleteDialogComponent,
        InvestigacionSumariaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioInvestigacionSumariaModule {}
