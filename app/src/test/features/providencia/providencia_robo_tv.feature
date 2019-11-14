# language: es
@providencia
Característica: Registrar el robo de la tv como nueva providencia
    Como el usuario Administrador
    Con el fin de probar el flujo completo

    Esquema del escenario: Llenar el formulario de la caratura de la providencia
        Dado que existe el endpoint "/"
        Cuando creamos la providencia para el solicitante "<rut_solicitante>"
        Y para el implicado "<rut_implicado>"
        Entonces se crea la providencia
        Y se muestra el id "<id>"

    Ejemplos:
        | rut_solicitante | rut_implicado | id |
        | 1234567-8       | 2345678-9     | 1  |

    Escenario: Validar la providencia creada
        Dado que existe la providencia con id "1"
        Y que estoy logueado usuario "admin"
        Y entramos a la url "/providencia/1/show"
        Cuando presionamos boton "continuar"
        Entonces la providencia con id "1" se ha validado
        Y se redirige al detalle
        Y se muestra el estado "validado"

    Ejemplos:
    | rut_solicitante | rut_implicado| estado | id |
    | 1234567-8       | 2345678-9    | creada | 1  |

    Escenario: Asignar numero de referencia
        Dado que existe la providencia con id "1"
        Y que este logeado usuario admin
        Y entramos a la url "providencia/id/view"




