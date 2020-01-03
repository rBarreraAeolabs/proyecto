import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IProvidencia, IProvidenciaResponse} from 'app/shared/model/providencia.model';
import { DerivacionService } from 'app/entities/derivacion';
import { ProvidenciaService} from './providencia.service';
import { PlantillaService} from '../plantilla/plantilla.service';
import { IAdjunto} from '../../shared/model/adjunto.model';
import { Principal} from 'app/core';
import Swal from 'sweetalert2';

@Component({
    selector: 'jhi-providencia-devolver-dialog',
    templateUrl: './providencia-devolver-dialog.component.html'
})
export class ProvidenciaDevolverDialogComponent implements OnInit {
    providencia: IProvidencia;
    providenciaResponse: IProvidenciaResponse = new IProvidenciaResponse();
    observacionDerivacion: string;
    adjuntos: IAdjunto[];
    private _providencia: IProvidencia;
    isDevolver = false;
    cuenta: any;
    usuario: any;

    constructor(
        public activeModal: NgbActiveModal,
        private derivacionService: DerivacionService,
        private router: Router,
        private eventManager: JhiEventManager,
        private plantillaService: PlantillaService,
        private providenciaService: ProvidenciaService,
        private principal: Principal

    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    ngOnInit() {
        this.cuenta = this.principal.identity();
        this.usuario = this.cuenta.__zone_symbol__value.perfil.nombre;
        console.log('usuario: ', this.usuario);
        if (this.usuario === 'usuario') {
            console.log('el usuario es fiscal el devuelve');
            this.isDevolver = true;
        }
    }

    get providencias() {
        return this._providencia;
    }

    set providencias(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    confirmarDevolucion(id: number) {

        Swal.fire({
            title: 'Estas seguro que deseas devolver la providencia #'+ id + '?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si',
            cancelButtonText: 'No'
        }).then((result) => {
            if (result.value) {
                this.providenciaResponse.estadoActual = this.providencia.estadoActual;
                this.providenciaResponse.providenciaId = id;
                this.providenciaResponse.adjuntosDTOs = this.adjuntos;
                this.providenciaResponse.observacion = this.observacionDerivacion;
                this.providenciaService.goBackwards(this.providenciaResponse).subscribe(res => {
                    this.eventManager.broadcast({
                        name: 'providenciaAceptada',
                        content: 'Providencia notificacion'
                    });
                    this.activeModal.dismiss(true);
                    this.previousState();
                });
                Swal.fire(
                    'Devuelta!',
                    'la providencia ha sido devuelta con exito!.',
                    'success'
                )
            }
        })


    }

    previousState() {
        window.history.back();
    }

    getUploadedAdjuntos($event) {
        this.adjuntos = $event;
    }

    private onError(errorMessage: string) {
        console.log(errorMessage);
    }
}

@Component({
    selector: 'jhi-providencia-devolver-popup',
    template: ''
})
export class ProvidenciaDevolverPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaDevolverDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.providencia = providencia;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    // reason => {
                    //     this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                    //     this.ngbModalRef = null;
                    // }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
