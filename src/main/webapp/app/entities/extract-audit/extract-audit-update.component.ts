import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IExtractAudit } from 'app/shared/model/extract-audit.model';
import { ExtractAuditService } from './extract-audit.service';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from 'app/entities/extract-config';
import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from 'app/entities/extract-tables';

@Component({
    selector: 'jhi-extract-audit-update',
    templateUrl: './extract-audit-update.component.html'
})
export class ExtractAuditUpdateComponent implements OnInit {
    extractAudit: IExtractAudit;
    isSaving: boolean;

    extractconfigs: IExtractConfig[];

    extracttables: IExtractTables[];
    createdDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected extractAuditService: ExtractAuditService,
        protected extractConfigService: ExtractConfigService,
        protected extractTablesService: ExtractTablesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractAudit }) => {
            this.extractAudit = extractAudit;
            this.createdDate = this.extractAudit.createdDate != null ? this.extractAudit.createdDate.format(DATE_TIME_FORMAT) : null;
        });
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
        this.extractAudit.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        if (this.extractAudit.id !== undefined) {
            this.subscribeToSaveResponse(this.extractAuditService.update(this.extractAudit));
        } else {
            this.subscribeToSaveResponse(this.extractAuditService.create(this.extractAudit));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractAudit>>) {
        result.subscribe((res: HttpResponse<IExtractAudit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackExtractConfigById(index: number, item: IExtractConfig) {
        return item.id;
    }

    trackExtractTablesById(index: number, item: IExtractTables) {
        return item.id;
    }
}
