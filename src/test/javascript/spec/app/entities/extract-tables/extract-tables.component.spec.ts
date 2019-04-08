/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractTablesComponent } from 'app/entities/extract-tables/extract-tables.component';
import { ExtractTablesService } from 'app/entities/extract-tables/extract-tables.service';
import { ExtractTables } from 'app/shared/model/extract-tables.model';

describe('Component Tests', () => {
    describe('ExtractTables Management Component', () => {
        let comp: ExtractTablesComponent;
        let fixture: ComponentFixture<ExtractTablesComponent>;
        let service: ExtractTablesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractTablesComponent],
                providers: []
            })
                .overrideTemplate(ExtractTablesComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractTablesComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractTablesService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractTables(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractTables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
