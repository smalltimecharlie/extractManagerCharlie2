import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractParts } from 'app/shared/model/extract-parts.model';

@Component({
    selector: 'jhi-extract-parts-detail',
    templateUrl: './extract-parts-detail.component.html'
})
export class ExtractPartsDetailComponent implements OnInit {
    extractParts: IExtractParts;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractParts }) => {
            this.extractParts = extractParts;
        });
    }

    previousState() {
        window.history.back();
    }
}
