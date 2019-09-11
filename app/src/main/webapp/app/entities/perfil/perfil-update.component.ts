import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {JhiAlertService} from 'ng-jhipster';

import {IPerfil} from 'app/shared/model/perfil.model';
import {PerfilService} from './perfil.service';
import {IGrupo} from 'app/shared/model/grupo.model';
import {GrupoService} from 'app/entities/grupo';

interface IAuthority {
    state?: boolean;
    name?: string;
}

@Component({
    selector: 'jhi-perfil-update',
    templateUrl: './perfil-update.component.html'
})
export class PerfilUpdateComponent implements OnInit {
    private _perfil: IPerfil;
    isSaving: boolean;
    grupos: IGrupo[];
    nombre: string;
    idGrupo: string = null;
    idPerfil: string = null;

    // Variables para el checkbox primera columna
    crearProvidenciaPrivilege: IAuthority = {state: false, name: 'CREAR_PROVIDENCIA_PRIVILEGE'};
    crearPerfilPrivilege: IAuthority = {state: false, name: 'CREAR_PERFIL_PRIVILEGE'};
    crearFichaIngresoPrivilege: IAuthority = {state: false, name: 'CREAR_FICHA_INGRESO_PRIVILAGE'};
    crearGrupoPrivilege: IAuthority = {state: false, name: 'CREAR_GRUPO_PRIVILEGE'};
    crearUsuarioPrivilege: IAuthority = {state: false, name: 'CREAR_USUARIO_PRIVILEGE'};
    crearPlantillaPrivilege: IAuthority = {state: false, name: 'CREAR_PLANTILLA_PRIVILEGE'};
    editarInvestigacionPrivilege: IAuthority = {state: false, name: 'EDITAR_INVESTIGACION_PRIVILEGE'};
    editarPerfilPrivilege: IAuthority = {state: false, name: 'EDITAR_PERFIL_PRIVILEGE'};
    editarUsuarioPrivilege: IAuthority = {state: false, name: 'EDITAR_USUARIO_PRIVILEGE'};
    editarGrupoPrivilege: IAuthority = {state: false, name: 'EDITAR_GRUPO_PRIVILEGE'};

    // Variables para el checkbox segunda columna
    editarPlantillaPrivilege: IAuthority = {state: false, name: 'EDITAR_PLANTILLA_PRIVILEGE'};
    editarProvidenciaPrivilege: IAuthority = {state: false, name: 'EDITAR_PROVIDENCIA_PRIVILEGE'};
    actualizarAdjuntoPrivilege: IAuthority = {state: false, name: 'ACTUALIZAR_ADJUNTO_PRIVILEGE'};
    actualizarDocumentoPrivilege: IAuthority = {state: false, name: 'ACTUALIZAR_DOCUMENTO_PRIVILEGE'};
    eliminarDocumentoPrivilege: IAuthority = {state: false, name: 'ELIMINAR_DOCUMENTO_PRIVILEGE'};
    eliminarGrupoPrivilege: IAuthority = {state: false, name: 'ELIMINAR_GRUPO_PRIVILEGE'};
    eliminarPerfilPrivilege: IAuthority = {state: false, name: 'ELIMINAR_PERFIL_PRIVILEGE'};
    eliminarUsuarioPrivilege: IAuthority = {state: false, name: 'ELIMINAR_USUARIO_PRIVILEGE'};
    eliminarPlantillaPrivilege: IAuthority = {state: false, name: 'ELIMINAR_PLANTILLA_PRIVILEGE'};
    eliminarAdjuntoPrivilege: IAuthority = {state: false, name: 'ELIMINAR_ADJUNTO_PRIVILEGE'};

