/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Cliente;

public class ClienteService {
    
    Connection cn;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    String sql;
    
    public List<Cliente>listaCliente(){
        List<Cliente>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
//            sql="call SP_S_Cliente()";
            sql="select * from cliente order by IdCliente asc";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Cliente cli=new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setRuc(rs.getString(3));
                cli.setDni(rs.getString(4));
                cli.setDireccion(rs.getString(5));
                cli.setTelefono(rs.getString(6));
                cli.setObs(rs.getString(7));
                cli.setUsuario(rs.getString(8));
                lista.add(cli);
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
    
    
    public int obtenerIdCliente(){
        int n = 0;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="select max(IdCliente)+1 from cliente";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){ 
                n=rs.getInt(1);
            }
            
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return n;
    }
    
    public void insertarCliente(Cliente c){
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="insert into cliente(IdCliente, nombre, ruc, dni, direccion, "
                    + "telefono, obsv, usuario, contrasena) values (?,?,?,?,?,?,?,?,?)";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getRuc());
            ps.setString(4, c.getDni());
            ps.setString(5, c.getDireccion());
            ps.setString(6, c.getTelefono());
            ps.setString(7, c.getObs());
            ps.setString(8, c.getUsuario());
            ps.setString(9, c.getContraseña());
            ps.executeUpdate();
      
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void actualizarClientes(Cliente c){
        String mensaje;
        int n;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="update cliente set nombre=?, ruc=?, dni=?, direccion=?, telefono=?, obsv=?, "
                    + "usuario=?, contrasena=? where idcliente=?";
            ps=cn.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getRuc());
            ps.setString(3, c.getDni());
            ps.setString(4, c.getDireccion());
            ps.setString(5, c.getTelefono());
            ps.setString(6, c.getObs());
            ps.setString(7, c.getUsuario());
            ps.setString(8, c.getContraseña());
            ps.setInt(9, c.getIdCliente());
            
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
    
    public String eliminarCliente(Cliente c){
        String mensaje="";
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            String sql="delete from cliente where idCliente=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, c.getIdCliente());
            
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
        return mensaje;
    }
}
