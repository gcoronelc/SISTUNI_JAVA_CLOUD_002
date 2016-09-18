package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Empleado;

public class EmpleadoService {

    public List<Empleado> traerEmpleados(Empleado bean) {
        List<Empleado> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select CHR_EMPLCODIGO,VCH_EMPLPATERNO,"
                    + "VCH_EMPLMATERNO,VCH_EMPLNOMBRE,"
                    + "VCH_EMPLCIUDAD,"
                    + "VCH_EMPLDIRECCION,VCH_EMPLUSUARIO,"
                    + "VCH_EMPLCLAVE from empleado "
                    + "where CHR_EMPLCODIGO like concat(?,'%') "
                    + "and VCH_EMPLPATERNO like concat(?,'%') "
                    + "and VCH_EMPLMATERNO like concat(?,'%') "
                    + "and VCH_EMPLNOMBRE like concat(?,'%')";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCodigo());
            pstm.setString(2, bean.getPaterno());
            pstm.setString(3, bean.getMaterno());
            pstm.setString(4, bean.getNombre());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Empleado o = new Empleado();
                o.setCodigo(rs.getString("CHR_EMPLCODIGO"));
                o.setPaterno(rs.getString("VCH_EMPLPATERNO"));
                o.setMaterno(rs.getString("VCH_EMPLMATERNO"));
                o.setNombre(rs.getString("VCH_EMPLNOMBRE"));
                o.setCiudad(rs.getString("VCH_EMPLCIUDAD"));
                o.setDireccion(rs.getString("VCH_EMPLDIRECCION"));
                o.setUsuario(rs.getString("VCH_EMPLUSUARIO"));
                o.setClave(rs.getString("VCH_EMPLCLAVE"));
                lista.add(o);
            }
            rs.close();
            pstm.close();
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

    public Empleado traerPorCodigo(String codigo) {
        Empleado bean = null;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_emplcodigo, vch_emplpaterno, "
                    + "vch_emplmaterno, vch_emplnombre, "
                    + "vch_emplciudad, "
                    + "vch_empldireccion, vch_emplusuario "
                    + "from empleado "
                    + "where chr_emplcodigo = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bean = new Empleado();
                bean.setCodigo(rs.getString("chr_emplcodigo"));
                bean.setPaterno(rs.getString("vch_emplpaterno"));
                bean.setMaterno(rs.getString("vch_emplmaterno"));
                bean.setNombre(rs.getString("vch_emplnombre"));
                bean.setCiudad(rs.getString("vch_emplciudad"));
                bean.setDireccion(rs.getString("vch_empldireccion"));
                bean.setUsuario(rs.getString("vch_emplusuario"));
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "Error en el proceso de validación.";
            if (e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return bean;
    }

    public List<Empleado> traerLista(Empleado bean) {
        List<Empleado> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();

            String sql = "select CHR_EMPLCODIGO,VCH_EMPLPATERNO,"
                    + "VCH_EMPLMATERNO,VCH_EMPLNOMBRE,"
                    + "VCH_EMPLCIUDAD,"
                    + "VCH_EMPLDIRECCION,VCH_EMPLUSUARIO,"
                    + "VCH_EMPLCLAVE from empleado "
                    + "where CHR_EMPLCODIGO like concat(?,'%') "
                    + "and VCH_EMPLPATERNO like concat(?,'%') "
                    + "and VCH_EMPLMATERNO like concat(?,'%') "
                    + "and VCH_EMPLNOMBRE like concat(?,'%')";

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCodigo());
            pstm.setString(2, bean.getPaterno());
            pstm.setString(3, bean.getMaterno());
            pstm.setString(4, bean.getNombre());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Empleado o = new Empleado();
                o.setCodigo(rs.getString("chr_emplcodigo"));
                o.setPaterno(rs.getString("vch_emplpaterno"));
                o.setMaterno(rs.getString("vch_emplmaterno"));
                o.setNombre(rs.getString("vch_emplnombre"));
                o.setCiudad(rs.getString("vch_emplciudad"));
                o.setDireccion(rs.getString("vch_empldireccion"));
                o.setUsuario(rs.getString("vch_emplusuario"));
                lista.add(o);
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "Error en el proceso de validación.";
            if (e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }

    public void insertar(Empleado bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select int_contitem, int_contlongitud "
                    + "from contador "
                    + "where vch_conttabla = 'Empleado' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("ERROR: El contador de clientes no existe.");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont++;
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Empleado'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();
            String patron = "%0" + size + "d";
            String codigo = String.format(patron, cont);
            String clave = bean.getUsuario();
            sql = "insert into empleado(chr_emplcodigo, "
                    + "vch_emplpaterno, vch_emplmaterno, "
                    + "vch_emplnombre, vch_emplciudad, "
                    + "vch_empldireccion,vch_emplusuario, vch_emplclave) "
                    + "values(?,?,?,?,?,?,?,?) ";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            pstm.setString(2, bean.getPaterno());
            pstm.setString(3, bean.getMaterno());
            pstm.setString(4, bean.getNombre());
            pstm.setString(5, bean.getCiudad());
            pstm.setString(6, bean.getDireccion());
            pstm.setString(7, bean.getUsuario());
            pstm.setString(8, clave);
            pstm.executeUpdate();
            pstm.close();
            bean.setCodigo(codigo);
            cn.commit();
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException("Error en el proceso Registrar Empleado, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void actualizar(Empleado bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "update empleado "
                    + "set vch_emplpaterno = ?, vch_emplmaterno = ?, "
                    + "vch_emplnombre = ?, vch_emplciudad = ?, "
                    + "vch_empldireccion = ?,vch_emplusuario = ? "
                    + "where chr_emplcodigo = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, bean.getPaterno());
                pstm.setString(2, bean.getMaterno());
                pstm.setString(3, bean.getNombre());
                pstm.setString(4, bean.getCiudad());
                pstm.setString(5, bean.getDireccion());
                pstm.setString(6, bean.getUsuario());
                pstm.setString(7, bean.getCodigo());
                pstm.executeUpdate();
            }
            cn.commit();
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException("Error en el proceso Actualizar Empleado, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void eliminar(String codigo) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select int_contitem, int_contlongitud "
                    + "from contador "
                    + "where vch_conttabla = 'Empleado' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("ERROR: El contador de empleados no existe.");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont--;
            System.out.print("El codigo " + codigo);
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Empleado'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();
            sql = "delete from empleado "
                    + "where (chr_emplcodigo = ? ) ";

            pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            pstm.executeUpdate();
            pstm.close();
            cn.commit();
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            try {
                cn.rollback();
            } catch (Exception e1) {
            }
            throw new RuntimeException("Error en el proceso Eliminar Empleado, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean validarDatos(Empleado bean) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_emplcodigo "
                    + "from empleado "
                    + "where vch_emplpaterno = ? "
                    + "and   vch_emplmaterno = ? "
                    + "and   vch_emplnombre  = ? ";

            if (bean.getCodigo() != null) {
                sql += "and   chr_emplcodigo <> ?";
            }

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getPaterno());
            pstm.setString(2, bean.getMaterno());
            pstm.setString(3, bean.getNombre());
            if (bean.getCodigo() != null) {
                pstm.setString(4, bean.getCodigo());
            }

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                res = false;

            } else {
                res = true;
            }

            rs.close();
            pstm.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            String msg = "Error en el proceso de validación.";
            if (e.getMessage() != null) {
                msg += "\n" + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return res;
    }

    public Empleado validar(String usuario, String clave) {
        Empleado bean = null;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_emplcodigo, vch_emplpaterno,"
                    + "vch_emplmaterno, vch_emplnombre, vch_emplciudad, "
                    + "vch_empldireccion, vch_emplusuario "
                    + "from empleado "
                    + "where vch_emplusuario = ? "
                    + "and vch_emplclave = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, clave);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                bean = new Empleado();
                bean.setCodigo(rs.getString("chr_emplcodigo"));
                bean.setPaterno(rs.getString("vch_emplpaterno"));
                bean.setMaterno(rs.getString("vch_emplmaterno"));
                bean.setNombre(rs.getString("vch_emplnombre"));
                bean.setCiudad(rs.getString("vch_emplciudad"));
                bean.setDireccion(rs.getString("vch_empldireccion"));
                bean.setUsuario(rs.getString("vch_emplusuario"));
            }
            rs.close();
            pstm.close();
        } catch (Exception e) {
            String msg = "Error en el acceso a la BD.";
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                msg += " " + e.getMessage();
            }
            throw new RuntimeException(msg);
        } finally {
            try {
                cn.close();
            } catch (Exception e2) {
            }
        }
        return bean;
    }
}
