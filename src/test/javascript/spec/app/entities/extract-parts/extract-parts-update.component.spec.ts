/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractPartsUpdateComponent } from 'app/entities/extract-parts/extract-parts-update.component';
import { ExtractPartsService } from 'app/entities/extract-parts/extract-parts.service';
import { ExtractParts } from 'app/shared/model/extract-parts.model';

describe('Component Tests', () => {
    describe('ExtractParts Management Update Component', () => {
        let comp: ExtractPartsUpdateComponent;
        let fixture: ComponentFixture<ExtractPartsUpdateComponent>;
        let service: ExtractPartsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractPartsUpdateComponent]
            })
                .overrideTemplate(ExtractPartsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractPartsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractPartsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractParts(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractParts = entity;
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
                    const entity = new ExtractParts();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractParts = entity;
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
