import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISumarioAdministrativo } from 'app/shared/model/sumario-administrativo.model';

type EntityResponseType = HttpResponse<ISumarioAdministrativo>;
type EntityArrayResponseType = HttpResponse<ISumarioAdministrativo[]>;

@Injectable({ providedIn: 'root' })
export class SumarioAdministrativoService {
    private resourceUrl = SERVER_API_URL + 'api/sumario-administrativos';

    constructor(private http: HttpClient) {}

    create(sumarioAdministrativo: ISumarioAdministrativo): Observable<EntityResponseType> {
        return this.http.post<ISumarioAdministrativo>(this.resourceUrl, sumarioAdministrativo, { observe: 'response' });
    }

    update(sumarioAdministrativo: ISumarioAdministrativo): Observable<EntityResponseType> {
        return this.http.put<ISumarioAdministrativo>(this.resourceUrl, sumarioAdministrativo, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISumarioAdministrativo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISumarioAdministrativo[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
