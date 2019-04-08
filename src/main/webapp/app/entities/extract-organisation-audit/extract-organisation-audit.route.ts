import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';
import { ExtractOrganisationAuditService } from './extract-organisation-audit.service';
import { ExtractOrganisationAuditComponent } from './extract-organisation-audit.component';
import { ExtractOrganisationAuditDetailComponent } from './extract-organisation-audit-detail.component';
import { ExtractOrganisationAuditUpdateComponent } from './extract-organisation-audit-update.component';
import { ExtractOrganisationAuditDeletePopupComponent } from './extract-organisation-audit-delete-dialog.component';
import { IExtractOrganisationAudit } from 'app/shared/model/extract-organisation-audit.model';

@Injectable({ providedIn: 'root' })
export class ExtractOrganisationAuditResolve implements Resolve<IExtractOrganisationAudit> {
    constructor(private service: ExtractOrganisationAuditService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractOrganisationAudit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractOrganisationAudit>) => response.ok),
                map((extractOrganisationAudit: HttpResponse<ExtractOrganisationAudit>) => extractOrganisationAudit.body)
            );
        }
        return of(new ExtractOrganisationAudit());
    }
}

export const extractOrganisationAuditRoute: Routes = [
    {
        path: '',
        component: ExtractOrganisationAuditComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisationAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractOrganisationAuditDetailComponent,
        resolve: {
            extractOrganisationAudit: ExtractOrganisationAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisationAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractOrganisationAuditUpdateComponent,
        resolve: {
            extractOrganisationAudit: ExtractOrganisationAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisationAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractOrganisationAuditUpdateComponent,
        resolve: {
            extractOrganisationAudit: ExtractOrganisationAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisationAudits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractOrganisationAuditPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractOrganisationAuditDeletePopupComponent,
        resolve: {
            extractOrganisationAudit: ExtractOrganisationAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractOrganisationAudits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
