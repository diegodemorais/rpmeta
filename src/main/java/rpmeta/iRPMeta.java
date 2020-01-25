/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpmeta;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import bd.sys.Integrado;
import bd.sys.Fichas;
import bd.sys.Millennium;
import date.Data;
import date.DataPorcentagem;
import mail.CommonsMail;
import mail.ListaEmails;
import report.Jasper;
import report.PorcentagemJRDataSourceFactory;
import util.Config;

/**
 *
 * @author dm
 */
public class iRPMeta {
	private Millennium mw;
	private Fichas fc;
	public Integrado integra;
	private Map<String, Float> mapAnteriorInt, mapAnterior, mapAtual, mapMeta, mapDia, mapMetaParc;
	private Map<String, String> mapQuebra, mapSuperV, mapExtra;
	private Map<Float, String> mapRank;
	private DataPorcentagem data;
	private Jasper jasp;
	private CommonsMail mail;
	private Config config;
	private Map<String, Object> parJasp;

	iRPMeta(String dataReferencia, int metaParc) throws EmailException, IOException {
		Data.setReferencia(dataReferencia); // Setando data do par�metro da chamada do programa

		config = new Config();

		mw = new Millennium();
		fc = new Fichas();

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

		if (metaParc > 0) {
			mapMetaParc = new HashMap<>();
			for (Map.Entry<String, Float> entry : mapMeta.entrySet()) {
				float valorMetaParc = entry.getValue() * (metaParc / 100f);
				mapMetaParc.put(entry.getKey(), valorMetaParc);
			}
		} else {
			mapMetaParc = null;
		}
	}

	void GerarEnviar(String tipo, String listaEmails, int metaParc) throws EmailException {
		String tipoAux = null;
		if (tipo.equalsIgnoreCase("CODIGO")) {
			tipoAux = "grupo";
		}

		ListaEmails lista;
		if (listaEmails != null) {
			lista = new ListaEmails(listaEmails);
		} else {
			lista = new ListaEmails(tipoAux != null ? tipoAux : tipo);
		}

		if (tipo.equalsIgnoreCase("AVULSO")) {
			for (Map.Entry<String, String> entry : lista.get().entrySet()) {
				ListaEmails listaB = new ListaEmails(entry.getKey(), entry.getValue());
				mapQuebra = fc.avulsoB(entry.getValue());
				CriarAnexarEnviar(tipo + entry.getValue(), listaB, metaParc);
			}
		} else {
			mapQuebra = fc.getTipo(tipoAux != null ? tipoAux : tipo);
			CriarAnexarEnviar(tipo, lista, metaParc);
		}
	}

	private void CriarAnexarEnviar(String tipo, ListaEmails lista, int metaParc) throws EmailException {
		String Tipo = tipo.substring(0, 1).toUpperCase() + tipo.substring(1).toLowerCase();// 1a letra em maiúscula
		String arquivo;
		if (tipo.equalsIgnoreCase("CODIGO")) {
			arquivo = Tipo + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "hs.pdf"; // Nome do arquivo pdf
		} else {
			arquivo = "Meta100" + Tipo + ".pdf"; // Nome do arquivo pdf
		}
		PorcentagemJRDataSourceFactory fact = new PorcentagemJRDataSourceFactory(); // Fabrica
		if (tipo.equalsIgnoreCase("RANK")) {
			mapRank = fc.rankByPercMeta(mapAnteriorInt, mapAnterior, mapMeta, mapAtual);
			mapQuebra = fc.reorderByRankMeta(mapRank);
			mapExtra = fc.rankB(mapQuebra);
			jasp.metaAcompanhamento(Config.JASPER_RANK, arquivo, fact.createDatasource(mapQuebra, mapAnteriorInt,
					mapAnterior, mapAtual, mapMeta, mapDia, mapSuperV, mapRank, mapExtra), parJasp); // Gerando Relatório
		} else {
			String jasper = tipo.equalsIgnoreCase("CODIGO") ? Config.JASPER_COD : Config.JASPER_GERAL;
			jasp.metaAcompanhamento(jasper, arquivo,
					fact.createDatasource(mapQuebra, mapAnteriorInt, mapAnterior, mapAtual, mapMeta, mapDia, mapSuperV),
					parJasp); // Gerando relatório
		}
		mail.enviaMultiplosEmailComAnexo(lista.get(), Config.PATHPDF, arquivo); // Enviando e-mails

		// Meta parcial = % da meta oficial
		if (metaParc > 0 && mapMetaParc != null) {
			if(!(tipo.equalsIgnoreCase("RANK")) && !(tipo.equalsIgnoreCase("CODIGO")) ) {
				PorcentagemJRDataSourceFactory factMetaParc = new PorcentagemJRDataSourceFactory(); // Fabrica
				arquivo = arquivo.replace("Meta100", "Meta" + String.format("%02d", metaParc));
				jasp.metaAcompanhamento(Config.JASPER_GERAL, arquivo,
						factMetaParc.createDatasource(mapQuebra, mapAnteriorInt, mapAnterior, mapAtual, mapMetaParc, mapDia, mapSuperV),
						parJasp); // Gerando relatório
				mail.enviaMultiplosEmailComAnexo(lista.get(), Config.PATHPDF, arquivo); // Enviando e-mails
			}
		}
	}
}