/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractTablesDetailComponent } from 'app/entities/extract-tables/extract-tables-detail.component';
import { ExtractTables } from 'app/shared/model/extract-tables.model';

describe('Component Tests', () => {
    describe('ExtractTables Management Detail Component', () => {
        let comp: ExtractTablesDetailComponent;
        let fixture: ComponentFixture<ExtractTablesDetailComponent>;
        const route = ({ data: of({ extractTables: new ExtractTables(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractTablesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractTablesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractTablesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractTables).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
