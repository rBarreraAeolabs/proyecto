import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFichaIngresoSdj } from 'app/shared/model/ficha-ingreso-sdj.model';

type EntityResponseType = HttpResponse<IFichaIngresoSdj>;
type EntityArrayResponseType = HttpResponse<IFichaIngresoSdj[]>;

@Injectable({ providedIn: 'root' })
export class FichaIngresoSdjService {
    private resourceUrl = SERVER_API_URL + 'api/ficha-ingreso-sdjs';

    constructor(private http: HttpClient) {}

    create(fichaIngresoSdj: IFichaIngresoSdj): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fichaIngresoSdj);
        return this.http
            .post<IFichaIngresoSdj>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(fichaIngresoSdj: IFichaIngresoSdj): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(fichaIngresoSdj);
        return this.http
            .put<IFichaIngresoSdj>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFichaIngresoSdj>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFichaIngresoSdj[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(fichaIngresoSdj: IFichaIngresoSdj): IFichaIngresoSdj {
        const copy: IFichaIngresoSdj = Object.assign({}, fichaIngresoSdj, {
            fechaInicio:
                fichaIngresoSdj.fechaInicio != null && fichaIngresoSdj.fechaInicio.isValid() ? fichaIngresoSdj.fechaInicio.toJSON() : null,
            fechaHasta:
                fichaIngresoSdj.fechaHasta != null && fichaIngresoSdj.fechaHasta.isValid() ? fichaIngresoSdj.fechaHasta.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fechaInicio = res.body.fechaInicio != null ? moment(res.body.fechaInicio) : null;
        res.body.fechaHasta = res.body.fechaHasta != null ? moment(res.body.fechaHasta) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((fichaIngresoSdj: IFichaIngresoSdj) => {
            fichaIngresoSdj.fechaInicio = fichaIngresoSdj.fechaInicio != null ? moment(fichaIngresoSdj.fechaInicio) : null;
            fichaIngresoSdj.fechaHasta = fichaIngresoSdj.fechaHasta != null ? moment(fichaIngresoSdj.fechaHasta) : null;
        });
        return res;
    }
}
