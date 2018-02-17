/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author dm
 */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Mysql {

    final private String host;
    final private String user;
    final private String pass;
    final private String database;
   
    public Connection c;
  
    /**
     * Construtor da classe
     *
     * @param host Host em que se deseja conectar
     * @param database Nome do database em que se deseja conectar
     * @param user Nome do usuário
     * @param pass Senha do usuário
     */
    public Mysql ( String host, String database, String user, String pass ) {
        this.pass = pass;
        this.user = user;
        this.host = host;
        this.database = database;
    }
  
    /**
     * Método que estabelece a conexão com o banco de dados
     *
     * @return True se conseguir conectar, falso em caso contrário.
     */
    public boolean connect() {
        boolean isConnected = false;
       
        String url;
        String portNumber = "3306";
        String userName   = this.user;
        String passName   = this.pass;
        url = "jdbc:mysql://" + this.host + ":" + portNumber + "/" +this.database;
       
        try {//
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.c = DriverManager.getConnection(url,userName, passName);
            isConnected = true;
        } catch( SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
      
        return isConnected;
    }
  
    /**
     * Método que estabelece a desconexão com o banco de dados
     *
     * @return True se conseguir desconectar, falso em caso contrário.
     */
    public boolean disconnect() {
        boolean isConnected = false;
      
        String url;
        String portNumber = "3306";
        String userName   = this.user;
        String passName   = this.pass;
        url = "jdbc:mysql://" + this.host + ":" + portNumber + "/" +this.database;
            
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.c = DriverManager.getConnection(url,userName, passName);
            this.c.close();
            isConnected = true;
        } catch( SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
      
        return isConnected;
    }

    /**
     * Esse método executa a query dada, e retorna um ResultSet
     * Talvez fosse melhor idéia fazer esse método lançar uma exception
     * a faze-lo retornar null como eu fiz, porém isso é apenas um exemplo
     * para demonstrar a funcionalidade do comando execute
     *
     * @param query String contendo a query que se deseja executarQuery
     * @return ResultSet em caso de estar tudo Ok, null em caso de erro.
     */
    public ResultSet executarQuery( String query ) {
        Statement st;
        ResultSet rs;
      
        try {
            st = this.c.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
      
        return null;
    }
  
    /**
     * Executa uma query como update, delete ou insert.
     * Retorna o número de registros afetados quando falamos de um update ou delete
     * ou retorna 1 quando o insert é bem sucedido. Em outros casos retorna -1
     *
     * @param query A query que se deseja executarQuery
     * @return 0 para um insert bem sucedido. -1 para erro
     */
    public int executarComando( String query ) {
        Statement st;
        int result = -1;
      
        try {
            st = this.c.createStatement();
            result = st.executeUpdate(query);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
      
        return result;
    }
   
}