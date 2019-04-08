import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';

@Component({
    selector: 'jhi-extract-organisation-audit-detail',
    templateUrl: './extract-organisation-audit-detail.component.html'
})
export class ExtractOrganisationAuditDetailComponent implements OnInit {
    extractOrganisationAudit: IExtractOrganisationAudit;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractOrganisationAudit }) => {
            this.extractOrganisationAudit = extractOrganisationAudit;
        });
    }

    previousState() {
        window.history.back();
    }
}
