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

import javax.swing.DefaultComboBoxModel;

import dao.MedidaDao;
import db.DB;
import db.DbException;
import entities.Medida;

public class MedidaDaoJDBC implements MedidaDao {
	
	private Connection conn;

	public MedidaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Medida findByMedida(String medida) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Medida obj = new Medida();

		try {
			pst = conn.prepareStatement("SELECT * FROM medida WHERE medida = ?");
			pst.setString(1, medida);
			rs = pst.executeQuery();
			if (rs.next()) {
				obj.setId_medida(rs.getInt("id_medida"));
				obj.setMedida(rs.getString("medida"));
			}
			return obj;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}

	}
	
	@Override
	public Medida findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Medida obj = new Medida();

		try {
			pst = conn.prepareStatement("SELECT * FROM medida WHERE id_medida = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				obj.setId_medida(rs.getInt("id_medida"));
				obj.setMedida(rs.getString("medida"));
			}
			return obj;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DefaultComboBoxModel comboBoxMedida(DefaultComboBoxModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT DISTINCT medida FROM medida;");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addElement(rs.getString("medida"));	
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
