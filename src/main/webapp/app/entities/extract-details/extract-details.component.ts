import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IExtractDetails } from 'app/shared/model/extract-details.model';
import { AccountService } from 'app/core';
import { ExtractDetailsService } from './extract-details.service';

@Component({
    selector: 'jhi-extract-details',
    templateUrl: './extract-details.component.html'
})
export class ExtractDetailsComponent implements OnInit, OnDestroy {
    extractDetails: IExtractDetails[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractDetailsService: ExtractDetailsService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractDetailsService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractDetails[]>) => res.ok),
                map((res: HttpResponse<IExtractDetails[]>) => res.body)
            )
            .subscribe(
                (res: IExtractDetails[]) => {
                    this.extractDetails = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractDetails) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInExtractDetails() {
        this.eventSubscriber = this.eventManager.subscribe('extractDetailsListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
