/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractDetailsDetailComponent } from 'app/entities/extract-details/extract-details-detail.component';
import { ExtractDetails } from 'app/shared/model/extract-details.model';

describe('Component Tests', () => {
    describe('ExtractDetails Management Detail Component', () => {
        let comp: ExtractDetailsDetailComponent;
        let fixture: ComponentFixture<ExtractDetailsDetailComponent>;
        const route = ({ data: of({ extractDetails: new ExtractDetails(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractDetailsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractDetailsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractDetailsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
