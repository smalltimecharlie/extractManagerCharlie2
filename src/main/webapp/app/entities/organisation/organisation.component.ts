import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOrganisation } from 'app/shared/model/organisation.model';
import { AccountService } from 'app/core';
import { OrganisationService } from './organisation.service';

@Component({
    selector: 'jhi-organisation',
    templateUrl: './organisation.component.html'
})
export class OrganisationComponent implements OnInit, OnDestroy {
    organisations: IOrganisation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected organisationService: OrganisationService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.organisationService
            .query()
            .pipe(
                filter((res: HttpResponse<IOrganisation[]>) => res.ok),
                map((res: HttpResponse<IOrganisation[]>) => res.body)
            )
            .subscribe(
                (res: IOrganisation[]) => {
                    this.organisations = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOrganisations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOrganisation) {
        return item.id;
    }

    registerChangeInOrganisations() {
        this.eventSubscriber = this.eventManager.subscribe('organisationListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
