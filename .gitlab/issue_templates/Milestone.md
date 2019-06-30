<!--
Esta plantilla es para usar en los milestone.

Notas: 
  - Titulo del milestone es la version que se va a liberar (ej: v1.0.0). Si el repositorio genera mas 
    de una aplicacion se debe agregar el nombre (ej: GUI app - Versión 1.0.0)
  - Se pueden asociar con una minuta de cliente en particular.
  - En changelog agregar numero de issue (#5) y merge request (!3) segun corresponda.
-->

### Changelog:

- Requerimientos:
 * #1 - Crear  formulario para ingreso de solicitudes dentro de administración. MR !52
 * #2 - Crear mantenedor de Asuntos para solicitudes. MR !52
 * #3 - Generar reporte anual de solicitudes en PDF. 
- Bug fix:
 * #5 - No se calcula correctamente la fecha de vencimiento.
 * #6 - Sistema no permite descargar archivo de reporte en PDF. MR !53
- Regresiones:
 * #9 - Versiones de Postgres menores a 5.9 ya no son soportadas.
