import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';
import { ExtractOrganisationAuditService } from './extract-organisation-audit.service';

@Component({
    selector: 'jhi-extract-organisation-audit-delete-dialog',
    templateUrl: './extract-organisation-audit-delete-dialog.component.html'
})
export class ExtractOrganisationAuditDeleteDialogComponent {
    extractOrganisationAudit: IExtractOrganisationAudit;

    constructor(
        protected extractOrganisationAuditService: ExtractOrganisationAuditService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractOrganisationAuditService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractOrganisationAuditListModification',
                content: 'Deleted an extractOrganisationAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-organisation-audit-delete-popup',
    template: ''
})
export class ExtractOrganisationAuditDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractOrganisationAudit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractOrganisationAuditDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractOrganisationAudit = extractOrganisationAudit;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-organisation-audit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-organisation-audit', { outlets: { popup: null } }]);
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
