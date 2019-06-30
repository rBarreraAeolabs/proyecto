FROM alfresco/alfresco-content-repository-community:6.1.2-ga

COPY simple-ocr-repo-2.3.1.jar /usr/local/tomcat/webapps/alfresco/WEB-INF/lib/
COPY myocr.sh /usr/bin/myocr
RUN chmod +x /usr/bin/myocr

# Add here configurations for alfresco-global.properties
RUN echo -e '\n\
audit.enabled=true\n\
audit.alfresco-access.enabled=true\n\
audit.alfresco-access.sub-actions.enabled=true\n\
audit.filter.alfresco-access.default.enabled=true\n\
audit.filter.alfresco-access.transaction.user=~System;.*\n\
audit.filter.alfresco-access.transaction.type=cm:folder;cm:content\n\
audit.filter.alfresco-access.transaction.path=/app:company_home/.*\n\
ocr.command=/usr/bin/myocr\n\
ocr.output.verbose=true\n\
ocr.output.file.prefix.command=\n\
ocr.extra.commands=\n\
ocr.server.os=linux\n\
' >> /usr/local/tomcat/shared/classes/alfresco-global.properties

