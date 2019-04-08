import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractAudit } from 'app/shared/model/extract-audit.model';

@Component({
    selector: 'jhi-extract-audit-detail',
    templateUrl: './extract-audit-detail.component.html'
})
export class ExtractAuditDetailComponent implements OnInit {
    extractAudit: IExtractAudit;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractAudit }) => {
            this.extractAudit = extractAudit;
        });
    }

    previousState() {
        window.history.back();
    }
}
