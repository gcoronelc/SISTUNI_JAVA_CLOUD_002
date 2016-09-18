package com.dao;

import com.model.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends DAO {

    public void registrar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Insert into producto (nombre,precio) values (?,?)");
            psd.setString(1, pro.getNombre());
            psd.setDouble(2, pro.getPrecio());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Producto> listar() throws Exception {
        List<Producto> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareCall("Select codigo,nombre,precio FROM producto");
            rs = psd.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setCodigo(rs.getInt("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(rs.getDouble("precio"));
                lista.add(pro);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public Producto leerID(Producto pro) throws Exception {
        Producto pros = null;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Select codigo,nombre,precio from producto where codigo=?");
            psd.setInt(1, pro.getCodigo());
            rs = psd.executeQuery();
            while (rs.next()) {
                pros = new Producto();
                pros.setCodigo(rs.getInt("codigo"));
                pros.setNombre(rs.getString("nombre"));
                pros.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return pros;
    }

    public void modificar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Update producto set nombre=?,precio=? where codigo=?");
            psd.setString(1, pro.getNombre());
            psd.setDouble(2, pro.getPrecio());
            psd.setInt(3, pro.getCodigo());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void eliminar(Producto pro) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Delete from producto where codigo=?");
            psd.setInt(1, pro.getCodigo());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
