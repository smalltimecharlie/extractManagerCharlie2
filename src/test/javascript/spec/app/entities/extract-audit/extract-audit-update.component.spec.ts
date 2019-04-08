/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractAuditUpdateComponent } from 'app/entities/extract-audit/extract-audit-update.component';
import { ExtractAuditService } from 'app/entities/extract-audit/extract-audit.service';
import { ExtractAudit } from 'app/shared/model/extract-audit.model';

describe('Component Tests', () => {
    describe('ExtractAudit Management Update Component', () => {
        let comp: ExtractAuditUpdateComponent;
        let fixture: ComponentFixture<ExtractAuditUpdateComponent>;
        let service: ExtractAuditService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractAuditUpdateComponent]
            })
                .overrideTemplate(ExtractAuditUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractAuditUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractAuditService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractAudit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractAudit = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractAudit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractAudit = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
