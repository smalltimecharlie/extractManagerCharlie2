import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractTables } from 'app/shared/model/extract-tables.model';

type EntityResponseType = HttpResponse<IExtractTables>;
type EntityArrayResponseType = HttpResponse<IExtractTables[]>;

@Injectable({ providedIn: 'root' })
export class ExtractTablesService {
    public resourceUrl = SERVER_API_URL + 'api/extract-tables';

    constructor(protected http: HttpClient) {}

    create(extractTables: IExtractTables): Observable<EntityResponseType> {
        return this.http.post<IExtractTables>(this.resourceUrl, extractTables, { observe: 'response' });
    }

    update(extractTables: IExtractTables): Observable<EntityResponseType> {
        return this.http.put<IExtractTables>(this.resourceUrl, extractTables, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IExtractTables>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IExtractTables[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
