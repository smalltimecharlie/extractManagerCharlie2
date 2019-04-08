import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';
import { ExtractOrganisationService } from './extract-organisation.service';

@Component({
    selector: 'jhi-extract-organisation-delete-dialog',
    templateUrl: './extract-organisation-delete-dialog.component.html'
})
export class ExtractOrganisationDeleteDialogComponent {
    extractOrganisation: IExtractOrganisation;

    constructor(
        protected extractOrganisationService: ExtractOrganisationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractOrganisationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractOrganisationListModification',
                content: 'Deleted an extractOrganisation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-organisation-delete-popup',
    template: ''
})
export class ExtractOrganisationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractOrganisation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractOrganisationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractOrganisation = extractOrganisation;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-organisation', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-organisation', { outlets: { popup: null } }]);
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
