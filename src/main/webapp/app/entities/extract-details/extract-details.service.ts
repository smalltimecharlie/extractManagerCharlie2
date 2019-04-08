import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractDetails } from 'app/shared/model/extract-details.model';

type EntityResponseType = HttpResponse<IExtractDetails>;
type EntityArrayResponseType = HttpResponse<IExtractDetails[]>;

@Injectable({ providedIn: 'root' })
export class ExtractDetailsService {
    public resourceUrl = SERVER_API_URL + 'api/extract-details';

    constructor(protected http: HttpClient) {}

    create(extractDetails: IExtractDetails): Observable<EntityResponseType> {
        return this.http.post<IExtractDetails>(this.resourceUrl, extractDetails, { observe: 'response' });
    }

    update(extractDetails: IExtractDetails): Observable<EntityResponseType> {
        return this.http.put<IExtractDetails>(this.resourceUrl, extractDetails, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IExtractDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IExtractDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
