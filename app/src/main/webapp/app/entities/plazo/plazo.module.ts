import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {
    PlazoComponent,
    PlazoDetailComponent,
    PlazoUpdateComponent,
    PlazoDeletePopupComponent,
    PlazoDeleteDialogComponent,
    plazoRoute,
    plazoPopupRoute
} from './';

const ENTITY_STATES = [...plazoRoute, ...plazoPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PlazoComponent, PlazoDetailComponent, PlazoUpdateComponent, PlazoDeleteDialogComponent, PlazoDeletePopupComponent],
    entryComponents: [PlazoComponent, PlazoUpdateComponent, PlazoDeleteDialogComponent, PlazoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioPlazoModule {
}
