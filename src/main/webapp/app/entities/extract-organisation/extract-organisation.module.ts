import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ExtractManagerCharlie2SharedModule } from 'app/shared';
import {
    ExtractOrganisationComponent,
    ExtractOrganisationDetailComponent,
    ExtractOrganisationUpdateComponent,
    ExtractOrganisationDeletePopupComponent,
    ExtractOrganisationDeleteDialogComponent,
    extractOrganisationRoute,
    extractOrganisationPopupRoute
} from './';

const ENTITY_STATES = [...extractOrganisationRoute, ...extractOrganisationPopupRoute];

@NgModule({
    imports: [ExtractManagerCharlie2SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExtractOrganisationComponent,
        ExtractOrganisationDetailComponent,
        ExtractOrganisationUpdateComponent,
        ExtractOrganisationDeleteDialogComponent,
        ExtractOrganisationDeletePopupComponent
    ],
    entryComponents: [
        ExtractOrganisationComponent,
        ExtractOrganisationUpdateComponent,
        ExtractOrganisationDeleteDialogComponent,
        ExtractOrganisationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2ExtractOrganisationModule {}
