import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGrupo } from 'app/shared/model/grupo.model';

type EntityResponseType = HttpResponse<IGrupo>;
type EntityArrayResponseType = HttpResponse<IGrupo[]>;

@Injectable({ providedIn: 'root' })
export class GrupoService {
    private resourceUrl = SERVER_API_URL + 'api/grupos';
    constructor(private http: HttpClient) {}

    create(grupo: IGrupo): Observable<EntityResponseType> {
        return this.http.post<IGrupo>(this.resourceUrl, grupo, { observe: 'response' });
    }

    update(grupo: IGrupo): Observable<EntityResponseType> {
        return this.http.put<IGrupo>(this.resourceUrl, grupo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IGrupo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any, filtro?: any): Observable<EntityArrayResponseType> {
        let options = createRequestOption(req);

        options = options.set('nombre', filtro.nombre);

        return this.http
            .get<IGrupo[]>(this.resourceUrl + '/paged', { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getAllAsList(): Observable<EntityArrayResponseType> {
        return this.http.get<IGrupo[]>(this.resourceUrl + '/list', { observe: 'response' });
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.f_nacimiento = res.body.f_nacimiento != null ? moment(res.body.f_nacimiento) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((grupo: IGrupo) => {
                grupo.f_nacimiento = grupo.f_nacimiento != null ? moment(grupo.f_nacimiento) : null;
            });
        }
        return res;
    }
}
