import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Plazo} from 'app/shared/model/plazo.model';
import {PlazoService} from './plazo.service';
import {PlazoComponent} from './plazo.component';
import {PlazoDetailComponent} from './plazo-detail.component';
import {PlazoUpdateComponent} from './plazo-update.component';
import {PlazoDeletePopupComponent} from './plazo-delete-dialog.component';
import {IPlazo} from 'app/shared/model/plazo.model';

@Injectable({providedIn: 'root'})
export class PlazoResolve implements Resolve<IPlazo> {
    constructor(private service: PlazoService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((plazo: HttpResponse<Plazo>) => plazo.body));
        }
        return of(new Plazo());
    }
}

export const plazoRoute: Routes = [
    {
        path: 'plazo',
        component: PlazoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: [],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.plazo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plazo/:id/view',
        component: PlazoDetailComponent,
        resolve: {
            plazo: PlazoResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.plazo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plazo/new',
        component: PlazoUpdateComponent,
        resolve: {
            plazo: PlazoResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.plazo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plazo/:id/edit',
        component: PlazoUpdateComponent,
        resolve: {
            plazo: PlazoResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.plazo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plazoPopupRoute: Routes = [
    {
        path: 'plazo/:id/delete',
        component: PlazoDeletePopupComponent,
        resolve: {
            plazo: PlazoResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.plazo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
