import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractConfig } from 'app/shared/model/extract-config.model';

type EntityResponseType = HttpResponse<IExtractConfig>;
type EntityArrayResponseType = HttpResponse<IExtractConfig[]>;

@Injectable({ providedIn: 'root' })
export class ExtractConfigService {
    public resourceUrl = SERVER_API_URL + 'api/extract-configs';

    constructor(protected http: HttpClient) {}

    create(extractConfig: IExtractConfig): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractConfig);
        return this.http
            .post<IExtractConfig>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(extractConfig: IExtractConfig): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractConfig);
        return this.http
            .put<IExtractConfig>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IExtractConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IExtractConfig[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(extractConfig: IExtractConfig): IExtractConfig {
        const copy: IExtractConfig = Object.assign({}, extractConfig, {
            createdDate:
                extractConfig.createdDate != null && extractConfig.createdDate.isValid() ? extractConfig.createdDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((extractConfig: IExtractConfig) => {
                extractConfig.createdDate = extractConfig.createdDate != null ? moment(extractConfig.createdDate) : null;
            });
        }
        return res;
    }
}
