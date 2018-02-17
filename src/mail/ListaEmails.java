package mail;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author dm
 */
public final class ListaEmails {
    private Map<String,String> lista = new LinkedHashMap<>();
    
    public ListaEmails(String tipo){
        switch (tipo) {
            case ("GRUPO"):
                setGrupo();
                break;
            case ("SUPER"):
                setSuper();
                break;
            case ("NUMERICO"):
                setNumerico();
                break;                
            case ("AVULSO"):
                setAvulso();
                break;
            default:
                System.err.println("Lista de e-mails: '" + tipo + "' inválido.");
                break;
        }
    }
    
    public ListaEmails(String key, String value){
        lista.clear();
        lista.put(key, value);
    }
    
    private void setSuper(){
        lista.clear();
        lista.put("diegocpd@terra.com.br","Diego CPD"); //Diego
        lista.put("plveja@terra.com.br","PL Veja"); //Plinio notebook        
        lista.put("flaviomorato@terra.com.br","Flavio RP"); //Flavio
        lista.put("fmorato@terra.com.br","Fabiana RP"); //Fabiana
        lista.put("elainesupervisora@terra.com.br", "Elaine Super"); //Elaine
        lista.put("jaquelinesuprp@hotmail.com","Jaqueline Super"); //Jaqueline
        lista.put("paularadicalv@hotmail.com","Paula Super"); //Paula
        lista.put("thaisradical@yahoo.com.br","Thais Super"); //Thais
        lista.put("luzia.rueda@hotmail.com","Luzia Super"); //Luzia
    }
    
    private void setGrupo(){
        lista.clear();
        lista.put("diegocpd@terra.com.br","Diego CPD"); //Diego
        lista.put("plcodmw@gmail.com","Plinio"); //Plinio
        lista.put("plveja@terra.com.br","PL Veja"); //Plinio notebook
        lista.put("luc.morato@terra.com.br","Lucimara"); //Lucimara
        lista.put("flaviomorato@terra.com.br","Flavio RP"); //Flavio
        lista.put("fmorato@terra.com.br","Fabiana RP"); //Fabiana
        lista.put("avisorp@terra.com.br","Fernanda"); //Fernanda
    }
    
    private void setNumerico(){
        lista.clear();
        lista.put("diegocpd@terra.com.br","Diego CPD"); //Diego
        lista.put("flaviomorato@terra.com.br","Flavio RP"); //Flavio
    }    
    
    private void setAvulso(){
        lista.clear(); 
//        lista.put("diegocpd@terra.com.br","00"); //Diego        
        lista.put("bscod@terra.com.br","00");
        lista.put("pdcod@terra.com.br","03");
        lista.put("ajcod@terra.com.br","04");
        lista.put("pmcod@terra.com.br","06");
        lista.put("pfcod@terra.com.br","08");
        lista.put("rrcod@terra.com.br","09");
//        lista.put("clcod@terra.com.br","10");
//        lista.put("rodrigo.piva@terra.com.br","10");  //Rodrigo ex sup
        lista.put("smcod@terra.com.br","11");
        lista.put("12cod@terra.com.br","12");
        lista.put("sacod1@terra.com.br","13");
        lista.put("rhcod@terra.com.br","14");
        lista.put("lmcod@terra.com.br","16");
        lista.put("stcod@terra.com.br","17");
        lista.put("racod@terra.com.br","18");
        lista.put("jvcod@terra.com.br","19");
        lista.put("prcod2@terra.com.br","33");
        lista.put("lacod@terra.com.br","34");
        lista.put("cfcod@terra.com.br","35");
        lista.put("jncod@terra.com.br","40");
        lista.put("rfcod@terra.com.br","45");
//        lista.put("fhcod@terra.com.br","50");
        lista.put("sjcod@terra.com.br","51");
        lista.put("cmcod@terra.com.br","53");
        lista.put("jhcod@terra.com.br","54");
        lista.put("bacod2@terra.com.br","57");
        lista.put("accod@terra.com.br","58");
        lista.put("lscod@terra.com.br","59");
        lista.put("s2cod1@terra.com.br","60");
        lista.put("svcod@terra.com.br","61");
        lista.put("cncod@terra.com.br","62");
        lista.put("avcod@terra.com.br","63");
        lista.put("imcod1@terra.com.br","64");
        lista.put("lbcod@terra.com.br","65");
        lista.put("czcod@terra.com.br","66");
        lista.put("cscod@terra.com.br","67");
        lista.put("l4cod@terra.com.br","68");
        lista.put("lpcod@terra.com.br","69");
        lista.put("sicod@terra.com.br","70");
        lista.put("sycod@terra.com.br","71");
        lista.put("cacod@terra.com.br","72");     
        lista.put("hlcod@terra.com.br","73");   
        lista.put("cjcod@terra.com.br","89");
    }
    
    public Map<String,String> get(){
        return lista;
    }
}