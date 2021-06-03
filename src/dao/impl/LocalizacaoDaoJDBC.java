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

import dao.LocalizacaoDao;
import db.DB;
import db.DbException;
import entities.Localizacao;

public class LocalizacaoDaoJDBC implements LocalizacaoDao {

	private Connection conn;

	public LocalizacaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Localizacao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Localizacao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Localizacao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll(Localizacao obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Localizacao findByLocalizacao(String localizacao) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Localizacao obj = new Localizacao();

		try {
			pst = conn.prepareStatement("SELECT * FROM localizacao WHERE localizacao = ?");
			pst.setString(1, localizacao);
			rs = pst.executeQuery();
			if (rs.next()) {
				obj.setIdLocalizacao(rs.getInt("id_localizacao"));
				obj.setLocalizacao(rs.getString("localizacao"));
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
	public DefaultComboBoxModel comboBoxLocalizacao(DefaultComboBoxModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT DISTINCT localizacao FROM localizacao ORDER BY localizacao ASC;");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addElement(rs.getString("localizacao"));
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
