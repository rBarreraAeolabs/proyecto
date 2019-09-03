/**
 * Created by sneiraillanes on 09-05-2019.
 */
import {Component, OnDestroy, OnInit} from '@angular/core';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute, Router} from '@angular/router';
import {JhiEventManager} from 'ng-jhipster';
import {ProvidenciaService} from './providencia.service';
// import {IProvidencia, IProvidenciaUpdateMadre, Providencia} from '../../shared/model/providencia.model';
import {IProvidencia, IProvidenciaUpdateForType, Providencia} from '../../shared/model/providencia.model';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';

@Component({
    selector: 'jhi-providencia-relacionar-dialog',
    templateUrl: './providencia-relacionar-dialog.component.html'
})
export class ProvidenciaRelacionarDialogComponent implements OnInit {

    private _providencia: IProvidencia;
    providencias: IProvidencia[];

    providenciaUpdateForType: IProvidenciaUpdateForType = {
        providenciaId: null,
        providenciaMadreId: null,
        providenciaMadreNumeroReferencia: null
    };
    providenciaSeleccionada: IProvidencia;

    constructor(
        private activeModal: NgbActiveModal,
        private router: Router,
        private eventManager: JhiEventManager,
        private providenciaService: ProvidenciaService
    ) {}

    ngOnInit() {
        this.providenciaService.getAllWithoutPagination().subscribe(
            (req: HttpResponse<IProvidencia[]>) => {
                this.providencias = req.body.filter(prov => prov.id !== this.providencia.id);
                      },
            (req: HttpErrorResponse) => {
                console.log('error', req.message);
            }
        );

        this.providenciaUpdateForType.providenciaId = this.providencia.id;
      }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    previousState() {
        window.history.back();
    }

    get providencia() {
        return this._providencia;
    }

    set providencia(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    onChange($event) {
        console.log($event);
        this.providenciaUpdateForType.providenciaMadreId = $event.target.value;
        this.providenciaSeleccionada = this.providencias.filter(pro => pro.id === parseInt($event.target.value, 10),
            pro2 => pro2.numerarReferencia === parseInt($event.target.value, 10)
        )[0];
        this.providenciaUpdateForType.providenciaMadreNumeroReferencia =  this.providenciaSeleccionada.numeroReferencia;
        console.log('weada: ' + this.providenciaUpdateForType.providenciaMadreNumeroReferencia);
 }

    onSave() {
        this.providenciaService.providenciaUpdateForType(this.providenciaUpdateForType).subscribe(
            (req: HttpResponse<IProvidencia>) => {
                if (req.body.id !== null || typeof req.body.id !== 'undefined') {
                    this.activeModal.close('updated');
                    console.log('weada2: ', this.providenciaUpdateForType.providenciaMadreNumeroReferencia);
                }
            },
            (req: HttpErrorResponse) => {
                console.log('error ', req.message);
            }
        );
    }
}

@Component({
    selector: 'jhi-providencia-relacionar-popup',
    template: ''
})
export class ProvidenciaRelacionarPopupComponent implements OnInit, OnDestroy {

    private ngbModalRef: NgbModalRef;

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private modalService: NgbModal
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({providencia}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaRelacionarDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });

                this.ngbModalRef.componentInstance.providencia = providencia;

                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true, queryParamsHandling: 'merge'});
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true, queryParamsHandling: 'merge'});
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        } );
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
