/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractAuditDetailComponent } from 'app/entities/extract-audit/extract-audit-detail.component';
import { ExtractAudit } from 'app/shared/model/extract-audit.model';

describe('Component Tests', () => {
    describe('ExtractAudit Management Detail Component', () => {
        let comp: ExtractAuditDetailComponent;
        let fixture: ComponentFixture<ExtractAuditDetailComponent>;
        const route = ({ data: of({ extractAudit: new ExtractAudit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractAuditDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractAuditDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractAuditDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractAudit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
