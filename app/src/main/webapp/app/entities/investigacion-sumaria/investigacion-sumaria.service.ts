import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IInvestigacionSumaria} from 'app/shared/model/investigacion-sumaria.model';

type EntityResponseType = HttpResponse<IInvestigacionSumaria>;
type EntityArrayResponseType = HttpResponse<IInvestigacionSumaria[]>;

@Injectable({providedIn: 'root'})
export class InvestigacionSumariaService {
    private resourceUrl = SERVER_API_URL + 'api/investigacion-sumarias';

    constructor(private http: HttpClient) {
    }

    create(investigacionSumaria: IInvestigacionSumaria): Observable<EntityResponseType> {
        return this.http.post<IInvestigacionSumaria>(this.resourceUrl, investigacionSumaria, {observe: 'response'});
    }

    update(investigacionSumaria: IInvestigacionSumaria): Observable<EntityResponseType> {
        return this.http.put<IInvestigacionSumaria>(this.resourceUrl, investigacionSumaria, {observe: 'response'});
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInvestigacionSumaria>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInvestigacionSumaria[]>(this.resourceUrl, {params: options, observe: 'response'});
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }
}
