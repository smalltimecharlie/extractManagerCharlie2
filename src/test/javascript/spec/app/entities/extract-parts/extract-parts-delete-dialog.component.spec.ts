/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractPartsDeleteDialogComponent } from 'app/entities/extract-parts/extract-parts-delete-dialog.component';
import { ExtractPartsService } from 'app/entities/extract-parts/extract-parts.service';

describe('Component Tests', () => {
    describe('ExtractParts Management Delete Component', () => {
        let comp: ExtractPartsDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractPartsDeleteDialogComponent>;
        let service: ExtractPartsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractPartsDeleteDialogComponent]
            })
                .overrideTemplate(ExtractPartsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractPartsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractPartsService);
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
