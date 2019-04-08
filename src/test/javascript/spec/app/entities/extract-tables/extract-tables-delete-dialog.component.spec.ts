/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractTablesDeleteDialogComponent } from 'app/entities/extract-tables/extract-tables-delete-dialog.component';
import { ExtractTablesService } from 'app/entities/extract-tables/extract-tables.service';

describe('Component Tests', () => {
    describe('ExtractTables Management Delete Component', () => {
        let comp: ExtractTablesDeleteDialogComponent;
        let fixture: ComponentFixture<ExtractTablesDeleteDialogComponent>;
        let service: ExtractTablesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractTablesDeleteDialogComponent]
            })
                .overrideTemplate(ExtractTablesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractTablesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractTablesService);
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
