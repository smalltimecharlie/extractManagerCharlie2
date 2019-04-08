import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';
import { AccountService } from 'app/core';
import { ExtractOrganisationService } from './extract-organisation.service';

@Component({
    selector: 'jhi-extract-organisation',
    templateUrl: './extract-organisation.component.html'
})
export class ExtractOrganisationComponent implements OnInit, OnDestroy {
    extractOrganisations: IExtractOrganisation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected extractOrganisationService: ExtractOrganisationService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.extractOrganisationService
            .query()
            .pipe(
                filter((res: HttpResponse<IExtractOrganisation[]>) => res.ok),
                map((res: HttpResponse<IExtractOrganisation[]>) => res.body)
            )
            .subscribe(
                (res: IExtractOrganisation[]) => {
                    this.extractOrganisations = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInExtractOrganisations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IExtractOrganisation) {
        return item.id;
    }

    registerChangeInExtractOrganisations() {
        this.eventSubscriber = this.eventManager.subscribe('extractOrganisationListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
