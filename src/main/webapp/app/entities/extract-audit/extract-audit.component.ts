import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractAudit } from 'app/shared/model/extract-audit.model';
import { AccountService } from 'app/core';
import { ExtractAuditService } from './extract-audit.service';

@Component({
    selector: 'jhi-extract-audit',
    templateUrl: './extract-audit.component.html'
})
export class ExtractAuditComponent implements OnInit, OnDestroy {
    extractAudits: IExtractAudit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractAuditService: ExtractAuditService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractAuditService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractAudit[]>) => res.ok),
                map((res: HttpResponse<IExtractAudit[]>) => res.body)
            )
            .subscribe(
                (res: IExtractAudit[]) => {
                    this.extractAudits = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractAudits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractAudit) {
        return item.id;
    }

    registerChangeInExtractAudits() {
        this.eventSubscriber = this.eventManager.subscribe('extractAuditListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
