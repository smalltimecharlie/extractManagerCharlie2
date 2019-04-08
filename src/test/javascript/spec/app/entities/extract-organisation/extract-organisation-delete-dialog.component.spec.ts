/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationDeleteDialogComponent } from 'app/entities/extract-organisation/extract-organisation-delete-dialog.component';
import { ExtractOrganisationService } from 'app/entities/extract-organisation/extract-organisation.service';

describe('Component Tests', () => {
    describe('ExtractOrganisation Management Delete Component', () => {
        let comp: ExtractOrganisationDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractOrganisationDeleteDialogComponent>;
        let service: ExtractOrganisationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationDeleteDialogComponent]
            })
                .overrideTemplate(ExtractOrganisationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractOrganisationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationService);
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
