package com.gruposolux.rcivil.pdisciplinario.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to App.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Storage storage = new ApplicationProperties.Storage();

    public ApplicationProperties() {
    }

    public ApplicationProperties.Storage getStorage() { return this.storage; }

    public static class Storage {
        private int maxFileSize;
        private String adjuntosDir;
        private String tmpDir;
        private String excelDir;
        private String imprimirDir;
        private int maxTimeForFileExpire;
        private String alfrsco_atompub_url;
        private String repository_id;
        private String alfresco_temp_rootFolder;
        private String alfresco_final_rootFolder;
        private String baseUrl;
        private String userAlfresco;
        private String passAlfresco;

        private Storage() {
        }

        public String getAdjuntosDir() {
            return adjuntosDir;
        }

        public void setAdjuntosDir(String adjuntosDir) {
            this.adjuntosDir = adjuntosDir;
        }

        public String getTmpDir() {
            return tmpDir;
        }

        public void setTmpDir(String tmpDir) {
            this.tmpDir = tmpDir;
        }

        public int getMaxTimeForFileExpire() {
            return maxTimeForFileExpire;
        }

        public void setMaxTimeForFileExpire(int maxTimeForFileExpire) {
            this.maxTimeForFileExpire = maxTimeForFileExpire;
        }

        public void setMaxFileSize(int maxFileSize) {
            this.maxFileSize = maxFileSize;
        }

        public int getMaxFileSize() {
            return maxFileSize;
        }

        public String getExcelDir() {
            return excelDir;
        }

        public void setExcelDir(String excelDir) {
            this.excelDir = excelDir;
        }

        public String getImprimirDir() {
            return imprimirDir;
        }

        public void setImprimirDir(String imprimirDir) {
            this.imprimirDir = imprimirDir;
        }

        public String getAlfrsco_atompub_url() {
            return alfrsco_atompub_url;
        }

        public void setAlfrsco_atompub_url(String alfrsco_atompub_url) {
            this.alfrsco_atompub_url = alfrsco_atompub_url;
        }

        public String getRepository_id() {
            return repository_id;
        }

        public void setRepository_id(String repository_id) {
            this.repository_id = repository_id;
        }

        public String getAlfresco_temp_rootFolder() {
            return alfresco_temp_rootFolder;
        }

        public void setAlfresco_temp_rootFolder(String alfresco_temp_rootFolder) {
            this.alfresco_temp_rootFolder = alfresco_temp_rootFolder;
        }

        public String getAlfresco_final_rootFolder() {
            return alfresco_final_rootFolder;
        }

        public void setAlfresco_final_rootFolder(String alfresco_final_rootFolder) {
            this.alfresco_final_rootFolder = alfresco_final_rootFolder;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getUserAlfresco() {
            return userAlfresco;
        }

        public void setUserAlfresco(String userAlfresco) {
            this.userAlfresco = userAlfresco;
        }

        public String getPassAlfresco() {
            return passAlfresco;
        }

        public void setPassAlfresco(String passAlfresco) {
            this.passAlfresco = passAlfresco;
        }
    }

}
