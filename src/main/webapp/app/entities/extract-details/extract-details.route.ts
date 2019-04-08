import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractDetails } from 'app/shared/model/extract-details.model';
import { ExtractDetailsService } from './extract-details.service';
import { ExtractDetailsComponent } from './extract-details.component';
import { ExtractDetailsDetailComponent } from './extract-details-detail.component';
import { ExtractDetailsUpdateComponent } from './extract-details-update.component';
import { ExtractDetailsDeletePopupComponent } from './extract-details-delete-dialog.component';
import { IExtractDetails } from 'app/shared/model/extract-details.model';

@Injectable({ providedIn: 'root' })
export class ExtractDetailsResolve implements Resolve<IExtractDetails> {
    constructor(private service: ExtractDetailsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractDetails> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractDetails>) => response.ok),
                map((extractDetails: HttpResponse<ExtractDetails>) => extractDetails.body)
            );
        }
        return of(new ExtractDetails());
    }
}

export const extractDetailsRoute: Routes = [
    {
        path: '',
        component: ExtractDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractDetailsDetailComponent,
        resolve: {
            extractDetails: ExtractDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractDetailsUpdateComponent,
        resolve: {
            extractDetails: ExtractDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractDetails'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractDetailsUpdateComponent,
        resolve: {
            extractDetails: ExtractDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractDetailsPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractDetailsDeletePopupComponent,
        resolve: {
            extractDetails: ExtractDetailsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
