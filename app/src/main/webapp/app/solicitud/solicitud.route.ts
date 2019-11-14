import { Route } from '@angular/router';
import { SolicitudComponent } from 'app/solicitud/solicitud.component';
import { UserRouteAccessService } from 'app/core';

export const SOLICITUD_ROUTE: Route = {
    path: 'solicitud',
    component: SolicitudComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Registro Disciplinario'
    },
    canActivate: [UserRouteAccessService]
};
