/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.ProductoTO;
import uni.service.ICrudDao;

/**
 *
 * @author Alumno
 */
public class ProductoDao implements ICrudDao<ProductoTO> {

    //variables
    Connection cn = null;
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = "";
    List<ProductoTO> lista = null;
    ProductoTO pro = null;

    @Override
    public void create(ProductoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            sql = "insert into productos(idproducto,descripcion,idlinea,preciocompra,precioventa,stock)"
                    + " values(?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            //preparar valores de los prametros
            ps.setString(1, t.getIdproducto());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getLinea());
            ps.setDouble(4, t.getPreciocompra());
            ps.setDouble(5, t.getPrecioventa());
            ps.setInt(6, t.getStock());
            ps.executeUpdate();//
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(ProductoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            sql = "update productos set descripcion=?,idlinea=?,preciocompra=?,precioventa=?,stock=?"
                    + " where idproducto=?";
            ps = cn.prepareStatement(sql);
            //preparar valores de los prametros         
            ps.setString(1, t.getDescripcion());
            ps.setInt(2, t.getLinea());
            ps.setDouble(3, t.getPreciocompra());
            ps.setDouble(4, t.getPrecioventa());
            ps.setInt(5, t.getStock());
            ps.setString(6, t.getIdproducto());
            ps.executeUpdate();//
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(ProductoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            sql = "delete from productos where idproducto=?";
            ps = cn.prepareStatement(sql);
            //preparar valores de los prametros      
            ps.setString(1, t.getIdproducto());
            ps.executeUpdate();//
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public ProductoTO find(ProductoTO t) throws Exception {
        pro = null;
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from productos where idproducto=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, t.getIdproducto());
            rs = ps.executeQuery();
            if (rs.next()) {
                pro = new ProductoTO();
                pro.setIdproducto(rs.getString(1));
                pro.setDescripcion(rs.getString(2));
                pro.setLinea(rs.getInt(3));
                pro.setPreciocompra(rs.getDouble(4));
                pro.setPrecioventa(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return pro;
    }
    

    @Override
    public List<ProductoTO> readAll() throws Exception {
        lista = new ArrayList<>();
        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            st = cn.createStatement();
            //ejecutar comando
            rs = st.executeQuery("select * from productos order by descripcion");
            while (rs.next()) {
                pro = new ProductoTO();
                //asignar valores al objeto pro
                pro.setIdproducto(rs.getString(1));
                pro.setDescripcion(rs.getString(2));
                pro.setLinea(rs.getInt(3));
                pro.setPreciocompra(rs.getDouble(4));
                pro.setPrecioventa(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
                //adicionar pro a la coleccion lista
                lista.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }

    //Listar productos con filtro por inicio de nombre
    public List<ProductoTO> readAll(String nombre) throws Exception {
        lista = new ArrayList<>();

        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            sql = "select * from productos where descripcion like ?";
            ps = cn.prepareStatement(sql);
            //prepara valor del parametro
            ps.setString(1, nombre + "%");
            //ejecutar comando
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProductoTO();
                //asignar valores al objeto pro
                pro.setIdproducto(rs.getString(1));
                pro.setDescripcion(rs.getString(2));
                pro.setLinea(rs.getInt(3));
                pro.setPreciocompra(rs.getDouble(4));
                pro.setPrecioventa(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
                //adicionar pro a la coleccion lista
                lista.add(pro);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }

    //Listar productos con filtro por linea de producto (tipo)
    public List<ProductoTO> readAll(int linea) throws Exception {
        lista = new ArrayList<>();
        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            sql = "select * from productos where idlinea=?";
            ps = cn.prepareStatement(sql);
            //prepara valor del parametro

            ps.setInt(1, linea);
            //ejecutar comando
            rs = ps.executeQuery();
            while (rs.next()) {
                pro = new ProductoTO();
                //asignar valores al objeto pro
                pro.setIdproducto(rs.getString(1));
                pro.setDescripcion(rs.getString(2));
                pro.setLinea(rs.getInt(3));
                pro.setPreciocompra(rs.getDouble(4));
                pro.setPrecioventa(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
                //adicionar pro a la coleccion lista
                lista.add(pro);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return lista;
    }
    
    public ProductoTO productByName(String t) throws Exception {
        pro = null;
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from productos where descripcion=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, t);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro = new ProductoTO();
                pro.setIdproducto(rs.getString(1));
                pro.setDescripcion(rs.getString(2));
                pro.setLinea(rs.getInt(3));
                pro.setPreciocompra(rs.getDouble(4));
                pro.setPrecioventa(rs.getDouble(5));
                pro.setStock(rs.getInt(6));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return pro;
    }
    
}
