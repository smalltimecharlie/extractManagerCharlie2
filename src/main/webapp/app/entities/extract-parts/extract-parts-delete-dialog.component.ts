import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractParts } from 'app/shared/model/extract-parts.model';
import { ExtractPartsService } from './extract-parts.service';

@Component({
    selector: 'jhi-extract-parts-delete-dialog',
    templateUrl: './extract-parts-delete-dialog.component.html'
})
export class ExtractPartsDeleteDialogComponent {
    extractParts: IExtractParts;

    constructor(
        protected extractPartsService: ExtractPartsService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractPartsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractPartsListModification',
                content: 'Deleted an extractParts'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-parts-delete-popup',
    template: ''
})
export class ExtractPartsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractParts }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractPartsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractParts = extractParts;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-parts', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-parts', { outlets: { popup: null } }]);
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
