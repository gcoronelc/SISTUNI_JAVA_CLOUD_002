package pe.egcc.app.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.util.JdbcUtil;

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

  
  public void procRetiro(String cuenta, double importe, String codEmp, String clave){
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // La Tx se controla en la base de datos dentro del SP
      cn.setAutoCommit(true);
      // Proceso
      String sql = "{call usp_egcc_retiro(?,?,?,?)}";
      CallableStatement cstm = cn.prepareCall(sql);
      cstm.setString(1, cuenta);
      cstm.setDouble(2, importe);
      cstm.setString(3, codEmp);
      cstm.setString(4, clave);
      cstm.executeUpdate();
      cstm.close();
    } catch (Exception e) {
      String texto = "ERROR: No se pudo ejecutar el proceso RETIRO.";
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
  
  
  public double conSaldoCuenta(String cuenta){
    double saldo = 0.0;
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // La Tx se controla en la base de datos dentro del SP
      cn.setAutoCommit(true);
      // Proceso
      String sql = "{call usp_egcc_saldo_cuenta(?,?)}";
      CallableStatement cstm = cn.prepareCall(sql);
      cstm.setString(1, cuenta);
      cstm.registerOutParameter(2, Types.DECIMAL);
      cstm.executeUpdate();
      saldo = cstm.getDouble(2);
      cstm.close();
    } catch (Exception e) {
      String texto = "ERROR: No se pudo consultar la BD.";
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
    return saldo;
  }
  
  public List<Map<String,?>> conMovimientos(String cuenta){
    List<Map<String,?>> lista = null;
    Connection cn = null;
    try {
      // Conexión
      cn = AccesoDB.getConnection();
      // Proceso
      String sql = "select SUCUCODIGO, SUCUNOMBRE, CLIECODIGO,"
          + "CLIEPATERNO, CLIEMATERNO, CLIENOMBRE, CUENCODIGO,"
          + "CUENSALDO, CUENESTADO, MOVINUMERO, MOVIFECHA, "
          + "MOVIIMPORTE, CUENREFERENCIA, TIPOCODIGO, TIPONOMBRE, "
          + "TIPOACCION, MONECODIGO, MONENOMBRE "
          + "from v_movimiento where CUENCODIGO = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      lista = JdbcUtil.rsToList(rs);
      rs.close();
      pstm.close();
    } catch (Exception e) {
      String texto = "ERROR: No se pudo consultar la BD.";
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
    return lista;
  }
  
}
