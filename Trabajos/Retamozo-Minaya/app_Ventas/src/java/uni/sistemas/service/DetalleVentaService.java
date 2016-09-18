
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.DetalleVenta;
import uni.sistemas.modelo.Producto;
import uni.sistemas.modelo.Venta;

public class DetalleVentaService {
    
    Connection cn;
    CallableStatement cs;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<DetalleVenta>listaDetalle(){
        List<DetalleVenta>lista=new ArrayList<>();
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="select * from detalleVenta order by asc";
            cs=cn.prepareCall(sql);
            rs=cs.executeQuery();
            while(rs.next()){
                DetalleVenta dv=new DetalleVenta();
                Venta v=new Venta();
                Producto p=new Producto();
                
                v.setIdVenta(rs.getInt(1));
                dv.setVenta(v);
                p.setIdProducto(rs.getInt(2));
                dv.setProducto(p);
                dv.setCantidad(rs.getDouble(3));
                dv.setCosto(rs.getDouble(4));
                dv.setPrecio(rs.getDouble(5));
                dv.setTotal(rs.getDouble(6));
                
                lista.add(dv);
            }
            rs.close();
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }
    
    public String insertarDetalle(DetalleVenta v){
        String sql;
        String mensaje="";
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="call SP_I_DetalleVenta(?,?,?,?,?,?)";
            cs=cn.prepareCall(sql);
            cs.setInt(1, v.getVenta().getIdVenta());
            cs.setInt(2, v.getProducto().getIdProducto());
            cs.setDouble(3, v.getCantidad());
            cs.setDouble(4, v.getCosto());
            cs.setDouble(5, v.getPrecio());
            cs.setDouble(6, v.getTotal());
            
            n=cs.executeUpdate();
            
            if(n==1){
                mensaje="Exito al insertar los datos";
            }else{
                mensaje="Error al insertar";
            }
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return mensaje;
    }
    
    public String actualizarDetalle(DetalleVenta v){
        String sql;
        String mensaje="";
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="call SP_U_DetalleVenta(?,?,?,?,?,?)";
            cs=cn.prepareCall(sql);
            cs.setInt(1, v.getVenta().getIdVenta());
            cs.setInt(2, v.getProducto().getIdProducto());
            cs.setDouble(3, v.getCantidad());
            cs.setDouble(4, v.getCosto());
            cs.setDouble(5, v.getPrecio());
            cs.setDouble(6, v.getTotal());
            
            n=cs.executeUpdate();
            
            if(n==1){
                mensaje="Exito al actualizar los detalles de la venta: "+v.getVenta().getIdVenta();
            }else{
                mensaje="Error al insertar";
            }
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return mensaje;
    }
    
    public String eliminarDetalle(DetalleVenta v){
        String sql;
        String mensaje="";
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="delete from DetalleVenta where idVenta=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, v.getVenta().getIdVenta());
            
            n=ps.executeUpdate();
            
            if(n==1){
                mensaje="Exito al eliminar el detalle de la venta: "+v.getVenta().getIdVenta();
            }else{
                mensaje="Error al eliminar";
            }
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return mensaje;
    }
}
