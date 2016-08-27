package pe.egcc.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.egcc.app.db.AccesoDB;
import pe.egcc.app.domain.Sucursal;

/**
 *
 * @author Gustavo Coronel
 */
public class CuentaModel {

	public List<Sucursal> traerSucursales() {
		List<Sucursal> lista = new ArrayList<>();
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select chr_sucucodigo,vch_sucunombre,"
			    + "vch_sucuciudad,vch_sucudireccion,int_sucucontcuenta "
			    + "from sucursal ";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				Sucursal bean = new Sucursal();
				bean.setCodigo(rs.getString("chr_sucucodigo"));
				bean.setNombre(rs.getString("vch_sucunombre"));
				bean.setCiudad(rs.getString("vch_sucuciudad"));
				bean.setDireccion(rs.getString("vch_sucudireccion"));
				bean.setContador(rs.getInt("int_sucucontcuenta"));
				lista.add(bean);
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("ERROR en la BD.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	public List<String> traerCuentas() {
		List<String> lista = new ArrayList<>();
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select chr_cuencodigo from cuenta";
			Statement stm = cn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				lista.add(rs.getString("chr_cuencodigo"));
			}
			rs.close();
			stm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("ERROR en la BD.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	public List<String> traerCuentas(String sucursal) {
		List<String> lista = new ArrayList<>();
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select chr_cuencodigo from cuenta "
			    + "where chr_sucucodigo = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, sucursal);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				lista.add(rs.getString("chr_cuencodigo"));
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("ERROR en la BD.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

	public List<Map<String, Object>> traerMov(String cuenta) {
		List<Map<String, Object>> lista = new ArrayList<>();
		Connection cn = null;
		try {
			cn = AccesoDB.getConnection();
			String sql = "select chr_cuencodigo, int_movinumero,"
			    + "chr_tipocodigo, dtt_movifecha, dec_moviimporte "
			    + "from movimiento " + "where chr_cuencodigo = ?";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Map<String, Object> r = new HashMap();
				r.put("cuenta", rs.getString("chr_cuencodigo"));
				r.put("nromov", rs.getInt("int_movinumero"));
				r.put("tipo", rs.getString("chr_tipocodigo"));
				r.put("fecha", rs.getDate("dtt_movifecha"));
				r.put("importe", rs.getDouble("dec_moviimporte"));
				lista.add(r);
			}
			rs.close();
			pstm.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			throw new RuntimeException("ERROR en la BD.");
		} finally {
			try {
				cn.close();
			} catch (Exception e) {
			}
		}
		return lista;
	}

}
