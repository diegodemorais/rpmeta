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
    public static String PATHPDF, JASPER_GERAL, JASPER_RANK, JRXML, BANCOFICHAS;

    public Config() throws IOException{
        CONFIG.load(new FileInputStream(ARQUIVO));      
        PATHPDF = getPathPDF();
        JASPER_GERAL = getJasperGeral();
        JASPER_RANK = getJasperRank();
        JRXML = getJRXML();
        BANCOFICHAS = getBancoFichas();   
        
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
    
    private String getJRXML() {
        return CONFIG.getProperty("JRXML");
    }
    
    private String getBancoFichas() {
        return CONFIG.getProperty("BANCOFICHAS");
    }
}


