import {Component, OnDestroy, OnInit} from '@angular/core';
import {IProvidencia, IProvidenciaResponse} from 'app/shared/model/providencia.model';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {DerivacionService} from 'app/entities/derivacion';
import {ActivatedRoute, Router} from '@angular/router';
import {JhiEventManager} from 'ng-jhipster';
import {ProvidenciaService} from 'app/entities/providencia/providencia.service';
import {PlantillaService} from 'app/entities/plantilla';
import {DocumentoService} from 'app/entities/documento';
import {IDocumento} from 'app/shared/model/documento.model';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {RespuestaService} from '../respuesta/respuesta.service';
import {IRespuesta, Respuesta} from '../../shared/model/respuesta.model';

@Component({
    selector: 'jhi-providencia-asignar-numero-resolucion-dialog',
    templateUrl: './providencia-asignar-numero-resolucion.component.html'
})
export class ProvidenciaAsignarNumeroResolucionComponent implements OnInit {
    providencia: IProvidencia;
    private _providencia: IProvidencia;
    documento: IDocumento;
    showTable = false;

    constructor(
        public activeModal: NgbActiveModal,
        private derivacionService: DerivacionService,
        private router: Router,
        private eventManager: JhiEventManager,
        private plantillaService: PlantillaService,
        private providenciaService: ProvidenciaService,
        private documentoService: DocumentoService,
        private respuestaService: RespuestaService
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    ngOnInit() {
        this.documentoService.findByProvidencia(this.providencia).subscribe(
            (req: HttpResponse<IDocumento>) => {
                console.log('Recibiendo doc en funcion de pro', req.body);
                this.documento = req.body;
                this.showTable = true;
            },
            (req: HttpErrorResponse) => {
                console.log('Error de capa 8', req.message);
                this.showTable = false;
            }
        );
    }

    get providencias() {
        return this._providencia;
    }

    set providencias(providencia: IProvidencia) {
        this._providencia = providencia;
    }

    asignarNumero() {

        this.documento.id = null;

        this.documentoService.create(this.documento).subscribe(
            (req: HttpResponse<IDocumento>) => {

                const respuesta: IRespuesta = new Respuesta();
                respuesta.providencia = this.providencia;
                respuesta.documentos = [req.body];
                respuesta.estadoProvidencia = this.providencia.estadoActual;

                this.respuestaService.create(respuesta).subscribe(
                    (req1: HttpResponse<IRespuesta>) => {
                        console.log('número asignado');
                        const providenciaResponse: IProvidenciaResponse = {
                            providenciaId: this.providencia.id,
                            estadoActual: this.providencia.estadoActual,
                            adjuntosDTOs: null,
                            documentosDTOs: null,
                            observacion: 'Asignación del número de resolución en la providencia N°' + this.providencia.id
                        };

                        this.providenciaService.reply(providenciaResponse).subscribe(
                            (req2: HttpResponse<any>) => {
                                this.eventManager.broadcast({
                                    name: 'providenciaListModification',
                                    content: 'Providencia derivada'
                                });
                                this.activeModal.dismiss(true);
                                this.previousState();
                            },
                            (req3: HttpErrorResponse) => console.log('error reply', req3.message)
                        );
                    },
                    (req4: HttpErrorResponse) => console.log('error respuesta ', req4.message)
                );

            },
            (req: HttpErrorResponse) => {
                console.log('error', req.message);
            }
        );
    }

    previousState() {
        window.history.back();
    }

    private onError(errorMessage: string) {
        console.log(errorMessage);
    }
}

@Component({
    selector: 'jhi-providencia-asignar-numero-resolucion-popup',
    template: ''
})
export class ProvidenciaAsignarNumeroResolucionPopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({providencia}) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProvidenciaAsignarNumeroResolucionComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.providencia = providencia;
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
