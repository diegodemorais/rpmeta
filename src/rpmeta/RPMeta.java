package rpmeta;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.commons.mail.EmailException;

/**
 *
 * @author dm
 */
public class RPMeta {

    /**
     * @param args the command line arguments
     * @throws org.apache.commons.mail.EmailException
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws EmailException, MalformedURLException, IOException {
        boolean argNum = false;
        boolean argHoje = false;
        iRPMeta pdf;

//        Setando par�metros de acordo com argumentos passados na inicializa��o do programa
        if (args.length > 0) { //Programa iniciado sem par�metros
            for (String arg: args ){
                arg = arg.toUpperCase().trim();
                if (arg.contains("-HOJE"))  //-hoje
                    argHoje = true;
                if (arg.contains("-NUM"))  //-num
                    argNum = true;
            }
        }
        
        if (argHoje)
            pdf = new iRPMeta("hoje"); //Data de refer�ncia � "hoje"
        else
            pdf = new iRPMeta("ontem"); //Quando n�o tem par�metros, a data de refer�ncia padr�o � "ontem"                            
        
        if (argNum)
            pdf.GerarEnviar("numerico");            
        else {
            pdf.GerarEnviar("grupo");
            pdf.GerarEnviar("super");
            pdf.GerarEnviar("avulso");       
        }
        
    }
}