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


import javax.swing.table.DefaultTableModel;

import dao.HistoricoViaturaDao;
import db.DB;
import db.DbException;
import entities.HistoricoViatura;

public class HistoricoViaturaDaoJDBC implements HistoricoViaturaDao{
	
	private Connection conn;

	public HistoricoViaturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	//INSERIR HISTORICO DE VIATURA
		@Override
		public void insertHistoricoViatura(HistoricoViatura historicoViatura) {
			PreparedStatement pst = null;

			try {
				pst = conn.prepareStatement("INSERT INTO historico_viatura (data_entrada, hora_entrada, data_saida, hora_saida, id_viatura, id_unidade, id_login, id_militar) VALUE (?,?,?,?,?,?,?,?)");
				pst.setDate(1, new java.sql.Date(historicoViatura.getData_entrada().getTime()));
				pst.setTime(2, new java.sql.Time(historicoViatura.getHora_entrada().getTime()));
				pst.setDate(3, new java.sql.Date(historicoViatura.getData_saida().getTime()));
				pst.setTime(4, new java.sql.Time(historicoViatura.getHora_saida().getTime()));
				pst.setInt(5, historicoViatura.getViatura().getId_viatura());
				pst.setInt(6, historicoViatura.getUnidade().getIdUnidade());
				pst.setInt(7, historicoViatura.getLogin().getIdlogin());
				pst.setInt(8, historicoViatura.getMilitar().getId_militar());

				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeStatement(pst);

			}
		}
		
		//CONSULTA PARA MONTAR A TABELA DE HISTORICO DE VIATURA
		@Override
		public DefaultTableModel tableHistoricoViatura(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("DATA");

			try {
				pst = conn.prepareStatement("SELECT * FROM historico_viatura;");
				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] {
							rs.getString("DATA"),
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
