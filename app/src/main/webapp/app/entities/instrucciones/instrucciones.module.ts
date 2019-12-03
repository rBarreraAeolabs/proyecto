import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { PdisciplinarioSharedModule } from 'app/shared';
import { instruccionesPopupRoute, instruccionesRoute } from 'app/entities/instrucciones/instrucciones.route';
import { InstruccionesComponent } from 'app/entities/instrucciones/instrucciones.component';
import { InstruccionesDetailComponent } from 'app/entities/instrucciones/instrucciones-detail.component';
import { InstruccionesDeleteDialogComponent, InstruccionesDeletePopupComponent } from 'app/entities/instrucciones/instrucciones-delete-dialog.component';
import {InstruccionesUpdateComponent } from 'app/entities/instrucciones/instrucciones-update.component';
const ENTITY_STATES = [...instruccionesRoute, ...instruccionesPopupRoute];

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InstruccionesComponent,
        InstruccionesDetailComponent,
        InstruccionesUpdateComponent,
        InstruccionesDeleteDialogComponent,
        InstruccionesDeletePopupComponent,
    ],
    entryComponents: [InstruccionesComponent, InstruccionesUpdateComponent, InstruccionesDeleteDialogComponent, InstruccionesDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioInstruccionesModule {}
