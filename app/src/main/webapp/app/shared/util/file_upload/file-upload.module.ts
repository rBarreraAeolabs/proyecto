import { NgModule } from '@angular/core';

import { NgxUploaderModule } from 'ngx-uploader';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FileUploadComponent } from './file-upload.component';

@NgModule({
    imports: [NgxUploaderModule, BrowserModule, CommonModule],
    exports: [FileUploadComponent],
    declarations: [FileUploadComponent],
    entryComponents: [FileUploadComponent]
})
export class AppFileUploaderModule {}
