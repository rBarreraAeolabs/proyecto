import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {
    PlantillaComponent,
    PlantillaDetailComponent,
    PlantillaUpdateComponent,
    PlantillaDeletePopupComponent,
    PlantillaDeleteDialogComponent,
    plantillaRoute,
    plantillaPopupRoute
} from './';
import {CKEditorModule} from 'ng2-ckeditor';

const ENTITY_STATES = [...plantillaRoute, ...plantillaPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES), CKEditorModule],
    declarations: [
        PlantillaComponent,
        PlantillaDetailComponent,
        PlantillaUpdateComponent,
        PlantillaDeleteDialogComponent,
        PlantillaDeletePopupComponent
    ],
    entryComponents: [PlantillaComponent, PlantillaUpdateComponent, PlantillaDeleteDialogComponent, PlantillaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioPlantillaModule {
}
