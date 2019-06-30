<!--
Notas: 
  - Titulo del issue es una descripción breve del requerimiento (ej: CU10 - Sistema debe generar reporte anual de solicitudes en formato PDF).
  - Usar etiqueta "Requerimiento", y etiqueta de la aplicación/modulo correspondiente (ej. Gui app). 
  - Asociar el milestone correspondiente a este sprint/release (ej: v1.0.0).
  - Se pueden crear subtareas con checklist (ej: Diseño de solución, Codificación, Ejecución de pruebas de aceptación, Ejecución de pruebas de integración, Documentación).
-->

### Descripción detallada:

_Corresponde a Caso de Uso 10._

_Crear formulario para generar reporte en PDF de las solicitudes (se adjunta formato), el cual consiste en una tabla con los campos "nombre", "fecha" y "estado". El formulario debe permitir filtrar las solicitudes por fecha (solo pudiendo elegir el año)._

### Criterios de aceptación:

* El reporte generado debe seguir el formato adjunto.
* El reporte debe mostrar todos los registros del año elegido; si no se especificó el año no se debe permitir al usuario generarlo.

### Plan de Pruebas

* Pruebas de aceptación
  * PA1 - Se genera un reporte en PDF y se descarga automáticamente.
  * PA2 - Se filtran solicitudes según año para incluirlas en el reporte.

* Pruebas de integración
  * No aplica

### Subtareas

- [ ] Solicitar a cliente los perfiles de usuario.
- [ ] Generar las notificaciones que correspondan.
- [ ] Crear pruebas unitarias.


/label ~Requerimiento 
