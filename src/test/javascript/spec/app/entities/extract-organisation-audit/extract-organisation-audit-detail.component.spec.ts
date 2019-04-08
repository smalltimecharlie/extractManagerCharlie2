/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractOrganisationAuditDetailComponent } from 'app/entities/extract-organisation-audit/extract-organisation-audit-detail.component';
import { ExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';

describe('Component Tests', () => {
    describe('ExtractOrganisationAudit Management Detail Component', () => {
        let comp: ExtractOrganisationAuditDetailComponent;
        let fixture: ComponentFixture<ExtractOrganisationAuditDetailComponent>;
        const route = ({ data: of({ extractOrganisationAudit: new ExtractOrganisationAudit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractOrganisationAuditDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractOrganisationAuditDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractOrganisationAuditDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractOrganisationAudit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
