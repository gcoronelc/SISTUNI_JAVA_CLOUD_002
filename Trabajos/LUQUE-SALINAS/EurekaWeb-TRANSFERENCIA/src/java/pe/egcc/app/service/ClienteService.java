package pe.egcc.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Cliente;

public class ClienteService {

    public Cliente traerPorCodigo(String codigo) {
        Cliente bean = null;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_cliecodigo, vch_cliepaterno, "
                    + "vch_cliematerno, vch_clienombre, "
                    + "chr_cliedni, vch_clieciudad, "
                    + "vch_cliedireccion, vch_clietelefono, "
                    + "vch_clieemail "
                    + "from cliente "
                    + "where chr_cliecodigo = ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bean= new Cliente();
                bean.setCodigo(rs.getString("chr_cliecodigo"));
                bean.setPaterno(rs.getString("vch_cliepaterno"));
                bean.setMaterno(rs.getString("vch_cliematerno"));
                bean.setNombre(rs.getString("vch_clienombre"));
                bean.setDni(rs.getString("chr_cliedni"));
                bean.setCiudad(rs.getString("vch_clieciudad"));
                bean.setDireccion(rs.getString("vch_cliedireccion"));
                bean.setTelefono(rs.getString("vch_clietelefono"));
                bean.setEmail(rs.getString("vch_clieemail"));
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

    public List<Cliente> traerLista(Cliente bean) {
        List<Cliente> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_cliecodigo,vch_cliepaterno,"
                    + "vch_cliematerno,vch_clienombre,"
                    + "chr_cliedni,vch_clieciudad,"
                    + "vch_cliedireccion,vch_clietelefono,"
                    + "vch_clieemail from cliente "
                    + "where chr_cliecodigo like concat(?,'%') "
                    + "and vch_cliepaterno like concat(?,'%') "
                    + "and vch_cliematerno like concat(?,'%') "
                    + "and vch_clienombre like concat(?,'%')";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, bean.getCodigo());
            pstm.setString(2, bean.getPaterno());
            pstm.setString(3, bean.getMaterno());
            pstm.setString(4, bean.getNombre());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cliente o = new Cliente();
                o.setCodigo(rs.getString("chr_cliecodigo"));
                o.setPaterno(rs.getString("vch_cliepaterno"));
                o.setMaterno(rs.getString("vch_cliematerno"));
                o.setNombre(rs.getString("vch_clienombre"));
                o.setDni(rs.getString("chr_cliedni"));
                o.setCiudad(rs.getString("vch_clieciudad"));
                o.setDireccion(rs.getString("vch_cliedireccion"));
                o.setTelefono(rs.getString("vch_clietelefono"));
                o.setEmail(rs.getString("vch_clieemail"));
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

    public List<Cliente> consultarNomCliente(String nomCliente) {
        List<Cliente> lista = new ArrayList<>();
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_cliecodigo, vch_cliepaterno, "
                    + "vch_cliematerno, vch_clienombre, "
                    + "chr_cliedni, vch_clieciudad, "
                    + "vch_cliedireccion, vch_clietelefono, "
                    + "vch_clieemail "
                    + "from cliente "
                    + "where concat(vch_cliepaterno,' ',vch_cliematerno,' ',vch_clienombre) like ? ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            pstm.setString(1, nomCliente);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Cliente bean = new Cliente();
                bean.setCodigo(rs.getString("chr_cliecodigo"));
                bean.setPaterno(rs.getString("vch_cliepaterno"));
                bean.setMaterno(rs.getString("vch_cliematerno"));
                bean.setNombre(rs.getString("vch_clienombre"));
                bean.setDni(rs.getString("chr_cliedni"));
                bean.setCiudad(rs.getString("vch_clieciudad"));
                bean.setDireccion(rs.getString("vch_cliedireccion"));
                bean.setTelefono(rs.getString("vch_clietelefono"));
                bean.setEmail(rs.getString("vch_clieemail"));
                lista.add(bean);
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

    public void insertar(Cliente bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "select int_contitem, int_contlongitud "
                    + "from contador "
                    + "where vch_conttabla = 'Cliente' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No hay contador de clientes");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont++;
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Cliente'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();
            String patron = "%0" + size + "d";
            String codigo = String.format(patron, cont);
            sql = "insert into cliente(chr_cliecodigo, "
                    + "vch_cliepaterno, vch_cliematerno, "
                    + "vch_clienombre, chr_cliedni, "
                    + "vch_clieciudad, vch_cliedireccion, "
                    + "vch_clietelefono, vch_clieemail) "
                    + "values(?,?,?,?,?,?,?,?,?) ";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, codigo);
            pstm.setString(2, bean.getPaterno());
            pstm.setString(3, bean.getMaterno());
            pstm.setString(4, bean.getNombre());
            pstm.setString(5, bean.getDni());
            pstm.setString(6, bean.getCiudad());
            pstm.setString(7, bean.getDireccion());
            pstm.setString(8, bean.getTelefono());
            pstm.setString(9, bean.getEmail());
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
            throw new RuntimeException("No se registro el cliente, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void actualizar(Cliente bean) {
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            cn.setAutoCommit(false);
            String sql = "update cliente "
                    + "set vch_cliepaterno = ?, vch_cliematerno = ?, "
                    + "vch_clienombre = ?, chr_cliedni = ?, "
                    + "vch_clieciudad = ?, vch_cliedireccion = ?,"
                    + "vch_clietelefono = ?, vch_clieemail = ? "
                    + "where chr_cliecodigo = ?";

            try (PreparedStatement pstm = cn.prepareStatement(sql)) {
                pstm.setString(1, bean.getPaterno());
                pstm.setString(2, bean.getMaterno());
                pstm.setString(3, bean.getNombre());
                pstm.setString(4, bean.getDni());
                pstm.setString(5, bean.getCiudad());
                pstm.setString(6, bean.getDireccion());
                pstm.setString(7, bean.getTelefono());
                pstm.setString(8, bean.getEmail());
                pstm.setString(9, bean.getCodigo());
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
            throw new RuntimeException("No se actualizo el cliente, inténtelo más tarde.");
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
                    + "where vch_conttabla = 'Cliente' "
                    + "for update ";
            PreparedStatement pstm = cn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (!rs.next()) {
                throw new SQLException("No hay el contador de clientes");
            }
            int cont = rs.getInt("int_contitem");
            int size = rs.getInt("int_contlongitud");
            rs.close();
            pstm.close();
            cont--;
            System.out.print("El codigo " + codigo);
            sql = "update contador set int_contitem = ? "
                    + "where vch_conttabla = 'Cliente'";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, cont);
            pstm.executeUpdate();

            sql = "delete from cliente "
                    + "where (chr_cliecodigo = ? ) ";

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
            throw new RuntimeException("No se elimino el cliente, inténtelo más tarde.");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public boolean validarDatos(Cliente bean) {
        boolean res = false;
        Connection cn = null;
        try {
            cn = AccesoDB.getConnection();
            String sql = "select chr_cliecodigo "
                    + "from cliente "
                    + "where vch_cliepaterno = ? "
                    + "and   vch_cliematerno = ? "
                    + "and   vch_clienombre  = ?";

            if (bean.getCodigo() != null) {
                sql += "and   chr_cliecodigo <> ?";
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
