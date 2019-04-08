import { NgModule } from '@angular/core';

import { ExtractManagerCharlie2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ExtractManagerCharlie2SharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ExtractManagerCharlie2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ExtractManagerCharlie2SharedCommonModule {}
