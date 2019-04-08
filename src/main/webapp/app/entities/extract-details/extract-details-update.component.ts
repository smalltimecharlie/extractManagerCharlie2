import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IExtractDetails } from 'app/shared/model/extract-details.model';
import { ExtractDetailsService } from './extract-details.service';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from 'app/entities/extract-config';

@Component({
    selector: 'jhi-extract-details-update',
    templateUrl: './extract-details-update.component.html'
})
export class ExtractDetailsUpdateComponent implements OnInit {
    extractDetails: IExtractDetails;
    isSaving: boolean;

    extractconfigs: IExtractConfig[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected extractDetailsService: ExtractDetailsService,
        protected extractConfigService: ExtractConfigService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractDetails }) => {
            this.extractDetails = extractDetails;
        });
        this.extractConfigService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IExtractConfig[]>) => mayBeOk.ok),
                map((response: HttpResponse<IExtractConfig[]>) => response.body)
            )
            .subscribe((res: IExtractConfig[]) => (this.extractconfigs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.extractDetails.id !== undefined) {
            this.subscribeToSaveResponse(this.extractDetailsService.update(this.extractDetails));
        } else {
            this.subscribeToSaveResponse(this.extractDetailsService.create(this.extractDetails));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractDetails>>) {
        result.subscribe((res: HttpResponse<IExtractDetails>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
