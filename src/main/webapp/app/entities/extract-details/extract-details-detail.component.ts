import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractDetails } from 'app/shared/model/extract-details.model';

@Component({
    selector: 'jhi-extract-details-detail',
    templateUrl: './extract-details-detail.component.html'
})
export class ExtractDetailsDetailComponent implements OnInit {
    extractDetails: IExtractDetails;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractDetails }) => {
            this.extractDetails = extractDetails;
        });
    }

    previousState() {
        window.history.back();
    }
}
