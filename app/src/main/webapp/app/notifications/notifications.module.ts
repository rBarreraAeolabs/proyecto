import { NOTIFICACION_ROUTE } from 'app/notifications/notifications.route';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { NotificationsComponent } from 'app/notifications/notifications.component';
import { RouterModule } from '@angular/router';
import { PdisciplinarioSharedModule } from 'app/shared';

@NgModule({
    imports: [PdisciplinarioSharedModule, RouterModule.forChild([NOTIFICACION_ROUTE])],
    declarations: [NotificationsComponent],
    exports: [NotificationsComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioNotificationsModule {}
