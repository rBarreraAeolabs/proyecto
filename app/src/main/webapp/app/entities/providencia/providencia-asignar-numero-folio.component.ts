import { Component, OnDestroy, OnInit } from '@angular/core';
import {IProvidencia, IProvidenciaUpdateFolio} from 'app/shared/model/providencia.model';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager } from 'ng-jhipster';
import { ProvidenciaService } from 'app/entities/providencia/providencia.service';
import { HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-providencia-asignar-numero-folio',
    templateUrl: './providencia-asignar-numero-folio.component.html'
})
export class ProvidenciaAsignarNumeroFolioComponent implements OnInit {
    providencia: IProvidencia;
    private _providencia: IProvidencia;

    providenciaUpdateFolio: IProvidenciaUpdateFolio = {
        providenciaId: null,
        folio: null
    };

    constructor(
        public activeModal: NgbActiveModal,
        private router: Router,
        private eventManager: JhiEventManager,
        private providenciaService: ProvidenciaService
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    ngOnInit() {
        this.providenciaUpdateFolio.providenciaId = this.providencia.id;
        console.log('esta es la providencia ', this.providencia.id);
    }

    get providencias() {
        return this._providencia;
    }

    set providencias(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    asignarfolio() {
        this.providenciaService.updateFolio(this.providenciaUpdateFolio)
            .subscribe(
                (req: HttpResponse<IProvidencia>) => {
                    this.activeModal.close(req.body);
                },
                (req: HttpErrorResponse) => {

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
    selector: 'jhi-providencia-asignar-numero-folio-popup',
    template: ''
})
export class  ProvidenciaAsignarNumeroFolioPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaAsignarNumeroFolioComponent as Component, {
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
