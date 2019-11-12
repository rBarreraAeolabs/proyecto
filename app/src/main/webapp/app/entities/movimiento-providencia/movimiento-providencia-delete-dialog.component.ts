import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { MovimientoProvidenciaService } from './movimiento-providencia.service';

@Component({
    selector: 'jhi-movimiento-providencia-delete-dialog',
    templateUrl: './movimiento-providencia-delete-dialog.component.html'
})
export class MovimientoProvidenciaDeleteDialogComponent {
    movimientoProvidencia: IMovimientoProvidencia;

    constructor(
        private movimientoProvidenciaService: MovimientoProvidenciaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.movimientoProvidenciaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'movimientoProvidenciaListModification',
                content: 'Deleted an movimientoProvidencia'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-movimiento-providencia-delete-popup',
    template: ''
})
export class MovimientoProvidenciaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ movimientoProvidencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MovimientoProvidenciaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.movimientoProvidencia = movimientoProvidencia;
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
