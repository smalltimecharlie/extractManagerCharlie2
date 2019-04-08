import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';

@Component({
    selector: 'jhi-extract-organisation-detail',
    templateUrl: './extract-organisation-detail.component.html'
})
export class ExtractOrganisationDetailComponent implements OnInit {
    extractOrganisation: IExtractOrganisation;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractOrganisation }) => {
            this.extractOrganisation = extractOrganisation;
        });
    }

    previousState() {
        window.history.back();
    }
}
