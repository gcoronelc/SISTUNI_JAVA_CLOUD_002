package com.dao;

import com.model.Persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO extends DAO {

    public void registrar(Persona per) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Insert into Persona (nombre,sexo) values (?,?)");
            psd.setString(1, per.getNombre());
            psd.setString(2, per.getSexo());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Persona> listar() throws Exception {
        List<Persona> lista;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareCall("Select codigo,nombre,sexo FROM persona");
            rs = psd.executeQuery();
            lista = new ArrayList();
            while (rs.next()) {
                Persona per = new Persona();
                per.setCodigo(rs.getInt("codigo"));
                per.setNombre(rs.getString("nombre"));
                per.setSexo(rs.getString("sexo"));
                lista.add(per);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    public Persona leerID(Persona per) throws Exception {
        Persona pers = null;
        ResultSet rs;
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Select codigo,nombre,sexo from Persona where codigo=?");
            psd.setInt(1, per.getCodigo());
            rs = psd.executeQuery();
            while (rs.next()) {
                pers = new Persona();
                pers.setCodigo(rs.getInt("codigo"));
                pers.setNombre(rs.getString("nombre"));
                pers.setSexo(rs.getString("sexo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return pers;
    }

    public void modificar(Persona per) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Update Persona set nombre=?,sexo=? where codigo=?");
            psd.setString(1, per.getNombre());
            psd.setString(2, per.getSexo());
            psd.setInt(3, per.getCodigo());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void eliminar(Persona per) throws Exception {
        try {
            this.Conectar();
            PreparedStatement psd = this.getCn().prepareStatement("Delete from Persona where codigo=?");
            psd.setInt(1, per.getCodigo());
            psd.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
