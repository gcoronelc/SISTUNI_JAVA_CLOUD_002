package uni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.DetalleTO;
import uni.entity.VentaTO;
import uni.entity.VentaTOAjax;
import uni.service.IDocumentoDao;

public class VentaDao implements IDocumentoDao<VentaTO> {
    //variables

    Connection cn = null;
    PreparedStatement ps = null;
    CallableStatement csv, csd, csp = null;
    ResultSet rs = null;
    String sql = "";

    Statement st = null;        
    List<VentaTO> lista = null;
    VentaTO pro = null;
    
    List<DetalleTO> listaDet = null;
    DetalleTO prodet = null;
    
    List<VentaTOAjax> listaVentAjax = null;
    VentaTOAjax proajax = null;
    
    
    @Override
    public void registraDocumento(VentaTO d) throws Exception {
        int nro;
        try {
            cn = AccesoDB.getConnection();
            // activa la transaccion
            cn.setAutoCommit(false);
            nro = numeroFactura();//genera nro de venta
            d.setIdventa(nro);
            sql = "{call sp_Registra_Venta(?,?,?,?,?,?)}";
            csv = cn.prepareCall(sql);
            //preparar los valores a pasar al sp
            csv.setInt(1, d.getIdventa());
            csv.setString(2, d.getIdcliente());
            csv.setString(3, d.getIdempleado());
            csv.setString(4, d.getTipodoc());
            csv.setString(5, d.getNrodoc());
            csv.setDouble(6, d.getTotal());
            csv.executeUpdate();   //ejecutar sp sp_Registrar_Venta
            sql = "{call sp_Registra_Detalle(?,?,?,?,?)}";
            csd = cn.prepareCall(sql);
            sql = "{call sp_Actualiza_Stock(?,?)}";
            csp = cn.prepareCall(sql);
            for (DetalleTO de : d.getDetalle()) {
                //preparar lovalores para sp de detalle
                csd.setInt(1, d.getIdventa());
                csd.setString(2, de.getIdproducto());
                csd.setDouble(3, de.getPrecio());
                csd.setInt(4, de.getCantidad());
                csd.setDouble(5, de.getImporte());
                csd.executeUpdate();//ejecuta sp sp_Registra_detalle
                //preparar los valores para sp de producto
                csp.setInt(2, de.getCantidad());
                csp.setString(1, de.getIdproducto());
                csp.executeUpdate();//ejecuta sp de sp_Actualiza_Stock
            }
            csv.close();
            csd.close();
            csp.close();
            cn.commit();//confirma la transaccion
        } catch (Exception e) {
            try {
                cn.rollback();//deshace la transaccion
            } catch (SQLException e1) {
            }
            throw e;
        } finally {
            cn.close();
        }
    }

    private int numeroFactura() throws SQLException {
        sql = "select valor from control where parametro='Ventas'";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int cont = rs.getInt(1);
        rs.close();
        sql = "update control set valor=valor+1 where parametro='Ventas'";
        ps = cn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        return cont;
    }
        
    public List<VentaTOAjax> readAllVentas(String idventa) throws Exception {
        listaVentAjax = new ArrayList<>();
        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            sql = "select v.IDVENTA,v.IDCLIENTE,v.IDEMPLEADO, decode(v.TIPODOC,'1','FACTURA','BOLETA') TIPODOC,v.NRODOC,v.TOTAL, (select e.nombre || ' ' || e.apellidos from empleados e where e.IDEMPLEADO = v.IDEMPLEADO) DESEMPLEADO from ventas v where v.idventa like ? order by v.IDVENTA";
            ps = cn.prepareStatement(sql);
            //prepara valor del parametro

            ps.setString(1, idventa);
            //ejecutar comando
            rs = ps.executeQuery();
            while (rs.next()) {
                proajax = new VentaTOAjax();
                //asignar valores al objeto pro
                proajax.setIdventa(rs.getInt(1));
                proajax.setIdcliente(rs.getString(2));  
                proajax.setIdempleado(rs.getString(3));
                proajax.setTipodoc(rs.getString(4));
                proajax.setNrodoc(rs.getString(5));
                proajax.setTotal(rs.getDouble(6));
                proajax.setDesEmpleado("EDISON TAIPE");                
                //adicionar pro a la coleccion lista
                listaVentAjax.add(proajax);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return listaVentAjax;
    }
    
    public List<DetalleTO> readAllDetalle(int idventa) throws Exception {
        listaDet = new ArrayList<>();
        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            sql = "select D.IDPRODUCTO ,(select p.descripcion from productos p where p.IDPRODUCTO = d.IDPRODUCTO) NOMBRE ,d.PRECIO,d.CANTIDAD,d.IMPORTE from DetalleVenta d where d.idventa = ?";
            ps = cn.prepareStatement(sql);
            //prepara valor del parametro

            ps.setInt(1, idventa);
            //ejecutar comando
            rs = ps.executeQuery();
            while (rs.next()) {
                prodet = new DetalleTO();
                //asignar valores al objeto pro
                prodet.setIdproducto(rs.getString(1));
                prodet.setNombre(rs.getString(2));
                prodet.setPrecio(rs.getDouble(3));
                prodet.setCantidad(rs.getInt(4));
                prodet.setImporte(rs.getDouble(5));                
                                
                listaDet.add(prodet);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return listaDet;
    }
}
