package pe.egcc.app.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JdbcUtil {

  private JdbcUtil() {
  }

  /**
   * 
   * @param rs
   * @return Retorna una lista de objetoa Map procedentes del ResultSet.
   * @throws SQLException
   */
  public static List<Map<String, ?>> rsToList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<Map<String, ?>> results = new ArrayList<>();
    while (rs.next()) {
      Map<String, Object> row = new HashMap<>();
      for (int i = 1; i <= columns; i++) {
        row.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
      }
      results.add(row);
    }
    return results;
  }

  /**
   * 
   * @param rs
   * @return Retorna un objeto Map con la fila actual del ResultSet.
   * @throws SQLException
   */
  public static Map<String, ?> rsToMap(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    Map<String, Object> rec = new HashMap<String,Object>();
    for (int i = 1; i <= columns; i++) {
      rec.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
    }
    return rec;
  }

}
