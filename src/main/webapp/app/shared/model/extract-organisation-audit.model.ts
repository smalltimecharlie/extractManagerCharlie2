import { Moment } from 'moment';
import { IOrganisation } from 'app/shared/model/organisation.model';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { IExtractTables } from 'app/shared/model/extract-tables.model';

export interface IExtractOrganisationAudit {
    id?: number;
    createdDate?: Moment;
    success?: boolean;
    organisation?: IOrganisation;
    extractConfig?: IExtractConfig;
    extractTables?: IExtractTables;
}

export class ExtractOrganisationAudit implements IExtractOrganisationAudit {
    constructor(
        public id?: number,
        public createdDate?: Moment,
        public success?: boolean,
        public organisation?: IOrganisation,
        public extractConfig?: IExtractConfig,
        public extractTables?: IExtractTables
    ) {
        this.success = this.success || false;
    }
}
