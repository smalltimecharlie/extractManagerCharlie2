/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractPartsDetailComponent } from 'app/entities/extract-parts/extract-parts-detail.component';
import { ExtractParts } from 'app/shared/model/extract-parts.model';

describe('Component Tests', () => {
    describe('ExtractParts Management Detail Component', () => {
        let comp: ExtractPartsDetailComponent;
        let fixture: ComponentFixture<ExtractPartsDetailComponent>;
        const route = ({ data: of({ extractParts: new ExtractParts(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractPartsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractPartsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractPartsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractParts).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
