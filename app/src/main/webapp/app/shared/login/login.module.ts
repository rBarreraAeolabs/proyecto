import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PdisciplinarioSharedModule } from 'app/shared';
import { LOGIN_ROUTE } from 'app/shared/login/login.route';

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild([LOGIN_ROUTE])],
    declarations: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioLoginModule {}
