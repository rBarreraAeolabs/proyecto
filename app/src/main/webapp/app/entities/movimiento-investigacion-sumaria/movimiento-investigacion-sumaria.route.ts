import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MovimientoInvestigacionSumaria } from 'app/shared/model/movimiento-investigacion-sumaria.model';
import { MovimientoInvestigacionSumariaService } from './movimiento-investigacion-sumaria.service';
import { MovimientoInvestigacionSumariaComponent } from './movimiento-investigacion-sumaria.component';
import { MovimientoInvestigacionSumariaDetailComponent } from './movimiento-investigacion-sumaria-detail.component';
import { MovimientoInvestigacionSumariaUpdateComponent } from './movimiento-investigacion-sumaria-update.component';
import { MovimientoInvestigacionSumariaDeletePopupComponent } from './movimiento-investigacion-sumaria-delete-dialog.component';
import { IMovimientoInvestigacionSumaria } from 'app/shared/model/movimiento-investigacion-sumaria.model';

@Injectable({ providedIn: 'root' })
export class MovimientoInvestigacionSumariaResolve implements Resolve<IMovimientoInvestigacionSumaria> {
    constructor(private service: MovimientoInvestigacionSumariaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(
                    map(
                        (movimientoInvestigacionSumaria: HttpResponse<MovimientoInvestigacionSumaria>) =>
                            movimientoInvestigacionSumaria.body
                    )
                );
        }
        return of(new MovimientoInvestigacionSumaria());
    }
}

export const movimientoInvestigacionSumariaRoute: Routes = [
    {
        path: 'movimiento-investigacion-sumaria',
        component: MovimientoInvestigacionSumariaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.movimientoInvestigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-investigacion-sumaria/:id/view',
        component: MovimientoInvestigacionSumariaDetailComponent,
        resolve: {
            movimientoInvestigacionSumaria: MovimientoInvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoInvestigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-investigacion-sumaria/new',
        component: MovimientoInvestigacionSumariaUpdateComponent,
        resolve: {
            movimientoInvestigacionSumaria: MovimientoInvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoInvestigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-investigacion-sumaria/:id/edit',
        component: MovimientoInvestigacionSumariaUpdateComponent,
        resolve: {
            movimientoInvestigacionSumaria: MovimientoInvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoInvestigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const movimientoInvestigacionSumariaPopupRoute: Routes = [
    {
        path: 'movimiento-investigacion-sumaria/:id/delete',
        component: MovimientoInvestigacionSumariaDeletePopupComponent,
        resolve: {
            movimientoInvestigacionSumaria: MovimientoInvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoInvestigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
