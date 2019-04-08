/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractDetailsComponent } from 'app/entities/extract-details/extract-details.component';
import { ExtractDetailsService } from 'app/entities/extract-details/extract-details.service';
import { ExtractDetails } from 'app/shared/model/extract-details.model';

describe('Component Tests', () => {
    describe('ExtractDetails Management Component', () => {
        let comp: ExtractDetailsComponent;
        let fixture: ComponentFixture<ExtractDetailsComponent>;
        let service: ExtractDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractDetailsComponent],
                providers: []
            })
                .overrideTemplate(ExtractDetailsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractDetailsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractDetailsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractDetails(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
