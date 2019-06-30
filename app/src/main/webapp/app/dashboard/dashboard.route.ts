import { Route } from '@angular/router';
import { DashboardComponent } from 'app/dashboard/dashboard.component';
import { UserRouteAccessService } from 'app/core';

export const DASHBOARD_ROUTE: Route = {
    path: 'dashboard',
    component: DashboardComponent,
    data: {
        authorities: ['ROLE_USER', 'VISUALIZAR_DASHBOARD_PRIVILEGE'],
        pageTitle: 'pdisciplinarioApp.dashboard.title'
    },
    canActivate: [UserRouteAccessService]
};
