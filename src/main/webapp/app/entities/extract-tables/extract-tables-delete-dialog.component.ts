import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from './extract-tables.service';

@Component({
    selector: 'jhi-extract-tables-delete-dialog',
    templateUrl: './extract-tables-delete-dialog.component.html'
})
export class ExtractTablesDeleteDialogComponent {
    extractTables: IExtractTables;

    constructor(
        protected extractTablesService: ExtractTablesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractTablesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractTablesListModification',
                content: 'Deleted an extractTables'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-tables-delete-popup',
    template: ''
})
export class ExtractTablesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractTables }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractTablesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractTables = extractTables;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-tables', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-tables', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
