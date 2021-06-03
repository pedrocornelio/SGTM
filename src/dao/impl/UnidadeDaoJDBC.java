package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import dao.UnidadeDao;
import db.DB;
import db.DbException;
import entities.Unidade;

public class UnidadeDaoJDBC implements UnidadeDao {

	private Connection conn;

	public UnidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Unidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Unidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Unidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll(Unidade obj) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Unidade findByUnidade(String unidade) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Unidade obj = new Unidade();

		try {
			pst = conn.prepareStatement("SELECT * FROM unidade where unidade = ?");
			pst.setString(1, unidade);
			rs = pst.executeQuery();
			while (rs.next()) {
				obj.setIdUnidade(rs.getInt("id_unidade"));
				obj.setNomeUnidade(rs.getString("unidade"));
				obj.setCodigoUndiade(rs.getString("codigo_unidade"));
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DefaultComboBoxModel comboBoxUnidade(DefaultComboBoxModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT unidade FROM unidade ORDER BY unidade ASC");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addElement(rs.getString("unidade"));
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
