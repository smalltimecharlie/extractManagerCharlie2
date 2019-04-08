/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractDetailsDeleteDialogComponent } from 'app/entities/extract-details/extract-details-delete-dialog.component';
import { ExtractDetailsService } from 'app/entities/extract-details/extract-details.service';

describe('Component Tests', () => {
    describe('ExtractDetails Management Delete Component', () => {
        let comp: ExtractDetailsDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractDetailsDeleteDialogComponent>;
        let service: ExtractDetailsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractDetailsDeleteDialogComponent]
            })
                .overrideTemplate(ExtractDetailsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractDetailsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractDetailsService);
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
