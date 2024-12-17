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
import javax.swing.table.DefaultTableModel;

import dao.LoginDao;
import db.DB;
import db.DbException;
import entities.Login;

public class LoginDaoJDBC implements LoginDao {
	
	private Connection conn;

	private Login instantiateLogin(ResultSet rs) throws SQLException {
		Login login = new Login();
		login.setIdlogin(rs.getInt("id_login"));
		login.setNome(rs.getString("nome"));
		login.setnBM(rs.getString("nBM"));
		login.setSenha(rs.getString("senha"));
		login.setLiberarAcesso(rs.getBoolean("liberar_acesso"));
		login.setAlmoxAdmin(rs.getBoolean("almox_admin"));
		login.setAlmoxEdicao(rs.getBoolean("almox_edicao"));
		login.setAlmoxHist(rs.getBoolean("almox_hist"));
		login.setGerencia(rs.getBoolean("gerencia"));
		login.setOficina(rs.getBoolean("oficina"));
		login.setCompras(rs.getBoolean("compras"));
		return login;
	};
	
	
	public LoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Login obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO login (nome, nBM, senha, liberar_acesso, almox_hist, almox_edicao, almox_admin, oficina, gerencia, compras) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getnBM());
			pst.setString(3, obj.getSenha());
			pst.setBoolean(4, obj.getLiberarAcesso());
			pst.setBoolean(5, obj.getAlmoxHist());
			pst.setBoolean(6, obj.getAlmoxEdicao());
			pst.setBoolean(7, obj.getAlmoxAdmin());
			pst.setBoolean(8, obj.getOficina());
			pst.setBoolean(9, obj.getGerencia());
			pst.setBoolean(10, obj.getCompras());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public void update(Boolean LiberarAcesso, Boolean AlmoxHist, Boolean AlmoxEdicao, Boolean AlmoxAdmin, Boolean Oficina, Boolean Gerencia, Boolean Compras, String Senha, Login obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE login SET liberar_acesso = ?, almox_hist = ?, almox_edicao = ?, almox_admin = ?, oficina = ?, gerencia = ?, compras = ?, senha = ? WHERE id_login = ?");
			pst.setBoolean(1, LiberarAcesso);
			pst.setBoolean(2, AlmoxHist);
			pst.setBoolean(3, AlmoxEdicao);
			pst.setBoolean(4, AlmoxAdmin);
			pst.setBoolean(5, Oficina);
			pst.setBoolean(6, Gerencia);
			pst.setBoolean(7, Compras);
			pst.setString(8, Senha);
			pst.setInt(9, obj.getIdlogin());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM login WHERE id_login=?");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}

	}

	@Override
	public Login findById(Integer id) {
		return null;
	}
	
	@Override
	public Login findByNBM(String nbm) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM login WHERE nBM=?");
			pst.setString(1, nbm);
			rs = pst.executeQuery();
			if (rs.next()) {
				Login obj = instantiateLogin(rs);
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
	public Login findByNBMSenha(String nBM, String senha) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT * FROM login WHERE nBM=? and senha =?");
			pst.setString(1, nBM);
			pst.setString(2, senha);
			rs = pst.executeQuery();
			if (rs.next()) {
				Login obj = instantiateLogin(rs);
				return obj;
			} else {
				JOptionPane.showInternalMessageDialog(null, "USUÁRIO E/OU SENHA INVÁLIDO");
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
	public DefaultTableModel tableList(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("NOME");
		model.addColumn("NºBM");
		model.addColumn("SENHA");
		model.addColumn("LIBERAR ACESSO");
		model.addColumn("ALMOX HIST");
		model.addColumn("ALMOX EDIÇÂO");
		model.addColumn("ALMOX ADMIN");
		model.addColumn("OFICINA");
		model.addColumn("GERENCIA");
		model.addColumn("COMPRAS");
		
		try {
			pst = conn.prepareStatement("SELECT upper(nome), nBM, senha, liberar_acesso, almox_hist, almox_edicao , almox_admin, oficina, gerencia, compras FROM login;");
			rs = pst.executeQuery();		
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("upper(nome)"),
						rs.getString("nBM"),
						rs.getString("senha"),
						rs.getBoolean("liberar_acesso"),
						rs.getBoolean("almox_hist"),
						rs.getBoolean("almox_edicao"),
						rs.getBoolean("almox_admin"),
						rs.getBoolean("oficina"),
						rs.getBoolean("gerencia"),
						rs.getBoolean("compras")
						});
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
