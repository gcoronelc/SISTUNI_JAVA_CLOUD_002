
package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Usuario;

public class UsuarioService {
    
    Connection cn;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    
    public List<Usuario>listaUsuarios(){
        List<Usuario>lista=new ArrayList<Usuario>();
        String sql;
        try {
            cn=AccesoDB.ConnexionDB();
            sql="select * from tipousuario";
            ps=cn.prepareCall(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario u=new Usuario();
                u.setId(rs.getInt(1));
                u.setDescripcion(rs.getString(2));
                
                lista.add(u);
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
}
