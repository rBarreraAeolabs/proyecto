import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute, navbarRoute } from './layouts';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
    imports: [
        RouterModule.forRoot(
            [
                ...LAYOUT_ROUTES,
                {
                    path: 'admin',
                    loadChildren: './admin/admin.module#PdisciplinarioAdminModule'
                }
            ],
            // { useHash: true, enableTracing: DEBUG_INFO_ENABLED }
            { useHash: true }
        )
    ],
    exports: [RouterModule]
})
export class PdisciplinarioAppRoutingModule {}
