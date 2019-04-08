/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ExtractPartsService } from 'app/entities/extract-parts/extract-parts.service';
import { IExtractParts, ExtractParts } from 'app/shared/model/extract-parts.model';

describe('Service Tests', () => {
    describe('ExtractParts Service', () => {
        let injector: TestBed;
        let service: ExtractPartsService;
        let httpMock: HttpTestingController;
        let elemDefault: IExtractParts;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ExtractPartsService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ExtractParts(
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ExtractParts', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ExtractParts(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ExtractParts', async () => {
                const returnedFromService = Object.assign(
                    {
                        extractPart: 'BBBBBB',
                        retentionPeriod: 1,
                        fileFormat: 'BBBBBB',
                        retryCount: 1,
                        schedule: 'BBBBBB',
                        fileType: 'BBBBBB',
                        compression: 'BBBBBB',
                        pGPCert: 'BBBBBB',
                        sFTPPubKey: 'BBBBBB',
                        sFTPUsername: 'BBBBBB',
                        encryptionType: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ExtractParts', async () => {
                const returnedFromService = Object.assign(
                    {
                        extractPart: 'BBBBBB',
                        retentionPeriod: 1,
                        fileFormat: 'BBBBBB',
                        retryCount: 1,
                        schedule: 'BBBBBB',
                        fileType: 'BBBBBB',
                        compression: 'BBBBBB',
                        pGPCert: 'BBBBBB',
                        sFTPPubKey: 'BBBBBB',
                        sFTPUsername: 'BBBBBB',
                        encryptionType: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ExtractParts', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
