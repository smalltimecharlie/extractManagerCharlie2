/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractDetailsUpdateComponent } from 'app/entities/extract-details/extract-details-update.component';
import { ExtractDetailsService } from 'app/entities/extract-details/extract-details.service';
import { ExtractDetails } from 'app/shared/model/extract-details.model';

describe('Component Tests', () => {
    describe('ExtractDetails Management Update Component', () => {
        let comp: ExtractDetailsUpdateComponent;
        let fixture: ComponentFixture<ExtractDetailsUpdateComponent>;
        let service: ExtractDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractDetailsUpdateComponent]
            })
                .overrideTemplate(ExtractDetailsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractDetailsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractDetailsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractDetails(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractDetails = entity;
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
                    const entity = new ExtractDetails();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractDetails = entity;
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
