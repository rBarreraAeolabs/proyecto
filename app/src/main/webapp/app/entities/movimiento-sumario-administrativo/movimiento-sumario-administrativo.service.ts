import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMovimientoSumarioAdministrativo } from 'app/shared/model/movimiento-sumario-administrativo.model';

type EntityResponseType = HttpResponse<IMovimientoSumarioAdministrativo>;
type EntityArrayResponseType = HttpResponse<IMovimientoSumarioAdministrativo[]>;

@Injectable({ providedIn: 'root' })
export class MovimientoSumarioAdministrativoService {
    private resourceUrl = SERVER_API_URL + 'api/movimiento-sumario-administrativos';

    constructor(private http: HttpClient) {}

    create(movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoSumarioAdministrativo);
        return this.http
            .post<IMovimientoSumarioAdministrativo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoSumarioAdministrativo);
        return this.http
            .put<IMovimientoSumarioAdministrativo>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMovimientoSumarioAdministrativo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMovimientoSumarioAdministrativo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo): IMovimientoSumarioAdministrativo {
        const copy: IMovimientoSumarioAdministrativo = Object.assign({}, movimientoSumarioAdministrativo, {
            fecha:
                movimientoSumarioAdministrativo.fecha != null && movimientoSumarioAdministrativo.fecha.isValid()
                    ? movimientoSumarioAdministrativo.fecha.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((movimientoSumarioAdministrativo: IMovimientoSumarioAdministrativo) => {
            movimientoSumarioAdministrativo.fecha =
                movimientoSumarioAdministrativo.fecha != null ? moment(movimientoSumarioAdministrativo.fecha) : null;
        });
        return res;
    }
}
