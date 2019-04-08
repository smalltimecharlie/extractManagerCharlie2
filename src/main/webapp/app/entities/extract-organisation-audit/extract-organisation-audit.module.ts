import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractOrganisationAuditComponent,
    ExtractOrganisationAuditDetailComponent,
    ExtractOrganisationAuditUpdateComponent,
    ExtractOrganisationAuditDeletePopupComponent,
    ExtractOrganisationAuditDeleteDialogComponent,
    extractOrganisationAuditRoute,
    extractOrganisationAuditPopupRoute
} from './';

const ENTITY_STATES = [...extractOrganisationAuditRoute, ...extractOrganisationAuditPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractOrganisationAuditComponent,
        ExtractOrganisationAuditDetailComponent,
        ExtractOrganisationAuditUpdateComponent,
        ExtractOrganisationAuditDeleteDialogComponent,
        ExtractOrganisationAuditDeletePopupComponent
    ],
    entryComponents: [
        ExtractOrganisationAuditComponent,
        ExtractOrganisationAuditUpdateComponent,
        ExtractOrganisationAuditDeleteDialogComponent,
        ExtractOrganisationAuditDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractOrganisationAuditModule {}
