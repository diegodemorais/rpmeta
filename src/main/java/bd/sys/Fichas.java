/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.sys;

import bd.Access;
import date.Data;
import mail.ListaEmails;
import util.Config;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dm
 */
public class Fichas {

	private static Access ac;

	public Fichas() {
		// ac = new Access("////192.168.0.100//D//Sistemas//SisFFG//Banco//Meta.mdb");
		String database = Config.BANCO_FICHAS.replace("/", "//");
		ac = new Access(database);
		ac.connect();
	}

	public Map<String, String> getTipo(String tipo) {
		switch (tipo.toUpperCase()) {
		case ("GRUPO"):
			return grupoB();
		case ("SUPER"):
			return superB();
		case ("NUMERICO"):
			return numericoB();
		case ("RANK"):
			return null;
		default:
			System.err.println("Lista de e-mails: '" + tipo + "' inválido.");
			return null;
		}
	}

	public Map<Float, String> rankByPercMeta(Map<String, Float> anteriorInt, Map<String, Float> anterior,
			Map<String, Float> meta, Map<String, Float> atual) {
		Map<Float, String> mapReodered = new TreeMap<>(Collections.reverseOrder());
		for (Map.Entry<String, Float> entry : atual.entrySet()) { // Iteração para cada B, calculando diferença da meta
																	// e ordenando
			String loja = entry.getKey();
			try {
				Float metaParcial;
				Float metaVal = meta.get(loja) * 1000;
				Float atualVal = entry.getValue();
				if (anteriorInt.get(loja) != null) {
					Float anteriorIntVal = anteriorInt.get(loja) * 1000;
					Float anteriorVal = anterior.get(loja) * 1000;

					Float percMetaAtual = (metaVal / anteriorIntVal) - 1;
					metaParcial = anteriorVal + (anteriorVal * percMetaAtual);
				} else {
					int daysInMonth = Data.referencia().getMonth().length(true);
					metaParcial = (metaVal / daysInMonth) * Data.referencia().getDayOfMonth();
				}
				Float percMeta = (atualVal / metaParcial) - 1;
				mapReodered.put(percMeta, loja);
			} catch (Exception e) {
				mapReodered.put(Float.parseFloat(loja) * -1, loja); // Caso algum informação venha null e não possa
																	// fazer o cálculo
			}
		}
		return mapReodered;
	}

	public Map<String, String> reorderByRankMeta(Map<Float, String> mapReodered) {
		Map<String, String> result = new LinkedHashMap<>();
		for (Map.Entry<Float, String> entry : mapReodered.entrySet()) { // Iteração no mapa ordenado e inserindo na
																		// mesma ordem em outro mapa tipado corretamente
																		// e com o rank
			result.put(entry.getValue(), "B");
		}
		return result;
	}

	public Map<String, String> rankB(Map<String, String> mapQuebra) {
		Map<String, String> result = new LinkedHashMap<>();
		Integer i = 1;
		for (Map.Entry<String, String> entry : mapQuebra.entrySet()) {
			result.put(entry.getKey(), i + "º");
			i++;
		}
		return result;
	}

	public Map<String, String> superB() {
		String sql;
		ResultSet rs;
		Map<String, String> map = new LinkedHashMap<>();

		sql = "SELECT Lojb010.NUM as B, Lojb010.Super as SUPERV \n"
				+ "FROM Lojb010 INNER JOIN TAB_SUPER ON Lojb010.Super = TAB_SUPER.Super \n"
				+ "ORDER BY TAB_SUPER.Ordem, Lojb010.NUM";

		rs = ac.executar(sql);

		try {
			while (rs.next()) {
				map.put(rs.getString("B"), rs.getString("SUPERV"));
				// System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
			}
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}

		return map;
	}

	public Map<String, String> grupoB() {
		String sql;
		ResultSet rs;
		Map<String, String> map = new LinkedHashMap<>();

		sql = "SELECT Lojb010.NUM as B, Lojb010.Grupo as SUPERV \n" + "FROM Lojb010 \n"
				+ "WHERE Lojb010.Grupo IS NOT NULL " + "ORDER BY Lojb010.Grupo, Lojb010.Ordem";

		rs = ac.executar(sql);

		try {
			while (rs.next()) {
				map.put(rs.getString("B"), rs.getString("SUPERV"));
				// System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
			}
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}

		return map;
	}

	public Map<String, String> avulsoB(String B) {
		Map<String, String> map = new LinkedHashMap<>();
		try {
			map.put(B, B);
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}
		return map;
	}

	public Map<String, String> numericoB() {
		String sql;
		ResultSet rs;
		Map<String, String> map = new LinkedHashMap<>();

		sql = "SELECT Lojb010.NUM as B, 1 as NUMERICO \n" + "FROM Lojb010 \n" + "WHERE Lojb010.Grupo IS NOT NULL "
				+ "ORDER BY Lojb010.NUM";

		rs = ac.executar(sql);

		try {
			while (rs.next()) {
				map.put(rs.getString("B"), rs.getString("NUMERICO"));
				// System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
			}
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}

		return map;
	}

	public Map<String, String> todasB() {
		String sql;
		ResultSet rs;
		Map<String, String> map = new LinkedHashMap<>();

		sql = "SELECT Lojb010.NUM as B, Lojb010.LOJA as SIGLA \n" + "FROM Lojb010 \n" + "ORDER BY Lojb010.NUM";

		rs = ac.executar(sql);

		try {
			while (rs.next()) {
				map.put(rs.getString("B"), rs.getString("SIGLA"));
				// System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
			}
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}

		return map;
	}

	public Map<String, Float> metaB(String mes, String ano) {
		String sql;
		ResultSet rs;
		Map<String, Float> map = new HashMap<>();

		sql = "SELECT TAB_META.MT_F_LOJA as B, TAB_META.MT_VALOR as META \n" + "FROM TAB_META \n"
				+ "WHERE ((TAB_META.MT_MES=" + mes + ") AND (TAB_META.MT_ANO=" + ano + "))";
		rs = ac.executar(sql);

		try {
			while (rs.next()) {
				map.put(rs.getString("B"), rs.getFloat("META"));
			}
		} catch (Exception e) {
			System.err.println("Error :" + e.getMessage());
		}

		return map;
	}

}
