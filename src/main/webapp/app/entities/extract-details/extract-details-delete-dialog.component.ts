import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractDetails } from 'app/shared/model/extract-details.model';
import { ExtractDetailsService } from './extract-details.service';

@Component({
    selector: 'jhi-extract-details-delete-dialog',
    templateUrl: './extract-details-delete-dialog.component.html'
})
export class ExtractDetailsDeleteDialogComponent {
    extractDetails: IExtractDetails;

    constructor(
        protected extractDetailsService: ExtractDetailsService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractDetailsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractDetailsListModification',
                content: 'Deleted an extractDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-details-delete-popup',
    template: ''
})
export class ExtractDetailsDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractDetails }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractDetailsDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractDetails = extractDetails;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-details', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-details', { outlets: { popup: null } }]);
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
