import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import {IProvidencia, IProvidenciaItemList} from 'app/shared/model/providencia.model';
import {Principal, UserService} from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { ProvidenciaService } from './providencia.service';
import {Moment} from 'moment';
import * as moment from 'moment';

@Component({
    selector: 'jhi-providencia',
    templateUrl: './providencia.component.html'
})
export class ProvidenciaComponent implements OnInit, OnDestroy {
    currentAccount: any;
    providencias: IProvidenciaItemList[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    now: Moment = moment();
    userDgdp = false;
    color: string = '#c3e6cb';

    constructor(
        private providenciaService: ProvidenciaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private userService: UserService
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.providenciaService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IProvidencia[]>) => this.paginateProvidencias(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/providencia'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/providencia',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProvidencias();
        this.verifyUserDgdp();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: Moment, item: IProvidenciaItemList) {
        return item.fechaCreacion;
    }
    generarColor(estado: string, stanby: boolean) {
        let color = '#c3e6cb';
        console.log(estado);
        switch (estado) {
            case 'INVESTIGACION - PRORROGA_SOLICITADA - PETICION_PRORROGA_1':
            case 'INVESTIGACION_PRORROGA_1 - PRORROGA_SOLICITADA - PETICION_PRORROGA_2':
            case 'REVISION_SUMARIO - ABOGADO_RESPONDE - DGD_DESPACHA_SUMARIO_COMPLETO':
            case 'INVESTIGACION_PRORROGA_1 - ABOGADO_RESPONDE - DGD_DESPACHA_SUMARIO_COMPLETO':
            case 'INVESTIGACION_PRORROGA_2 - ABOGADO_RESPONDE - DGD_DESPACHA_SUMARIO_COMPLETO':
            case 'INFORME_JURIDICO - ABSOLVER - DEMANDADO_NOTIFICADO':
            case 'INFORME_JURIDICO - SOBRESEER - DEMANDADO_NOTIFICADO':
            case 'INFORME_JURIDICO - SANCIONA - ALCANCE_SIN_RESOLUCION':
            case 'INVESTIGACION - PRORROGA_SOLICITADA - PETICION_PRORROGA':
             // if (stanby !== true) {
                    color = '#F68334';
                    break;
                // } else {

                    // color = '#f5c6cb';
                // }
                // break;

        }
        return color;
    }

    registerChangeInProvidencias() {
        this.eventSubscriber = this.eventManager.subscribe('providenciaListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateProvidencias(data: IProvidencia[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.providencias = data;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    verifyUserDgdp() {
        this.userService.verifyUserDgdp().subscribe(
            (req: HttpResponse<any>) => {
                this.userDgdp = req.body;
            },
            (req: HttpErrorResponse) => {
                console.log('error', req.message);
            }
        );
    }
}
