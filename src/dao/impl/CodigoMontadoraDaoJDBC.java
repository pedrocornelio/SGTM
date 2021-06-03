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

import dao.CodigoMontadoraDao;
import db.DB;
import db.DbException;
import entities.CodigoMontadora;
import entities.Montadora;
import entities.Produto;

public class CodigoMontadoraDaoJDBC implements CodigoMontadoraDao {

	private Connection conn;
	
	public CodigoMontadoraDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(CodigoMontadora codigoMontadora, Montadora montadora, Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO codigo_montadora"
					+ "(codigo_montadora, id_montadora, id_produto) VALUE (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, codigoMontadora.getCodigo_montadora());
			pst.setInt(2, montadora.getIdMontadora());
			pst.setInt(3, produto.getId_produto());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				codigoMontadora.setId_codigo_montadora(id);
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
	public CodigoMontadora findCodigoMontadora (Integer idProduto, String codigoMontadora) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		CodigoMontadora object = new CodigoMontadora();

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_montadora where id_produto = ? and codigo_montadora = ?");
			
			pst.setInt(1, idProduto);
			pst.setString(2, codigoMontadora);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				object.setId_codigo_montadora(rs.getInt("id_codigo_montadora"));
				object.setCodigo_montadora(rs.getString("codigo_montadora"));
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
	public CodigoMontadora findCodigoMontadoraNull (Integer idProduto) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		CodigoMontadora object = new CodigoMontadora();

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_montadora where id_produto = ? and codigo_montadora is null");
			
			pst.setInt(1, idProduto);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				object.setId_codigo_montadora(rs.getInt("id_codigo_montadora"));
				object.setCodigo_montadora(rs.getString("codigo_montadora"));
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
	public void updateCodigoMontadora(CodigoMontadora codigoMontadora) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE codigo_montadora "
					+ "SET codigo_montadora = ?, id_montadora = ? "
					+ "WHERE id_codigo_montadora = ?");
			pst.setString(1, codigoMontadora.getCodigo_montadora());
			pst.setInt(2, codigoMontadora.getMontadora().getIdMontadora());
			pst.setInt(3, codigoMontadora.getId_codigo_montadora());

			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DefaultComboBoxModel comboBoxCodigoMontadora(DefaultComboBoxModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM codigo_montadora where id_produto = ?");
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();
			model.addElement("");
			while (rs.next()) {
				model.addElement(rs.getString("codigo_montadora"));
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
			public DefaultTableModel tableEquivalenciaCodMontadora(DefaultTableModel model, Integer id_produto) {
				PreparedStatement pst = null;
				ResultSet rs = null;

				model.addColumn("REF");
				model.addColumn("MONTADORA");
				model.addColumn("COD.MONTADORA");

				try {
					pst = conn.prepareStatement(
							"SELECT * FROM codigo_montadora cm\r\n" + 
							"JOIN montadora m\r\n" + 
							"ON m.id_montadora = cm.id_montadora\r\n" + 
							"WHERE id_produto = ?");

					pst.setInt(1, id_produto);
					
					rs = pst.executeQuery();
					while (rs.next()) {
						model.addRow(new Object[] { 
								rs.getString("id_codigo_montadora"),
								rs.getString("nome_montadora"), 
								rs.getString("codigo_montadora")});
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
