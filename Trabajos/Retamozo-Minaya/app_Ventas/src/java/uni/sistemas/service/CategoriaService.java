package uni.sistemas.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import uni.sistemas.DB.AccesoDB;
import uni.sistemas.modelo.Categoria;

public class CategoriaService {

    Connection cn;
    CallableStatement cs;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";

    public List<Categoria> listaCategoria(Categoria c) {
        List<Categoria> lista = new ArrayList<>();
        try {
            cn = AccesoDB.ConnexionDB();
            if (c.getIdCategoria() > 0) {
                sql = "select IdCategoria,Descripcion from Categoria where idcategoria like"
                        + " concat(?,'%') order by idcategoria";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, c.getIdCategoria());
            } else {
                sql = "select IdCategoria,Descripcion from Categoria order by idcategoria asc";
                ps = cn.prepareStatement(sql);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria ct = new Categoria();
                ct.setIdCategoria(rs.getInt(1));
                ct.setDescripcion(rs.getString(2));
                lista.add(ct);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("No se logro encontrar la base de datos");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return lista;
    }

    public void insertarCategoria(Categoria c) {
        String resultado = "";
        try {
            cn = AccesoDB.ConnexionDB();
            sql = "insert into categoria(IdCategoria,Descripcion) values (?,?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getIdCategoria());
            ps.setString(2, c.getDescripcion());
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("No se logro registrar la categoria");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void ActualizarCategoria(Categoria c) {
        String mensaje;
        try {
            cn = AccesoDB.ConnexionDB();
            sql = "update categoria set descripcion=? where idCategoria=?";
            ps = cn.prepareCall(sql);
            ps.setString(1, c.getDescripcion());
            ps.setInt(2, c.getIdCategoria());
            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al encontrar el codigo");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public void eliminarCategoria(Categoria c) {
        try {
            cn = AccesoDB.ConnexionDB();
            sql = "delete from categoria where idcategoria=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, c.getIdCategoria());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
    }

    public int obtenerId() {
        int n = 0;
        try {
            cn = AccesoDB.ConnexionDB();
            sql = "select max(IdCategoria)+1 from Categoria ";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw new RuntimeException("No se logro encontrar la base de datos");
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }
        }
        return n;
    }
}
