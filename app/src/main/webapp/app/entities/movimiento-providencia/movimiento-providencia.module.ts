import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {
    MovimientoProvidenciaComponent,
    MovimientoProvidenciaDetailComponent,
    MovimientoProvidenciaUpdateComponent,
    MovimientoProvidenciaDeletePopupComponent,
    MovimientoProvidenciaDeleteDialogComponent,
    movimientoProvidenciaRoute,
    movimientoProvidenciaPopupRoute
} from './';

const ENTITY_STATES = [...movimientoProvidenciaRoute, ...movimientoProvidenciaPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MovimientoProvidenciaDetailComponent,
        MovimientoProvidenciaUpdateComponent,
        MovimientoProvidenciaDeleteDialogComponent,
        MovimientoProvidenciaDeletePopupComponent
    ],
    entryComponents: [
        MovimientoProvidenciaUpdateComponent,
        MovimientoProvidenciaDeleteDialogComponent,
        MovimientoProvidenciaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioMovimientoProvidenciaModule {
}
