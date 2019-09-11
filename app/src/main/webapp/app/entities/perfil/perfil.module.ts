import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {
    PerfilComponent,
    PerfilDetailComponent,
    PerfilUpdateComponent,
    PerfilDeletePopupComponent,
    PerfilDeleteDialogComponent,
    perfilRoute,
    perfilPopupRoute
} from './';

import {AngularMultiSelectModule} from 'angular2-multiselect-dropdown';

const ENTITY_STATES = [...perfilRoute, ...perfilPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES), AngularMultiSelectModule],
    declarations: [PerfilComponent, PerfilDetailComponent, PerfilUpdateComponent, PerfilDeleteDialogComponent, PerfilDeletePopupComponent],
    entryComponents: [PerfilComponent, PerfilUpdateComponent, PerfilDeleteDialogComponent, PerfilDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioPerfilModule {
}
