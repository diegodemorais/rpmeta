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
    public static String PATHPDF, JASPER, JRXML;

    public Config() throws IOException{
        CONFIG.load(new FileInputStream(ARQUIVO));      
        PATHPDF = getPathPDF();
        JASPER = getJasper();
        JRXML = getJRXML();        
    }

    private String getPathPDF() throws IOException{
        return CONFIG.getProperty("PDF");
    }

    private String getJasper() {
        return CONFIG.getProperty("JASPER");
    }
    
    private String getJRXML() {
        return CONFIG.getProperty("JRXML");
    }
    
    
}


