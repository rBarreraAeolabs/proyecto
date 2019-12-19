import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMovimientoProvidencia } from 'app/shared/model/movimiento-providencia.model';
import { IProvidencia } from 'app/shared/model/providencia.model';
import {IFiltroMovPro} from 'app/shared/model/filtroMovimiento.model';

type EntityResponseType = HttpResponse<IMovimientoProvidencia>;
type EntityArrayResponseType = HttpResponse<IMovimientoProvidencia[]>;

@Injectable({ providedIn: 'root' })
export class MovimientoProvidenciaService {
    private resourceUrl = SERVER_API_URL + 'api/movimiento-providencias';

    constructor(private http: HttpClient) {}

    create(movimientoProvidencia: IMovimientoProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoProvidencia);
        return this.http
            .post<IMovimientoProvidencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(movimientoProvidencia: IMovimientoProvidencia): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoProvidencia);
        return this.http
            .put<IMovimientoProvidencia>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMovimientoProvidencia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    findByIdProvidencia(filtroMoviPro: IFiltroMovPro): Observable<EntityArrayResponseType> {
        // const copy = this.convertDateFromClientProvidencia(filtroMoviPro);
        return this.http
            .post<IMovimientoProvidencia[]>(this.resourceUrl + '/providencia', filtroMoviPro, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    traerMovimientoDeLaProvidencia(id: number): Observable<EntityArrayResponseType> {
        // const copy = this.convertDateFromClientProvidencia(filtroMoviPro);
        return this.http
            .post<IMovimientoProvidencia[]>(this.resourceUrl + '/providencia/movimiento', id, { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    getAllWithoutPagination(): Observable<EntityArrayResponseType> {
        return this.http
            .get<IMovimientoProvidencia[]>(this.resourceUrl + '/list', { observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMovimientoProvidencia[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(movimientoProvidencia: IMovimientoProvidencia): IMovimientoProvidencia {
        const copy: IMovimientoProvidencia = Object.assign({}, movimientoProvidencia, {
            fecha:
                movimientoProvidencia.fecha != null && movimientoProvidencia.fecha.isValid() ? movimientoProvidencia.fecha.toJSON() : null
        });
        return copy;
    }

    private convertDateFromClientProvidencia(providencia: IProvidencia): IProvidencia {
        const copy: IProvidencia = Object.assign({}, providencia, {
            fechaSolicitud:
                providencia.fechaSolicitud != null && providencia.fechaSolicitud.isValid() ? providencia.fechaSolicitud.toJSON() : null,
            fechaCreacion:
                providencia.fechaCreacion != null && providencia.fechaCreacion.isValid() ? providencia.fechaCreacion.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((movimientoProvidencia: IMovimientoProvidencia) => {
            movimientoProvidencia.fecha = movimientoProvidencia.fecha != null ? moment(movimientoProvidencia.fecha) : null;
        });
        return res;
    }

    private convertDateArrayProvidenciaFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((providencia: IProvidencia) => {
            providencia.fechaSolicitud = providencia.fechaSolicitud != null ? moment(providencia.fechaSolicitud) : null;
            providencia.fechaCreacion = providencia.fechaCreacion != null ? moment(providencia.fechaCreacion) : null;
        });
        return res;
    }
}
