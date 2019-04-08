/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractConfigUpdateComponent } from 'app/entities/extract-config/extract-config-update.component';
import { ExtractConfigService } from 'app/entities/extract-config/extract-config.service';
import { ExtractConfig } from 'app/shared/model/extract-config.model';

describe('Component Tests', () => {
    describe('ExtractConfig Management Update Component', () => {
        let comp: ExtractConfigUpdateComponent;
        let fixture: ComponentFixture<ExtractConfigUpdateComponent>;
        let service: ExtractConfigService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractConfigUpdateComponent]
            })
                .overrideTemplate(ExtractConfigUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractConfigUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractConfigService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractConfig(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractConfig = entity;
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
                    const entity = new ExtractConfig();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractConfig = entity;
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
