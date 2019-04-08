/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationComponent } from 'app/entities/extract-organisation/extract-organisation.component';
import { ExtractOrganisationService } from 'app/entities/extract-organisation/extract-organisation.service';
import { ExtractOrganisation } from 'app/shared/model/extract-organisation.model';

describe('Component Tests', () => {
    describe('ExtractOrganisation Management Component', () => {
        let comp: ExtractOrganisationComponent;
        let fixture: ComponentFixture<ExtractOrganisationComponent>;
        let service: ExtractOrganisationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationComponent],
                providers: []
            })
                .overrideTemplate(ExtractOrganisationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractOrganisationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractOrganisation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractOrganisations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
