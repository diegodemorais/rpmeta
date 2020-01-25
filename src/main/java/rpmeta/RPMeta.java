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
	 */
	public static void main(String[] args) throws IOException, EmailException {
		boolean argNum = false;
		boolean argHoje = false;
		boolean argCod = false;
		int argMetaParc = 90;
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
			pdf = new iRPMeta("hoje", argMetaParc); // Data de referência é "hoje"
		else
			pdf = new iRPMeta("ontem", argMetaParc); // Quando não tem parâmetros, a data de referência padrão é "ontem"

		if (argNum)
			pdf.GerarEnviar("numerico", listaEmails, argMetaParc);
		else if (argCod) {
			pdf.GerarEnviar("codigo", listaEmails, argMetaParc);
		} else {
			pdf.GerarEnviar("grupo", listaEmails, argMetaParc);
			pdf.GerarEnviar("super", listaEmails, argMetaParc);
			pdf.GerarEnviar("avulso", listaEmails, argMetaParc);
			pdf.GerarEnviar("rank", listaEmails, argMetaParc);
		}

	}
}