import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';
import { AccountService } from 'app/core';
import { ExtractOrganisationAuditService } from './extract-organisation-audit.service';

@Component({
    selector: 'jhi-extract-organisation-audit',
    templateUrl: './extract-organisation-audit.component.html'
})
export class ExtractOrganisationAuditComponent implements OnInit, OnDestroy {
    extractOrganisationAudits: IExtractOrganisationAudit[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractOrganisationAuditService: ExtractOrganisationAuditService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractOrganisationAuditService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractOrganisationAudit[]>) => res.ok),
                map((res: HttpResponse<IExtractOrganisationAudit[]>) => res.body)
            )
            .subscribe(
                (res: IExtractOrganisationAudit[]) => {
                    this.extractOrganisationAudits = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractOrganisationAudits();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractOrganisationAudit) {
        return item.id;
    }

    registerChangeInExtractOrganisationAudits() {
        this.eventSubscriber = this.eventManager.subscribe('extractOrganisationAuditListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
