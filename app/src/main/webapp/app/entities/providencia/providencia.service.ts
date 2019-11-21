import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProvidencia, IProvidenciaResponse, IProvidenciaUpdateForType, IProvidenciaUpdateNroReferencia, IProvidenciaUpdateTipoSolicitud} from 'app/shared/model/providencia.model';
import { IPlantilla} from '../../shared/model/plantilla.model';
// librerias para descargar formato excel
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

type EntityResponseType = HttpResponse<IProvidencia>;
type EntityArrayResponseType = HttpResponse<IProvidencia[]>;

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

@Injectable({ providedIn: 'root' })
export class ProvidenciaService {
    private resourceUrl = SERVER_API_URL + 'api/providencias';

    constructor(private http: HttpClient) {}

    public exportAsExcelFile(json: any[], excelFileName: string): void {
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
        this.saveAsExcelFile(excelBuffer, excelFileName);
    }
    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
        FileSaver.saveAs(data, fileName + '_export_' + new  Date().getTime() + EXCEL_EXTENSION);
    }

    create(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http
            .post<IProvidencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http
            .put<IProvidencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    providenciaUpdateForType(providenciaUpdateForType: IProvidenciaUpdateForType): Observable<EntityResponseType> {
        console.log('este es el objeto' , providenciaUpdateForType);
        return this.http
            .put<IProvidencia>(`${this.resourceUrl}/updateProvidenciaForType`, providenciaUpdateForType, { observe: 'response'} )
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    updateFiscal(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http.
        put<IProvidencia>(`${this.resourceUrl}/fiscal`, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    updateNroReferencia(providencia: IProvidenciaUpdateNroReferencia): Observable<EntityResponseType> {
        return this.http.
        put<IProvidencia>(`${this.resourceUrl}/nroReferencia`, providencia, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    updateTipoSolicitud(providencia: IProvidenciaUpdateTipoSolicitud): Observable<EntityResponseType> {
        console.log('almacenando la providencia', providencia);
        return this.http.
        put<IProvidencia>(`${this.resourceUrl}/tipoSolicitud`, providencia, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProvidencia>(`${this.resourceUrl}/detalle/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProvidencia[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    reply(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/reply', response, {observe: 'response'});
    }

    terminoProbatorio(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/terminoProbatorio', response, {observe: 'response'});
    }

    siDeAcuerdo(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/siDeAcuerdo', response, {observe: 'response'});
    }
    noPropone(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/noPropone', response, {observe: 'response'});
    }
    noReabro(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/noReabro', response, {observe: 'response'});
    }
    aceptar(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/aceptar', response, {observe: 'response'});
    }

    rechazar(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/rechazar', response, {observe: 'response'});
    }

    goBackwards(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/comeback', response, {observe: 'response'});
    }

    prorroga(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/prorroga', response, {observe: 'response'});
    }

    inculpadoNoEnviaMemo(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/inculpadoNoEnviaMemo', response, {observe: 'response'});
    }

    inculpadoEnviaMemo(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/inculpadoEnviaMemo', response, {observe: 'response'});
    }
    updNotificaInculpado(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/notificaDemandado', response, {observe: 'response'});
    }
    updateFolio(providencia: IProvidenciaUpdateNroReferencia): Observable<EntityResponseType> {
        return this.http.
        put<IProvidencia>(`${this.resourceUrl}/updateFolio`, providencia, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }
        formulaCargos(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/formularCargos', response, {observe: 'response'});
    }

    remiteExpediente(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/remiteExpediente', response, {observe: 'response'});
    }

    apela(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/apela', response, {observe: 'response'});
    }

    noApela(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/noApela', response, {observe: 'response'});
    }

    fiscalNotificaCierre(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/fiscalNotificaCierre', response, {observe: 'response'});
    }

    getActionsPermitted(providencia: IProvidencia): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/actions', providencia, {observe: 'response'});
    }

    getPlantillasEnabled(providencia: IProvidencia): Observable<HttpResponse<IPlantilla[]>> {
        return this.http.post<IPlantilla[]>(this.resourceUrl + '/templates', providencia, {observe: 'response'});
    }

    getAllByRunOrEntidadImplicada(run: string, entidadImplicadaId: number, providenciaId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IProvidencia[]>(`${this.resourceUrl}/related/${run}/${entidadImplicadaId}/${providenciaId}`, {observe: 'response'});
    }

    findByFiscal( providecia: IProvidencia): Observable<EntityResponseType> {
        return this.http
            .post<IProvidencia>(`${this.resourceUrl}/providencia`, providecia, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    getAllWithoutPagination(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IProvidencia[]>(`${this.resourceUrl}/buscarNroReferencia`, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    private convertDateFromClient(providencia: IProvidencia): IProvidencia {
        const copy: IProvidencia = Object.assign({}, providencia, {
            fechaSolicitud:
                providencia.fechaSolicitud != null && providencia.fechaSolicitud.isValid() ? providencia.fechaSolicitud.toJSON() : null,
            fechaCreacion:
                providencia.fechaCreacion != null && providencia.fechaCreacion.isValid() ? providencia.fechaCreacion.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fechaSolicitud = res.body.fechaSolicitud != null ? moment(res.body.fechaSolicitud) : null;
        res.body.fechaCreacion = res.body.fechaCreacion != null ? moment(res.body.fechaCreacion) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((providencia: IProvidencia) => {
            providencia.fechaSolicitud = providencia.fechaSolicitud != null ? moment(providencia.fechaSolicitud) : null;
            providencia.fechaCreacion = providencia.fechaCreacion != null ? moment(providencia.fechaCreacion) : null;
        });
        return res;
    }

    tomaRazon(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/tomaRazon', response, {observe: 'response'});
    }
    registra(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/registra', response, {observe: 'response'});
    }
    representa(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/representa', response, {observe: 'response'});
    }
}
