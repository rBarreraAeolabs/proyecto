import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IProvidencia, IProvidenciaResponse } from 'app/shared/model/providencia.model';
import { DerivacionService } from 'app/entities/derivacion';
import { PlantillaService } from '../plantilla/plantilla.service';
import { ProvidenciaService } from './providencia.service';
import { IAdjunto } from '../../shared/model/adjunto.model';
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-providencia-realizo-resolucion',
    templateUrl: './providencia-realizo-resolucion.component.html'
})
export class ProvidenciaRealizoResolucionComponent implements OnInit {
    providencia: IProvidencia;
    providenciaResponse: IProvidenciaResponse = new IProvidenciaResponse();
    observacionDerivacion: string;
    private _providencia: IProvidencia;
    adjuntos: IAdjunto[];
    isDerivar = true;
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
    }
    get providencias() {
        return this._providencia;
    }

    set providencias(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    realizoResolucion(id: number ) {

        this.providenciaResponse.estadoActual = this.providencia.estadoActual;
        this.providenciaResponse.providenciaId = id;
        this.providenciaResponse.adjuntosDTOs = this.adjuntos;
        this.providenciaResponse.observacion = this.observacionDerivacion;
        this.providencia.requisito = this.providencia.requisito;
        this.providencia.etapa = this.providencia.etapa;
        this.providenciaService.alcanceConResolucion(this.providenciaResponse).subscribe(res => {
            this.eventManager.broadcast({
                name: 'providenciaListModification',
                content: 'Providencia alcance'
            });
            this.activeModal.dismiss(true);
            this.previousState();
        });
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
    selector: 'jhi-providencia-realizo-resolucion-popup',
    template: ''
})
export class ProvidenciaRealizoResolucionPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaRealizoResolucionComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.providencia = providencia;
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
