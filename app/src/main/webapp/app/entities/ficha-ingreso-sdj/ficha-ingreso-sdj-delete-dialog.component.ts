import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFichaIngresoSdj } from 'app/shared/model/ficha-ingreso-sdj.model';
import { FichaIngresoSdjService } from './ficha-ingreso-sdj.service';

@Component({
    selector: 'jhi-ficha-ingreso-sdj-delete-dialog',
    templateUrl: './ficha-ingreso-sdj-delete-dialog.component.html'
})
export class FichaIngresoSdjDeleteDialogComponent {
    fichaIngresoSdj: IFichaIngresoSdj;

    constructor(
        private fichaIngresoSdjService: FichaIngresoSdjService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fichaIngresoSdjService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'fichaIngresoSdjListModification',
                content: 'Deleted an fichaIngresoSdj'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ficha-ingreso-sdj-delete-popup',
    template: ''
})
export class FichaIngresoSdjDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fichaIngresoSdj }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FichaIngresoSdjDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.fichaIngresoSdj = fichaIngresoSdj;
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
