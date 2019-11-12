import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiLanguageHelper, User, UserService } from 'app/core';
import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from 'app/entities/perfil';
import { GrupoService } from 'app/entities/grupo';
import {IGrupo} from '../../shared/model/grupo.model';

@Component({
    selector: 'jhi-user-mgmt-update',
    templateUrl: './user-management-update.component.html'
})
export class UserMgmtUpdateComponent implements OnInit {
    user: User;
    languages: any[];
    authorities: any[];
    isSaving: boolean;
    perfiles: IPerfil[];
    idPerfil: string = null;
    doNotMatch: string;
    newPassword: string;
    confirmPassword: string;
    grupos: IGrupo[];
    idGrupo: string = null;

    constructor(
        private languageHelper: JhiLanguageHelper,
        private userService: UserService,
        private route: ActivatedRoute,
        private perfilesService: PerfilService,
        private grupoService: GrupoService,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? user.body : user;
        });
        this.authorities = [];
        this.userService.authorities().subscribe(authorities => {
            this.authorities = authorities;
        });

        this.perfiles = [];
        this.perfilesService.query().subscribe(perfiles => {
            this.perfiles = perfiles.body;
        });

        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });

        this.grupoService.getAllAsList().subscribe(response => {
            this.grupos = response.body;
        });
    }

    previousState() {
        this.router.navigate(['/user-management']);
    }

    save() {
        this.isSaving = true;
        this.user.perfil = this.perfiles
            .filter(perfil => {
                if (parseInt(this.idPerfil, 10) === perfil.id) {
                    return perfil;
                }
            })
            .pop();

        this.user.grupo = this.grupos.filter(grupo => {
            return grupo.id === parseInt(this.idGrupo, 10);
        }).pop();

        if (this.doNotMatch == null) {
            this.user.password = this.newPassword;
            this.user.langKey = 'es';

            if (this.user.id !== null) {
                this.userService.update(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
            } else {
                this.userService.create(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
            }
        }
    }
    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    setLogin() {
        if (this.user.email !== null && typeof this.user.email !== 'undefined') {
            this.user.login = this.user.email;
        }
    }

    confirmMatch() {
        if (this.newPassword !== this.confirmPassword) {
            this.doNotMatch = 'ERROR';
        } else {
            this.doNotMatch = null;
        }
    }
}
