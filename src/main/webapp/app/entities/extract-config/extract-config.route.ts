import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExtractConfig } from 'app/shared/model/extract-config.model';
import { ExtractConfigService } from './extract-config.service';
import { ExtractConfigComponent } from './extract-config.component';
import { ExtractConfigDetailComponent } from './extract-config-detail.component';
import { ExtractConfigUpdateComponent } from './extract-config-update.component';
import { ExtractConfigDeletePopupComponent } from './extract-config-delete-dialog.component';
import { IExtractConfig } from 'app/shared/model/extract-config.model';

@Injectable({ providedIn: 'root' })
export class ExtractConfigResolve implements Resolve<IExtractConfig> {
    constructor(private service: ExtractConfigService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IExtractConfig> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExtractConfig>) => response.ok),
                map((extractConfig: HttpResponse<ExtractConfig>) => extractConfig.body)
            );
        }
        return of(new ExtractConfig());
    }
}

export const extractConfigRoute: Routes = [
    {
        path: '',
        component: ExtractConfigComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractConfigs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ExtractConfigDetailComponent,
        resolve: {
            extractConfig: ExtractConfigResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractConfigs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ExtractConfigUpdateComponent,
        resolve: {
            extractConfig: ExtractConfigResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractConfigs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ExtractConfigUpdateComponent,
        resolve: {
            extractConfig: ExtractConfigResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractConfigs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const extractConfigPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ExtractConfigDeletePopupComponent,
        resolve: {
            extractConfig: ExtractConfigResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ExtractConfigs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
