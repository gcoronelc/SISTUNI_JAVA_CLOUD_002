package pe.egcc.app.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Sucursal;

public class SucursalService {
    
    public Sucursal traerPorCodigo(String codigo) {
        Sucursal bean = null;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_sucucodigo, "
                    + "vch_sucunombre, "
                    + "vch_sucuciudad, "
                    + "vch_sucudireccion,"
                    + "int_sucucontcuenta "
                    + "from sucursal "
                    + "where chr_sucucodigo = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bean= new Sucursal();
                bean.setCodigo(rs.getString(1));
                bean.setNombre(rs.getString(2));
                bean.setCiudad(rs.getString(3));
                bean.setDireccion(rs.getString(4));
                bean.setContcuenta(rs.getInt(5));
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

    public List<Sucursal> traerLista(Sucursal bean) {
        List<Sucursal> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_sucucodigo, "
                    + "vch_sucunombre, "
                    + "vch_sucuciudad, "
                    + "vch_sucudireccion, "
                    + "int_sucucontcuenta "
                    + "from sucursal "
                    + "where chr_sucucodigo like concat(?,'%') "
                    + "and vch_sucunombre like concat(?,'%') "
                    + "and vch_sucuciudad like concat(?,'%')";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCodigo());
            pstm.setString(2, bean.getNombre());
            pstm.setString(3, bean.getCiudad());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Sucursal o= new Sucursal();
                o.setCodigo(rs.getString(1));
                o.setNombre(rs.getString(2));
                o.setCiudad(rs.getString(3));
                o.setDireccion(rs.getString(4));
                o.setContcuenta(rs.getInt(5));
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

    public void insertar(Sucursal bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select int_contitem, int_contlongitud "
                    + "from contador "
                    + "where vch_conttabla = 'Sucursal' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("ERROR: El contador de sucursales no existe.");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont++;
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Sucursal'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();
            String patron = "%0" + size + "d";
            String codigo = String.format(patron, cont);
            sql = "insert into sucursal(chr_sucucodigo, vch_sucunombre, "
                    + "vch_sucuciudad, vch_sucudireccion, int_sucucontcuenta) "
                    + "values(?,?,?,?,0) ";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            pstm.setString(2, bean.getNombre());
            pstm.setString(3, bean.getCiudad());
            pstm.setString(4, bean.getDireccion());
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
            throw new RuntimeException("Error en el proceso Registrar Sucursal, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void actualizar(Sucursal bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "update sucursal "
                    + "set vch_sucunombre = ?, vch_sucuciudad = ?, "
                    + "vch_sucudireccion = ? "
                    + "where chr_sucucodigo = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, bean.getNombre());
                pstm.setString(2, bean.getCiudad());
                pstm.setString(3, bean.getDireccion());
                pstm.setString(4, bean.getCodigo());
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
            throw new RuntimeException("Error en el proceso Actualizar Sucursal, inténtelo más tarde.");
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
                    + "where vch_conttabla = 'Sucursal' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("ERROR: El contador de sucursales no existe.");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont--;
            System.out.print("El codigo " + codigo);
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Sucursal'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();
            sql = "delete from sucursal "
                    + "where (chr_sucucodigo = ? ) ";

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
            throw new RuntimeException("Error en el proceso Eliminar Sucursal, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public List<Sucursal> getSucursalesConCuenta() {
        List<Sucursal> lista = new ArrayList<Sucursal>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_sucucodigo, vch_sucunombre, "
                    + "vch_sucuciudad, vch_sucudireccion, int_sucucontcuenta "
                    + "from sucursal where chr_sucucodigo in "
                    + "(select distinct chr_sucucodigo from cuenta)";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Sucursal bean = new Sucursal();
                bean.setCodigo(rs.getString(1));
                bean.setNombre(rs.getString(2));
                bean.setCiudad(rs.getString(3));
                bean.setDireccion(rs.getString(4));
                bean.setContcuenta(rs.getInt(5));
                lista.add(bean);            
                
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
        return lista;
    }

    public boolean validarDatos(Sucursal bean) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_sucucodigo "
                    + "from sucursal "
                    + "where vch_sucunombre = ? ";

            if (bean.getCodigo() != null) {
                sql += "and   chr_sucucodigo <> ?";
            }

            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getNombre());
            if (bean.getCodigo() != null) {
                pstm.setString(2, bean.getCodigo());
            }

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                res = false;

            } else {
                res = true;
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
        return res;
    }
}
