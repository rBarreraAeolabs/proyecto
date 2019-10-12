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
    selector: 'jhi-providencia-fiscal-cierre',
    templateUrl: './providencia-fiscal-cierre.component.html'
})
export class ProvidenciaFiscalCierreComponent implements OnInit {
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
        if (this.usuario === 'Jefatura') {
            console.log('el usuario es jefe el envia');
            this.isDerivar = false;
        }
    }
    get providencias() {
        return this._providencia;
    }

    set providencias(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    confirmarRespuesta(id: number ) {

        this.providenciaResponse.estadoActual = this.providencia.estadoActual;
        this.providenciaResponse.providenciaId = id;
        this.providenciaResponse.adjuntosDTOs = this.adjuntos;
        this.providenciaResponse.observacion = this.observacionDerivacion;
        this.providencia.requisito = this.providencia.requisito;
        this.providencia.etapa = this.providencia.etapa;
        this.providenciaService.reply(this.providenciaResponse).subscribe(res => {
            this.eventManager.broadcast({
                name: 'providenciaListModification',
                content: 'Providencia aceptada'
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
    selector: 'jhi-providencia-responder-popup',
    template: ''
})
export class ProvidenciaFiscalCierrePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaFiscalCierreComponent as Component, {
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
