import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractAudit } from 'app/shared/model/extract-audit.model';
import { ExtractAuditService } from './extract-audit.service';
import { ExtractAuditComponent } from './extract-audit.component';
import { ExtractAuditDetailComponent } from './extract-audit-detail.component';
import { ExtractAuditUpdateComponent } from './extract-audit-update.component';
import { ExtractAuditDeletePopupComponent } from './extract-audit-delete-dialog.component';
import { IExtractAudit } from 'app/shared/model/extract-audit.model';

@Injectable({ providedIn: 'root' })
export class ExtractAuditResolve implements Resolve<IExtractAudit> {
    constructor(private service: ExtractAuditService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractAudit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractAudit>) => response.ok),
                map((extractAudit: HttpResponse<ExtractAudit>) => extractAudit.body)
            );
        }
        return of(new ExtractAudit());
    }
}

export const extractAuditRoute: Routes = [
    {
        path: '',
        component: ExtractAuditComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractAuditDetailComponent,
        resolve: {
            extractAudit: ExtractAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractAuditUpdateComponent,
        resolve: {
            extractAudit: ExtractAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractAudits'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractAuditUpdateComponent,
        resolve: {
            extractAudit: ExtractAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractAudits'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractAuditPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractAuditDeletePopupComponent,
        resolve: {
            extractAudit: ExtractAuditResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractAudits'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
