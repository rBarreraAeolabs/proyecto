import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { map } from 'rxjs/operators';
import { HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { InstruccionesComponent } from './instrucciones.component';
import { IInstrucciones, Instrucciones } from 'app/shared/model/Instrucciones.model';
import { InstruccionesService } from './instrucciones.service';
import { InstruccionesDetailComponent } from './instrucciones-detail.component';
import { InstruccionesUpdateComponent } from './instrucciones-update.component';
import { InstruccionesDeletePopupComponent } from './instrucciones-delete-dialog.component';


@Injectable({ providedIn: 'root' })
export class InstruccionesResolve implements Resolve<IInstrucciones> {
    constructor(private service: InstruccionesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((instrucciones: HttpResponse<Instrucciones>) => instrucciones.body));
        }
        return of(new Instrucciones());
    }
}

export const instruccionesRoute: Routes = [
    {
        path: 'instrucciones',
        component: InstruccionesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_INSTRUCCIONES'],
            defaultSort: 'id,asc',
                pageTitle: 'pdisciplinarioApp.instrucciones.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instrucciones/:id/view',
        component: InstruccionesDetailComponent,
        resolve: {
            instrucciones: InstruccionesResolve
        },
        data: {
            authorities: ['ROLE_USER', 'VISUALIZAR_INSTRUCCIONES'],
            pageTitle: 'pdisciplinarioApp.instrucciones.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instrucciones/new',
        component: InstruccionesUpdateComponent,
        resolve: {
            instrucciones: InstruccionesResolve
        },
        data: {
            authorities: ['ROLE_USER', 'CREAR_INSTRUCCION'],
                pageTitle: 'pdisciplinarioApp.instrucciones.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instrucciones/:id/edit',
        component: InstruccionesUpdateComponent,
        resolve: {
            instrucciones: InstruccionesResolve
        },
        data: {
            authorities: ['ROLE_USER', 'EDITAR_INSTRUCCIONES'],
            pageTitle: 'pdisciplinarioApp.instrucciones.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const instruccionesPopupRoute: Routes = [
    {
        path: 'instrucciones/:id/delete',
        component: InstruccionesDeletePopupComponent,
        resolve: {
            instrucciones: InstruccionesResolve
        },
        data: {
            authorities: ['ROLE_USER', 'ELIMINAR_INSTRUCCION'],
            pageTitle: 'pdisciplinarioApp.intrucciones.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
