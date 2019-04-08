import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';
import { ExtractOrganisationAuditService } from './extract-organisation-audit.service';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { OrganisationService } from 'app/entities/organisation';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from 'app/entities/extract-config';
import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from 'app/entities/extract-tables';

@Component({
    selector: 'jhi-extract-organisation-audit-update',
    templateUrl: './extract-organisation-audit-update.component.html'
})
export class ExtractOrganisationAuditUpdateComponent implements OnInit {
    extractOrganisationAudit: IExtractOrganisationAudit;
    isSaving: boolean;

    organisations: IOrganisation[];

    extractconfigs: IExtractConfig[];

    extracttables: IExtractTables[];
    createdDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected extractOrganisationAuditService: ExtractOrganisationAuditService,
        protected organisationService: OrganisationService,
        protected extractConfigService: ExtractConfigService,
        protected extractTablesService: ExtractTablesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractOrganisationAudit }) => {
            this.extractOrganisationAudit = extractOrganisationAudit;
            this.createdDate =
                this.extractOrganisationAudit.createdDate != null
                    ? this.extractOrganisationAudit.createdDate.format(DATE_TIME_FORMAT)
                    : null;
        });
        this.organisationService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrganisation[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrganisation[]>) => response.body)
            )
            .subscribe((res: IOrganisation[]) => (this.organisations = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.extractConfigService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IExtractConfig[]>) => mayBeOk.ok),
                map((response: HttpResponse<IExtractConfig[]>) => response.body)
            )
            .subscribe((res: IExtractConfig[]) => (this.extractconfigs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.extractTablesService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IExtractTables[]>) => mayBeOk.ok),
                map((response: HttpResponse<IExtractTables[]>) => response.body)
            )
            .subscribe((res: IExtractTables[]) => (this.extracttables = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.extractOrganisationAudit.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        if (this.extractOrganisationAudit.id !== undefined) {
            this.subscribeToSaveResponse(this.extractOrganisationAuditService.update(this.extractOrganisationAudit));
        } else {
            this.subscribeToSaveResponse(this.extractOrganisationAuditService.create(this.extractOrganisationAudit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractOrganisationAudit>>) {
        result.subscribe(
            (res: HttpResponse<IExtractOrganisationAudit>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrganisationById(index: number, item: IOrganisation) {
        return item.id;
    }

    trackExtractConfigById(index: number, item: IExtractConfig) {
        return item.id;
    }

    trackExtractTablesById(index: number, item: IExtractTables) {
        return item.id;
    }
}
