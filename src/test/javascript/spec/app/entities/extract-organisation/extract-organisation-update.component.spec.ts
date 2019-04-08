/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationUpdateComponent } from 'app/entities/extract-organisation/extract-organisation-update.component';
import { ExtractOrganisationService } from 'app/entities/extract-organisation/extract-organisation.service';
import { ExtractOrganisation } from 'app/shared/model/extract-organisation.model';

describe('Component Tests', () => {
    describe('ExtractOrganisation Management Update Component', () => {
        let comp: ExtractOrganisationUpdateComponent;
        let fixture: ComponentFixture<ExtractOrganisationUpdateComponent>;
        let service: ExtractOrganisationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationUpdateComponent]
            })
                .overrideTemplate(ExtractOrganisationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractOrganisationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractOrganisation(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractOrganisation = entity;
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
                    const entity = new ExtractOrganisation();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractOrganisation = entity;
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
