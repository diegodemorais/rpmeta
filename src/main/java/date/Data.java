package date;

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
        LocalDate data;
//        LocalDate data = LocalDate.of(2016, 9, 30);
        if (parReferencia == null)
                data = LocalDate.now().minusDays(2);  //ontem
        else {
            switch (parReferencia) {
                case "hoje":
                    data = LocalDate.now(); //hoje
                    break;
                case "ontem":
                default:
                    data = LocalDate.now().minusDays(2);  //ontem
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
    
    public static LocalDate anoAnterior(LocalDate data){
        LocalDate novaData = LocalDate.of(data.getYear()-1,data.getMonthValue(),data.getDayOfMonth());
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
