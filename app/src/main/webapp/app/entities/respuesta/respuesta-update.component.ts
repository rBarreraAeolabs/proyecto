/**
 * Created by sneiraillanes on 22-04-2019.
 */
import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable, from, of} from 'rxjs';
import {IRespuesta, Respuesta} from '../../shared/model/respuesta.model';
import {IPlantilla, Plantilla} from '../../shared/model/plantilla.model';
import {IAdjunto} from '../../shared/model/adjunto.model';
import {Documento, IDocumento} from '../../shared/model/documento.model';
import {RespuestaService} from './respuesta.service';
import {DocumentoService} from '../documento/documento.service';
import {PlantillaService} from '../plantilla/plantilla.service';
import {IProvidencia} from '../../shared/model/providencia.model';
import {ProvidenciaService} from '../providencia/providencia.service';
import {JhiAlertService} from 'ng-jhipster';

@Component({
    selector: 'jhi-respuesta-update',
    templateUrl: './respuesta-update.component.html'
})
export class RespuestaUpdateComponent implements OnInit {
    @Input() providencia: IProvidencia;
    @Output() resp: EventEmitter<any> = new EventEmitter();

    private _respuesta: IRespuesta = new Respuesta();
    comentario: string;
    isSaving: boolean;
    plantillaUtilizada: IPlantilla;
    adjuntos: IAdjunto[];
    documentos: IDocumento[] = [];
    showEditorTemplate = false;
    plantillas: IPlantilla[];
    showMessageOtherUser = false;

    constructor(
        private respuestaService: RespuestaService,
        private documentoService: DocumentoService,
        private plantillaService: PlantillaService,
        private activatedRoute: ActivatedRoute,
        private providenciaService: ProvidenciaService,
        private jhiAlertService: JhiAlertService
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.plantillaUtilizada = new Plantilla(null, null, '', null, null);
        // this.onSelectPlantilla(2651);
        this.respuestaService.getByProvidencia(this.providencia).subscribe(
            (req: HttpResponse<IRespuesta>) => {
                if (req.body.id !== null) {
                    this._respuesta = req.body;
                    this.comentario = this._respuesta.comentario;
                    this.adjuntos = this._respuesta.adjuntos;
                    this.documentos = this._respuesta.documentos;
                    this.resp.emit(this.respuesta);
                }
            },
            (req: HttpErrorResponse) => this.onError(req.status)
        );

        this.providenciaService.getPlantillasEnabled(this.providencia).subscribe(
            (req: HttpResponse<IPlantilla[]>) => {
                this.plantillas = req.body;
            },
            (req: HttpErrorResponse) => this.onError(req.status)
        );
    }

    previousState() {
        window.history.back();
    }

    save(guardar: boolean) {
        this.isSaving = true;
        this._respuesta.adjuntos = this.adjuntos;
        this._respuesta.documentos = this.documentos;
        this._respuesta.comentario = this.comentario;
        this._respuesta.guardada = guardar;
        this._respuesta.providencia = this.providencia;
        this._respuesta.estadoProvidencia = this.providencia.estadoActual;
        if (this.respuesta.id !== undefined) {
            this.subscribeToSaveResponse(this.respuestaService.update(this.respuesta));
        } else {
            this.subscribeToSaveResponse(this.respuestaService.create(this.respuesta));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRespuesta>>) {
        result.subscribe(
            (res: HttpResponse<IRespuesta>) => {
                this.resp.emit(res.body);
                this.onSaveSuccess();
            },
            (res: HttpErrorResponse) => this.onSaveError());
        this.isSaving = false;
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.ngOnInit();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    get respuesta() {
        return this._respuesta;
    }

    set respuesta(respuesta: IRespuesta) {
        this._respuesta = respuesta;
    }

    getUploadedAdjuntos($event) {
        this.adjuntos = $event;
        setTimeout(() => {
            this.save(false);
        }, 300);
    }

    getNotDeletedDocuments($event) {
        this.documentos = $event;
        setTimeout(() => {
            this.save(false);
        }, 300);
    }

    onSelectPlantilla(idPlantilla: string) {
        if (idPlantilla !== 'Seleccionar') {
            this.plantillaService.find(parseInt(idPlantilla, 10)).subscribe(plantilla => {
                this.plantillaUtilizada = plantilla.body;
                this.showEditorTemplate = true;
            });
        } else {
            this.plantillaUtilizada = new Plantilla(null, null, '', null, null);
            this.showEditorTemplate = false;
        }
    }

    onAttachPlantilla() {
        this.isSaving = true;
        const documento: IDocumento = new Documento();
        documento.contenido = this.plantillaUtilizada.contenido;
        documento.providenciaId = this.providencia.id;
        documento.archivoNombre = this.plantillaUtilizada.nombre;
        documento.tipoPlantilla = this.plantillaUtilizada.tipo;

        this.documentoService.create(documento).subscribe(
            (res: HttpResponse<IDocumento>) => {
                this.documentos.push(res.body);
                this.save(false);
            },
            (error: HttpErrorResponse) => {
                console.log(error);
                this.isSaving = false;
            }
        );
    }

    private onError(status: number) {
        console.log(status);
        if (status === 404) {
            this.jhiAlertService.error('No existen respuestas asociadas a la providencia.', null, null);
        }
    }

}
