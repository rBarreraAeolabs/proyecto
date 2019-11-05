import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { FichaIngresoSdj } from 'app/shared/model/ficha-ingreso-sdj.model';
import { FichaIngresoSdjService } from './ficha-ingreso-sdj.service';
import { FichaIngresoSdjComponent } from './ficha-ingreso-sdj.component';
import { FichaIngresoSdjDetailComponent } from './ficha-ingreso-sdj-detail.component';
import { FichaIngresoSdjUpdateComponent } from './ficha-ingreso-sdj-update.component';
import { FichaIngresoSdjDeletePopupComponent } from './ficha-ingreso-sdj-delete-dialog.component';
import { IFichaIngresoSdj } from 'app/shared/model/ficha-ingreso-sdj.model';

@Injectable({ providedIn: 'root' })
export class FichaIngresoSdjResolve implements Resolve<IFichaIngresoSdj> {
    constructor(private service: FichaIngresoSdjService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((fichaIngresoSdj: HttpResponse<FichaIngresoSdj>) => fichaIngresoSdj.body));
        }
        return of(new FichaIngresoSdj());
    }
}

export const fichaIngresoSdjRoute: Routes = [
    {
        path: 'ficha-ingreso-sdj',
        component: FichaIngresoSdjComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_FICHA_INGRESO'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.fichaIngresoSdj.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ficha-ingreso-sdj/:id/view',
        component: FichaIngresoSdjDetailComponent,
        resolve: {
            fichaIngresoSdj: FichaIngresoSdjResolve
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_FICHA_INGRESO'],
            pageTitle: 'pdisciplinarioApp.fichaIngresoSdj.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ficha-ingreso-sdj/new',
        component: FichaIngresoSdjUpdateComponent,
        resolve: {
            fichaIngresoSdj: FichaIngresoSdjResolve
        },
        data: {
            authorities: ['ROLE_USER', 'CREAR_FICHA_INGRESO'],
            pageTitle: 'pdisciplinarioApp.fichaIngresoSdj.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ficha-ingreso-sdj/:id/edit',
        component: FichaIngresoSdjUpdateComponent,
        resolve: {
            fichaIngresoSdj: FichaIngresoSdjResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.fichaIngresoSdj.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fichaIngresoSdjPopupRoute: Routes = [
    {
        path: 'ficha-ingreso-sdj/:id/delete',
        component: FichaIngresoSdjDeletePopupComponent,
        resolve: {
            fichaIngresoSdj: FichaIngresoSdjResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.fichaIngresoSdj.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
