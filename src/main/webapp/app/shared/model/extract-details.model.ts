import { IExtractConfig } from 'app/shared/model/extract-config.model';

export interface IExtractDetails {
    id?: number;
    key?: string;
    valueContentType?: string;
    value?: any;
    extractConfig?: IExtractConfig;
}

export class ExtractDetails implements IExtractDetails {
    constructor(
        public id?: number,
        public key?: string,
        public valueContentType?: string,
        public value?: any,
        public extractConfig?: IExtractConfig
    ) {}
}
