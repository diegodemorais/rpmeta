package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author dm
 */
public final class Config {
    private static final Properties CONFIG = new Properties();
    private static final String ARQUIVO = "config.ini";//local do arquivo
    public static String PATHPDF, JASPER_GERAL, JASPER_RANK, JASPER_COD, JRXML, BANCO_FICHAS, BANCO_MILLENNIUM, PERC_SURPRESA;

    public Config() throws IOException{
        CONFIG.load(new FileInputStream(ARQUIVO));      
        PATHPDF = getPathPDF();
        JASPER_GERAL = getJasperGeral();
        JASPER_RANK = getJasperRank();
        JASPER_COD = getJasperCod();
        PERC_SURPRESA = getPercSurpresa();
        JRXML = getJRXML();
        BANCO_FICHAS = getBancoFichas();   
        BANCO_MILLENNIUM = getBancoMillennium(); 
        
    }

    private String getPathPDF() throws IOException{
        return CONFIG.getProperty("PDF");
    }

    private String getJasperGeral() {
        return CONFIG.getProperty("JASPER_GERAL");
    }
    
    private String getJasperRank() {
        return CONFIG.getProperty("JASPER_RANK");
    }
    
    private String getJasperCod() {
        return CONFIG.getProperty("JASPER_COD");
    }
    
    private String getPercSurpresa() {
        return CONFIG.getProperty("PERC_SURPRESA");
    }
        
    private String getJRXML() {
        return CONFIG.getProperty("JRXML");
    }
    
    private String getBancoFichas() {
        return CONFIG.getProperty("BANCO_FICHAS");
    }
    
    private String getBancoMillennium() {
        return CONFIG.getProperty("BANCO_MILLENNIUM");
    }
}


