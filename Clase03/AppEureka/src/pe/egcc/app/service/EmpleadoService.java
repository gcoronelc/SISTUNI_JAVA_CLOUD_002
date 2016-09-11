package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.management.RuntimeErrorException;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.model.Empleado;

public class EmpleadoService {

  /**
   * Valida el usuario y clave de un empleado, si no son 
   * correctos genera un excepción de tipo RuntimeException.
   * 
   * @param usuario
   * @param clave
   * @return Si datos son correctos retorna un objeto de tipo Empleado.
   */
  public Empleado validar(String usuario, String clave){
    Empleado bean = null;
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_emplcodigo, vch_emplpaterno, "
          + "vch_emplmaterno, vch_emplnombre, vch_emplciudad, "
          + "vch_empldireccion, vch_emplusuario "
          + "from empleado where vch_emplusuario = ? "
          + "and vch_emplclave = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, usuario);
      pstm.setString(2, clave);
      ResultSet rs = pstm.executeQuery();
      if(rs.next()){
        bean = new Empleado();
        bean.setCodigo(rs.getString("chr_emplcodigo"));
        bean.setPaterno(rs.getString("vch_emplpaterno"));
        bean.setMaterno(rs.getString("vch_emplmaterno"));
        bean.setNombre(rs.getString("vch_emplnombre"));
        bean.setCiudad(rs.getString("vch_emplciudad"));
        bean.setDireccion(rs.getString("vch_empldireccion"));
        bean.setUsuario(rs.getString("vch_emplusuario"));
        bean.setClave("*********");
      }
      rs.close();
      pstm.close();
      if(bean == null){
        throw new Exception("Datos son incorrectos.");
      }
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      try {
        cn.close();
      } catch (Exception e2) {
      }
    }
    return bean;
  }
  
}
