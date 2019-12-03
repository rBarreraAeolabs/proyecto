import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInstrucciones } from 'app/shared/model/Instrucciones.model';

type EntityResponseType = HttpResponse<IInstrucciones>;
type EntityArrayResponseType = HttpResponse<IInstrucciones[]>;

@Injectable({ providedIn: 'root' })
export class InstruccionesService {
    private resourceUrl = SERVER_API_URL + 'api/instrucciones';

    constructor(private http: HttpClient) {}

    create(instrucciones: IInstrucciones): Observable<EntityResponseType> {
        return this.http.post<IInstrucciones>(this.resourceUrl, instrucciones, { observe: 'response' });
    }

    update(instrucciones: IInstrucciones): Observable<EntityResponseType> {
        return this.http.put<IInstrucciones>(this.resourceUrl, instrucciones, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInstrucciones>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInstrucciones[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IInstrucciones[]>(`${this.resourceUrl}/all`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
