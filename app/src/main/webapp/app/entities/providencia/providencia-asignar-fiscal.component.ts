/**
 * Created by sneiraillanes on 10-05-2019.
 */
import { Component, OnDestroy, OnInit } from '@angular/core';
import {IProvidencia, IProvidenciaResponse} from 'app/shared/model/providencia.model';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { ProvidenciaService } from 'app/entities/providencia/providencia.service';
import { JhiEventManager } from 'ng-jhipster';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import {UserService} from "../../core/user/user.service";

@Component({
    selector: 'jhi-providencia-asignar-fiscal',
    templateUrl: './providencia-asignar-fiscal.component.html'
})
export class ProvidenciaAsignarFiscalComponent implements OnInit {

    private _providencia: IProvidencia;

    constructor(
        public activeModal: NgbActiveModal,
        private providenciaService: ProvidenciaService,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    ngOnInit() {}

    get providencia() {
        return this._providencia;
    }

    set providencia(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    asignarFiscal() {

        this.providenciaService.updateFiscal(this.providencia).subscribe(
            (req: HttpResponse<IProvidencia>) => {
                this.activeModal.close('fiscal asignado');
            },
            (req: HttpErrorResponse) => {
                console.log('error', req.message);
            }
        );

    }

    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        console.log(errorMessage);
    }
}

@Component({
    selector: 'jhi-providencia-asignar-fiscal-popup',
    template: ''
})
export class  ProvidenciaAsignarFiscalPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}
    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaAsignarFiscalComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.providencia = providencia;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }
    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
