import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';

@Injectable({
    providedIn: 'root'
})
export class UploadFileService {
    private resourceUrl = SERVER_API_URL + 'api/adjuntos';
    private resourceUrlDoc = SERVER_API_URL + 'api/documentos';

    constructor(private http: HttpClient) {}

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    deleteDoc(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrlDoc}/${id}`, { observe: 'response' });
    }

    // pushFileToStorage(file: File): Observable<HttpEvent<[]>> {
    //     const formdata: FormData = new FormData();
    //
    //     console.log('format data', formdata);
    //
    //     formdata.append('file', file);
    //
    //     console.log('file', file);
    //
    //     return this.http.post<any>(this.resourceUrlPostFiles, formdata, {
    //         observe: 'response',
    //         reportProgress: true
    //     });
    // }
    //
    // getFiles(): Observable<any> {
    //     console.log('archivos retornados ');
    //     return this.http.get(this.resourceUrlGetAllFiles);
    // }
}
