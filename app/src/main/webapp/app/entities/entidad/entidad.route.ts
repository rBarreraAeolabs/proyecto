import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {map} from 'rxjs/operators';
import {HttpResponse} from '@angular/common/http';
import {of} from 'rxjs';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {EntidadComponent} from 'app/entities/entidad/entidad.component';
import {Entidad, IEntidad} from 'app/shared/model/entidad.model';
import {EntidadService} from 'app/entities/entidad/entidad.service';
import {EntidadDetailComponent} from 'app/entities/entidad/entidad-detail.component';
import {EntidadUpdateComponent} from 'app/entities/entidad/entidad-update.component';
import {EntidadDeletePopupComponent} from 'app/entities/entidad/entidad-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class EntidadResolve implements Resolve<IEntidad> {
    constructor(private service: EntidadService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((entidad: HttpResponse<Entidad>) => entidad.body));
        }
        return of(new Entidad());
    }
}

export const entidadRoute: Routes = [
    {
        path: 'entidad',
        component: EntidadComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: [],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.entidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entidad/:id/view',
        component: EntidadDetailComponent,
        resolve: {
            entidad: EntidadResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.entidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entidad/new',
        component: EntidadUpdateComponent,
        resolve: {
            entidad: EntidadResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.entidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'entidad/:id/edit',
        component: EntidadUpdateComponent,
        resolve: {
            entidad: EntidadResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.entidad.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const entidadPopupRoute: Routes = [
    {
        path: 'entidad/:id/delete',
        component: EntidadDeletePopupComponent,
        resolve: {
            entidad: EntidadResolve
        },
        data: {
            authorities: [],
            pageTitle: 'pdisciplinarioApp.entidad.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
