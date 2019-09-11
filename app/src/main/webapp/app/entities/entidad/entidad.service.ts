import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IEntidad} from 'app/shared/model/entidad.model';

type EntityResponseType = HttpResponse<IEntidad>;
type EntityArrayResponseType = HttpResponse<IEntidad[]>;

@Injectable({providedIn: 'root'})
export class EntidadService {
    private resourceUrl = SERVER_API_URL + 'api/entidades';

    constructor(private http: HttpClient) {
    }

    create(entidad: IEntidad): Observable<EntityResponseType> {
        console.log('ESTOY DE VIO EN EL SERVICE', entidad);
        return this.http.post<IEntidad>(this.resourceUrl, entidad, {observe: 'response'});
    }

    update(entidad: IEntidad): Observable<EntityResponseType> {
        return this.http.put<IEntidad>(this.resourceUrl, entidad, {observe: 'response'});
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEntidad>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEntidad[]>(this.resourceUrl, {params: options, observe: 'response'});
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IEntidad[]>(`${this.resourceUrl}/all`, {observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
