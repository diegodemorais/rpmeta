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
	 * @param args
	 *            the command line arguments
	 * @throws org.apache.commons.mail.EmailException
	 * @throws java.net.MalformedURLException
	 */
	public static void main(String[] args) throws EmailException, MalformedURLException, IOException {
		boolean argNum = false;
		boolean argHoje = false;
		boolean argCod = false;
		String tipo = null;
		String listaEmails = null;
		iRPMeta pdf;

//		 Setando parâmetros de acordo com argumentos passados na inicialização do
//		 programa
		if (args.length > 0) { // Programa iniciado sem parâmetros
			for (String arg : args) {
				arg = arg.toUpperCase().trim();
				if (arg.contains("-HOJE")) // -hoje
					argHoje = true;
				if (arg.contains("-NUM")) // -num
					argNum = true;
				if (arg.contains("-COD")) {// -cod
					argCod = true;
					argHoje = true;
				}
			   if (arg.contains(".CSV")) {
				   listaEmails = arg;
			   }
			}
		}

		if (argHoje)
			pdf = new iRPMeta("hoje"); // Data de referência é "hoje"
		else
			pdf = new iRPMeta("ontem"); // Quando não tem parâmetros, a data de referência padrão é "ontem"

		if (argNum)
			pdf.GerarEnviar("numerico", listaEmails);
		else if (argCod) {
			pdf.GerarEnviar("codigo", listaEmails);
		} else {
			pdf.GerarEnviar("grupo", listaEmails);
			pdf.GerarEnviar("super", listaEmails);
			pdf.GerarEnviar("avulso", listaEmails);
			pdf.GerarEnviar("rank", listaEmails);
		}

	}
}