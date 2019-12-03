import './vendor.ts';

import { Injector, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { LocalStorageService, Ng2Webstorage, SessionStorageService } from 'ngx-webstorage';
import { JhiEventManager, NgJhipsterModule } from 'ng-jhipster';
import { AuthInterceptor } from './blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from './blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from './blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from './blocks/interceptor/notification.interceptor';
import { PdisciplinarioSharedModule } from 'app/shared';
import { PdisciplinarioCoreModule } from 'app/core';
import { PdisciplinarioAppRoutingModule } from './app-routing.module';
import { PdisciplinarioHomeModule } from './home/home.module';
import { PdisciplinarioAccountModule } from './account/account.module';
import { PdisciplinarioEntityModule } from './entities/entity.module';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';
import { FormsModule } from '@angular/forms';
import * as moment from 'moment';
// import {PopupModule} from 'ng2-opd-popud';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { ActiveMenuDirective, ErrorComponent, FooterComponent, JhiMainComponent, NavbarComponent, PageRibbonComponent } from './layouts';
import { PdisciplinarioDashboardModule } from './dashboard/dashboard.module';
import { PdisciplinarioSolicitudModule } from './solicitud/solicitud.module';
import { PdisciplinarioLoginModule } from './shared/login/login.module';
import { PdisciplinarioAdminModule } from './admin/admin.module';
import { PdisciplinarioNotificationsModule } from 'app/notifications/notifications.module';

@NgModule({
    imports: [
        BrowserModule,
        PdisciplinarioAppRoutingModule,
        PdisciplinarioAdminModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
        NgJhipsterModule.forRoot({
            // configúralo a continuación en verdadero para que las alertas parezcan tostadas
            alertAsToast: false,
            alertTimeout: 5000
        }),
        PdisciplinarioSharedModule,
        PdisciplinarioCoreModule,
        PdisciplinarioHomeModule,
        PdisciplinarioAccountModule,
        PdisciplinarioEntityModule,
        AngularMultiSelectModule,
        FormsModule,
        PdisciplinarioNotificationsModule,
        PdisciplinarioDashboardModule,
        PdisciplinarioSolicitudModule,
        PdisciplinarioLoginModule
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [LocalStorageService, SessionStorageService]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class PdisciplinarioAppModule {
    constructor(private dpConfig: NgbDatepickerConfig) {
        this.dpConfig.minDate = { year: moment().year() - 100, month: 1, day: 1 };
    }
}
