/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationAuditDeleteDialogComponent } from 'app/entities/extract-organisation-audit/extract-organisation-audit-delete-dialog.component';
import { ExtractOrganisationAuditService } from 'app/entities/extract-organisation-audit/extract-organisation-audit.service';

describe('Component Tests', () => {
    describe('ExtractOrganisationAudit Management Delete Component', () => {
        let comp: ExtractOrganisationAuditDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractOrganisationAuditDeleteDialogComponent>;
        let service: ExtractOrganisationAuditService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationAuditDeleteDialogComponent]
            })
                .overrideTemplate(ExtractOrganisationAuditDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractOrganisationAuditDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationAuditService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
