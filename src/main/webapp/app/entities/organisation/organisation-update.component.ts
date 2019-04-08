import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { OrganisationService } from './organisation.service';

@Component({
    selector: 'jhi-organisation-update',
    templateUrl: './organisation-update.component.html'
})
export class OrganisationUpdateComponent implements OnInit {
    organisation: IOrganisation;
    isSaving: boolean;

    constructor(protected organisationService: OrganisationService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ organisation }) => {
            this.organisation = organisation;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.organisation.id !== undefined) {
            this.subscribeToSaveResponse(this.organisationService.update(this.organisation));
        } else {
            this.subscribeToSaveResponse(this.organisationService.create(this.organisation));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganisation>>) {
        result.subscribe((res: HttpResponse<IOrganisation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
