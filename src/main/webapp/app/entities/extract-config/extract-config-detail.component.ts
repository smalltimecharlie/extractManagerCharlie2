import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractConfig } from 'app/shared/model/extract-config.model';

@Component({
    selector: 'jhi-extract-config-detail',
    templateUrl: './extract-config-detail.component.html'
})
export class ExtractConfigDetailComponent implements OnInit {
    extractConfig: IExtractConfig;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractConfig }) => {
            this.extractConfig = extractConfig;
        });
    }

    previousState() {
        window.history.back();
    }
}
