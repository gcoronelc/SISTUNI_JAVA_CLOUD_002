package pe.egcc.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {
  
  private AccesoDB() {
  }

  public static Connection getConnection() throws SQLException {
    Connection cn = null;
    try {
      // Datos conexión
      String driver = "com.mysql.jdbc.Driver";
      //String driver = "oracle.jdbc.OracleDriver";
      String url = "jdbc:mysql://localhost/eurekabank";
      //String url = "jdbc:mysql://ip/eurekabank";
      //String url = "jdbc:oracle:thin:@localhost:1521:orcl";
      String user = "eureka";
     String pass = "admin";
      // Cargar el driver a memoria
      Class.forName(driver).newInstance();
      // Obtener el objeto Connection
      cn = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      throw e;
    } catch(ClassNotFoundException e){
      throw new SQLException("ERROR, no se encuentra el driver.");
    } catch(Exception e){
      throw new SQLException("ERROR, no se tiene acceso al servidor.");
    }
    return cn;
  }
}