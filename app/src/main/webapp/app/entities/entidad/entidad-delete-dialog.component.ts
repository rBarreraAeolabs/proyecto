import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import {IEntidad} from 'app/shared/model/entidad.model';
import {EntidadService} from 'app/entities/entidad/entidad.service';

@Component({
    selector: 'jhi-entidad-delete-dialog',
    templateUrl: './entidad-delete-dialog.component.html'
})
export class EntidadDeleteDialogComponent {
    entidad: IEntidad;

    constructor(private entidadService: EntidadService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.entidadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'entidadListModification',
                content: 'Deleted an entidad'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-entidad-delete-popup',
    template: ''
})
export class EntidadDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ entidad }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EntidadDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.entidad = entidad;
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
