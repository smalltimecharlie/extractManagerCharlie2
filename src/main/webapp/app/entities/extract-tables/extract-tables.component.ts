import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { AccountService } from 'app/core';
import { ExtractTablesService } from './extract-tables.service';

@Component({
    selector: 'jhi-extract-tables',
    templateUrl: './extract-tables.component.html'
})
export class ExtractTablesComponent implements OnInit, OnDestroy {
    extractTables: IExtractTables[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractTablesService: ExtractTablesService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractTablesService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractTables[]>) => res.ok),
                map((res: HttpResponse<IExtractTables[]>) => res.body)
            )
            .subscribe(
                (res: IExtractTables[]) => {
                    this.extractTables = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractTables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractTables) {
        return item.id;
    }

    registerChangeInExtractTables() {
        this.eventSubscriber = this.eventManager.subscribe('extractTablesListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
