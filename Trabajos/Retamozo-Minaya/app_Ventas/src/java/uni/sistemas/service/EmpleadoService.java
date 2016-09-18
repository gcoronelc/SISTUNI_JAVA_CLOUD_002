package uni.sistemas.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Empleado;
import uni.sistemas.modelo.TipoDocumento;
import uni.sistemas.modelo.Usuario;

public class EmpleadoService {

    Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Empleado validar(String usuario, String clave) {
        Empleado emple = null;
        TipoDocumento tipo = null;
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "select * from empleado where usuario = ? "
                    + "and contrasena = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            if (rs.next()) {
                emple = new Empleado();
                tipo = new TipoDocumento();
                emple.setIdEmpleado(rs.getInt(1));
                emple.setNombre(rs.getString(2));
                emple.setApellido(rs.getString(3));
                emple.setSexo(rs.getString(4));
                emple.setFechaNac(rs.getDate(5));
                emple.setDireccion(rs.getString(6));
                emple.setTelefono(rs.getString(7));
                emple.setCelular(rs.getString(8));
                emple.setEmail(rs.getString(9));
                emple.setDni(rs.getString(10));
                emple.setFechaIng(rs.getDate(11));
                emple.setSueldo(rs.getDouble(12));
                emple.setEstado(rs.getString(13));
                emple.setUsuario(rs.getString(14));
                emple.setContraseña("**********");
                tipo.setIdTipoDocu(rs.getInt(16));
            }
            rs.close();
            ps.close();
            if (emple == null) {
                throw new Exception("Datos son incorrectos.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return emple;
    }

    public List<Empleado> listaEmpleado() {
        List<Empleado> lista = new ArrayList<>();
        Empleado emple = null;
        Usuario usu = null;
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "select e.idempleado, e.nombre, e.apellido, e.sexo, e.fechaNac, "
                    + "e.direccion, e.telefono, e.celular, e.email, e.dni, e.fechaIng, e.sueldo, "
                    + "e.estado, e.usuario, e.contrasena, tu.descripcion from empleado e "
                    + "inner join tipoUsuario tu on e.idTipoUsuario=tu.idTipoUsuario order by e.idEmpleado asc";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emple = new Empleado();
                usu = new Usuario();
                emple.setIdEmpleado(rs.getInt(1));
                emple.setNombre(rs.getString(2));
                emple.setApellido(rs.getString(3));
                emple.setSexo(rs.getString(4));
                emple.setFechaNac(rs.getDate(5));
                emple.setDireccion(rs.getString(6));
                emple.setTelefono(rs.getString(7));
                emple.setCelular(rs.getString(8));
                emple.setEmail(rs.getString(9));
                emple.setDni(rs.getString(10));
                emple.setFechaIng(rs.getDate(11));
                emple.setSueldo(rs.getDouble(12));
                emple.setEstado(rs.getString(13));
                emple.setUsuario(rs.getString(14));
                emple.setContraseña(rs.getString(15));
                usu.setDescripcion(rs.getString(16));
                emple.setUsu(usu);
                lista.add(emple);
            }
            rs.close();
            ps.close();
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }

    public void insertarEmpleado(Empleado e) {
        
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "insert into empleado(idempleado, nombre, apellido, "
                    + "sexo, fechaNac, fechaIng, estado, idTipoUsuario) values (?,?,?,?,?,?,?,?)";
            
//            String sql="insert into empleado(idempleado, nombre, apellido, sexo, fechaNac, "
//                    + "direccion, telefono, celular, email, dni, fechaIng, sueldo, "
//                    + "estado, usuario, contrasena, idTipoUsuario) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            
            SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
           
            java.util.Date dat1=formato.parse(e.getFechaNac().toString());
            java.util.Date dat2=formato.parse(e.getFechaIng().toString());
            java.sql.Date sql1=new java.sql.Date(dat1.getTime());
            java.sql.Date sql2=new java.sql.Date(dat2.getTime());
            
            ps = cn.prepareStatement(sql);
            ps.setInt(1, e.getIdEmpleado());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido());
            ps.setString(4, e.getSexo());
            ps.setDate(5, sql1);
//            ps.setString(6, e.getDireccion());
//            ps.setString(7, e.getTelefono());
//            ps.setString(8, e.getCelular());
//            ps.setString(9, e.getEmail());
//            ps.setString(10, e.getDni());
            ps.setDate(6, sql2);
//            ps.setDouble(12, e.getSueldo());
            ps.setString(7, e.getEstado());
//            ps.setString(14, e.getUsuario());
//            ps.setString(15, e.getContraseña());
            ps.setInt(8, e.getUsu().getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception ex) {
            }
        }
    }

    public void actualizarEmpleado(Empleado e) {
        try {
            cn = AccesoDB.ConnexionDB();
//            String sql="update empleado set nombre=?, apellido=?, sexo=?, fechaNac=?, "
//                    + "direccion=?, telefono=?, celular=?, email=?, dni=?, fechaIng=?, sueldo=?, "
//                    + "estado=?, usuario=?, contrasena=?, idTipoUsuario=? where idempleado=?";
            String sql = "update empleado set direccion=?, celular=?, email=?, estado=? where "
                    + "idempleado=?";
            ps = cn.prepareStatement(sql);

//            ps.setString(1, e.getNombre());
//            ps.setString(2, e.getApellido());
//            ps.setString(3, e.getSexo());
//            ps.setDate(4, (Date) e.getFechaNac());
            ps.setString(1, e.getDireccion());
//            ps.setString(6, e.getTelefono());
            ps.setString(2, e.getCelular());
            ps.setString(3, e.getEmail());
//            ps.setString(9, e.getDni());
//            ps.setDate(10, (Date) e.getFechaIng());
//            ps.setDouble(11, e.getSueldo());
            ps.setString(4, e.getEstado());
//            ps.setString(13, e.getUsuario());
//            ps.setString(14, e.getContraseña());
//            ps.setInt(15, e.getUsu().getId());
            ps.setInt(5, e.getIdEmpleado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception ex) {
            }
        }
    }

    public void eliminarEmpleado(Empleado e) {
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "delete from empleado where idempleado=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, e.getIdEmpleado());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception ex) {
            }
        }
    }

    public int obtenerId() {
        int n = 0;
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "select max(idEmpleado)+1 from empleado";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return n;
    }

    public List<Usuario> listaTipoUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        Usuario usu = null;
        try {
            cn = AccesoDB.ConnexionDB();
            String sql = "select idtipoUsuario,descripcion from tipoUsuario order by idTipoUsuario";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                usu = new Usuario();
                usu.setId(rs.getInt(1));
                usu.setDescripcion(rs.getString(2));
                lista.add(usu);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return lista;
    }
}
