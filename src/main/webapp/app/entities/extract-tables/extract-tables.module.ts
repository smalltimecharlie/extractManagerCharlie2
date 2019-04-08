import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractTablesComponent,
    ExtractTablesDetailComponent,
    ExtractTablesUpdateComponent,
    ExtractTablesDeletePopupComponent,
    ExtractTablesDeleteDialogComponent,
    extractTablesRoute,
    extractTablesPopupRoute
} from './';

const ENTITY_STATES = [...extractTablesRoute, ...extractTablesPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractTablesComponent,
        ExtractTablesDetailComponent,
        ExtractTablesUpdateComponent,
        ExtractTablesDeleteDialogComponent,
        ExtractTablesDeletePopupComponent
    ],
    entryComponents: [
        ExtractTablesComponent,
        ExtractTablesUpdateComponent,
        ExtractTablesDeleteDialogComponent,
        ExtractTablesDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractTablesModule {}
