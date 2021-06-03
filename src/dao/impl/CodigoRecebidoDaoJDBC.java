/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import dao.CodigoRecebidoDao;
import db.DB;
import db.DbException;
import entities.CodigoRecebido;
import entities.Produto;

public class CodigoRecebidoDaoJDBC implements CodigoRecebidoDao {

private Connection conn;
	
	public CodigoRecebidoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(CodigoRecebido codigoRecebido, Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO codigo_recebido (marca, codigo_recebido, id_produto) "
					+ "VALUE (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, codigoRecebido.getMarca());
			pst.setString(2, codigoRecebido.getCodigo_recebido());
			pst.setInt(3, produto.getId_produto());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				codigoRecebido.setId_codigo_recebido(id);
			} else {
				throw new DbException("ERRO DE INSERÇÃO!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public CodigoRecebido findByCodigoRecebido (Integer idProduto, String marca, String codigoRecebido) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		CodigoRecebido object = new CodigoRecebido();

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_recebido where id_produto = ? and marca = ? and codigo_recebido = ?");
			
			pst.setInt(1, idProduto);
			pst.setString(2, marca);
			pst.setString(3, codigoRecebido);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				object.setId_codigo_recebido(rs.getInt("id_codigo_recebido"));
				object.setMarca(rs.getString("marca"));
				object.setCodigo_recebido(rs.getString("codigo_recebido"));
			}
			
			return object;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public void updateCodigoRecebido(CodigoRecebido codigoRecebido) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE codigo_recebido "
					+ "SET marca = ?, codigo_recebido = ? "
					+ "WHERE id_codigo_recebido = ?");
			pst.setString(1, codigoRecebido.getMarca());
			pst.setString(2, codigoRecebido.getCodigo_recebido());
			pst.setInt(3, codigoRecebido.getId_codigo_recebido());

			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DefaultComboBoxModel comboBoxCodigoRecebido(DefaultComboBoxModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_recebido where id_produto = ?");
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();
			model.addElement("");
			while (rs.next()) {
				model.addElement(rs.getString("codigo_recebido"));
			}
			return model;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DefaultComboBoxModel comboBoxMarca(DefaultComboBoxModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_recebido where id_produto = ?");
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();
			model.addElement("");
			while (rs.next()) {
				model.addElement(rs.getString("marca"));
			}
			return model;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA PARA A TABELA DEQUIVALÊNCIA
	@Override
	public DefaultTableModel tableEquivalenciaCodRecebido(DefaultTableModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("REF");
		model.addColumn("MARCA");
		model.addColumn("COD.RECEBIDO");

		try {
			pst = conn.prepareStatement(
					"SELECT * FROM codigo_recebido WHERE id_produto = ?");

			pst.setInt(1, id_produto);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { 
						rs.getString("id_codigo_recebido"),
						rs.getString("marca"), 
						rs.getString("codigo_recebido")});
			}
			return model;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
}
