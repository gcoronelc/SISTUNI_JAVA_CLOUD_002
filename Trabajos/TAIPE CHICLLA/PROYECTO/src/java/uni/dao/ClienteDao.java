package uni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import uni.database.AccesoDB;
import uni.entity.ClienteTO;
import uni.entity.ProductoTO;
import uni.service.ICrudDao;

public class ClienteDao implements ICrudDao<ClienteTO> {

    // variables
    Connection cn = null;
    PreparedStatement ps = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    String sql = "";
    List<ClienteTO> lista;

    @Override
    public void create(ClienteTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ClienteTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ClienteTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClienteTO find(ClienteTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ClienteTO> readAll() throws Exception {
        lista = new ArrayList<>();
        try {
            cn = AccesoDB.getConnection();
            ps = cn.prepareStatement("select * from clientes order by nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                ClienteTO cli = new ClienteTO();
                //asignar valores al objeto pro
                cli.setIdcliente(rs.getString(1));
                cli.setNombre(rs.getString(2));
                cli.setDireccion(rs.getString(3));
                cli.setRucdni(rs.getString(4));
                cli.setTelefono(rs.getString(5));
                lista.add(cli);
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
    
    public String clientByName(String t) throws Exception {
        String cod = null;
        try {
            cn = AccesoDB.getConnection();
            sql = "select idcliente from clientes where nombre=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, t);
            rs = ps.executeQuery();
            rs.next();
            cod = rs.getString(1);
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return cod;
    }


}
