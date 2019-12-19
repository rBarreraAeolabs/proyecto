import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { Principal } from 'app/core';
import { MovimientoProvidenciaService } from './movimiento-providencia.service';
import { NgbTabsetConfig } from '@ng-bootstrap/ng-bootstrap';
import { ProvidenciaService } from 'app/entities/providencia';
import { IProvidencia } from 'app/shared/model/providencia.model';
import { Moment } from 'moment';
import {FiltroMovPro} from 'app/shared/model/filtroMovimiento.model';

@Component({
    selector: 'jhi-movimiento-providencia',
    templateUrl: './movimiento-providencia.component.html',
    providers: [NgbTabsetConfig] // agregar NgbTabsetConfig a los proveedores de componentes
})
export class MovimientoProvidenciaComponent implements OnInit, OnDestroy {
    @Input() public providencia: IProvidencia;

    filtroMovPro: FiltroMovPro;
    currentAccount: any;
    movimientosProvidencia: IMovimientoProvidencia[];
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

    constructor(
        private movimientoProvidenciaService: MovimientoProvidenciaService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private providenciaService: ProvidenciaService,
        private router: Router,
        private eventManager: JhiEventManager
    ) {}

    loadAll() {
        this.filtroMovPro = {
            providencia: this.providencia,
            filtroMovimientoProvidencia: null
        };

        this.movimientoProvidenciaService.traerMovimientoDeLaProvidencia(this.filtroMovPro.providencia.id)
            .subscribe(
                (res: HttpResponse<IMovimientoProvidencia[]>) => {
                    this.movimientosProvidencia = res.body;
                    this.movimientosProvidencia.sort((firstMov, secondMov) => {
                        return secondMov.fecha.isBefore(firstMov.fecha) ? -1 : 1;
                    });
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMovimientoProvidencias();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMovimientoProvidencia) {
        return item.id;
    }

    registerChangeInMovimientoProvidencias() {
        this.eventSubscriber = this.eventManager.subscribe('movimientoProvidenciaListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    calculateRemainingDays(fechaMov: Moment, plazoDias: number) {

    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
