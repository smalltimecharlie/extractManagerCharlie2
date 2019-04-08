import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from './extract-config.service';

@Component({
    selector: 'jhi-extract-config-delete-dialog',
    templateUrl: './extract-config-delete-dialog.component.html'
})
export class ExtractConfigDeleteDialogComponent {
    extractConfig: IExtractConfig;

    constructor(
        protected extractConfigService: ExtractConfigService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.extractConfigService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'extractConfigListModification',
                content: 'Deleted an extractConfig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-extract-config-delete-popup',
    template: ''
})
export class ExtractConfigDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ extractConfig }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ExtractConfigDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.extractConfig = extractConfig;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/extract-config', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/extract-config', { outlets: { popup: null } }]);
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
