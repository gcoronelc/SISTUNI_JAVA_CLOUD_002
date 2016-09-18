
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Categoria;
import uni.sistemas.modelo.Producto;

public class ProductoService {
    
    Connection cn;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    
    public List<Producto>listaProductos(){
        List<Producto>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="select p.IdProducto, p.codigo, p.nombre, p.descripcion, "
                    + "p.stock, p.stockmin, p.precioCosto, p.precioVenta, p.Utilidad, "
                    + "p.estado, p.imagen, c.descripcion from producto p inner join categoria c "
                    + "on p.idCategoria=c.idCategoria order by p.idproducto asc";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                Categoria cat=new Categoria();
                p.setIdProducto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setStock(rs.getDouble(5));
                p.setStockMin(rs.getDouble(6));
                p.setPrecioCosto(rs.getDouble(7));
                p.setPrecioVenta(rs.getDouble(8));
                p.setUtilidad(rs.getDouble(9));
                p.setEstado(rs.getString(10));
                p.setImagen(rs.getString(11));
                cat.setDescripcion(rs.getString(12));
                p.setCategoria(cat);
                lista.add(p);
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
    
    public void registrarProducto(Producto p){
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="insert into Producto(IdProducto, codigo, nombre, "
                    + "descripcion, stock, stockmin, precioCosto, precioVenta, "
                    + "Utilidad, estado, imagen, idCategoria) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, p.getIdProducto());
            ps.setString(2, p.getCodigo());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDescripcion());
            ps.setDouble(5, p.getStock());
            ps.setDouble(6, p.getStockMin());
            ps.setDouble(7, p.getPrecioCosto());
            ps.setDouble(8, p.getPrecioVenta());
            ps.setDouble(9, p.getUtilidad());
            ps.setString(10, p.getEstado());
            ps.setString(11, p.getImagen());
            ps.setInt(12, p.getCategoria().getIdCategoria());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el producto");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public int obtenerIdProducto(){
        int n=0;
       try {
            cn=AccesoDB.ConnexionDB();
            String sql="select max(idproducto)+1 from producto";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
           if(rs.next()){
                n=rs.getInt(1);
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
       return n;
    }
    
    public void actualizarProducto(Producto p){
        int n=0;
        String mensaje="";
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="update producto set codigo=?, nombre=?, "
                    + "descripcion=?, stock=?, stockmin=?, precioCosto=?, precioVenta=?, "
                    + "Utilidad=?, estado=?, imagen=?, idCategoria=? where idproducto=?";
            ps=cn.prepareStatement(sql);
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getStock());
            ps.setDouble(5, p.getStockMin());
            ps.setDouble(6, p.getPrecioCosto());
            ps.setDouble(7, p.getPrecioVenta());
            ps.setDouble(8, p.getUtilidad());
            ps.setString(9, p.getEstado());
            ps.setString(10, p.getImagen());
            ps.setInt(11, p.getCategoria().getIdCategoria());
            ps.setInt(12, p.getIdProducto());
            ps.executeUpdate();
            
            if(n==1){
                mensaje="Exito al registrar el producto de codigo: "+p.getCodigo();
            }else{
                mensaje="Error al registrar el producto";
            }
            cs.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el producto");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void eliminarProducto(Producto p){
        int n=0;
        String mensaje="";
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="delete from producto where idproducto=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, p.getIdProducto());
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el producto");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public List<Categoria>listaCategoria(){
        List<Categoria>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="select IdCategoria,Descripcion from Categoria";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Categoria ct=new Categoria();
                ct.setIdCategoria(rs.getInt(1));
                ct.setDescripcion(rs.getString(2));
                lista.add(ct);
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
