/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractPartsComponent } from 'app/entities/extract-parts/extract-parts.component';
import { ExtractPartsService } from 'app/entities/extract-parts/extract-parts.service';
import { ExtractParts } from 'app/shared/model/extract-parts.model';

describe('Component Tests', () => {
    describe('ExtractParts Management Component', () => {
        let comp: ExtractPartsComponent;
        let fixture: ComponentFixture<ExtractPartsComponent>;
        let service: ExtractPartsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractPartsComponent],
                providers: []
            })
                .overrideTemplate(ExtractPartsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractPartsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractPartsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractParts(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractParts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
