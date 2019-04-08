/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationDetailComponent } from 'app/entities/extract-organisation/extract-organisation-detail.component';
import { ExtractOrganisation } from 'app/shared/model/extract-organisation.model';

describe('Component Tests', () => {
    describe('ExtractOrganisation Management Detail Component', () => {
        let comp: ExtractOrganisationDetailComponent;
        let fixture: ComponentFixture<ExtractOrganisationDetailComponent>;
        const route = ({ data: of({ extractOrganisation: new ExtractOrganisation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractOrganisationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractOrganisationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractOrganisation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
