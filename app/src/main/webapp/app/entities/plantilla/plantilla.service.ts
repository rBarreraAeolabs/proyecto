import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantilla } from 'app/shared/model/plantilla.model';

type EntityResponseType = HttpResponse<IPlantilla>;
type EntityArrayResponseType = HttpResponse<IPlantilla[]>;

@Injectable({ providedIn: 'root' })
export class PlantillaService {
    private resourceUrl = SERVER_API_URL + 'api/plantillas';

    constructor(private http: HttpClient) {}

    create(plantilla: IPlantilla): Observable<EntityResponseType> {
        return this.http.post<IPlantilla>(this.resourceUrl, plantilla, { observe: 'response' });
    }

    update(plantilla: IPlantilla): Observable<EntityResponseType> {
        return this.http.put<IPlantilla>(this.resourceUrl, plantilla, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantilla>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantilla[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IPlantilla[]>(this.resourceUrl + '/list', {observe: 'response'});
    }
}
