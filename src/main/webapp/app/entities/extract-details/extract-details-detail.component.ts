import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IExtractDetails } from 'app/shared/model/extract-details.model';

@Component({
    selector: 'jhi-extract-details-detail',
    templateUrl: './extract-details-detail.component.html'
})
export class ExtractDetailsDetailComponent implements OnInit {
    extractDetails: IExtractDetails;

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractDetails }) => {
            this.extractDetails = extractDetails;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
