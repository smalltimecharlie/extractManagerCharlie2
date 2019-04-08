import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractParts } from 'app/shared/model/extract-parts.model';
import { ExtractPartsService } from './extract-parts.service';
import { ExtractPartsComponent } from './extract-parts.component';
import { ExtractPartsDetailComponent } from './extract-parts-detail.component';
import { ExtractPartsUpdateComponent } from './extract-parts-update.component';
import { ExtractPartsDeletePopupComponent } from './extract-parts-delete-dialog.component';
import { IExtractParts } from 'app/shared/model/extract-parts.model';

@Injectable({ providedIn: 'root' })
export class ExtractPartsResolve implements Resolve<IExtractParts> {
    constructor(private service: ExtractPartsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractParts> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractParts>) => response.ok),
                map((extractParts: HttpResponse<ExtractParts>) => extractParts.body)
            );
        }
        return of(new ExtractParts());
    }
}

export const extractPartsRoute: Routes = [
    {
        path: '',
        component: ExtractPartsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractParts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractPartsDetailComponent,
        resolve: {
            extractParts: ExtractPartsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractParts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractPartsUpdateComponent,
        resolve: {
            extractParts: ExtractPartsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractParts'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractPartsUpdateComponent,
        resolve: {
            extractParts: ExtractPartsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractParts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractPartsPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractPartsDeletePopupComponent,
        resolve: {
            extractParts: ExtractPartsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractParts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
