import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SumarioAdministrativo } from 'app/shared/model/sumario-administrativo.model';
import { SumarioAdministrativoService } from './sumario-administrativo.service';
import { SumarioAdministrativoComponent } from './sumario-administrativo.component';
import { SumarioAdministrativoDetailComponent } from './sumario-administrativo-detail.component';
import { SumarioAdministrativoUpdateComponent } from './sumario-administrativo-update.component';
import { SumarioAdministrativoDeletePopupComponent } from './sumario-administrativo-delete-dialog.component';
import { ISumarioAdministrativo } from 'app/shared/model/sumario-administrativo.model';

@Injectable({ providedIn: 'root' })
export class SumarioAdministrativoResolve implements Resolve<ISumarioAdministrativo> {
    constructor(private service: SumarioAdministrativoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((sumarioAdministrativo: HttpResponse<SumarioAdministrativo>) => sumarioAdministrativo.body));
        }
        return of(new SumarioAdministrativo());
    }
}

export const sumarioAdministrativoRoute: Routes = [
    {
        path: 'sumario-administrativo',
        component: SumarioAdministrativoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.sumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sumario-administrativo/:id/view',
        component: SumarioAdministrativoDetailComponent,
        resolve: {
            sumarioAdministrativo: SumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.sumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sumario-administrativo/new',
        component: SumarioAdministrativoUpdateComponent,
        resolve: {
            sumarioAdministrativo: SumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.sumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sumario-administrativo/:id/edit',
        component: SumarioAdministrativoUpdateComponent,
        resolve: {
            sumarioAdministrativo: SumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.sumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sumarioAdministrativoPopupRoute: Routes = [
    {
        path: 'sumario-administrativo/:id/delete',
        component: SumarioAdministrativoDeletePopupComponent,
        resolve: {
            sumarioAdministrativo: SumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.sumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
