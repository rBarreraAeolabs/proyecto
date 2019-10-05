import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { MovimientoProvidenciaService } from './movimiento-providencia.service';
import { MovimientoProvidenciaComponent } from './movimiento-providencia.component';
import { MovimientoProvidenciaDetailComponent } from './movimiento-providencia-detail.component';
import { MovimientoProvidenciaUpdateComponent } from './movimiento-providencia-update.component';
import { MovimientoProvidenciaDeletePopupComponent } from './movimiento-providencia-delete-dialog.component';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';

@Injectable({ providedIn: 'root' })
export class MovimientoProvidenciaResolve implements Resolve<IMovimientoProvidencia> {
    constructor(private service: MovimientoProvidenciaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((movimientoProvidencia: HttpResponse<MovimientoProvidencia>) => movimientoProvidencia.body));
        }
        return of(new MovimientoProvidencia());
    }
}

export const movimientoProvidenciaRoute: Routes = [
    {
        path: 'movimiento-providencia',
        component: MovimientoProvidenciaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PROVIDENCIA'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.movimientoProvidencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-providencia/:id/view',
        component: MovimientoProvidenciaDetailComponent,
        resolve: {
            movimientoProvidencia: MovimientoProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PROVIDENCIA'],
            pageTitle: 'pdisciplinarioApp.movimientoProvidencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-providencia/new',
        component: MovimientoProvidenciaUpdateComponent,
        resolve: {
            movimientoProvidencia: MovimientoProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'CREAR_PROVIDENCIA', 'EDITAR_PROVIDENCIA'],
            pageTitle: 'pdisciplinarioApp.movimientoProvidencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'movimiento-providencia/:id/edit',
        component: MovimientoProvidenciaUpdateComponent,
        resolve: {
            movimientoProvidencia: MovimientoProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoProvidencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const movimientoProvidenciaPopupRoute: Routes = [
    {
        path: 'movimiento-providencia/:id/delete',
        component: MovimientoProvidenciaDeletePopupComponent,
        resolve: {
            movimientoProvidencia: MovimientoProvidenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.movimientoProvidencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
