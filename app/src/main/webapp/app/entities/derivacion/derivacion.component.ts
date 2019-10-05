import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IDerivacion } from 'app/shared/model/derivacion.model';
import { Principal } from 'app/core';

import { DerivacionService } from './derivacion.service';

@Component({
    selector: 'jhi-derivacion',
    templateUrl: './derivacion.component.html'
})
export class DerivacionComponent implements OnInit, OnDestroy {
    @Input() public idProvidencia: number;

    currentAccount: any;
    derivacions: IDerivacion[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    predicate: any;
    reverse: any;

    constructor(
        private derivacionService: DerivacionService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {}

    loadAll() {
        this.derivacionService.getByProvidencia(this.idProvidencia).subscribe(
            (res: HttpResponse<IDerivacion[]>) => {
                this.derivacions = res.body;
                console.log('derivaciones ', this.derivacions);
                this.derivacions.sort((firstDer, secondDer) => {
                    return secondDer.fechaDerivacion.isBefore(firstDer.fechaDerivacion) ? -1 : 1;
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
        this.registerChangeInDerivacions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDerivacion) {
        return item.id;
    }

    registerChangeInDerivacions() {
        this.eventSubscriber = this.eventManager.subscribe('derivacionListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
