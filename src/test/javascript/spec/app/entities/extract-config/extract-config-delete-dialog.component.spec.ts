/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractConfigDeleteDialogComponent } from 'app/entities/extract-config/extract-config-delete-dialog.component';
import { ExtractConfigService } from 'app/entities/extract-config/extract-config.service';

describe('Component Tests', () => {
    describe('ExtractConfig Management Delete Component', () => {
        let comp: ExtractConfigDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractConfigDeleteDialogComponent>;
        let service: ExtractConfigService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractConfigDeleteDialogComponent]
            })
                .overrideTemplate(ExtractConfigDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractConfigDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractConfigService);
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
