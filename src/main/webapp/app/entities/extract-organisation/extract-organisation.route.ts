import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractOrganisation } from 'app/shared/model/extract-organisation.model';
import { ExtractOrganisationService } from './extract-organisation.service';
import { ExtractOrganisationComponent } from './extract-organisation.component';
import { ExtractOrganisationDetailComponent } from './extract-organisation-detail.component';
import { ExtractOrganisationUpdateComponent } from './extract-organisation-update.component';
import { ExtractOrganisationDeletePopupComponent } from './extract-organisation-delete-dialog.component';
import { IExtractOrganisation } from 'app/shared/model/extract-organisation.model';

@Injectable({ providedIn: 'root' })
export class ExtractOrganisationResolve implements Resolve<IExtractOrganisation> {
    constructor(private service: ExtractOrganisationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractOrganisation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractOrganisation>) => response.ok),
                map((extractOrganisation: HttpResponse<ExtractOrganisation>) => extractOrganisation.body)
            );
        }
        return of(new ExtractOrganisation());
    }
}

export const extractOrganisationRoute: Routes = [
    {
        path: '',
        component: ExtractOrganisationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractOrganisationDetailComponent,
        resolve: {
            extractOrganisation: ExtractOrganisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractOrganisationUpdateComponent,
        resolve: {
            extractOrganisation: ExtractOrganisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractOrganisationUpdateComponent,
        resolve: {
            extractOrganisation: ExtractOrganisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractOrganisationPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractOrganisationDeletePopupComponent,
        resolve: {
            extractOrganisation: ExtractOrganisationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
