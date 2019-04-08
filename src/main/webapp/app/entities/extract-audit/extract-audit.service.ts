import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractAudit } from 'app/shared/model/extract-audit.model';

type EntityResponseType = HttpResponse<IExtractAudit>;
type EntityArrayResponseType = HttpResponse<IExtractAudit[]>;

@Injectable({ providedIn: 'root' })
export class ExtractAuditService {
    public resourceUrl = SERVER_API_URL + 'api/extract-audits';

    constructor(protected http: HttpClient) {}

    create(extractAudit: IExtractAudit): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractAudit);
        return this.http
            .post<IExtractAudit>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(extractAudit: IExtractAudit): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractAudit);
        return this.http
            .put<IExtractAudit>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IExtractAudit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IExtractAudit[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(extractAudit: IExtractAudit): IExtractAudit {
        const copy: IExtractAudit = Object.assign({}, extractAudit, {
            createdDate: extractAudit.createdDate != null && extractAudit.createdDate.isValid() ? extractAudit.createdDate.toJSON() : null
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
            res.body.forEach((extractAudit: IExtractAudit) => {
                extractAudit.createdDate = extractAudit.createdDate != null ? moment(extractAudit.createdDate) : null;
            });
        }
        return res;
    }
}
