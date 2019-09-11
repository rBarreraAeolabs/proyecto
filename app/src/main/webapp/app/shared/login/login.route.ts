import {Route} from '@angular/router';
import {JhiLoginModalComponent} from 'app/shared';

export const LOGIN_ROUTE: Route = {
    path: 'iniciar-sesion',
    component: JhiLoginModalComponent,
    data: {
        authorities: [],
        pageTitle: 'pdisciplinarioApp.login.title'
    }
};