    // Variables para el checkbox tercera columna
    visualizarFichaIngresoPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_FICHA_INGRESO_PRIVILEGE'};
    visualizarInvestigacionPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_INVESTIGACION_PRIVILEGE'};
    visualizarAdjuntoPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_ADJUNTO_PRIVILEGE'};
    visualizarDocumentoPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_DOCUMENTO_PRIVILEGE'};
    visualizarProvidenciaPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_PROVIDENCIA_PRIVILEGE'};
    visualizarPerfilPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_PERFIL_PRIVILEGE'};
    visualizarUsuarioPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_USUARIO_PRIVILEGE'};
    visualizarGrupoPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_GRUPO_PRIVILEGE'};
    visualizarPlantillaPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_PLANTILLA_PRIVILEGE'};
    visualizarDashboardPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_DASHBOARD_PRIVILEGE'};

    // Variables para el checkbox cuarta columna
    visualizarNotificacionPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_NOTIFICACION_PRIVILEGE'};
    visualizarMenuUsuarioPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_MENU_USUARIO_PRIVILAGE'};
    visualizarSumarioPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_SUMARIO_PRIVILEGE'};
    visualizarProcedimientosPrivilege: IAuthority = {state: false, name: 'VISUALIZAR_PROCEDIMIENTOS_PRIVILEGE'};
    adjuntarAdjuntoPrivilege: IAuthority = {state: false, name: 'ADJUNTAR_ADJUNTO_PRIVILEGE'};
    descargarAdjuntoPrivilege: IAuthority = {state: false, name: 'DESCARGAR_ADJUNTO_PRIVILEGE'};
    asignarTtipoAProvidenciaPrivilege: IAuthority = {state: false, name: 'ASIGNAR_TIPO_A_PROVIDENCIA_PRIVILEGE'};
    derivarProvidenciaPrivilege: IAuthority = {state: false, name: 'DERIVAR_PROVIDENCIA_PRIVILEGE'};
    adjuntarDocumentoPrivilege: IAuthority = {state: false, name: 'ADJUNTAR_DOCUMENTO_PRIVILEGE'};
    descargarDocumentoPrivilege: IAuthority = {state: false, name: 'DESCARGAR_DOCUMENTO_PRIVILEGE'};

    selectedAuthorities = [];

