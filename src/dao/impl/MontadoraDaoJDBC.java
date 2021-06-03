package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;

import dao.MontadoraDao;
import db.DB;
import db.DbException;
import entities.Montadora;

@SuppressWarnings("rawtypes")
public class MontadoraDaoJDBC implements MontadoraDao {

	private Connection conn;

	public MontadoraDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Montadora findByMontadora(String montadora) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Montadora obj = new Montadora();

		try {
			pst = conn.prepareStatement("SELECT * FROM montadora WHERE nome_montadora = ? ");
			pst.setString(1, montadora);
			rs = pst.executeQuery();
			while (rs.next()) {
				obj.setIdMontadora(rs.getInt("id_montadora"));
				obj.setNomeMontadora(rs.getString("nome_montadora"));
			}
			return obj;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public DefaultComboBoxModel comboBoxMontadora(DefaultComboBoxModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT nome_montadora FROM montadora ORDER BY nome_montadora ASC");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addElement(rs.getString("nome_montadora"));
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
