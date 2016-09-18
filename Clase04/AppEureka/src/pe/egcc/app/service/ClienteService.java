package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.model.Cliente;

public class ClienteService {
  
  public List<Cliente> traerClientes(Cliente bean){
    List<Cliente> lista = new ArrayList<>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_cliecodigo,vch_cliepaterno,"
          + "vch_cliematerno,vch_clienombre,"
          + "chr_cliedni,vch_clieciudad,"
          + "vch_cliedireccion,vch_clietelefono,"
          + "vch_clieemail from cliente "
          + "where chr_cliecodigo like concat(?,'%') "
          + "and vch_cliepaterno like concat(?,'%') "
          + "and vch_cliematerno like concat(?,'%') "
          + "and vch_clienombre like concat(?,'%')";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, bean.getCodigo());
      pstm.setString(2, bean.getPaterno());
      pstm.setString(3, bean.getMaterno());
      pstm.setString(4, bean.getNombre());
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        Cliente o = new Cliente();
        o.setCodigo(rs.getString("chr_cliecodigo"));
        o.setPaterno(rs.getString("vch_cliepaterno"));
        o.setMaterno(rs.getString("vch_cliematerno"));
        o.setNombre(rs.getString("vch_clienombre"));
        o.setDni(rs.getString("chr_cliedni"));
        o.setCiudad(rs.getString("vch_clieciudad"));
        o.setDireccion(rs.getString("vch_cliedireccion"));
        o.setTelefono(rs.getString("vch_clietelefono"));
        o.setEmail(rs.getString("vch_clieemail"));
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
