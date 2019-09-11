import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IProvidenciaDerivacion} from 'app/shared/model/providencia-derivacion.model';
import {IDerivacion} from '../../shared/model/derivacion.model';

type EntityResponseTypeDerivacion = HttpResponse<IDerivacion>;
type EntityArrayResponseTypeDerivacion = HttpResponse<IDerivacion[]>;
// IProvidenciaDerivacion
type EntityResponseTypeDP = HttpResponse<IProvidenciaDerivacion>;
type EntityArrayResponseTypeDP = HttpResponse<IProvidenciaDerivacion[]>;

@Injectable({providedIn: 'root'})
export class DerivacionService {
    private resourceUrl = SERVER_API_URL + 'api/derivacions';

    constructor(private http: HttpClient) {
    }

    create(derivacion: IDerivacion): Observable<EntityResponseTypeDerivacion> {
        const copy = this.convertDateFromClientDerivacion(derivacion);
        return this.http
            .post<IDerivacion>(this.resourceUrl, copy, {observe: 'response'})
            .pipe(map((res: EntityResponseTypeDerivacion) => this.convertDateFromServerDerivacion(res)));
    }

    update(providenciaDerivacion: IProvidenciaDerivacion): Observable<EntityResponseTypeDP> {
        return this.http.put(this.resourceUrl, providenciaDerivacion, {observe: 'response'});
    }

    find(id: number): Observable<EntityResponseTypeDerivacion> {
        return this.http
            .get<IDerivacion>(`${this.resourceUrl}/${id}`, {observe: 'response'})
            .pipe(map((res: EntityResponseTypeDerivacion) => this.convertDateFromServerDerivacion(res)));
    }

    query(req?: any): Observable<EntityArrayResponseTypeDerivacion> {
        const options = createRequestOption(req);
        return this.http
            .get<IDerivacion[]>(this.resourceUrl + '/paged', {params: options, observe: 'response'})
            .pipe(map((res: EntityArrayResponseTypeDerivacion) => this.convertDateArrayFromServerDerivacion(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    getByProvidencia(idProvidencia: number): Observable<EntityArrayResponseTypeDerivacion> {
        return this.http
            .get<IDerivacion[]>(`${this.resourceUrl}/list/${idProvidencia}`, {observe: 'response'})
            .pipe(map((res: EntityArrayResponseTypeDerivacion) => this.convertDateArrayFromServerDerivacion(res)));
    }

    private convertDateFromClientDerivacion(derivacion: IDerivacion): IDerivacion {
        const copy: IDerivacion = Object.assign({}, derivacion, {
            fechaDerivacion:
                derivacion.fechaDerivacion != null && derivacion.fechaDerivacion.isValid() ? derivacion.fechaDerivacion.toJSON() : null
        });
        return copy;
    }

    private convertDateFromClientDP(derivacion: IProvidenciaDerivacion): IProvidenciaDerivacion {
        const copy: IProvidenciaDerivacion = Object.assign({}, derivacion, {
            fechaDerivacion:
                derivacion.derivacionDTO.fechaDerivacion != null && derivacion.derivacionDTO.fechaDerivacion.isValid()
                    ? derivacion.derivacionDTO.fechaDerivacion.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServerDerivacion(res: EntityResponseTypeDerivacion): EntityResponseTypeDerivacion {
        res.body.fechaDerivacion = res.body.fechaDerivacion != null ? moment(res.body.fechaDerivacion) : null;
        return res;
    }

    private convertDateFromServerDP(res: EntityResponseTypeDP): EntityResponseTypeDP {
        res.body.derivacionDTO.fechaDerivacion =
            res.body.derivacionDTO.fechaDerivacion != null ? moment(res.body.derivacionDTO.fechaDerivacion) : null;
        return res;
    }

    private convertDateArrayFromServerDerivacion(res: EntityArrayResponseTypeDerivacion): EntityArrayResponseTypeDerivacion {
        res.body.forEach((derivacion: IDerivacion) => {
            derivacion.fechaDerivacion = derivacion.fechaDerivacion != null ? moment(derivacion.fechaDerivacion) : null;
        });
        return res;
    }

    private convertDateArrayFromServerDP(res: EntityArrayResponseTypeDP): EntityArrayResponseTypeDP {
        res.body.forEach((derivacion: IProvidenciaDerivacion) => {
            derivacion.derivacionDTO.fechaDerivacion =
                derivacion.derivacionDTO.fechaDerivacion != null ? moment(derivacion.derivacionDTO.fechaDerivacion) : null;
        });
        return res;
    }
}
