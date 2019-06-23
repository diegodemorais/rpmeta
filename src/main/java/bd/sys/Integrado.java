/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.sys;

import bd.Mysql;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author dm
 */
public class Integrado {

   private static Mysql mysql;
   
   public Integrado(){
	   mysql = new Mysql("mysql03.rpaps.locaweb.com.br","rpaps_2","rpaps_2","poter12poter12");
	   mysql.connect();
   } 
   
   public Map<String,Float> metaB(String mes, String ano){
       String sql;
       ResultSet rs;
       Map<String,Float> map = new HashMap<>();
       
       sql = "SELECT TAB_META.MT_F_LOJA as B, TAB_META.MT_VALOR as META \n" +
             "FROM TAB_META \n" +
             "WHERE ((TAB_META.MT_MES=" + mes + ") AND (TAB_META.MT_ANO=" + ano + "))";
       rs = mysql.executarQuery(sql);
       
       try {   
           while (rs.next()){
               map.put(rs.getString("B"),rs.getFloat("META"));
           }
       } catch (Exception e) {
           System.err.println("Error :" + e.getMessage());
       }       
       
       return map;
   }
    
}
