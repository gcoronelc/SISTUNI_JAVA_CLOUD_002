package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Movimiento;

public class MovimientoService {
  
  public List<Movimiento> traerMovimientos(Movimiento bean){
    List<Movimiento> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select m.CHR_CUENCODIGO,m.INT_MOVINUMERO,"
      		+ "m.DTT_MOVIFECHA,e.VCH_EMPLPATERNO,"
      		+ "tm.VCH_TIPODESCRIPCION,tm.VCH_TIPOACCION,m.DEC_MOVIIMPORTE,"
      		+ "m.CHR_CUENREFERENCIA from movimiento m "
      		+ "join empleado e on m.CHR_EMPLCODIGO=e.CHR_EMPLCODIGO "
      		+ "join tipomovimiento tm on m.CHR_TIPOCODIGO=tm.CHR_TIPOCODIGO "
      		+ "where m.CHR_CUENCODIGO=? order by m.DTT_MOVIFECHA asc";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getCuencodigo());

      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
    	Movimiento o = new Movimiento();
        o.setCuencodigo(rs.getString(1));
        o.setMovinumero(rs.getString(2));
        o.setMovifecha(rs.getString(3));
        o.setEmplcodigo(rs.getString(4));
        o.setTipocodigo(rs.getString(5));
        o.setTipoaccion(rs.getString(6));
        o.setMoviimporte(rs.getString(7));
        o.setCuenreferencia(rs.getInt(8));
        
        lista.add(o);
      }
      rs.close();
      pstm.close();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return lista;
  }

}
