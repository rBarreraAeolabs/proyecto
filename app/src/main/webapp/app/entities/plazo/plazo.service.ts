import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlazo } from 'app/shared/model/plazo.model';
import {IRespuesta} from '../../shared/model/respuesta.model';

type EntityResponseType = HttpResponse<IPlazo>;
type EntityArrayResponseType = HttpResponse<IPlazo[]>;

@Injectable({ providedIn: 'root' })
export class PlazoService {
    private resourceUrl = SERVER_API_URL + 'api/plazos';

    constructor(private http: HttpClient) {}

    create(plazo: IPlazo): Observable<EntityResponseType> {
        return this.http.post<IPlazo>(this.resourceUrl, plazo, { observe: 'response' });
    }

    update(plazo: IPlazo): Observable<EntityResponseType> {
        return this.http.put<IPlazo>(this.resourceUrl, plazo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlazo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlazo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IRespuesta[]>(`${this.resourceUrl}/all`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
