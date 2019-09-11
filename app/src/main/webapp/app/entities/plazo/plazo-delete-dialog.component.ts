import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {IPlazo} from 'app/shared/model/plazo.model';
import {PlazoService} from './plazo.service';

@Component({
    selector: 'jhi-plazo-delete-dialog',
    templateUrl: './plazo-delete-dialog.component.html'
})
export class PlazoDeleteDialogComponent {
    plazo: IPlazo;

    constructor(private plazoService: PlazoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.plazoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'plazoListModification',
                content: 'Deleted an plazo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-plazo-delete-popup',
    template: ''
})
export class PlazoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({plazo}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PlazoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.plazo = plazo;
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
