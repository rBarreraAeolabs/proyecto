import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { InvestigacionSumaria } from 'app/shared/model/investigacion-sumaria.model';
import { InvestigacionSumariaService } from './investigacion-sumaria.service';
import { InvestigacionSumariaComponent } from './investigacion-sumaria.component';
import { InvestigacionSumariaDetailComponent } from './investigacion-sumaria-detail.component';
import { InvestigacionSumariaUpdateComponent } from './investigacion-sumaria-update.component';
import { InvestigacionSumariaDeletePopupComponent } from './investigacion-sumaria-delete-dialog.component';
import { IInvestigacionSumaria } from 'app/shared/model/investigacion-sumaria.model';

@Injectable({ providedIn: 'root' })
export class InvestigacionSumariaResolve implements Resolve<IInvestigacionSumaria> {
    constructor(private service: InvestigacionSumariaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((investigacionSumaria: HttpResponse<InvestigacionSumaria>) => investigacionSumaria.body));
        }
        return of(new InvestigacionSumaria());
    }
}

export const investigacionSumariaRoute: Routes = [
    {
        path: 'investigacion-sumaria',
        component: InvestigacionSumariaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'pdisciplinarioApp.investigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'investigacion-sumaria/:id/view',
        component: InvestigacionSumariaDetailComponent,
        resolve: {
            investigacionSumaria: InvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.investigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'investigacion-sumaria/new',
        component: InvestigacionSumariaUpdateComponent,
        resolve: {
            investigacionSumaria: InvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.investigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'investigacion-sumaria/:id/edit',
        component: InvestigacionSumariaUpdateComponent,
        resolve: {
            investigacionSumaria: InvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.investigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const investigacionSumariaPopupRoute: Routes = [
    {
        path: 'investigacion-sumaria/:id/delete',
        component: InvestigacionSumariaDeletePopupComponent,
        resolve: {
            investigacionSumaria: InvestigacionSumariaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pdisciplinarioApp.investigacionSumaria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
