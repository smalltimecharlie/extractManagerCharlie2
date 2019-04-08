import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from './extract-tables.service';

@Component({
    selector: 'jhi-extract-tables-update',
    templateUrl: './extract-tables-update.component.html'
})
export class ExtractTablesUpdateComponent implements OnInit {
    extractTables: IExtractTables;
    isSaving: boolean;

    constructor(protected extractTablesService: ExtractTablesService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ extractTables }) => {
            this.extractTables = extractTables;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.extractTables.id !== undefined) {
            this.subscribeToSaveResponse(this.extractTablesService.update(this.extractTables));
        } else {
            this.subscribeToSaveResponse(this.extractTablesService.create(this.extractTables));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtractTables>>) {
        result.subscribe((res: HttpResponse<IExtractTables>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
