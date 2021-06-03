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

import dao.OriginalidadeDao;
import db.DB;
import db.DbException;
import entities.Originalidade;

public class OriginalidadeDaoJDBC implements OriginalidadeDao {

	private Connection conn;

	public OriginalidadeDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Originalidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Originalidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Originalidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findAll(Originalidade obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Originalidade findByOriginalidade(String originalidade) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Originalidade original = new Originalidade();
			pst = conn.prepareStatement("SELECT * FROM originalidade WHERE tipo_originalidade = ? ");
			pst.setString(1, originalidade);
			rs = pst.executeQuery();
			if (rs.next()) {
				original.setIdOriginalidade(rs.getInt("id_originalidade"));
				original.setOriginalidade(rs.getString("tipo_originalidade"));
				return original;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Originalidade findById(Integer originalidade) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Originalidade original = new Originalidade();
			pst = conn.prepareStatement("SELECT * FROM originalidade WHERE id_originalidade = ? ");
			pst.setInt(1, originalidade);
			rs = pst.executeQuery();
			if (rs.next()) {
				original.setIdOriginalidade(rs.getInt("id_originalidade"));
				original.setOriginalidade(rs.getString("tipo_originalidade"));
				return original;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

}
