/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractAuditComponent } from 'app/entities/extract-audit/extract-audit.component';
import { ExtractAuditService } from 'app/entities/extract-audit/extract-audit.service';
import { ExtractAudit } from 'app/shared/model/extract-audit.model';

describe('Component Tests', () => {
    describe('ExtractAudit Management Component', () => {
        let comp: ExtractAuditComponent;
        let fixture: ComponentFixture<ExtractAuditComponent>;
        let service: ExtractAuditService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractAuditComponent],
                providers: []
            })
                .overrideTemplate(ExtractAuditComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractAuditComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractAuditService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractAudit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
