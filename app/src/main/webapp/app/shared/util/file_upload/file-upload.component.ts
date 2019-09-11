import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {LocalStorageService, SessionStorageService} from 'ngx-webstorage';
import {CookieService} from 'ngx-cookie';
import {HttpClient} from '@angular/common/http';
import {humanizeBytes, UploaderOptions, UploadFile, UploadInput, UploadOutput, UploadStatus} from 'ngx-uploader';
import {UploadFileService} from './upload-file.service';
import {IAdjunto} from '../../model/adjunto.model';
import {IDocumento} from '../../model/documento.model';

@Component({
    selector: 'jhi-file-upload-component',
    templateUrl: './file-upload.component.html',
    styleUrls: ['./file-upload.component.css'],
    providers: [CookieService]
})
export class FileUploadComponent implements OnInit, OnChanges {
    @Output() fileResponse: EventEmitter<any> = new EventEmitter();
    @Output() notDeletedDocument: EventEmitter<any> = new EventEmitter();
    @Input() adjuntos: IAdjunto[];
    @Input() documentos: IDocumento[];

    url = 'api/adjuntos/_upload';
    formData: FormData;
    files: UploadFile[];
    uploadInput: EventEmitter<UploadInput>;
    humanizeBytes: Function;
    dragOver: boolean;
    options: UploaderOptions;
    showBar: true;

    listFiles = [];

    fileHashes = [];
    listIdFiles = [];

    token: string;
    nombreAdjunto: String;
    sizeLimit = 8000000;
    /** aqui le doy 8mb*/

    colorC = true;
    colorP = true;
    colorF = true;

    constructor(
        private http: HttpClient,
        private sessionStorage: SessionStorageService,
        private uploadService: UploadFileService,
        private localStorage: LocalStorageService
    ) {
        // aqui solo limito la cantidad de archivos que se suben al mismo tiempo
        this.options = {concurrency: 1}; //  ,maxUploads: 3 agregarioa limite pero no funciona bien
        this.files = [];
        this.uploadInput = new EventEmitter<UploadInput>();
        this.humanizeBytes = humanizeBytes;
    }

    ngOnInit() {
        this.token = this.localStorage.retrieve('authenticationToken') || this.sessionStorage.retrieve('authenticationToken');
    }

    ngOnChanges() {
        if (typeof this.adjuntos !== 'undefined' && this.adjuntos !== null && this.adjuntos.length > 0) {
            this.listFiles = this.adjuntos;
        }
        if (typeof this.documentos !== 'undefined' && this.documentos !== null && this.documentos.length > 0) {

        }
    }

    onUploadOutput(output: UploadOutput): void {
        if (output.type === 'allAddedToQueue') {
            const event: UploadInput = {
                type: 'uploadAll',
                url: this.url,
                method: 'POST',
                headers: {
                    Authorization: 'Bearer ' + this.token
                },
                data: {foo: 'bar'}
            };
            this.uploadInput.emit(event);
        } else if (output.type === 'uploading' && typeof output.file !== 'undefined') {
            const index = this.files.findIndex(file => typeof output.file !== 'undefined' && file.id === output.file.id);
            this.files[index] = output.file;
        } else if (output.type === 'cancelled' || output.type === 'removed') {
            this.files = this.files.filter((file: UploadFile) => file !== output.file);
        } else if (output.type === 'dragOver') {
            this.dragOver = true;
        } else if (output.type === 'dragOut') {
            this.dragOver = false;
        } else if (output.type === 'drop') {
            this.dragOver = false;
        } else if (output.type === 'rejected' && typeof output.file !== 'undefined') {

        } else if (output.type === 'done') {
            this.files.forEach(file => {
                this.fileHashes.push(file.response.hash);
                this.fileResponse.emit(this.listFiles);
                this.listFiles.push(file.response.fileinfo);
            });
            console.log('list ', this.listFiles.length);
            /** condicion de subida de archivos*/
        } else if (output.type === 'addedToQueue' && typeof output.file !== 'undefined') {
            this.nombreAdjunto = output.file.name.substring(output.file.name.lastIndexOf('.') + 1).toLowerCase();
            console.log('nombre archivos: ', output.file.name, 'extension: ', this.nombreAdjunto, 'tamaño: ', output.file.size);
            /** cambio de color*/
            if (this.listFiles.length >= 3) {
                this.colorC = false;
                alert('No puedes subir mas de 3 archivos');
            } else {
                this.colorC = true;
            }
            if (output.file.size > this.sizeLimit) {
                this.colorP = false;
                alert('el archivo no puede pesar mas de 8Mb');
            } else {
                this.colorP = true;
            }
            if (this.nombreAdjunto !== 'pdf') {
                this.colorF = false;
                alert('El archivo debe ser PDF');
            } else {
                this.colorF = true;
            }
            /** condicion  debe ser menor a 3 archivos y menor al tamaño limite y formato pdf*/
            if (this.listFiles.length < 3 && output.file.size < this.sizeLimit && this.nombreAdjunto === 'pdf') {
                this.files.push(output.file);
                console.log('cantidad de archivos', this.listFiles.length);
            }
        }
        this.files = this.files.filter(file => file.progress.status !== UploadStatus.Done);
    }

    startUpload(): void {
        const event: UploadInput = {
            type: 'uploadAll',
            url: this.url,
            headers: {
                Authorization: 'Bearer ' + this.token
            },
            method: 'POST',
            data: {foo: 'bar'}
        };

        this.uploadInput.emit(event);
    }

    cancelUpload(id: string): void {
        this.uploadInput.emit({type: 'cancel', id});
    }

    /** este es el eliminar de la lista que se ve */
    removeFile(id: string): void {
        let hashAux = null;
        this.uploadService.delete(parseInt(id, 10)).subscribe(response => {
            this.listFiles = this.listFiles.filter(file => {
                if (file.id !== parseInt(id, 10)) {
                    return true;
                } else {
                    hashAux = file.hash;
                    /** solo devuelvo el color */
                    this.colorC = true;
                    this.colorP = true;
                    this.colorF = true;
                }
            });

            this.fileHashes = this.fileHashes.filter(hash => {
                if (hash !== hashAux) {
                    return true;
                }
            });

            this.fileResponse.emit(this.listFiles);
        });
    }

    removeDocument(id: string): void {
        console.log('delete document ' + id);
        this.uploadService.deleteDoc(parseInt(id, 10)).subscribe(response => {
            this.documentos = this.documentos.filter(doc => doc.id !== parseInt(id, 10));
            this.notDeletedDocument.emit(this.documentos);
        });
    }

    removeAllFiles(): void {
        this.uploadInput.emit({type: 'removeAll'});
    }
}
