package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pe.egcc.app.db.AccesoDB;

public class CuentaService {

  public void procDeposito(String cuenta, double importe, String codEmp) {
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // Inicio de la Tx
      cn.setAutoCommit(false);
      // Leer datos de la cuenta
      String sql = "select dec_cuensaldo, int_cuencontmov " 
          + "from cuenta where chr_cuencodigo = ? " 
          + "for update ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      if (!rs.next()) {
        throw new RuntimeException("Cuenta " + cuenta + " no existe.");
      }
      double saldo = rs.getDouble("dec_cuensaldo");
      int conta = rs.getInt("int_cuencontmov");
      // Actualizar datos de la cuenta
      saldo += importe;
      conta++;
      sql = "update cuenta set dec_cuensaldo = ?, " 
          + "int_cuencontmov = ? " 
          + "where chr_cuencodigo = ? ";
      pstm = cn.prepareStatement(sql);
      pstm.setDouble(1, saldo);
      pstm.setInt(2, conta);
      pstm.setString(3, cuenta);
      pstm.executeUpdate();
      pstm.close();
      // Insertar movimiento
      sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
          + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) " 
          + "values(?,?,sysdate,?,'003',?)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      pstm.setInt(2, conta);
      pstm.setString(3, codEmp);
      pstm.setDouble(4, importe);
      pstm.executeUpdate();
      pstm.close();
      // Confirmar Tx
      cn.commit();
    } catch (Exception e) {
      try {
        cn.rollback();
      } catch (Exception e1) {
      }
      String texto = "ERROR: No se pudo ejecutar el proceso DEPOSITO.";
      if (e != null && !e.getMessage().isEmpty()) {
        texto += "\n" + e.getMessage();
      }
      throw new RuntimeException(texto);
    } finally {
      try {
        cn.close();
      } catch (Exception e) {
      }
    }

  }

}
