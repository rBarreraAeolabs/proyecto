import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';
import { VERSION } from 'app/app.constants';
import {JhiLanguageHelper, Principal, LoginModalService, LoginService, UserService, AccountService} from 'app/core';
import { ProfileService } from '../profiles/profile.service';
import {NotificacionService} from 'app/notifications/notificaciones.service';

@Component({
    selector: 'jhi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['navbar.scss']
})
export class NavbarComponent implements OnInit {
    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    noLeidas: number;
    notificacionesNoLeidas: any;
    todasNotificaciones: any;
    account: any;

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private notificacionService: NotificacionService,
        private idUsuarioLogeado: UserService,
        private accountService: AccountService

    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.accountService.get().subscribe(usuarioLogeado => {
            this.account = usuarioLogeado.body;
            this.notificacionService.find(this.account.id).subscribe(res => {
                this.todasNotificaciones = res.body;
                this.notificacionesNoLeidas = this.todasNotificaciones.filter(noti => noti.visto === false);
                this.noLeidas = this.notificacionesNoLeidas.length;
                console.log(res.body);
                console.log(this.notificacionesNoLeidas);
            });
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
        this.profileService.getProfileInfo().then(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
        this.changeLanguage('es');
    }

    changeLanguage(languageKey: string) {
        this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['iniciar-sesion']);
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.principal.getImageUrl() : null;
    }

    marcarLeida(id) {
        this.notificacionService.marcarLeidas(id).subscribe(res1 => {
            res1=null;
            this.notificacionService.find(this.account.id).subscribe(res => {
                this.todasNotificaciones = res.body;
                this.notificacionesNoLeidas = this.todasNotificaciones.filter(noti => noti.visto === false);
                this.noLeidas = this.notificacionesNoLeidas.length;
                console.log(this.notificacionesNoLeidas);
            });
        });
    }
}
