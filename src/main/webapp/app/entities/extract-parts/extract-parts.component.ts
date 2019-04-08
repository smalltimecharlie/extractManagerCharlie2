import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractParts } from 'app/shared/model/extract-parts.model';
import { AccountService } from 'app/core';
import { ExtractPartsService } from './extract-parts.service';

@Component({
    selector: 'jhi-extract-parts',
    templateUrl: './extract-parts.component.html'
})
export class ExtractPartsComponent implements OnInit, OnDestroy {
    extractParts: IExtractParts[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractPartsService: ExtractPartsService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractPartsService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractParts[]>) => res.ok),
                map((res: HttpResponse<IExtractParts[]>) => res.body)
            )
            .subscribe(
                (res: IExtractParts[]) => {
                    this.extractParts = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractParts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractParts) {
        return item.id;
    }

    registerChangeInExtractParts() {
        this.eventSubscriber = this.eventManager.subscribe('extractPartsListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
