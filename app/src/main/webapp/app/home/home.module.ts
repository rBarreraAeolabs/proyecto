import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {HOME_ROUTE, HomeComponent} from './';
import {PdisciplinarioNotificationsModule} from 'app/notifications/notifications.module';

@NgModule({
    imports: [PdisciplinarioSharedModule, PdisciplinarioNotificationsModule, RouterModule.forChild([HOME_ROUTE])],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioHomeModule {
}
