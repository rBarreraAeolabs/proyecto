import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {ISumarioAdministrativo} from 'app/shared/model/sumario-administrativo.model';
import {SumarioAdministrativoService} from './sumario-administrativo.service';

@Component({
    selector: 'jhi-sumario-administrativo-delete-dialog',
    templateUrl: './sumario-administrativo-delete-dialog.component.html'
})
export class SumarioAdministrativoDeleteDialogComponent {
    sumarioAdministrativo: ISumarioAdministrativo;

    constructor(
        private sumarioAdministrativoService: SumarioAdministrativoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sumarioAdministrativoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sumarioAdministrativoListModification',
                content: 'Deleted an sumarioAdministrativo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sumario-administrativo-delete-popup',
    template: ''
})
export class SumarioAdministrativoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({sumarioAdministrativo}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SumarioAdministrativoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sumarioAdministrativo = sumarioAdministrativo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{outlets: {popup: null}}], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
                        });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{outlets: {popup: null}}], {
                            replaceUrl: true,
                            queryParamsHandling: 'merge'
                        });
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
