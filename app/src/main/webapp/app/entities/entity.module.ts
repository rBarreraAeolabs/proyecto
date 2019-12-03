import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PdisciplinarioPerfilModule } from './perfil/perfil.module';
import { PdisciplinarioGrupoModule } from './grupo/grupo.module';
import { PdisciplinarioPlazoModule } from './plazo/plazo.module';
import { PdisciplinarioPlantillaModule } from './plantilla/plantilla.module';
import { PdisciplinarioEntidadModule } from 'app/entities/entidad/entidad.module';
import { PdisciplinarioInstruccionesModule } from 'app/entities/instrucciones/instrucciones.module';
import { PdisciplinarioDocumentoModule } from './documento/documento.module';
import { PdisciplinarioAdjuntoModule } from './adjunto/adjunto.module';
import { PdisciplinarioFichaIngresoSdjModule } from './ficha-ingreso-sdj/ficha-ingreso-sdj.module';
import { PdisciplinarioDerivacionModule } from './derivacion/derivacion.module';
import { PdisciplinarioProvidenciaModule } from './providencia/providencia.module';
import { PdisciplinarioSumarioAdministrativoModule } from './sumario-administrativo/sumario-administrativo.module';
import { PdisciplinarioInvestigacionSumariaModule } from './investigacion-sumaria/investigacion-sumaria.module';
import { PdisciplinarioMovimientoSumarioAdministrativoModule } from './movimiento-sumario-administrativo/movimiento-sumario-administrativo.module';
import { PdisciplinarioMovimientoInvestigacionSumariaModule } from './movimiento-investigacion-sumaria/movimiento-investigacion-sumaria.module';
import { PdisciplinarioRespuestaModule } from 'app/entities/respuesta/respuesta.module';

/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        PdisciplinarioPerfilModule,
        PdisciplinarioGrupoModule,
        PdisciplinarioPlazoModule,
        PdisciplinarioPlantillaModule,
        PdisciplinarioEntidadModule,
        PdisciplinarioInstruccionesModule,
        PdisciplinarioDocumentoModule,
        PdisciplinarioAdjuntoModule,
        PdisciplinarioFichaIngresoSdjModule,
        PdisciplinarioDerivacionModule,
        PdisciplinarioProvidenciaModule,
        PdisciplinarioSumarioAdministrativoModule,
        PdisciplinarioInvestigacionSumariaModule,
        PdisciplinarioRespuestaModule,
        // PdisciplinarioMovimientoProvidenciaModule,
        PdisciplinarioMovimientoSumarioAdministrativoModule,
        PdisciplinarioMovimientoInvestigacionSumariaModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PdisciplinarioEntityModule {}
