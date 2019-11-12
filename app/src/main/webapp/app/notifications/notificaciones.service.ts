import {SERVER_API_URL} from 'app/app.constants';
import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {createRequestOption} from 'app/shared';

type EntityResponseType = HttpResponse<any>;
type EntityArrayResponseType = HttpResponse<any>;

@Injectable({ providedIn: 'root' })
export class NotificacionService {
    public resourceUrl = SERVER_API_URL + 'api/notificacion-in-browsers';

    constructor(protected http: HttpClient) {}

    create(notificacion: any): Observable<EntityResponseType> {
        return this.http.post<any>(this.resourceUrl, notificacion, { observe: 'response' });
    }

    update(notificacion: any): Observable<EntityResponseType> {
        return this.http.put<any>(this.resourceUrl, notificacion, { observe: 'response' });
    }

    find(userId: number): Observable<EntityResponseType> {
        return this.http.get<any>(`${this.resourceUrl}/${userId}`, { observe: 'response' });
    }

    marcarLeidas(id: number): Observable<EntityResponseType> {
        return this.http.put<any>(`${this.resourceUrl}/noti/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<any[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    notificacionCount(): Observable<EntityArrayResponseType> {
        return this.http
            .get<any>(`${this.resourceUrl}/count`, { observe: 'response' });

    }

    listarNotificacionNoLeidas(): Observable<EntityArrayResponseType> {
        return this.http
            .get<any>(`${this.resourceUrl}/no-leidas`, { observe: 'response' });

    }

    listarNotificaciones(): Observable<EntityArrayResponseType> {
        return this.http
            .get<any>(`${this.resourceUrl}`, { observe: 'response' });

    }
}
