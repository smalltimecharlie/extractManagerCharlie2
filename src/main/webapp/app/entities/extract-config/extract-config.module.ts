import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractConfigComponent,
    ExtractConfigDetailComponent,
    ExtractConfigUpdateComponent,
    ExtractConfigDeletePopupComponent,
    ExtractConfigDeleteDialogComponent,
    extractConfigRoute,
    extractConfigPopupRoute
} from './';

const ENTITY_STATES = [...extractConfigRoute, ...extractConfigPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractConfigComponent,
        ExtractConfigDetailComponent,
        ExtractConfigUpdateComponent,
        ExtractConfigDeleteDialogComponent,
        ExtractConfigDeletePopupComponent
    ],
    entryComponents: [
        ExtractConfigComponent,
        ExtractConfigUpdateComponent,
        ExtractConfigDeleteDialogComponent,
        ExtractConfigDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractConfigModule {}
