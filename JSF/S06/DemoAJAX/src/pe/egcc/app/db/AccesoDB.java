package pe.egcc.app.db;

/**
 * @author Gustavo Coronel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class AccesoDB {

	private AccesoDB() {
	}

	public static Connection getConnection() throws SQLException {
		Connection cn = null;
		try {
			// Datos
			String driver = "oracle.jdbc.OracleDriver";
			String url = "jdbc:oracle:thin:@172.17.3.180:1521:XE";
			String user = "eureka";
			String pass = "admin";

			// Cargar el driver
			Class.forName(driver).newInstance();

			// Obtener conexión
			cn = DriverManager.getConnection(url, user, pass);
			
		} catch (SQLException e) {
			throw e;
			
		} catch (ClassNotFoundException ex) {
			throw new SQLException("No existe el driver.");
			
		} catch (Exception ex) {
			throw new SQLException("No se puede tener acceso a la base de datos.");
		}
		
		return cn;
	}

}
