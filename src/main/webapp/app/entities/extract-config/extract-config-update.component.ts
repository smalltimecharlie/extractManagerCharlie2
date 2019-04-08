import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from './extract-config.service';

@Component({
    selector: 'jhi-extract-config-update',
    templateUrl: './extract-config-update.component.html'
})
export class ExtractConfigUpdateComponent implements OnInit {
    extractConfig: IExtractConfig;
    isSaving: boolean;
    createdDate: string;

    constructor(protected extractConfigService: ExtractConfigService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractConfig }) => {
            this.extractConfig = extractConfig;
            this.createdDate = this.extractConfig.createdDate != null ? this.extractConfig.createdDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.extractConfig.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        if (this.extractConfig.id !== undefined) {
            this.subscribeToSaveResponse(this.extractConfigService.update(this.extractConfig));
        } else {
            this.subscribeToSaveResponse(this.extractConfigService.create(this.extractConfig));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractConfig>>) {
        result.subscribe((res: HttpResponse<IExtractConfig>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
