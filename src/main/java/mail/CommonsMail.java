package mail;

/**
 *
 * @author dm
 */
import date.Data;
import java.net.MalformedURLException;
import java.util.Map;
import org.apache.commons.mail.*;

public class CommonsMail {
    public CommonsMail() throws EmailException, MalformedURLException {
//		enviaEmailSimples();
//		enviaEmailComAnexo();
//		enviaEmailFormatoHtml();
    }
    /**
     * envia email simples(somente texto)
     * @throws EmailException
     */
    public void enviaEmailSimples() throws EmailException {
            SimpleEmail email = new SimpleEmail();
//                email.setDebug(true);
            email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
            email.addTo("diegocpd@terra.com.br", "Diego CPD"); //destinatário
            email.setFrom("rpcpd87@gmail.com","Diego de Morais"); // remetente
            email.setSubject("Teste"); // assunto do e-mail
            email.setMsg("Teste"); //conteudo do e-mail
            email.setAuthentication("rpcpd87@gmail.com", "poter12poter12");
            email.setSmtpPort(465);
            email.setSSL(true);
//		email.setSSL(true);
//		email.setTLS(true);
            email.send();	
    }

    /**
     * envia email com arquivo anexo
     * @param emailTo
     * @param nomeTo
     * @param caminhoAnexo
     * @param anexo
     * @throws EmailException
     */
    public void enviaEmailComAnexo(String emailTo, String nomeTo, String caminhoAnexo, String anexo, boolean debug) throws EmailException{

//        try {
        // cria o anexo 1.
        EmailAttachment anexo1 = new EmailAttachment();
        anexo1.setPath(caminhoAnexo + anexo); //caminho do arquivo (RAIZ_PROJETO/teste/teste.txt)
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
        anexo1.setDescription("Simulado META: " + Data.referencia().format(Data.formatarddMMyyyy()) + " - " + anexo);
        anexo1.setName(anexo);

        MultiPartEmail email = new MultiPartEmail(); // configura o email
        if(debug) email.setDebug(true);
        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
        email.addTo(emailTo,nomeTo); //destinatário
        email.setFrom("rpcpd87@gmail.com", "TESTE"); // remetente
        email.setSubject("Simulado: " + Data.referencia().format(Data.formatarddMMyyyy()) + " - " + anexo); // assunto do e-mail
        email.setMsg("Simulado referente a " + Data.referencia().format(Data.formatarddMMyyyy()) + "." + System.getProperty("line.separator") + "Arquivo: " + anexo); //conteudo do e-mail
        email.setAuthentication("rpcpd87@gmail.com", "ndkzwlglviltegcn");
        email.setSmtpPort(465);
        email.setSSL(true);
//		email.setTLS(true);
        email.attach(anexo1); // adiciona arquivo(s) anexo(s)
//		email.attach(anexo2);
        email.send();
//        } catch (EmailException e) {
//            System.err.println(e.getMessage());
//        }

    }

    public void enviaMultiplosEmailComAnexo(Map<String,String> destinatarios,String caminhoAnexo, String anexo, boolean debug) throws EmailException{
        System.out.println("Enviando e-mails com o anexo " + anexo + "... ");
        for (Map.Entry<String,String> entry : destinatarios.entrySet()) {
            try {
                enviaEmailComAnexo(entry.getKey(),entry.getValue(),caminhoAnexo, anexo, debug);
            } catch (EmailException e){
                System.err.println("Anexo: " + anexo + "  Email: " + entry.getKey() + "  Erro: " + e.getMessage());
            }
        }
        System.out.println("E-mails enviados.");
    }
                
//    /**
//     * Envia email no formato HTML
//     * @throws EmailException 
//     * @throws MalformedURLException 
//     */                
//    public void enviaEmailFormatoHtml() throws EmailException, MalformedURLException {
//            HtmlEmail email = new HtmlEmail();
//            // adiciona uma imagem ao corpo da mensagem e retorna seu id
//            URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//            String cid = email.embed(url, "Apache logo");	
//            // configura a mensagem para o formato HTML
//            email.setHtmlMsg("&lt;html&gt;Logo do Apache - <img >&lt;/html&gt;");
//            // configure uma mensagem alternativa caso o servidor não suporte HTML
//            email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
//            email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
//            email.addTo("teste@gmail.com", "Guilherme"); //destinatário
//            email.setFrom("teste@gmail.com", "Eu"); // remetente
//            email.setSubject("Teste -&gt; Html Email"); // assunto do e-mail
//            email.setMsg("Teste de Email HTML utilizando commons-email"); //conteudo do e-mail
//            email.setAuthentication("teste", "xxxxx");
//            email.setSmtpPort(465);
//            email.setSSL(true);
//            email.setTLS(true);
//            // envia email
//            email.send();
//    }
 }