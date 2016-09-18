
package uni.sistemas.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {

    public AccesoDB() {
    }
    
    public static Connection ConnexionDB() throws Exception{
        Connection cn=null;
        try {
            String driver="oracle.jdbc.OracleDriver";
            String url="jdbc:oracle:thin:@172.17.3.167:1521:XE";
            String user="VENTASDB";
            String password="ventasdb";
            Class.forName(driver).newInstance();
            cn=DriverManager.getConnection(url, user, password);
            
        } catch (SQLException ex) {
            throw ex;
        }catch(ClassNotFoundException ce){
            throw new SQLException("Error no se tiene acceso al driver");
        }catch(InstantiationException | IllegalAccessException e){
            throw new SQLException("Error no se tiene acceso al servidor");
        }
        return cn;
    }
}
