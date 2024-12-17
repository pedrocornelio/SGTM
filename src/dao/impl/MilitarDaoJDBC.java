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

import javax.swing.JOptionPane;

import dao.MilitarDao;
import db.DB;
import db.DbException;
import entities.Militar;

public class MilitarDaoJDBC implements MilitarDao{
	
	private Connection conn;
	
	private Militar instantiateMilitar(ResultSet rs) throws SQLException {
		Militar militar = new Militar();
		militar.setId_militar(rs.getInt("id_militar"));
		militar.setNome(rs.getString("nome"));
		militar.setnBM(rs.getString("nBM"));
		return militar;
	};

	public MilitarDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Militar obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO militar (nome, nBM) VALUES "
					+ "(?, ?)");
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getnBM());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public Militar findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM militar WHERE id_militar = ?");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				Militar obj = instantiateMilitar(rs);
				return obj;
			} else {
				return null;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO DE ACESSO AO BANCO DE DADOS", "ERRO DE ACESSO", 0);
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Militar findByNBM(String nbm) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM militar WHERE nbm = ?");
			pst.setString(1, nbm);
			rs = pst.executeQuery();
			if (rs.next()) {
				Militar obj = instantiateMilitar(rs);
				return obj;
			} else {
				return null;
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "ERRO DE ACESSO AO BANCO DE DADOS");
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Militar findByName(String nome) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM militar WHERE nome = ?");
			pst.setString(1, nome);
			rs = pst.executeQuery();
			if (rs.next()) {
				Militar obj = instantiateMilitar(rs);
				return obj;
			} else {
				return null;
			}
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "ERRO DE ACESSO AO BANCO DE DADOS");
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

}
