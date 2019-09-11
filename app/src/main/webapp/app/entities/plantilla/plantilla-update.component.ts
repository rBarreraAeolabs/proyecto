import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IPlantilla} from 'app/shared/model/plantilla.model';
import {PlantillaService} from './plantilla.service';

@Component({
    selector: 'jhi-plantilla-update',
    templateUrl: './plantilla-update.component.html'
})
export class PlantillaUpdateComponent implements OnInit {
    private _plantilla: IPlantilla;
    isSaving: boolean;
    editorStyle = {
        height: '400px'
    };

    constructor(private plantillaService: PlantillaService, private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({plantilla}) => {
            this.plantilla = plantilla;
            console.log('plantilla ', this.plantilla.contenido);
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantilla.id !== undefined) {
            this.subscribeToSaveResponse(this.plantillaService.update(this.plantilla));
        } else {
            this.subscribeToSaveResponse(this.plantillaService.create(this.plantilla));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlantilla>>) {
        result.subscribe((res: HttpResponse<IPlantilla>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    get plantilla() {
        return this._plantilla;
    }

    set plantilla(plantilla: IPlantilla) {
        this._plantilla = plantilla;
    }

    onChange(e) {
        console.log('onChange', e);
    }

    onEditorChange(e) {
        console.log('onEditorChange', e);
    }

    onReady(e) {
        console.log('onReady', e);
    }

    onFocus(e) {
        console.log('onFocus', e);
    }

    onBlur(e) {
        console.log('onBlur', e);
    }

    onContentDom(e) {
        console.log('onContentDom', e);
    }

    onFileUploadRequest(e) {
        console.log('onFileUploadRequest', e);
    }

    onFileUploadResponse(e) {
        console.log('onFileUploadResponse', e);
    }

    onPaste(e) {
        console.log('onPaste', e);
    }

    onDrop(e) {
        console.log('onDrop', e);
    }
}
