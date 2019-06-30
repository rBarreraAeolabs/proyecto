import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAdjunto } from 'app/shared/model/adjunto.model';

type EntityResponseType = HttpResponse<IAdjunto>;
type EntityArrayResponseType = HttpResponse<IAdjunto[]>;

@Injectable({ providedIn: 'root' })
export class AdjuntoService {
    private resourceUrl = SERVER_API_URL + 'api/adjuntos';

    constructor(private http: HttpClient) {}

    create(adjunto: IAdjunto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(adjunto);
        return this.http
            .post<IAdjunto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(adjunto: IAdjunto): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(adjunto);
        return this.http
            .put<IAdjunto>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAdjunto>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAdjunto[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    download(hash: string): Observable<HttpResponse<Blob>> {
        return this.http.get(`${this.resourceUrl}/${hash}/download`, {observe: 'response', responseType: 'blob'});
    }

    private convertDateFromClient(adjunto: IAdjunto): IAdjunto {
        const copy: IAdjunto = Object.assign({}, adjunto, {
            fechaCreado: adjunto.fechaCreado != null && adjunto.fechaCreado.isValid() ? adjunto.fechaCreado.format(DATE_FORMAT) : null,
            fechaSubido: adjunto.fechaSubido != null && adjunto.fechaSubido.isValid() ? adjunto.fechaSubido.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fechaCreado = res.body.fechaCreado != null ? moment(res.body.fechaCreado) : null;
        res.body.fechaSubido = res.body.fechaSubido != null ? moment(res.body.fechaSubido) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((adjunto: IAdjunto) => {
            adjunto.fechaCreado = adjunto.fechaCreado != null ? moment(adjunto.fechaCreado) : null;
            adjunto.fechaSubido = adjunto.fechaSubido != null ? moment(adjunto.fechaSubido) : null;
        });
        return res;
    }
}
