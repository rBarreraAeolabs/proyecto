/**
 * Created by sneiraillanes on 01-04-2019.
 */
import {Component, OnInit, OnDestroy, Input} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { IAdjunto } from 'app/shared/model/adjunto.model';
import { IDocumento } from '../../shared/model/documento.model';
import { DocumentoService } from '../documento/documento.service';
import { HttpResponse } from '@angular/common/http';
import { AdjuntoService } from 'app/entities/adjunto';
import {IMovimientoProvidencia} from '../../shared/model/movimiento-providencia.model';

@Component({
    selector: 'jhi-comentario-bitacora-modal-popup',
    templateUrl: './comentario-bitacora-modal.component.html'
})
export class ComentarioBitacoraModalComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    @Input() movimientoProvidencia: IMovimientoProvidencia;

    constructor(
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private modalService: NgbModal,
        public activeModal: NgbActiveModal,
        private adjuntoService: AdjuntoService,
        private documentoService: DocumentoService
    ) {}

    ngOnInit() {}

    ngOnDestroy() {
        this.ngbModalRef = null;
    }

    open(content) {
        console.log(content);
        this.modalService.open(content, { size: 'lg', backdrop: 'static' }).result.then(
            result => {

            },
            reason => {

            });
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    download(hash: string, tipo: string) {

        if (tipo === 'adjunto') {
            this.adjuntoService.download(hash).subscribe(result => {
                this.downloadAsPdf(result);
            });
        } else {
            this.documentoService.download(hash).subscribe(result => {
                this.downloadAsPdf(result);
            });
        }
    }

    viewPdfOnOtherTabChrome(hash: string, tipo: string) {
        if (tipo === 'adjunto') {
            this.adjuntoService.download(hash).subscribe(result => {
                const url = window.URL.createObjectURL(result.body);
                window.open(url);
            });
        } else {
            this.documentoService.download(hash).subscribe(result => {
                const url = window.URL.createObjectURL(result.body);
                window.open(url);
            });
        }
    }

    downloadAsPdf(result: HttpResponse<Blob>) {
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(result.body);
        const contentDisposition = result.headers.get('content-disposition');
        const fileName = contentDisposition.substring(contentDisposition.indexOf('=') + 2,
            contentDisposition.indexOf('"', contentDisposition.indexOf('"') + 1));

        if (navigator.msSaveBlob) {
            // IE 10+
            navigator.msSaveBlob(result.body, fileName);
        }

        link.download = fileName;
        link.click();
        window.URL.revokeObjectURL(link.href);
    }

}