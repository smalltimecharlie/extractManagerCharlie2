import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractAudit } from 'app/shared/model/extract-audit.model';
import { ExtractAuditService } from './extract-audit.service';

@Component({
    selector: 'jhi-extract-audit-delete-dialog',
    templateUrl: './extract-audit-delete-dialog.component.html'
})
export class ExtractAuditDeleteDialogComponent {
    extractAudit: IExtractAudit;

    constructor(
        protected extractAuditService: ExtractAuditService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractAuditService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractAuditListModification',
                content: 'Deleted an extractAudit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-audit-delete-popup',
    template: ''
})
export class ExtractAuditDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractAudit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractAuditDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractAudit = extractAudit;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-audit', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-audit', { outlets: { popup: null } }]);
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
