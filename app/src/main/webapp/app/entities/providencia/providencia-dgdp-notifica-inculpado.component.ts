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

@Component({
    selector: 'jhi-providencia-dgdp-notifica-inculpado',
    templateUrl: './providencia-dgdp-notifica-inculpado.component.html'
})
export class ProvidenciaDgdpNotificaInculpadoComponent implements OnInit {
    providencia: IProvidencia;
    providenciaResponse: IProvidenciaResponse = new IProvidenciaResponse();
    observacionDerivacion: string;
    adjuntos: IAdjunto[];
    private _providencia: IProvidencia;
    isProrroga = false;
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

    notificarDGDP(id: number) {
        this.providenciaResponse.estadoActual = this.providencia.estadoActual;
        this.providenciaResponse.providenciaId = id;
        this.providenciaResponse.adjuntosDTOs = this.adjuntos;
        this.providenciaResponse.observacion = this.observacionDerivacion;

        this.providenciaService.notificarDGDP(this.providenciaResponse).subscribe(res => {
            this.eventManager.broadcast({
                name: 'providencia',
                content: 'Providencia upd notifica inculpado'
            });
            this.activeModal.dismiss(true);
            this.previousState();
        });
    }

    previousState() {
        if (this.providencia.requisito === 'NOTIFICA_E_INFORMA_SENSURA' || this.providencia.requisito === 'NOTIFICA_E_INFORMA_DESTITUCION'  ) {
            window.history.back();
        } else {
        }
    }

    getUploadedAdjuntos($event) {
        this.adjuntos = $event;
    }

    private onError(errorMessage: string) {
        console.log(errorMessage);
    }
}

@Component({
    selector: 'jhi-providencia-dgdp-notifica-inculpado-popup',
    template: ''
})
export class ProvidenciaDgdpNotificaInculpadoPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ providencia }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaDgdpNotificaInculpadoComponent as Component, {
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
