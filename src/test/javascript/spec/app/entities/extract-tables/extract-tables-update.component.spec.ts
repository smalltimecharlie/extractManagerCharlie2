/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractTablesUpdateComponent } from 'app/entities/extract-tables/extract-tables-update.component';
import { ExtractTablesService } from 'app/entities/extract-tables/extract-tables.service';
import { ExtractTables } from 'app/shared/model/extract-tables.model';

describe('Component Tests', () => {
    describe('ExtractTables Management Update Component', () => {
        let comp: ExtractTablesUpdateComponent;
        let fixture: ComponentFixture<ExtractTablesUpdateComponent>;
        let service: ExtractTablesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractTablesUpdateComponent]
            })
                .overrideTemplate(ExtractTablesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractTablesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractTablesService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ExtractTables(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractTables = entity;
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
                    const entity = new ExtractTables();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.extractTables = entity;
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
