import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';

type EntityResponseType = HttpResponse<IExtractOrganisation>;
type EntityArrayResponseType = HttpResponse<IExtractOrganisation[]>;

@Injectable({ providedIn: 'root' })
export class ExtractOrganisationService {
    public resourceUrl = SERVER_API_URL + 'api/extract-organisations';

    constructor(protected http: HttpClient) {}

    create(extractOrganisation: IExtractOrganisation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractOrganisation);
        return this.http
            .post<IExtractOrganisation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(extractOrganisation: IExtractOrganisation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(extractOrganisation);
        return this.http
            .put<IExtractOrganisation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IExtractOrganisation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IExtractOrganisation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(extractOrganisation: IExtractOrganisation): IExtractOrganisation {
        const copy: IExtractOrganisation = Object.assign({}, extractOrganisation, {
            modifiedDate:
                extractOrganisation.modifiedDate != null && extractOrganisation.modifiedDate.isValid()
                    ? extractOrganisation.modifiedDate.toJSON()
                    : null,
            createdDate:
                extractOrganisation.createdDate != null && extractOrganisation.createdDate.isValid()
                    ? extractOrganisation.createdDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.modifiedDate = res.body.modifiedDate != null ? moment(res.body.modifiedDate) : null;
            res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((extractOrganisation: IExtractOrganisation) => {
                extractOrganisation.modifiedDate =
                    extractOrganisation.modifiedDate != null ? moment(extractOrganisation.modifiedDate) : null;
                extractOrganisation.createdDate = extractOrganisation.createdDate != null ? moment(extractOrganisation.createdDate) : null;
            });
        }
        return res;
    }
}
