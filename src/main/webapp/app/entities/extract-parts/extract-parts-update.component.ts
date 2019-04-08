import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IExtractParts } from 'app/shared/model/extract-parts.model';
import { ExtractPartsService } from './extract-parts.service';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from 'app/entities/extract-config';
import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from 'app/entities/extract-tables';

@Component({
    selector: 'jhi-extract-parts-update',
    templateUrl: './extract-parts-update.component.html'
})
export class ExtractPartsUpdateComponent implements OnInit {
    extractParts: IExtractParts;
    isSaving: boolean;

    extractconfigs: IExtractConfig[];

    extracttables: IExtractTables[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected extractPartsService: ExtractPartsService,
        protected extractConfigService: ExtractConfigService,
        protected extractTablesService: ExtractTablesService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractParts }) => {
            this.extractParts = extractParts;
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
        if (this.extractParts.id !== undefined) {
            this.subscribeToSaveResponse(this.extractPartsService.update(this.extractParts));
        } else {
            this.subscribeToSaveResponse(this.extractPartsService.create(this.extractParts));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractParts>>) {
        result.subscribe((res: HttpResponse<IExtractParts>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
