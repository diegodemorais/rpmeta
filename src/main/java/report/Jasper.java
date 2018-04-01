package report;

/**
 *
 * @author dm
 */

import util.Config;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class Jasper{
   
    public Jasper(){
    }
    
    public void metaAcompanhamento(String jasper, String arqPDF, JRDataSource lista, Map<String,Object> params){
        String pdf = Config.PATHPDF + arqPDF;
        try {
            System.out.println("Gerando relatório...");
//            JasperReport report = JasperCompileManager.compileReport(Config.JRXML); // compilacao do JRXML
            JasperPrint print;
            print = JasperFillManager.fillReport(jasper,params,lista);
            // exportacao do relatorio para outro formato, no caso PDF
            JasperExportManager.exportReportToPdfFile(print,pdf);
            System.out.println("Relatório gerado.");
        } catch (JRException jre) {
            System.err.println("Error :" + jre.getMessage());
        }
    }
}
