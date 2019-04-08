import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractTables } from 'app/shared/model/extract-tables.model';
import { ExtractTablesService } from './extract-tables.service';
import { ExtractTablesComponent } from './extract-tables.component';
import { ExtractTablesDetailComponent } from './extract-tables-detail.component';
import { ExtractTablesUpdateComponent } from './extract-tables-update.component';
import { ExtractTablesDeletePopupComponent } from './extract-tables-delete-dialog.component';
import { IExtractTables } from 'app/shared/model/extract-tables.model';

@Injectable({ providedIn: 'root' })
export class ExtractTablesResolve implements Resolve<IExtractTables> {
    constructor(private service: ExtractTablesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractTables> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractTables>) => response.ok),
                map((extractTables: HttpResponse<ExtractTables>) => extractTables.body)
            );
        }
        return of(new ExtractTables());
    }
}

export const extractTablesRoute: Routes = [
    {
        path: '',
        component: ExtractTablesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractTables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractTablesDetailComponent,
        resolve: {
            extractTables: ExtractTablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractTables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractTablesUpdateComponent,
        resolve: {
            extractTables: ExtractTablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractTables'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractTablesUpdateComponent,
        resolve: {
            extractTables: ExtractTablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractTables'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractTablesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractTablesDeletePopupComponent,
        resolve: {
            extractTables: ExtractTablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractTables'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
