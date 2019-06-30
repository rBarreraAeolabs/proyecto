import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { auditsRoute } from './audits/audits.route';
import { configurationRoute } from './configuration/configuration.route';
import { docsRoute } from './docs/docs.route';
import { healthRoute } from './health/health.route';
import { metricsRoute } from './metrics/metrics.route';
import { userMgmtRoute } from './user-management/user-management.route';
import { logsRoute } from './logs/logs.route';

const ADMIN_ROUTES = [auditsRoute, configurationRoute, docsRoute, healthRoute, logsRoute, ...userMgmtRoute, metricsRoute];

export const adminState: Routes = [
    {
        path: '',
        data: {
            authorities: ['ROLE_ADMIN', 'CREAR_USUARIO_PRIVILEGE']
        },
        canActivate: [UserRouteAccessService],
        children: ADMIN_ROUTES
    }
];
