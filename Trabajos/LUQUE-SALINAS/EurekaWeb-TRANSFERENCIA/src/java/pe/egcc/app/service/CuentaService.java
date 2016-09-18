package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Cuenta;

public class CuentaService {

    public void procDeposito(String cuenta, double importe, String codEmp) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select dec_cuensaldo, int_cuencontmov "
                    + "from cuenta "
                    + "where chr_cuencodigo = ? "
                    + "for update";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("La cuenta no existe");
            }
            double saldo = rs.getDouble("dec_cuensaldo");
            int cont = rs.getInt("int_cuencontmov");
            rs.close();
            pstm.close();
            saldo += importe;
            cont++;
            sql = "update cuenta "
                    + "set dec_cuensaldo = ?, "
                    + "int_cuencontmov = ? "
                    + "where chr_cuencodigo = ? ";
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, saldo);
            pstm.setInt(2, cont);
            pstm.setString(3, cuenta);
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new RuntimeException("La cuenta no existe");
            }
            pstm.close();
            sql = "insert into movimiento(chr_cuencodigo, "
                    + "int_movinumero, dtt_movifecha, "
                    + "chr_emplcodigo, chr_tipocodigo, "
                    + "dec_moviimporte) values(?,?,SYSDATE,?,'003',?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuenta);
            pstm.setInt(2, cont);
            pstm.setString(3, codEmp);
            pstm.setDouble(4, importe);
            pstm.executeUpdate();
            pstm.close();
            cn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                cn.rollback();
            } catch (Exception e2) {
            }
            String msg = "Error en realizar el deposito";
            if (e != null && e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
    }

    public List<Cuenta> traerCuenta(Cuenta bean) {
        List<Cuenta> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select c.CHR_CUENCODIGO,m.VCH_MONEDESCRIPCION,"
                    + "s.VCH_SUCUNOMBRE,e.VCH_EMPLPATERNO,"
                    + "cli.VCH_CLIEPATERNO,"
                    + "c.DEC_CUENSALDO,c.DTT_CUENFECHACREACION,c.VCH_CUENESTADO,"
                    + "c.INT_CUENCONTMOV from cuenta c "
                    + "join moneda m on c.CHR_MONECODIGO=m.CHR_MONECODIGO "
                    + "join sucursal s on c.CHR_SUCUCODIGO=s.CHR_SUCUCODIGO "
                    + "join empleado e on c.CHR_EMPLCREACUENTA=e.CHR_EMPLCODIGO "
                    + "join cliente cli on c.CHR_CLIECODIGO=cli.CHR_CLIECODIGO "
                    + "where c.CHR_CUENCODIGO=?";

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCuencodigo());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cuenta o = new Cuenta();
                o.setCuencodigo(rs.getString(1));
                o.setMonecodigo(rs.getString(2));
                o.setSucucodigo(rs.getString(3));
                o.setEmplcreacuenta(rs.getString(4));
                o.setCliecodigo(rs.getString(5));
                o.setCuensaldo(rs.getString(6));
                o.setCuenfechacreacion(rs.getString(7));
                o.setCuenestado(rs.getString(8));
                o.setCuencontmov(rs.getString(9));
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
    

    public void transferir(String codigoemp,String cuorigen, String cudestino, double monto) {

        Connection cn = null;
        Connection cn2 = null;
               
        try {
            
            
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select dec_cuensaldo, int_cuencontmov "
                    + "from cuenta "
                    + "where chr_cuencodigo = ? "
                    + "for update";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuorigen);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("La cuenta origen no existe");
            }
            
            //////////////////////////////////////////////////
            ///Comprobar estado de cuenta destino
            ////////////////////////////////////////////////////
            
            cn2 = AccesoDB.getConnection();
            cn2.setAutoCommit(false);
            String sql2 = "select dec_cuensaldo, int_cuencontmov "
                    + "from cuenta "
                    + "where chr_cuencodigo = ? "
                    + "for update";
            PreparedStatement pstm2 = cn2.prepareStatement(sql2);
            pstm2.setString(1, cudestino);
            ResultSet rs2 = pstm2.executeQuery();
            if (!rs2.next()) {
                throw new RuntimeException("La cuenta destino no existe");
            }
            
            ////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            double saldo = rs.getDouble("dec_cuensaldo");
            int cont = rs.getInt("int_cuencontmov");
            rs.close();
            pstm.close();
            saldo -= monto;
            cont++;
            sql = "update cuenta "
                    + "set dec_cuensaldo = ?, "
                    + "int_cuencontmov = ? "
                    + "where chr_cuencodigo = ? ";
            pstm = cn.prepareStatement(sql);
            pstm.setDouble(1, saldo);
            pstm.setInt(2, cont);
            pstm.setString(3, cuorigen);
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new RuntimeException("La cuenta origen no existe");
            }
            pstm.close();
            sql = "insert into movimiento(chr_cuencodigo, "
                    + "int_movinumero, dtt_movifecha, "
                    + "chr_emplcodigo, chr_tipocodigo, "
                    + "dec_moviimporte) values(?,?,CURDATE(),?,'009',?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, cuorigen);
            pstm.setInt(2, cont);
            pstm.setString(3, codigoemp);
            pstm.setDouble(4, monto);
            pstm.executeUpdate();
            pstm.close();
            cn.commit();
            
            //////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////
            
            double saldo2 = rs2.getDouble("dec_cuensaldo");
            int cont2 = rs2.getInt("int_cuencontmov");
            rs2.close();
            pstm2.close();
            saldo2 += monto;
            cont2++;
            sql2 = "update cuenta "
                    + "set dec_cuensaldo = ?, "
                    + "int_cuencontmov = ? "
                    + "where chr_cuencodigo = ? ";
            pstm2 = cn2.prepareStatement(sql2);
            pstm2.setDouble(1, saldo2);
            pstm2.setInt(2, cont2);
            pstm2.setString(3, cudestino);
            int n2 = pstm2.executeUpdate();
            if (n2 == 0) {
                throw new RuntimeException("La cuenta destino no existe");
            }
            pstm2.close();
            sql2 = "insert into movimiento(chr_cuencodigo, "
                    + "int_movinumero, dtt_movifecha, "
                    + "chr_emplcodigo, chr_tipocodigo, "
                    + "dec_moviimporte) values(?,?,CURDATE(),?,'008',?)";
            pstm2 = cn2.prepareStatement(sql2);
            pstm2.setString(1, cudestino);
            pstm2.setInt(2, cont2);
            pstm2.setString(3, codigoemp);
            pstm2.setDouble(4, monto);
            pstm2.executeUpdate();
            pstm2.close();
            cn2.commit();
  
            ///////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////
            
        } catch (Exception e) {
            e.printStackTrace();
            try {
                cn.rollback();
                cn2.rollback();
            } catch (Exception e2) {
            }
            String msg = "Error en realizar la transferencia";
            if (e != null && e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
                cn2.close();
            } catch (Exception e2) {
            }
        }
        
    }
}
