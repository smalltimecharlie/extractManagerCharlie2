import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractParts } from 'app/shared/model/extract-parts.model';

type EntityResponseType = HttpResponse<IExtractParts>;
type EntityArrayResponseType = HttpResponse<IExtractParts[]>;

@Injectable({ providedIn: 'root' })
export class ExtractPartsService {
    public resourceUrl = SERVER_API_URL + 'api/extract-parts';

    constructor(protected http: HttpClient) {}

    create(extractParts: IExtractParts): Observable<EntityResponseType> {
        return this.http.post<IExtractParts>(this.resourceUrl, extractParts, { observe: 'response' });
    }

    update(extractParts: IExtractParts): Observable<EntityResponseType> {
        return this.http.put<IExtractParts>(this.resourceUrl, extractParts, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IExtractParts>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IExtractParts[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
