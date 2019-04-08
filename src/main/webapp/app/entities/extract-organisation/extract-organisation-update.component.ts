import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';
import { ExtractOrganisationService } from './extract-organisation.service';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { OrganisationService } from 'app/entities/organisation';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from 'app/entities/extract-config';

@Component({
    selector: 'jhi-extract-organisation-update',
    templateUrl: './extract-organisation-update.component.html'
})
export class ExtractOrganisationUpdateComponent implements OnInit {
    extractOrganisation: IExtractOrganisation;
    isSaving: boolean;

    organisations: IOrganisation[];

    extractconfigs: IExtractConfig[];
    modifiedDate: string;
    createdDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected extractOrganisationService: ExtractOrganisationService,
        protected organisationService: OrganisationService,
        protected extractConfigService: ExtractConfigService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractOrganisation }) => {
            this.extractOrganisation = extractOrganisation;
            this.modifiedDate =
                this.extractOrganisation.modifiedDate != null ? this.extractOrganisation.modifiedDate.format(DATE_TIME_FORMAT) : null;
            this.createdDate =
                this.extractOrganisation.createdDate != null ? this.extractOrganisation.createdDate.format(DATE_TIME_FORMAT) : null;
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.extractOrganisation.modifiedDate = this.modifiedDate != null ? moment(this.modifiedDate, DATE_TIME_FORMAT) : null;
        this.extractOrganisation.createdDate = this.createdDate != null ? moment(this.createdDate, DATE_TIME_FORMAT) : null;
        if (this.extractOrganisation.id !== undefined) {
            this.subscribeToSaveResponse(this.extractOrganisationService.update(this.extractOrganisation));
        } else {
            this.subscribeToSaveResponse(this.extractOrganisationService.create(this.extractOrganisation));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractOrganisation>>) {
        result.subscribe((res: HttpResponse<IExtractOrganisation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
