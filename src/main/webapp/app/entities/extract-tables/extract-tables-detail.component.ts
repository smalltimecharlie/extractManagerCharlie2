import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtractTables } from 'app/shared/model/extract-tables.model';

@Component({
    selector: 'jhi-extract-tables-detail',
    templateUrl: './extract-tables-detail.component.html'
})
export class ExtractTablesDetailComponent implements OnInit {
    extractTables: IExtractTables;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractTables }) => {
            this.extractTables = extractTables;
        });
    }

    previousState() {
        window.history.back();
    }
}
