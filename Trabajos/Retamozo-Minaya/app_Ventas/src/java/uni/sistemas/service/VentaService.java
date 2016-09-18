
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Cliente;
import uni.sistemas.modelo.Empleado;
import uni.sistemas.modelo.TipoDocumento;
import uni.sistemas.modelo.Venta;

public class VentaService {
   
    Connection cn;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    
    public List<Venta>listaVenta(){
        List<Venta>lista=new ArrayList<Venta>();
        String sql;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="select v.idventa, td.descripcion, c.nombre, e.apellido, e.nombre, "
                    + "v.serie, v.numero, v.fecha, v.totalVenta, v.igv, v.totalPagar, v.estado from venta v "
                    + "inner join tipodocumento td on v.idtipoDocumento=td.idTipoDocumento inner join"
                    + "cliente c on v.idcliente=c.idcliente inner join empleado e on "
                    + "v.idempleado=e.idempleado order by v.idventa asc";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Venta v=new Venta();
                TipoDocumento t=new TipoDocumento();
                Cliente c=new Cliente();
                Empleado e=new Empleado();
                
                v.setIdVenta(rs.getInt(1));
                t.setDescripcion(rs.getString(2));
                v.setTipoDocumento(t);
                c.setNombre(rs.getString(3));
                v.setCliente(c);
                e.setApellido(rs.getString(4));
                e.setNombre(rs.getString(5));
                v.setEmpleado(e);
                v.setSerie(rs.getString(6));
                v.setNumero(rs.getString(7));
                v.setFecha(rs.getDate(8));
                v.setTotalVenta(rs.getDouble(9));
                v.setIgv(rs.getDouble(10));
                v.setTotalPagar(rs.getDouble(11));
                v.setEstado(rs.getString(12));
                
                lista.add(v);
            }
            rs.close();
            ps.close();
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
    
    public void insertarVenta(Venta v){
        String sql;
        
        try {
            cn=AccesoDB.ConnexionDB();
            sql="insert into venta (idventa, idTipoDocumento, idcliente, idempleado, serie, numero, "
                    + "fecha, totalVenta, igv, totalPagar, estado) values (?,?,?,?,?,?,?,?,?,?,?)";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, v.getIdVenta());
            ps.setInt(2, v.getTipoDocumento().getIdTipoDocu());
            ps.setInt(3, v.getCliente().getIdCliente());
            ps.setInt(4, v.getEmpleado().getIdEmpleado());
            ps.setString(5, v.getSerie());
            ps.setString(6, v.getNumero());
            ps.setDate(7, (Date) v.getFecha());
            ps.setDouble(8, v.getTotalVenta());
            ps.setDouble(9, v.getIgv());
            ps.setDouble(10, v.getTotalPagar());
            ps.setString(11, v.getEstado());
            
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void actualizarVenta(Venta v){
        String sql;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="update venta set idTipoDocumento=?, idcliente=?, idempleado=?, serie=?, numero=?, "
                    + "igv=?, estado=? where idventa=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, v.getTipoDocumento().getIdTipoDocu());
            ps.setInt(2, v.getCliente().getIdCliente());
            ps.setInt(3, v.getEmpleado().getIdEmpleado());
            ps.setString(4, v.getSerie());
            ps.setString(5, v.getNumero());
            ps.setDouble(6, v.getIgv());
            ps.setString(7, v.getEstado());
            
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void eliminarVenta(Venta v){
        String sql;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="delete from venta where idventa=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, v.getIdVenta());
            
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public List<Cliente>listaCliente(){
        List<Cliente>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
//            sql="call SP_S_Cliente()";
            String sql="select nombre from cliente order by apellido asc";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Cliente cli=new Cliente();
                cli.setNombre(rs.getString(1));
            }
            rs.close();
            ps.close();
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
    
    public List<Empleado> listaEmpleado() {
        List<Empleado> lista = new ArrayList<>();
        Empleado emple = null;
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "select nombre, apellido from empleado order by apellido";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emple = new Empleado();
                emple.setNombre(rs.getString(2));
                emple.setApellido(rs.getString(3));
                lista.add(emple);
            }
            rs.close();
            ps.close();
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }
    
    public List<TipoDocumento> listaTipoDocumento(){
        List<TipoDocumento>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="select Descripcion from tipoDocumento order by Descripcion asc";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                TipoDocumento td=new TipoDocumento();
                td.setDescripcion(rs.getString(1));
                lista.add(td);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw new RuntimeException("No se logro encontrar la base de datos");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }
}
