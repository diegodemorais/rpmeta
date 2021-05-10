package date;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author dm
 */
public class Data {
    
    public Data(){
    }
    
    private static String parReferencia;
    
    public static void setReferencia(String data){
        parReferencia = data;
    }

    public static LocalDate referencia(){
//        return LocalDate.of(2018, 4, 30);
        LocalDate data;
        if (parReferencia == null)
                data = LocalDate.now().minusDays(1);  //ontem
        else {
            switch (parReferencia) {
                case "hoje":
                    data = LocalDate.now(); //hoje
                    break;
                case "ontem":
                default:
                    data = LocalDate.now().minusDays(1);  //ontem
                    break;
            }            
        }
        
        return data;
    }
       
    public static LocalDate primeiroDiaMes(LocalDate data){
        LocalDate novaData = data.withDayOfMonth(1);        
        return novaData;
    }
    
    
    public static LocalDate primeiroDiaMes(String mes, String ano){
        LocalDate novaData = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), 1);        
        return novaData;
    }    

   public static LocalDate ultimoDiaMes(String mes, String ano){
        LocalDate novaData, data;
        data = primeiroDiaMes(mes,ano);
        novaData = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), data.lengthOfMonth());        
        return novaData;
    }       
    
    public static LocalDate ultimoDiaMes(LocalDate data){
        LocalDate novaData = data.withDayOfMonth(data.lengthOfMonth());        
        return novaData;
    }
    
    public static String mes(LocalDate data){
        String mes = Integer.toString(data.getMonthValue());
        return mes;
    }
    
    public static String mesAtual(){
        return Data.mes(Data.referencia());
    }
    
    public static String ano(LocalDate data){
        String ano = Integer.toString(data.getYear());
        return ano;
    }
    
    public static String anoAtual(){
        return Data.ano(Data.referencia());
    }        
    
    public static LocalDate anoAnterior(LocalDate data, int anoAnterior){
        LocalDate novaData;
        try {
            novaData = LocalDate.of(data.getYear()-anoAnterior,data.getMonthValue(),data.getDayOfMonth());
        } catch (DateTimeException e) { // Ano bissexto (pra pegar dia 28 de fevereiro do ano interior)
            novaData = LocalDate.of(data.getYear()-anoAnterior,data.getMonthValue(),data.getDayOfMonth()-1);
        }
        return novaData;
    }
            
    public static DateTimeFormatter formatarMMddyyyy(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("MM/dd/yyyy");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //12/31/2016
    }
    
    public static DateTimeFormatter formatarddMMyyyy(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd/MM/yyyy");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //31/12/2016
    }    

    public static DateTimeFormatter formatarddMM(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd(MM)");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //31(12)
    }         
    
    public static DateTimeFormatter formatardd(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("dd");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //31
    }      
    
    public static DateTimeFormatter formatarMM(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("MM");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //12
    }      
    
    public static DateTimeFormatter formataryy(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("yy");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //2016
    }      
    
    public static DateTimeFormatter formataryyyy(){
        DateTimeFormatter formatador =  DateTimeFormatter.ofPattern("yyyy");        
        return formatador;
        //        System.out.println(referencia.format(formatador)); //2016
    } 
    
}
