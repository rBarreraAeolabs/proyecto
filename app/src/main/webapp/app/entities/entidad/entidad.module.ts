import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PdisciplinarioSharedModule } from 'app/shared';
import { entidadPopupRoute, entidadRoute } from 'app/entities/entidad/entidad.route';
import { EntidadComponent } from 'app/entities/entidad/entidad.component';
import { EntidadDetailComponent } from 'app/entities/entidad/entidad-detail.component';
import { EntidadDeleteDialogComponent, EntidadDeletePopupComponent } from 'app/entities/entidad/entidad-delete-dialog.component';
import {EntidadUpdateComponent } from 'app/entities/entidad/entidad-update.component';
const ENTITY_STATES = [...entidadRoute, ...entidadPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EntidadComponent,
        EntidadDetailComponent,
        EntidadUpdateComponent,
        EntidadDeleteDialogComponent,
        EntidadDeletePopupComponent,
    ],
    entryComponents: [EntidadComponent, EntidadUpdateComponent, EntidadDeleteDialogComponent, EntidadDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioEntidadModule {}
