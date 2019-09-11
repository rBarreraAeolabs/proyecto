import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IProvidencia, IProvidenciaResponse, IProvidenciaUpdateMadre} from 'app/shared/model/providencia.model';
import {IPlantilla} from '../../shared/model/plantilla.model';

type EntityResponseType = HttpResponse<IProvidencia>;
type EntityArrayResponseType = HttpResponse<IProvidencia[]>;

@Injectable({providedIn: 'root'})
export class ProvidenciaService {
    private resourceUrl = SERVER_API_URL + 'api/providencias';

    constructor(private http: HttpClient) {
    }

    create(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http
            .post<IProvidencia>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http
            .put<IProvidencia>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    updateProvidenciaMadre(providenciaUpdateMadre: IProvidenciaUpdateMadre): Observable<EntityResponseType> {
        return this.http
            .put<IProvidencia>(`${this.resourceUrl}/madre`, providenciaUpdateMadre, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    updateFiscal(providencia: IProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(providencia);
        return this.http.put<IProvidencia>(`${this.resourceUrl}/fiscal`, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IProvidencia>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IProvidencia[]>(this.resourceUrl, {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    reply(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/reply', response, {observe: 'response'});
    }

    goBackwards(response: IProvidenciaResponse): Observable<HttpResponse<any>> {
        return this.http.post<any>(this.resourceUrl + '/comeback', response, {observe: 'response'});
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

    findByFiscal(providecia: IProvidencia): Observable<EntityResponseType> {
        return this.http
            .post<IProvidencia>(`${this.resourceUrl}/providencia`, providecia, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    getAllWithoutPagination(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IProvidencia[]>(`${this.resourceUrl}/all`, {observe: 'response'})
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
}
