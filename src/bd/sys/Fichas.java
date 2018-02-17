/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.sys;

import bd.Access;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author dm
 */
public class Fichas {
    
    private static Access ac;
    
    public Fichas(){
        ac = new Access("////192.168.0.100//D//Sistemas//SisFFG//Banco//Meta.mdb");
        ac.connect();
    }
    
    public Map<String,String> getTipo(String tipo){
        switch (tipo.toUpperCase()) {
            case ("GRUPO"):
                return grupoB();
            case ("SUPER"):
                return superB();
            case ("NUMERICO"):
                return numericoB();                
            default:
                System.err.println("Lista de e-mails: '" + tipo + "' inválido.");
                return null;
        }
    }
    
    public Map<String,Float> metaB(String mes, String ano){
        String sql;
        ResultSet rs;
        Map<String,Float> map = new HashMap<>();
        
        sql = "SELECT TAB_META.MT_F_LOJA as B, TAB_META.MT_VALOR as META \n" +
              "FROM TAB_META \n" +
              "WHERE ((TAB_META.MT_MES=" + mes + ") AND (TAB_META.MT_ANO=" + ano + "))";
        rs = ac.executar(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getFloat("META"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }
    
    public Map<String,String> superB(){
        String sql;
        ResultSet rs;
        Map<String,String> map = new LinkedHashMap<>();
        
        sql = "SELECT Lojb010.NUM as B, Lojb010.Super as SUPERV \n" +
                "FROM Lojb010 INNER JOIN TAB_SUPER ON Lojb010.Super = TAB_SUPER.Super \n" +
                "ORDER BY TAB_SUPER.Ordem, Lojb010.NUM";

        rs = ac.executar(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getString("SUPERV"));
//                System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }
      
    public Map<String,String> grupoB(){
        String sql;
        ResultSet rs;
        Map<String,String> map = new LinkedHashMap<>();
        
        sql = "SELECT Lojb010.NUM as B, Lojb010.Grupo as SUPERV \n" +
                "FROM Lojb010 \n" +
                "WHERE Lojb010.Grupo IS NOT NULL " +
                "ORDER BY Lojb010.Grupo, Lojb010.Ordem";

        rs = ac.executar(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getString("SUPERV"));
//                System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }      
    
    public Map<String,String> avulsoB(String B){
        Map<String,String> map = new LinkedHashMap<>();      
        try {   
           map.put(B,B);
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        return map;
    }        
    
    public Map<String,String> numericoB(){
        String sql;
        ResultSet rs;
        Map<String,String> map = new LinkedHashMap<>();
        
        sql = "SELECT Lojb010.NUM as B, 1 as NUMERICO \n" +
                "FROM Lojb010 \n" +
                "WHERE Lojb010.Grupo IS NOT NULL " +
                "ORDER BY Lojb010.NUM";

        rs = ac.executar(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getString("NUMERICO"));
//                System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }   
    
    public Map<String,String> todasB(){
        String sql;
        ResultSet rs;
        Map<String,String> map = new LinkedHashMap<>();
        
        sql = "SELECT Lojb010.NUM as B, Lojb010.LOJA as SIGLA \n" +
                "FROM Lojb010 \n" +
                "ORDER BY Lojb010.NUM";

        rs = ac.executar(sql);
        
        try {   
            while (rs.next()){
                map.put(rs.getString("B"),rs.getString("SIGLA"));
//                System.out.println(rs.getString("SUPERV") + " ==== " + rs.getString("B"));
            }
        } catch (Exception e) {
            System.err.println("Error :" + e.getMessage());
        }       
        
        return map;
    }       
    
    
}
