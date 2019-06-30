import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import { SOLICITUD_ROUTE } from 'app/solicitud/solicitud.route';
import { SolicitudComponent } from 'app/solicitud/solicitud.component';

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild([SOLICITUD_ROUTE])],
    declarations: [SolicitudComponent]
})
export class PdisciplinarioSolicitudModule {}
