import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDocumento } from 'app/shared/model/documento.model';
import {IProvidencia} from 'app/shared/model/providencia.model';

type EntityResponseType = HttpResponse<IDocumento>;
type EntityArrayResponseType = HttpResponse<IDocumento[]>;

@Injectable({ providedIn: 'root' })
export class DocumentoService {
    private resourceUrl = SERVER_API_URL + 'api/documentos';

    constructor(private http: HttpClient) {}

    create(documento: IDocumento): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(documento);
        return this.http
            .post<IDocumento>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(documento: IDocumento): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(documento);
        return this.http
            .put<IDocumento>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDocumento>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDocumento[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    download(hash: string): Observable<HttpResponse<Blob>> {
        return this.http.get(`${this.resourceUrl}/${hash}/download`, { observe: 'response', responseType: 'blob' });
    }

    findByProvidencia( providecia: IProvidencia): Observable<EntityResponseType> {
        return this.http
            .post<IDocumento>(`${this.resourceUrl}/providencia`, providecia, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    private convertDateFromClient(documento: IDocumento): IDocumento {
        const copy: IDocumento = Object.assign({}, documento, {
            fechaCreado: documento.fechaCreado != null && documento.fechaCreado.isValid() ? documento.fechaCreado.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fechaCreado = res.body.fechaCreado != null ? moment(res.body.fechaCreado) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((documento: IDocumento) => {
            documento.fechaCreado = documento.fechaCreado != null ? moment(documento.fechaCreado) : null;
        });
        return res;
    }

}
