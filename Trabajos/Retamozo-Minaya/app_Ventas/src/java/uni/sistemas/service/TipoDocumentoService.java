
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.TipoDocumento;

public class TipoDocumentoService {
    
    Connection cn;
    CallableStatement cs;
    PreparedStatement ps;
    ResultSet rs;
    String sql="";
    
   public List<TipoDocumento> listaTipoDocumento(){
        List<TipoDocumento>lista=new ArrayList<>();
        try {
            cn=AccesoDB.ConnexionDB();
            sql="select IdTipoDocumento,Descripcion from tipoDocumento";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                TipoDocumento td=new TipoDocumento();
                td.setIdTipoDocu(rs.getInt(1));
                td.setDescripcion(rs.getString(2));
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
    public void insertarTipoDocumento(TipoDocumento td){
        try {
            cn=AccesoDB.ConnexionDB();
            sql="insert into tipoDocumento(IdTipoDocumento,Descripcion) values (?,?)";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, td.getIdTipoDocu());
            ps.setString(2, td.getDescripcion());
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("No se logro registrar la categoria");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void ActualizarTipoDocumento(TipoDocumento td){
        try {
            cn=AccesoDB.ConnexionDB();
            sql="update tipoDocumento set descripcion=? where idTipoDocumento=?";
            ps=cn.prepareStatement(sql);
            ps.setString(1, td.getDescripcion());
            ps.setInt(2, td.getIdTipoDocu());
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el codigo");
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public void eliminarTipoDocumento(TipoDocumento td){
        try {
            cn=AccesoDB.ConnexionDB();
            sql="delete from tipoDocumento where idTipoDocumento=?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, td.getIdTipoDocu());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }finally{
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }
    
    public int obtenerId(){
        int n=0;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="select max(IdTipoDocumento)+1 from tipoDocumento";
            ps=cn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                TipoDocumento td=new TipoDocumento();
                n=rs.getInt(1);
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
        return n;
    }
}
