import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvestigacionSumaria } from 'app/shared/model/investigacion-sumaria.model';
import { InvestigacionSumariaService } from './investigacion-sumaria.service';

@Component({
    selector: 'jhi-investigacion-sumaria-delete-dialog',
    templateUrl: './investigacion-sumaria-delete-dialog.component.html'
})
export class InvestigacionSumariaDeleteDialogComponent {
    investigacionSumaria: IInvestigacionSumaria;

    constructor(
        private investigacionSumariaService: InvestigacionSumariaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.investigacionSumariaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'investigacionSumariaListModification',
                content: 'Deleted an investigacionSumaria'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-investigacion-sumaria-delete-popup',
    template: ''
})
export class InvestigacionSumariaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ investigacionSumaria }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InvestigacionSumariaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.investigacionSumaria = investigacionSumaria;
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
