import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiPaginationUtil, JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {of} from 'rxjs';
import {map} from 'rxjs/operators';
import {Documento} from 'app/shared/model/documento.model';
import {DocumentoService} from './documento.service';
import {DocumentoComponent} from './documento.component';
import {DocumentoDetailComponent} from './documento-detail.component';
import {DocumentoUpdateComponent} from './documento-update.component';
import {DocumentoDeletePopupComponent} from './documento-delete-dialog.component';
import {IDocumento} from 'app/shared/model/documento.model';

@Injectable({providedIn: 'root'})
export class DocumentoResolve implements Resolve<IDocumento> {
    constructor(private service: DocumentoService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((documento: HttpResponse<Documento>) => documento.body));
        }
        return of(new Documento());
    }
}

export const documentoRoute: Routes = [
    {
        path: 'documento',
        component: DocumentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.documento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento/:id/view',
        component: DocumentoDetailComponent,
        resolve: {
            documento: DocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.documento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento/new',
        component: DocumentoUpdateComponent,
        resolve: {
            documento: DocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.documento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'documento/:id/edit',
        component: DocumentoUpdateComponent,
        resolve: {
            documento: DocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.documento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const documentoPopupRoute: Routes = [
    {
        path: 'documento/:id/delete',
        component: DocumentoDeletePopupComponent,
        resolve: {
            documento: DocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.documento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
