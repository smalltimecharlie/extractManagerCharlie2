/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationAuditComponent } from 'app/entities/extract-organisation-audit/extract-organisation-audit.component';
import { ExtractOrganisationAuditService } from 'app/entities/extract-organisation-audit/extract-organisation-audit.service';
import { ExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';

describe('Component Tests', () => {
    describe('ExtractOrganisationAudit Management Component', () => {
        let comp: ExtractOrganisationAuditComponent;
        let fixture: ComponentFixture<ExtractOrganisationAuditComponent>;
        let service: ExtractOrganisationAuditService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationAuditComponent],
                providers: []
            })
                .overrideTemplate(ExtractOrganisationAuditComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractOrganisationAuditComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractOrganisationAuditService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractOrganisationAudit(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractOrganisationAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
