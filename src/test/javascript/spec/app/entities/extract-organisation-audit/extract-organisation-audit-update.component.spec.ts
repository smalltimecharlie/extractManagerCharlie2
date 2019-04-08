/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationAuditUpdateComponent } from 'app/entities/extract-organisation-audit/extract-organisation-audit-update.component';
import { ExtractOrganisationAuditService } from 'app/entities/extract-organisation-audit/extract-organisation-audit.service';
import { ExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';

describe('Component Tests', () => {
    describe('ExtractOrganisationAudit Management Update Component', () => {
        let comp: ExtractOrganisationAuditUpdateComponent;
        let fixture: ComponentFixture<ExtractOrganisationAuditUpdateComponent>;
        let service: ExtractOrganisationAuditService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationAuditUpdateComponent]
            })
                .overrideTemplate(ExtractOrganisationAuditUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractOrganisationAuditUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationAuditService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractOrganisationAudit(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractOrganisationAudit = entity;
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
                    const entity = new ExtractOrganisationAudit();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractOrganisationAudit = entity;
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
