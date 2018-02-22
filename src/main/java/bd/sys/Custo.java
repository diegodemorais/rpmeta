/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.sys;

import bd.Mysql;
import java.sql.ResultSet;


/**
 *
 * @author dm
 */
public class Custo {

   private static Mysql CST;
   
   public Custo(){
       CST = new Mysql("rpcusto.mysql.dbaas.com.br","rpcusto","rpcusto","poter12poter12");
       CST.connect();
   } 
   
   public ResultSet getTermos(){
       ResultSet rs;
       String sql;
       sql = "SELECT * \n" +
                "FROM termo ;";
       rs = CST.executarQuery(sql);       
       return rs;
   }
    
}
