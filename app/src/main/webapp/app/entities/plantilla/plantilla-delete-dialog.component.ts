import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPlantilla } from 'app/shared/model/plantilla.model';
import { PlantillaService } from './plantilla.service';

@Component({
    selector: 'jhi-plantilla-delete-dialog',
    templateUrl: './plantilla-delete-dialog.component.html'
})
export class PlantillaDeleteDialogComponent {
    plantilla: IPlantilla;

    constructor(private plantillaService: PlantillaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plantillaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plantillaListModification',
                content: 'Deleted an plantilla'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plantilla-delete-popup',
    template: ''
})
export class PlantillaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantilla }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlantillaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.plantilla = plantilla;
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
