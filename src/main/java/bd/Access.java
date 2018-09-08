package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dm
 */

public class Access {

    final private String database;
   
    public Connection c;
  
    public Access ( String database ) {
        this.database = database;
    }
  
    public boolean connect() {
        boolean isConnected = false;
       
        String url;
        url = "jdbc:ucanaccess:"+ this.database;
        url += ";memory=false;immediatelyReleaseResources=true";
       
        try {//
           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver").newInstance();
            this.c = DriverManager.getConnection(url);
            isConnected = true;
        } catch( SQLException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( InstantiationException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            isConnected = false;
        }
      
        return isConnected;
    }
  
    public boolean disconnect() {
        boolean isConnected = false;
      
        String url;
        url = "jdbc:ucanaccess:"+ this.database;
            
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver").newInstance();
            this.c = DriverManager.getConnection(url);
            this.c.close();
            isConnected = true;
        } catch( SQLException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( ClassNotFoundException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( InstantiationException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch ( IllegalAccessException e ) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
      
        return isConnected;
    }

    public ResultSet executar( String query ) {
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
  
    public int inserir( String query ) {
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