/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpmeta;

import bd.sys.Custo;
import bd.sys.Fichas;
import bd.sys.Millennium;
import util.Config;
import date.Data;
import date.DataPorcentagem;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import mail.CommonsMail;
import mail.ListaEmails;
import org.apache.commons.mail.EmailException;
import report.PorcentagemJRDataSourceFactory;
import report.Jasper;

/**
 *
 * @author dm
 */
public class iRPMeta {
	public Millennium mw;
	public Fichas fc;
	public Custo cst;
	Map<String, Float> mapAnteriorInt, mapAnterior, mapAtual, mapMeta, mapDia;
	Map<String, String> mapQuebra, mapSuperV, mapExtra;
	Map<Float, String> mapRank;
	public DataPorcentagem data;
	public Jasper jasp;
	public CommonsMail mail;
	public Config config;
	Map<String, Object> parJasp;

	public iRPMeta(String dataReferencia) throws EmailException, MalformedURLException, IOException {
		Data.setReferencia(dataReferencia); // Setando data do par�metro da chamada do programa

		config = new Config();

		mw = new Millennium();
		fc = new Fichas();
		cst = new Custo();

		data = new DataPorcentagem();
		jasp = new Jasper();
		mail = new CommonsMail();

		parJasp = new HashMap<>();

		mapDia = mw.codB(data.dtReferencia, data.dtReferencia);
		mapAtual = mw.codB(data.dtAtualIni, data.dtAtualFim);
		mapAnterior = mw.codB(data.dtAnteriorIni, data.dtAnteriorFim);
		mapAnteriorInt = mw.codB(data.dtAnteriorIntIni, data.dtAnteriorIntFim);
		mapMeta = fc.metaB(Data.mesAtual(), Data.anoAtual());
		mapSuperV = fc.superB();

		parJasp.put("parDia", data.dtReferenciadd());
		parJasp.put("parMes", data.dtReferenciaMM());
		parJasp.put("parAno", data.dtReferenciayy());
		parJasp.put("parAnoAnterior", data.dtReferenciaAnterioryy());
		parJasp.put("parInflacao", "10");
		parJasp.put("parSurpresa", Config.PERC_SURPRESA);
		parJasp.put("parQtDiasMes", data.dtReferenciaUltimoDiaMes());
	}

	public void GerarEnviar(String tipo) throws EmailException {
		ListaEmails lista = new ListaEmails(tipo.toUpperCase()); // Lista de Destinatários
		if (tipo.equalsIgnoreCase("AVULSO")) {
			for (Map.Entry<String, String> entry : lista.get().entrySet()) { // Iteração para cada B
				ListaEmails listaB = new ListaEmails(entry.getKey(), entry.getValue()); // Nova lista de e-mails, só com
																						// a loja
				mapQuebra = fc.avulsoB(entry.getValue()); // Agrupamento e ordem
				CriarAnexarEnviar(tipo + entry.getValue(), listaB); // Criar PDF e Enviar e-mail
			}
		} else {
			mapQuebra = fc.getTipo(tipo); // Agrupamento e ordem
			CriarAnexarEnviar(tipo, lista); // Criar PDF e Enviar e-mail
		}
	}

	private void CriarAnexarEnviar(String tipo, ListaEmails lista) throws EmailException {
		String Tipo = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();// 1a letra em maiúscula
		String arquivo = "Meta" + Tipo + ".pdf"; // Nome do arquivo pdf
		PorcentagemJRDataSourceFactory fact = new PorcentagemJRDataSourceFactory(); // Fabrica
		if(tipo.toUpperCase().equals("RANK")) {
			mapRank = fc.rankByPercMeta(mapAnteriorInt, mapAnterior, mapMeta, mapAtual);
			mapQuebra = fc.reorderByRankMeta(mapRank);
			mapExtra = fc.rankB(mapQuebra);
			jasp.metaAcompanhamento(Config.JASPER_RANK, arquivo,
					fact.createDatasource(mapQuebra, mapAnteriorInt, mapAnterior, mapAtual, mapMeta, mapDia, mapSuperV, mapRank, mapExtra),
					parJasp); // Gerando relatório
		} else {
			jasp.metaAcompanhamento(Config.JASPER_GERAL, arquivo,
					fact.createDatasource(mapQuebra, mapAnteriorInt, mapAnterior, mapAtual, mapMeta, mapDia, mapSuperV),
					parJasp); // Gerando relatório
		}
		mail.enviaMultiplosEmailComAnexo(lista.get(), Config.PATHPDF, arquivo); // Enviando e-mails
	}
}