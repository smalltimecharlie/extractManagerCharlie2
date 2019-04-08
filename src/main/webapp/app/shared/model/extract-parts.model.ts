import { IExtractConfig } from 'app/shared/model/extract-config.model';
import { IExtractTables } from 'app/shared/model/extract-tables.model';

export interface IExtractParts {
    id?: number;
    extractPart?: string;
    retentionPeriod?: number;
    fileFormat?: string;
    retryCount?: number;
    schedule?: string;
    fileType?: string;
    compression?: string;
    pGPCert?: string;
    sFTPPubKey?: string;
    sFTPUsername?: string;
    encryptionType?: string;
    extractConfig?: IExtractConfig;
    extractTables?: IExtractTables;
}

export class ExtractParts implements IExtractParts {
    constructor(
        public id?: number,
        public extractPart?: string,
        public retentionPeriod?: number,
        public fileFormat?: string,
        public retryCount?: number,
        public schedule?: string,
        public fileType?: string,
        public compression?: string,
        public pGPCert?: string,
        public sFTPPubKey?: string,
        public sFTPUsername?: string,
        public encryptionType?: string,
        public extractConfig?: IExtractConfig,
        public extractTables?: IExtractTables
    ) {}
}
