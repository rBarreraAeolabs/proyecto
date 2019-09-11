import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil, JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Derivacion} from 'app/shared/model/derivacion.model';
import {DerivacionService} from './derivacion.service';
import {DerivacionComponent} from './derivacion.component';
import {DerivacionDetailComponent} from './derivacion-detail.component';
import {DerivacionUpdateComponent} from './derivacion-update.component';
import {DerivacionDeletePopupComponent} from './derivacion-delete-dialog.component';
import {IDerivacion} from 'app/shared/model/derivacion.model';

@Injectable({providedIn: 'root'})
export class DerivacionResolve implements Resolve<IDerivacion> {
    constructor(private service: DerivacionService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((derivacion: HttpResponse<IDerivacion>) => derivacion.body));
        }
        return of(new Derivacion());
    }
}

export const derivacionRoute: Routes = [
    {
        path: 'derivacion',
        component: DerivacionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.derivacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'derivacion/:id/view',
        component: DerivacionDetailComponent,
        resolve: {
            derivacion: DerivacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.derivacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'derivacion/new',
        component: DerivacionUpdateComponent,
        resolve: {
            derivacion: DerivacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.derivacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'derivacion/:id/edit',
        component: DerivacionUpdateComponent,
        resolve: {
            derivacion: DerivacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.derivacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const derivacionPopupRoute: Routes = [
    {
        path: 'derivacion/:id/delete',
        component: DerivacionDeletePopupComponent,
        resolve: {
            derivacion: DerivacionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.derivacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
