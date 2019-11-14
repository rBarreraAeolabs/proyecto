import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMovimientoInvestigacionSumaria } from 'app/shared/model/movimiento-investigacion-sumaria.model';
import { MovimientoInvestigacionSumariaService } from './movimiento-investigacion-sumaria.service';

@Component({
    selector: 'jhi-movimiento-investigacion-sumaria-delete-dialog',
    templateUrl: './movimiento-investigacion-sumaria-delete-dialog.component.html'
})
export class MovimientoInvestigacionSumariaDeleteDialogComponent {
    movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria;

    constructor(
        private movimientoInvestigacionSumariaService: MovimientoInvestigacionSumariaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.movimientoInvestigacionSumariaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'movimientoInvestigacionSumariaListModification',
                content: 'Deleted an movimientoInvestigacionSumaria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-movimiento-investigacion-sumaria-delete-popup',
    template: ''
})
export class MovimientoInvestigacionSumariaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ movimientoInvestigacionSumaria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MovimientoInvestigacionSumariaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.movimientoInvestigacionSumaria = movimientoInvestigacionSumaria;
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
