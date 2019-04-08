/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ExtractManagerCharlie2TestModule } from '../../../test.module';
import { ExtractConfigComponent } from 'app/entities/extract-config/extract-config.component';
import { ExtractConfigService } from 'app/entities/extract-config/extract-config.service';
import { ExtractConfig } from 'app/shared/model/extract-config.model';

describe('Component Tests', () => {
    describe('ExtractConfig Management Component', () => {
        let comp: ExtractConfigComponent;
        let fixture: ComponentFixture<ExtractConfigComponent>;
        let service: ExtractConfigService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ExtractManagerCharlie2TestModule],
                declarations: [ExtractConfigComponent],
                providers: []
            })
                .overrideTemplate(ExtractConfigComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ExtractConfigComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ExtractConfigService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ExtractConfig(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.extractConfigs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
