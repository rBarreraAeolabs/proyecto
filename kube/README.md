# Registro Civil - Procedimiento Disciplnario

## Desarrollo local

1) Levantar proyecto con docker

    docker-compose -f docker/dev.yml up -d

2) Abrir el contenedor de la app para instalar dependencias 

    docker-compose -f docker/dev.yml exec app bash
    npm install --no-bin-links --ignore-scripts
    exit

3) Luego se pueden abrir dos ventanas de docker, una para dejar ese comando que compila la parte de angular

    docker-compose -f docker/dev.yml exec app bash
    npm serve

4) Compilar la aplicacion con el IDE

5) Levantar la aplicacion con el IDE

6) Revisar via web la aplicacion en: http://localhost:8080/
