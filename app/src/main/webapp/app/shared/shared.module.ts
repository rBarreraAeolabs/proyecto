import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { NgbActiveModal, NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { HasAnyAuthorityDirective, JhiLoginModalComponent, PdisciplinarioSharedCommonModule, PdisciplinarioSharedLibsModule } from './';
import { FileUploadComponent } from './util/file_upload/file-upload.component';
import { AppFileUploaderModule } from './util/file_upload/file-upload.module';

@NgModule({
    imports: [
        PdisciplinarioSharedLibsModule,
        PdisciplinarioSharedCommonModule,
        AppFileUploaderModule
    ],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
    providers: [
        {
            provide: NgbDateAdapter,
            useClass: NgbDateMomentAdapter
        },
        NgbActiveModal
    ],
    entryComponents: [JhiLoginModalComponent, FileUploadComponent],
    exports: [
        PdisciplinarioSharedCommonModule,
        JhiLoginModalComponent,
        HasAnyAuthorityDirective,
        AppFileUploaderModule
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioSharedModule {
    static forRoot() {
        return {
            ngModule: PdisciplinarioSharedModule
        };
    }
}
