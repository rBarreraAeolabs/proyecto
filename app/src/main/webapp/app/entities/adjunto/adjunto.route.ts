import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Adjunto } from 'app/shared/model/adjunto.model';
import { AdjuntoService } from './adjunto.service';
import { AdjuntoComponent } from './adjunto.component';
import { AdjuntoDetailComponent } from './adjunto-detail.component';
import { AdjuntoUpdateComponent } from './adjunto-update.component';
import { AdjuntoDeletePopupComponent } from './adjunto-delete-dialog.component';
import { IAdjunto } from 'app/shared/model/adjunto.model';

@Injectable({ providedIn: 'root' })
export class AdjuntoResolve implements Resolve<IAdjunto> {
    constructor(private service: AdjuntoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((adjunto: HttpResponse<Adjunto>) => adjunto.body));
        }
        return of(new Adjunto());
    }
}

export const adjuntoRoute: Routes = [
    {
        path: 'adjunto',
        component: AdjuntoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.adjunto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adjunto/:id/view',
        component: AdjuntoDetailComponent,
        resolve: {
            adjunto: AdjuntoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.adjunto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adjunto/new',
        component: AdjuntoUpdateComponent,
        resolve: {
            adjunto: AdjuntoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.adjunto.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'adjunto/:id/edit',
        component: AdjuntoUpdateComponent,
        resolve: {
            adjunto: AdjuntoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.adjunto.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adjuntoPopupRoute: Routes = [
    {
        path: 'adjunto/:id/delete',
        component: AdjuntoDeletePopupComponent,
        resolve: {
            adjunto: AdjuntoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.adjunto.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }

];
