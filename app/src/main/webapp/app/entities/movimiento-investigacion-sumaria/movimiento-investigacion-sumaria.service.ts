import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMovimientoInvestigacionSumaria } from 'app/shared/model/movimiento-investigacion-sumaria.model';

type EntityResponseType = HttpResponse<IMovimientoInvestigacionSumaria>;
type EntityArrayResponseType = HttpResponse<IMovimientoInvestigacionSumaria[]>;

@Injectable({ providedIn: 'root' })
export class MovimientoInvestigacionSumariaService {
    private resourceUrl = SERVER_API_URL + 'api/movimiento-investigacion-sumarias';

    constructor(private http: HttpClient) {}

    create(movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoInvestigacionSumaria);
        return this.http
            .post<IMovimientoInvestigacionSumaria>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(movimientoInvestigacionSumaria);
        return this.http
            .put<IMovimientoInvestigacionSumaria>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMovimientoInvestigacionSumaria>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMovimientoInvestigacionSumaria[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria): IMovimientoInvestigacionSumaria {
        const copy: IMovimientoInvestigacionSumaria = Object.assign({}, movimientoInvestigacionSumaria, {
            fecha:
                movimientoInvestigacionSumaria.fecha != null && movimientoInvestigacionSumaria.fecha.isValid()
                    ? movimientoInvestigacionSumaria.fecha.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((movimientoInvestigacionSumaria: IMovimientoInvestigacionSumaria) => {
            movimientoInvestigacionSumaria.fecha =
                movimientoInvestigacionSumaria.fecha != null ? moment(movimientoInvestigacionSumaria.fecha) : null;
        });
        return res;
    }
}
