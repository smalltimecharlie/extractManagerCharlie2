import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractDetailsComponent,
    ExtractDetailsDetailComponent,
    ExtractDetailsUpdateComponent,
    ExtractDetailsDeletePopupComponent,
    ExtractDetailsDeleteDialogComponent,
    extractDetailsRoute,
    extractDetailsPopupRoute
} from './';

const ENTITY_STATES = [...extractDetailsRoute, ...extractDetailsPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractDetailsComponent,
        ExtractDetailsDetailComponent,
        ExtractDetailsUpdateComponent,
        ExtractDetailsDeleteDialogComponent,
        ExtractDetailsDeletePopupComponent
    ],
    entryComponents: [
        ExtractDetailsComponent,
        ExtractDetailsUpdateComponent,
        ExtractDetailsDeleteDialogComponent,
        ExtractDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractDetailsModule {}
