import { IExtractConfig } from 'app/shared/model/extract-config.model';

export interface IExtractDetails {
    id?: number;
    key?: string;
    value?: string;
    extractConfig?: IExtractConfig;
}

export class ExtractDetails implements IExtractDetails {
    constructor(public id?: number, public key?: string, public value?: string, public extractConfig?: IExtractConfig) {}
}
