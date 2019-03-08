package mail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author dm
 */
public final class ListaEmails {
    private Map<String,String> lista = new LinkedHashMap<String, String>();
    
    public ListaEmails(String tipo){
    	set(tipo);
    }
    
    
    public ListaEmails(String key, String value){
        lista.clear();
        lista.put(key, value);
    }
    
    private void clear() {
        lista.clear(); 
    }
    
    public Map<String,String> get(){
        return lista;
    }
    
    private void set(String tipo) {
    	clear();
    	
    	String csvFile;
    	if (tipo.toLowerCase().contains("csv")) {
    		csvFile = "emails/" + tipo.toLowerCase();
    	} else {
    		csvFile = "emails/" + tipo.toLowerCase() + ".csv";	
    	}
    	
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                lista.put(values[0],values[1]); 
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}