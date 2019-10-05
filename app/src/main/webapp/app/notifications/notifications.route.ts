import { Route } from '@angular/router';
import { NotificationsComponent } from 'app/notifications/notifications.component';

export const NOTIFICACION_ROUTE: Route = {
    path: 'notificaciones',
    component: NotificationsComponent,
    data: {
        authorities: [],
        pageTitle: 'notificacion.title'
    }
};
