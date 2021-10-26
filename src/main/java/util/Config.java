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
    public static String PATHPDF;
    public static String JASPER_GERAL;
    public static String JASPER_RANK;
    public static String JASPER_COD;
//    public static String JRXML;
    public static String BANCO_FICHAS;
    public static String BANCO_MILLENNIUM;
    public static String IP_MILLENNIUM;
    public static String USER_MILLENNIUM;
    public static String PASS_MILLENNIUM;
    public static String PERC_SURPRESA;
    public static int META_PARC;
    public static int ANO_ANTERIOR;

    public Config() throws IOException{
        CONFIG.load(new FileInputStream(ARQUIVO));      
        PATHPDF = getPathPDF();
        JASPER_GERAL = getJasperGeral();
        JASPER_RANK = getJasperRank();
        JASPER_COD = getJasperCod();
        PERC_SURPRESA = getPercSurpresa();
//        JRXML = getJRXML();
        BANCO_FICHAS = getBancoFichas();   
        BANCO_MILLENNIUM = getBancoMillennium();
        IP_MILLENNIUM = getIpMillennium();
        USER_MILLENNIUM = getUserMillennium();
        PASS_MILLENNIUM = getPassMillennium();
        META_PARC = getMetaParc();
        ANO_ANTERIOR = getAnoAnterior();
        
    }

    private String getPathPDF(){
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
        
//    private String getJRXML() {
//        return CONFIG.getProperty("JRXML");
//    }
    
    private String getBancoFichas() {
        return CONFIG.getProperty("BANCO_FICHAS");
    }
    
    private String getBancoMillennium() {
        return CONFIG.getProperty("BANCO_MILLENNIUM");
    }
    private String getIpMillennium() {
        return CONFIG.getProperty("IP_MILLENNIUM");
    }
    private String getUserMillennium() {
        return CONFIG.getProperty("USER_MILLENNIUM");
    }
    private String getPassMillennium() {
        return CONFIG.getProperty("PASS_MILLENNIUM");
    }

    private int getMetaParc() {
        return Integer.parseInt(CONFIG.getProperty("META_PARC"));
    }

    private int getAnoAnterior() {
        return Integer.parseInt(CONFIG.getProperty("ANO_ANTERIOR"));
    }
}


