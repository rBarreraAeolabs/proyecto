import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {PdisciplinarioSharedModule} from 'app/shared';
import {DASHBOARD_ROUTE} from 'app/dashboard/dashboard.route';
import {DashboardComponent} from 'app/dashboard/dashboard.component';
import {ChartsModule} from 'ng2-charts';
import {PdisciplinarioNotificationsModule} from 'app/notifications/notifications.module';

@NgModule({
    imports: [
        PdisciplinarioSharedModule,
        PdisciplinarioNotificationsModule,
        ChartsModule,
        RouterModule.forChild([DASHBOARD_ROUTE])],
    declarations: [DashboardComponent]
})
export class PdisciplinarioDashboardModule {
}
