import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Plantilla } from 'app/shared/model/plantilla.model';
import { PlantillaService } from './plantilla.service';
import { PlantillaComponent } from './plantilla.component';
import { PlantillaDetailComponent } from './plantilla-detail.component';
import { PlantillaUpdateComponent } from './plantilla-update.component';
import { PlantillaDeletePopupComponent } from './plantilla-delete-dialog.component';
import { IPlantilla } from 'app/shared/model/plantilla.model';

@Injectable({ providedIn: 'root' })
export class PlantillaResolve implements Resolve<IPlantilla> {
    constructor(private service: PlantillaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((plantilla: HttpResponse<Plantilla>) => plantilla.body));
        }
        return of(new Plantilla());
    }
}

export const plantillaRoute: Routes = [
    {
        path: 'plantilla',
        component: PlantillaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PLANTILLA'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.plantilla.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plantilla/:id/view',
        component: PlantillaDetailComponent,
        resolve: {
            plantilla: PlantillaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_PLANTILLA'],
            pageTitle: 'pdisciplinarioApp.plantilla.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plantilla/new',
        component: PlantillaUpdateComponent,
        resolve: {
            plantilla: PlantillaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'CREAR_PLANTILLA'],
            pageTitle: 'pdisciplinarioApp.plantilla.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plantilla/:id/edit',
        component: PlantillaUpdateComponent,
        resolve: {
            plantilla: PlantillaResolve
        },
        data: {
            authorities: ['ROLE_USER', 'EDITAR_PLANTILLA'],
            pageTitle: 'pdisciplinarioApp.plantilla.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantillaPopupRoute: Routes = [
    {
        path: 'plantilla/:id/delete',
        component: PlantillaDeletePopupComponent,
        resolve: {
            plantilla: PlantillaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.plantilla.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
