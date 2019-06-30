<!--
Notas: 
  - Titulo puede ser el mimso nombre de la rama o un resumen breve de lo que resuelve (ej: Correcciones para minuta X).
  - Mientras no este terminado el merge se debe agregar al titulo el prefijo "WIP:" (ej: WIP: Correcciones para minuta X).
  - Usar etiqueta "Release" o "Bugfix" segun corresponda. 
  - Asociar el milestone correspondiente a este sprint/release (ej: v1.0.0).
  - Se debe completar todo el checklist antes de aceptar el merge.
  - La seccion "Plan de pruebas" es opcional, sin embargo el QA se debe hacer con las versiones compiladas para ambiente productivo.
  - Los problemas encontrados durante las pruebas se deben agregar como comentarios, asi el sistema llevara un contador de estas dicusiones. 
-->

### Resumen:

_Este merge resuelve la mayoria de requerimientos de la minuta X. Por falta de informacion no se incluye modulo Y._

_Se debe agregar nuevo parametro `smpt_port: 25` en archivo de configuracion `app/config/parameters.yml`._

### Issues relacionados:

* Resuelve: #1, #2, #4 
* Pendinetes: #3

### Plan de Pruebas:

* Pruebas de aceptación
  * [ ] PA1 - Se genera un reporte en PDF y se descarga automáticamente.
  * [ ] PA2 - Se filtran solicitudes según año para incluirlas en el reporte.

* Pruebas de integración
  * [ ] No aplica

* Regresiones
  * [ ] No aplica

### Checklist general:

- [ ] Se resolvieron todos los conflictos con la rama destino?
- [ ] Pasaron todas las pruebas de aceptacion?
- [ ] Pasaron todas las pruebas de integracion?
- [ ] Se actualizo el archivo `changelog.md`?
- [ ] Se actualizo la version en el archivo `pom.xml`?
- [ ] Se resolvieron todas las discuciones de este MR?


/label ~Release 
/assign @bastian
/cc @kdiaz
