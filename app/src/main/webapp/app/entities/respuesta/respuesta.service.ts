import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import {IRespuesta} from '../../shared/model/respuesta.model';
import {IProvidencia} from '../../shared/model/providencia.model';

type EntityResponseType = HttpResponse<IRespuesta>;
type EntityArrayResponseType = HttpResponse<IRespuesta[]>;

@Injectable({ providedIn: 'root' })
export class RespuestaService {
    private resourceUrl = SERVER_API_URL + 'api/respuestas';

    constructor(private http: HttpClient) {}

    create(respuesta: IRespuesta): Observable<EntityResponseType> {
        return this.http.post<IRespuesta>(this.resourceUrl, respuesta, { observe: 'response' });
    }

    update(respuesta: IRespuesta): Observable<EntityResponseType> {
        return this.http.put<IRespuesta>(this.resourceUrl, respuesta, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IRespuesta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IRespuesta[]>(`${this.resourceUrl}/all`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    getByProvidencia(providencia: IProvidencia): Observable<EntityResponseType> {
        return this.http.post<IRespuesta>(`${this.resourceUrl}/providencia`, providencia, { observe: 'response' });
    }

    getByMovimientoProvidencia(movimientoProvidenciaId: number): Observable<EntityResponseType> {
        return this.http.get<IRespuesta>(`${this.resourceUrl}/movimiento-providencia/${movimientoProvidenciaId}`, { observe: 'response' });
    }
}
