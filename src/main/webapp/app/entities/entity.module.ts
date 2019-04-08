import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'organisation',
                loadChildren: './organisation/organisation.module#ExtractManagerCharlie2OrganisationModule'
            },
            {
                path: 'extract-config',
                loadChildren: './extract-config/extract-config.module#ExtractManagerCharlie2ExtractConfigModule'
            },
            {
                path: 'extract-organisation',
                loadChildren: './extract-organisation/extract-organisation.module#ExtractManagerCharlie2ExtractOrganisationModule'
            },
            {
                path: 'extract-parts',
                loadChildren: './extract-parts/extract-parts.module#ExtractManagerCharlie2ExtractPartsModule'
            },
            {
                path: 'extract-details',
                loadChildren: './extract-details/extract-details.module#ExtractManagerCharlie2ExtractDetailsModule'
            },
            {
                path: 'extract-tables',
                loadChildren: './extract-tables/extract-tables.module#ExtractManagerCharlie2ExtractTablesModule'
            },
            {
                path: 'extract-organisation-audit',
                loadChildren:
                    './extract-organisation-audit/extract-organisation-audit.module#ExtractManagerCharlie2ExtractOrganisationAuditModule'
            },
            {
                path: 'extract-audit',
                loadChildren: './extract-audit/extract-audit.module#ExtractManagerCharlie2ExtractAuditModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ExtractManagerCharlie2EntityModule {}
