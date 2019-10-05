import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDerivacion } from 'app/shared/model/derivacion.model';
import { DerivacionService } from './derivacion.service';

@Component({
    selector: 'jhi-derivacion-delete-dialog',
    templateUrl: './derivacion-delete-dialog.component.html'
})
export class DerivacionDeleteDialogComponent {
    derivacion: IDerivacion;

    constructor(private derivacionService: DerivacionService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.derivacionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'derivacionListModification',
                content: 'Deleted an derivacion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-derivacion-delete-popup',
    template: ''
})
export class DerivacionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ derivacion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DerivacionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.derivacion = derivacion;
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
