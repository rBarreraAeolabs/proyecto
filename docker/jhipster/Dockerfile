#FROM jhipster/jhipster:v4.14.5
FROM maven:3.5-slim

USER root

# install node y npm
RUN apt-get update && apt-get install -y build-essential

RUN \
  curl -sL https://deb.nodesource.com/setup_8.x | bash && \
  apt-get install -y nodejs git && \
 # cleanup
  apt-get clean && \
  rm -rf \
    /var/lib/apt/lists/* \
    /tmp/* \
    /var/tmp/*

# para evitar bug de permisos en npm (source: https://github.com/npm/npm/issues/20605)
RUN npm install -g npm@5.7.1
# para evitar errores de paquetes bower (https://www.npmjs.com/package/bower)
RUN npm install -g bower@1.8.4

RUN \
  # configure the "jhipster" user
  groupadd jhipster && \
  useradd jhipster -s /bin/bash -m -g jhipster -G sudo && \
  echo 'jhipster:jhipster' | chpasswd

USER jhipster

