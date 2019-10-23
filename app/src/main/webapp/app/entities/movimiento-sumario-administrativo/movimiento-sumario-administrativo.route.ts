import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MovimientoSumarioAdministrativo } from 'app/shared/model/movimiento-sumario-administrativo.model';
import { MovimientoSumarioAdministrativoService } from './movimiento-sumario-administrativo.service';
import { MovimientoSumarioAdministrativoComponent } from './movimiento-sumario-administrativo.component';
import { MovimientoSumarioAdministrativoDetailComponent } from './movimiento-sumario-administrativo-detail.component';
import { MovimientoSumarioAdministrativoUpdateComponent } from './movimiento-sumario-administrativo-update.component';
import { MovimientoSumarioAdministrativoDeletePopupComponent } from './movimiento-sumario-administrativo-delete-dialog.component';
import { IMovimientoSumarioAdministrativo } from 'app/shared/model/movimiento-sumario-administrativo.model';

@Injectable({ providedIn: 'root' })
export class MovimientoSumarioAdministrativoResolve implements Resolve<IMovimientoSumarioAdministrativo> {
    constructor(private service: MovimientoSumarioAdministrativoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (movimientoSumarioAdministrativo: HttpResponse<MovimientoSumarioAdministrativo>) =>
                            movimientoSumarioAdministrativo.body
                    )
                );
        }
        return of(new MovimientoSumarioAdministrativo());
    }
}

export const movimientoSumarioAdministrativoRoute: Routes = [
    {
        path: 'movimiento-sumario-administrativo',
        component: MovimientoSumarioAdministrativoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.movimientoSumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-sumario-administrativo/:id/view',
        component: MovimientoSumarioAdministrativoDetailComponent,
        resolve: {
            movimientoSumarioAdministrativo: MovimientoSumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoSumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-sumario-administrativo/new',
        component: MovimientoSumarioAdministrativoUpdateComponent,
        resolve: {
            movimientoSumarioAdministrativo: MovimientoSumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoSumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-sumario-administrativo/:id/edit',
        component: MovimientoSumarioAdministrativoUpdateComponent,
        resolve: {
            movimientoSumarioAdministrativo: MovimientoSumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoSumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const movimientoSumarioAdministrativoPopupRoute: Routes = [
    {
        path: 'movimiento-sumario-administrativo/:id/delete',
        component: MovimientoSumarioAdministrativoDeletePopupComponent,
        resolve: {
            movimientoSumarioAdministrativo: MovimientoSumarioAdministrativoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoSumarioAdministrativo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
