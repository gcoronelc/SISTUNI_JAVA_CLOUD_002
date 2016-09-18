package uni.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import uni.database.AccesoDB;
import uni.entity.EmpleadoTO;
import uni.service.ICrudDao;

public class EmpleadoDao implements ICrudDao<EmpleadoTO> {

    // variables
    Connection cn = null;
    PreparedStatement ps = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    String sql = "";
    List<EmpleadoTO> lista;
    EmpleadoTO emp = null;

    @Override
    public void create(EmpleadoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            //generar codigo de empleado
            String cod = generaCodigo();
            t.setIdempleado(cod);
            cs = cn.prepareCall("{call sp_Empleado_Adicionar(?,?,?,?,?,?)}");
            //preparar los valores para el parametro del sp
            cs.setString(1, t.getIdempleado());
            cs.setString(2, t.getNombre());
            cs.setString(3, t.getApellidos());
            cs.setString(4, t.getEmail());
            cs.setString(5, t.getUsuario());
            cs.setString(6, t.getClave());
            cs.executeUpdate();
            cs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void update(EmpleadoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            cs = cn.prepareCall("{call sp_Empleado_Actualizar(?,?,?,?,?,?)}");
            //preparar los valores para el parametro del sp
            cs.setString(1, t.getIdempleado());
            cs.setString(2, t.getNombre());
            cs.setString(3, t.getApellidos());
            cs.setString(4, t.getEmail());
            cs.setString(5, t.getUsuario());
            cs.setString(6, t.getClave());
            cs.executeUpdate();
            cs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public void delete(EmpleadoTO t) throws Exception {
        try {
            cn = AccesoDB.getConnection();
            sql = "{call sp_Empleado_Eliminar(?)}";
            cs = cn.prepareCall(sql);
            //preparar los valores para el parametro del sp
            cs.setString(1, t.getIdempleado());
            cs.executeUpdate();
            cs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
    }

    @Override
    public EmpleadoTO find(EmpleadoTO t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmpleadoTO> readAll() throws Exception {
        lista = new ArrayList<>();
        try {
            cn = AccesoDB.getConnection();
            ps = cn.prepareStatement("select * from empleados order by nombre");
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpleadoTO emp = new EmpleadoTO();
                //asignar valores al objeto pro
                emp.setIdempleado(rs.getString(1));
                emp.setNombre(rs.getString(2));
                emp.setApellidos(rs.getString(3));
                emp.setEmail(rs.getString(4));
                lista.add(emp);
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

    public String validar(String usu, String pas) throws SQLException {
        String sw = "0";
        try {
            cn = AccesoDB.getConnection();
            sql = "select * from empleados"
                    + " where upper(usuario)=upper(?) and upper(clave)=upper(?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, usu);
            ps.setString(2, pas);
            rs = ps.executeQuery();
            if (rs.next()) {
                sw = "1";
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            cn.close();
        }
        return sw;
    }

    private String generaCodigo() throws SQLException {
        String codigo = "";
        sql = "select valor from control where parametro='Empleados'";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        rs.next();
        int cont = rs.getInt(1);
        rs.close();
        sql = "update control set valor=valor+1 where parametro='Empleados'";
        ps = cn.prepareStatement(sql);
        ps.executeUpdate();
        if (cont < 10) {
            codigo = "E000" + cont;
        } else {
            codigo = "E00" + cont;
        }
        return codigo;
    }

    public String employeeByName(String t) throws Exception {
        String cod = null;       
        StringTokenizer token=new StringTokenizer(t);
        String nom=token.nextToken();
        String ape=token.nextToken();
        try {
            
            cn = AccesoDB.getConnection();
            sql = "select idempleado from empleados where nombre=? and apellidos=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nom);
             ps.setString(2, ape);
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
