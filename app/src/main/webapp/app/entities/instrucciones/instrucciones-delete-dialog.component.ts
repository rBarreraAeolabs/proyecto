import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInstrucciones } from 'app/shared/model/instrucciones.model';
import { InstruccionesService } from './instrucciones.service';

@Component({
    selector: 'jhi-instrucciones-delete-dialog',
    templateUrl: './instrucciones-delete-dialog.component.html'
})
export class InstruccionesDeleteDialogComponent {
    instrucciones: IInstrucciones;

    constructor(private instruccionesService: InstruccionesService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.instruccionesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'instruccionesListModification',
                content: 'Deleted an instrucción'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-instrucciones-delete-popup',
    template: ''
})
export class InstruccionesDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ instrucciones }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InstruccionesDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.instrucciones = instrucciones;
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
