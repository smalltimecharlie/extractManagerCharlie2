import { Moment } from 'moment';
import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { IExtractTables } from 'app/shared/model/extract-tables.model';

export interface IExtractAudit {
    id?: number;
    extractPart?: string;
    createdDate?: Moment;
    message?: string;
    extractStartTime?: string;
    extractEndTime?: string;
    firstExtractPoint?: string;
    lastExtractPoint?: string;
    outputFileName?: string;
    recordCount?: number;
    fileSize?: number;
    airflowUrl?: string;
    success?: boolean;
    extractConfig?: IExtractConfig;
    extractTables?: IExtractTables;
}

export class ExtractAudit implements IExtractAudit {
    constructor(
        public id?: number,
        public extractPart?: string,
        public createdDate?: Moment,
        public message?: string,
        public extractStartTime?: string,
        public extractEndTime?: string,
        public firstExtractPoint?: string,
        public lastExtractPoint?: string,
        public outputFileName?: string,
        public recordCount?: number,
        public fileSize?: number,
        public airflowUrl?: string,
        public success?: boolean,
        public extractConfig?: IExtractConfig,
        public extractTables?: IExtractTables
    ) {
        this.success = this.success || false;
    }
}
