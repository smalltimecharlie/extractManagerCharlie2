/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractConfigDetailComponent } from 'app/entities/extract-config/extract-config-detail.component';
import { ExtractConfig } from 'app/shared/model/extract-config.model';

describe('Component Tests', () => {
    describe('ExtractConfig Management Detail Component', () => {
        let comp: ExtractConfigDetailComponent;
        let fixture: ComponentFixture<ExtractConfigDetailComponent>;
        const route = ({ data: of({ extractConfig: new ExtractConfig(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractConfigDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ExtractConfigDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ExtractConfigDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.extractConfig).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
