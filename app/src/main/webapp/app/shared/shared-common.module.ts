import {NgModule} from '@angular/core';

import {PdisciplinarioSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent} from './';

@NgModule({
    imports: [PdisciplinarioSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent],
    exports: [PdisciplinarioSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent]
})
export class PdisciplinarioSharedCommonModule {
}
