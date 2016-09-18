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
import uni.entity.LineaTO;
import uni.entity.ProductoTO;
import uni.service.ICrudDao;

/**
 *
 * @author Alumno
 */
public class LineaDao implements ICrudDao <LineaTO>{

     //variables
    Connection cn = null;
    PreparedStatement ps= null;
    Statement st = null;
    ResultSet rs = null;
    String sql = "";
    List <LineaTO> lista= null;
    LineaTO linea=null;
    
    
    @Override
    public void create(LineaTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LineaTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(LineaTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LineaTO find(LineaTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LineaTO> readAll() throws Exception {
        lista = new ArrayList<>();
        try {
            //abrir conexion a la base de datos
            cn = AccesoDB.getConnection();
            //prepara comando
            st = cn.createStatement();
            //ejecutar comando
            rs = st.executeQuery("select * from lineas order by idlinea");
            while (rs.next()) {                
                linea = new LineaTO();
                //asignar valores al objeto linea
                linea.setIdlinea(rs.getInt(1));
                linea.setNombre(rs.getString(2));
                //adicionar pro a la coleccion lista
                lista.add (linea);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        }finally{
            cn.close();
        }
        return lista;  
    }
    
    
}
