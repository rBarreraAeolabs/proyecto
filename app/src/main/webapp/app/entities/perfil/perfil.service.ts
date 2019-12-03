import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPerfil } from 'app/shared/model/perfil.model';
import * as moment from "moment";
import {IGrupo} from "app/shared/model/grupo.model";
import {map} from "rxjs/operators";

type EntityResponseType = HttpResponse<IPerfil>;
type EntityArrayResponseType = HttpResponse<IPerfil[]>;

@Injectable({ providedIn: 'root' })
export class PerfilService {
    private resourceUrl = SERVER_API_URL + 'api/perfils';

    constructor(private http: HttpClient) {}

    create(perfil: IPerfil): Observable<EntityResponseType> {
        console.log(perfil);
        return this.http.post<IPerfil>(this.resourceUrl, perfil, { observe: 'response' });
    }

    update(perfil: IPerfil): Observable<EntityResponseType> {
        return this.http.put<IPerfil>(this.resourceUrl, perfil, { observe: 'response' });
    }

    // find(id: number): Observable<EntityResponseType> {
    //     return this.http.get<IPerfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    // }
    //
    // query(req?: any): Observable<EntityArrayResponseType> {
    //     const options = createRequestOption(req);
    //     return this.http.get<IPerfil[]>(this.resourceUrl, { params: options, observe: 'response' });
    // }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPerfil>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any, filtro?: any): Observable<EntityArrayResponseType> {
        let options = createRequestOption(req);

        options = options.set('nombre', filtro.nombre);

        return this.http
            .get<IPerfil[]>(this.resourceUrl + '/paged', { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/perfil/authorities');
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
