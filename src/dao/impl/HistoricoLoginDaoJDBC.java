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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.HistoricoLoginDao;
import db.DB;
import db.DbException;
import entities.HistoricoLogin;
import entities.Login;

public class HistoricoLoginDaoJDBC implements HistoricoLoginDao {

	private Connection conn;

	private Login instantiateLogin(ResultSet rs) throws SQLException {
		Login login = new Login();
		login.setIdlogin(rs.getInt("id_login"));
		login.setNome(rs.getString("nome"));
		login.setnBM(rs.getString("nBM"));
		return login;
	};

	private HistoricoLogin instatiateHistoricoLogin(ResultSet rs, Login login) throws SQLException {
		HistoricoLogin historicoLogin = new HistoricoLogin();
		historicoLogin.setId_historico_login(rs.getInt("id_historico_login"));
		historicoLogin.setData_login(rs.getTime("data_login"));
		historicoLogin.setHora_login(rs.getTime("hora_login"));
		historicoLogin.setLogin(login);
		return historicoLogin;
	}

	public HistoricoLoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(HistoricoLogin historicoLogin) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO historico_login (data_login, hora_login, id_login) VALUE (?,?,?)");
			pst.setDate(1, new java.sql.Date(historicoLogin.getData_login().getTime()));
			pst.setTime(2, new java.sql.Time(historicoLogin.getHora_login().getTime()));
			pst.setInt(3, historicoLogin.getLogin().getIdlogin());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}

	}

	public List<HistoricoLogin> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(
					"SELECT l.nome, l.nBM, h.data_hora_login FROM historico_login as h INNER JOIN login as l WHERE h.id_login = l.id_login");

			rs = pst.executeQuery();

			List<HistoricoLogin> list = new ArrayList<>();
			Map<Integer, Login> map = new HashMap<>();

			while (rs.next()) {
				Login login = map.get(rs.getInt("id_login"));

				if (login == null) {
					login = instantiateLogin(rs);
					map.put(rs.getInt("id_login"), login);
				}

				HistoricoLogin historicoLogin = instatiateHistoricoLogin(rs, login);
				list.add(historicoLogin);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		return null;
	}

}
