import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractAuditComponent,
    ExtractAuditDetailComponent,
    ExtractAuditUpdateComponent,
    ExtractAuditDeletePopupComponent,
    ExtractAuditDeleteDialogComponent,
    extractAuditRoute,
    extractAuditPopupRoute
} from './';

const ENTITY_STATES = [...extractAuditRoute, ...extractAuditPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractAuditComponent,
        ExtractAuditDetailComponent,
        ExtractAuditUpdateComponent,
        ExtractAuditDeleteDialogComponent,
        ExtractAuditDeletePopupComponent
    ],
    entryComponents: [
        ExtractAuditComponent,
        ExtractAuditUpdateComponent,
        ExtractAuditDeleteDialogComponent,
        ExtractAuditDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractAuditModule {}
