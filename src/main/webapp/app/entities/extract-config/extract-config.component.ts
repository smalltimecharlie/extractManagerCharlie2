import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { AccountService } from 'app/core';
import { ExtractConfigService } from './extract-config.service';

@Component({
    selector: 'jhi-extract-config',
    templateUrl: './extract-config.component.html'
})
export class ExtractConfigComponent implements OnInit, OnDestroy {
    extractConfigs: IExtractConfig[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractConfigService: ExtractConfigService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractConfigService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractConfig[]>) => res.ok),
                map((res: HttpResponse<IExtractConfig[]>) => res.body)
            )
            .subscribe(
                (res: IExtractConfig[]) => {
                    this.extractConfigs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractConfigs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractConfig) {
        return item.id;
    }

    registerChangeInExtractConfigs() {
        this.eventSubscriber = this.eventManager.subscribe('extractConfigListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