    constructor(
        private jhiAlertService: JhiAlertService,
        private perfilService: PerfilService,
        private grupoService: GrupoService,
        private activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;

        this.activatedRoute.data.subscribe(({perfil}) => {
            this._perfil = perfil;
            if (perfil.id !== null && typeof perfil.id !== 'undefined') {

                if (perfil.authorities !== null && perfil.authorities.length > 0) {
                    console.log('actualizando el perfil', this.perfil);
                    this.perfil.authorities.map(authority => {
                        this.selectPrivileges(authority.name);
                    });
                }

            }
            this.idPerfil = typeof this._perfil.id === 'undefined' ? null : '' + this._perfil.id;
        });

        this.perfilService.authorities().subscribe(authorities => {
            this.cargarInfoPerfilToUpdate();
        });

        this.grupoService.query().subscribe(
            (res: HttpResponse<IGrupo[]>) => {
                this.grupos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    selectPrivileges(value: string) {
        switch (value) {
            // PRIMERA COLUMNA
            case 'CREAR_PROVIDENCIA_PRIVILEGE' : {
                this.crearProvidenciaPrivilege.state = !this.crearProvidenciaPrivilege.state;
                if (this.crearProvidenciaPrivilege.state) {
                    this.selectedAuthorities.push(this.crearProvidenciaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_PROVIDENCIA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'CREAR_PERFIL_PRIVILEGE' : {
                this.crearPerfilPrivilege.state = !this.crearPerfilPrivilege.state;
                if (this.crearPerfilPrivilege.state) {
                    this.selectedAuthorities.push(this.crearPerfilPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_PERFIL_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'CREAR_FICHA_INGRESO_PRIVILAGE' : {
                this.crearFichaIngresoPrivilege.state = !this.crearFichaIngresoPrivilege.state;
                if (this.crearFichaIngresoPrivilege.state) {
                    this.selectedAuthorities.push(this.crearFichaIngresoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_FICHA_INGRESO_PRIVILAGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'CREAR_GRUPO_PRIVILEGE' : {
                this.crearGrupoPrivilege.state = !this.crearGrupoPrivilege.state;
                if (this.crearGrupoPrivilege.state) {
                    this.selectedAuthorities.push(this.crearGrupoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_GRUPO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'CREAR_USUARIO_PRIVILEGE' : {
                this.crearUsuarioPrivilege.state = !this.crearUsuarioPrivilege.state;
                if (this.crearUsuarioPrivilege.state) {
                    this.selectedAuthorities.push(this.crearUsuarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_USUARIO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'CREAR_PLANTILLA_PRIVILEGE' : {
                this.crearPlantillaPrivilege.state = !this.crearPlantillaPrivilege.state;
                if (this.crearPlantillaPrivilege.state) {
                    this.selectedAuthorities.push(this.crearPlantillaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'CREAR_PLANTILLA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'EDITAR_INVESTIGACION_PRIVILEGE' : {
                this.editarInvestigacionPrivilege.state = !this.editarInvestigacionPrivilege.state;
                if (this.editarInvestigacionPrivilege.state) {
                    this.selectedAuthorities.push(this.editarInvestigacionPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_INVESTIGACION_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'EDITAR_PERFIL_PRIVILEGE' : {
                this.editarPerfilPrivilege.state = !this.editarPerfilPrivilege.state;
                if (this.editarPerfilPrivilege.state) {
                    this.selectedAuthorities.push(this.editarPerfilPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_PERFIL_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'EDITAR_USUARIO_PRIVILEGE' : {
                this.editarUsuarioPrivilege.state = !this.editarUsuarioPrivilege.state;
                if (this.editarUsuarioPrivilege.state) {
                    this.selectedAuthorities.push(this.editarUsuarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_USUARIO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'EDITAR_GRUPO_PRIVILEGE' : {
                this.editarGrupoPrivilege.state = !this.editarGrupoPrivilege.state;
                if (this.editarGrupoPrivilege.state) {
                    this.selectedAuthorities.push(this.editarGrupoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_GRUPO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            // SEGUNDA COLUMNA
            case 'EDITAR_PLANTILLA_PRIVILEGE' : {
                this.editarPlantillaPrivilege.state = !this.editarPlantillaPrivilege.state;
                if (this.editarPlantillaPrivilege.state) {
                    this.selectedAuthorities.push(this.editarPlantillaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_PLANTILLA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'EDITAR_PROVIDENCIA_PRIVILEGE' : {
                this.editarProvidenciaPrivilege.state = !this.editarProvidenciaPrivilege.state;
                if (this.editarProvidenciaPrivilege.state) {
                    this.selectedAuthorities.push(this.editarProvidenciaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'EDITAR_PROVIDENCIA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ACTUALIZAR_ADJUNTO_PRIVILEGE' : {
                this.actualizarAdjuntoPrivilege.state = !this.actualizarAdjuntoPrivilege.state;
                if (this.actualizarAdjuntoPrivilege.state) {
                    this.selectedAuthorities.push(this.actualizarAdjuntoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ACTUALIZAR_ADJUNTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ACTUALIZAR_DOCUMENTO_PRIVILEGE' : {
                this.actualizarDocumentoPrivilege.state = !this.actualizarDocumentoPrivilege.state;
                if (this.actualizarDocumentoPrivilege.state) {
                    this.selectedAuthorities.push(this.actualizarDocumentoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ACTUALIZAR_DOCUMENTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_DOCUMENTO_PRIVILEGE' : {
                this.eliminarDocumentoPrivilege.state = !this.eliminarDocumentoPrivilege.state;
                if (this.eliminarDocumentoPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarDocumentoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_DOCUMENTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_GRUPO_PRIVILEGE' : {
                this.eliminarGrupoPrivilege.state = !this.eliminarGrupoPrivilege.state;
                if (this.eliminarGrupoPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarGrupoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_GRUPO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_PERFIL_PRIVILEGE' : {
                this.eliminarPerfilPrivilege.state = !this.eliminarPerfilPrivilege.state;
                if (this.eliminarPerfilPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarPerfilPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_PERFIL_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_USUARIO_PRIVILEGE' : {
                this.eliminarUsuarioPrivilege.state = !this.eliminarUsuarioPrivilege.state;
                if (this.eliminarUsuarioPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarUsuarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_USUARIO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_PLANTILLA_PRIVILEGE' : {
                this.eliminarPlantillaPrivilege.state = !this.eliminarPlantillaPrivilege.state;
                if (this.eliminarPlantillaPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarPlantillaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_PLANTILLA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ELIMINAR_ADJUNTO_PRIVILEGE' : {
                this.eliminarAdjuntoPrivilege.state = !this.eliminarAdjuntoPrivilege.state;
                if (this.eliminarAdjuntoPrivilege.state) {
                    this.selectedAuthorities.push(this.eliminarAdjuntoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ELIMINAR_ADJUNTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            // TERCERA COLUMNA
            case 'VISUALIZAR_FICHA_INGRESO_PRIVILEGE' : {
                this.visualizarFichaIngresoPrivilege.state = !this.visualizarFichaIngresoPrivilege.state;
                if (this.visualizarFichaIngresoPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarFichaIngresoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_FICHA_INGRESO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_INVESTIGACION_PRIVILEGE' : {
                this.visualizarInvestigacionPrivilege.state = !this.visualizarInvestigacionPrivilege.state;
                if (this.visualizarInvestigacionPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarInvestigacionPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_INVESTIGACION_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_ADJUNTO_PRIVILEGE' : {
                this.visualizarAdjuntoPrivilege.state = !this.visualizarAdjuntoPrivilege.state;
                if (this.visualizarAdjuntoPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarAdjuntoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_ADJUNTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_DOCUMENTO_PRIVILEGE' : {
                this.visualizarDocumentoPrivilege.state = !this.visualizarDocumentoPrivilege.state;
                if (this.visualizarDocumentoPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarDocumentoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_DOCUMENTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_PROVIDENCIA_PRIVILEGE' : {
                this.visualizarProvidenciaPrivilege.state = !this.visualizarProvidenciaPrivilege.state;
                if (this.visualizarProvidenciaPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarProvidenciaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_PROVIDENCIA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_PERFIL_PRIVILEGE' : {
                this.visualizarPerfilPrivilege.state = !this.visualizarPerfilPrivilege.state;
                if (this.visualizarPerfilPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarPerfilPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_PERFIL_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_USUARIO_PRIVILEGE' : {
                this.visualizarUsuarioPrivilege.state = !this.visualizarUsuarioPrivilege.state;
                if (this.visualizarUsuarioPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarUsuarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_USUARIO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_GRUPO_PRIVILEGE' : {
                this.visualizarGrupoPrivilege.state = !this.visualizarGrupoPrivilege.state;
                if (this.visualizarGrupoPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarGrupoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_GRUPO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_PLANTILLA_PRIVILEGE' : {
                this.visualizarPlantillaPrivilege.state = !this.visualizarPlantillaPrivilege.state;
                if (this.visualizarPlantillaPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarPlantillaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_PLANTILLA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_DASHBOARD_PRIVILEGE' : {
                this.visualizarDashboardPrivilege.state = !this.visualizarDashboardPrivilege.state;
                if (this.visualizarDashboardPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarDashboardPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_DASHBOARD_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            // CUARTA COLUMNA
            case 'VISUALIZAR_NOTIFICACION_PRIVILEGE' : {
                this.visualizarNotificacionPrivilege.state = !this.visualizarNotificacionPrivilege.state;
                if (this.visualizarNotificacionPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarNotificacionPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_NOTIFICACION_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_MENU_USUARIO_PRIVILAGE' : {
                this.visualizarMenuUsuarioPrivilege.state = !this.visualizarMenuUsuarioPrivilege.state;
                if (this.visualizarMenuUsuarioPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarMenuUsuarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_MENU_USUARIO_PRIVILAGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_SUMARIO_PRIVILEGE' : {
                this.visualizarSumarioPrivilege.state = !this.visualizarSumarioPrivilege.state;
                if (this.visualizarSumarioPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarSumarioPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_SUMARIO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'VISUALIZAR_PROCEDIMIENTOS_PRIVILEGE' : {
                this.visualizarProcedimientosPrivilege.state = !this.visualizarProcedimientosPrivilege.state;
                if (this.visualizarProcedimientosPrivilege.state) {
                    this.selectedAuthorities.push(this.visualizarProcedimientosPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'VISUALIZAR_PROCEDIMIENTOS_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ADJUNTAR_ADJUNTO_PRIVILEGE' : {
                this.adjuntarAdjuntoPrivilege.state = !this.adjuntarAdjuntoPrivilege.state;
                if (this.adjuntarAdjuntoPrivilege.state) {
                    this.selectedAuthorities.push(this.adjuntarAdjuntoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ADJUNTAR_ADJUNTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'DESCARGAR_ADJUNTO_PRIVILEGE' : {
                this.descargarAdjuntoPrivilege.state = !this.descargarAdjuntoPrivilege.state;
                if (this.descargarAdjuntoPrivilege.state) {
                    this.selectedAuthorities.push(this.descargarAdjuntoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'DESCARGAR_ADJUNTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ASIGNAR_TIPO_A_PROVIDENCIA_PRIVILEGE' : {
                this.asignarTtipoAProvidenciaPrivilege.state = !this.asignarTtipoAProvidenciaPrivilege.state;
                if (this.asignarTtipoAProvidenciaPrivilege.state) {
                    this.selectedAuthorities.push(this.asignarTtipoAProvidenciaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ASIGNAR_TIPO_A_PROVIDENCIA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'DERIVAR_PROVIDENCIA_PRIVILEGE' : {
                this.derivarProvidenciaPrivilege.state = !this.derivarProvidenciaPrivilege.state;
                if (this.derivarProvidenciaPrivilege.state) {
                    this.selectedAuthorities.push(this.derivarProvidenciaPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'DERIVAR_PROVIDENCIA_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'ADJUNTAR_DOCUMENTO_PRIVILEGE' : {
                this.adjuntarDocumentoPrivilege.state = !this.adjuntarDocumentoPrivilege.state;
                if (this.adjuntarDocumentoPrivilege.state) {
                    this.selectedAuthorities.push(this.adjuntarDocumentoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'ADJUNTAR_DOCUMENTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
            case 'DESCARGAR_DOCUMENTO_PRIVILEGE' : {
                this.descargarDocumentoPrivilege.state = !this.descargarDocumentoPrivilege.state;
                if (this.descargarDocumentoPrivilege.state) {
                    this.selectedAuthorities.push(this.descargarDocumentoPrivilege.name);
                } else {
                    let auxArray = [];
                    auxArray = this.selectedAuthorities.filter(a => {
                        return a !== 'DESCARGAR_DOCUMENTO_PRIVILEGE';
                    });
                    this.selectedAuthorities = auxArray;
                }
                break;
            }
        }
        console.log(value);
    }

    cargarInfoPerfilToUpdate() {
        if (this.idPerfil !== null && typeof this._perfil.id !== 'undefined') {
            this.nombre = this._perfil.nombre;
        }
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        console.log('arreglo de roles', this.selectedAuthorities);

        this._perfil.grupo = this.grupos
            .filter(grupo => {
                if (parseInt(this.idGrupo, 0) === grupo.id) {
                    return grupo;
                }
            })
            .pop();

        this._perfil.authorities = this.selectedAuthorities;
        this._perfil.nombre = this.nombre;
        console.log(this._perfil);

        if (typeof this._perfil.id !== 'undefined') {
            this.subscribeToSaveResponse(this.perfilService.update(this._perfil));
        } else {
            this.subscribeToSaveResponse(this.perfilService.create(this._perfil));
        }

        // this.perfil.authorities = this.selectedAuthorities.map(authority => {
        //     return authority.itemName.split(' ').join('_');
        // });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPerfil>>) {
        result.subscribe((res: HttpResponse<IPerfil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGrupoById(index: number, item: IGrupo) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }

    get perfil() {
        return this._perfil;
    }

    set perfil(perfil: IPerfil) {
        this._perfil = perfil;
    }

    private seleccionDeAuthorities() {
    }
}
