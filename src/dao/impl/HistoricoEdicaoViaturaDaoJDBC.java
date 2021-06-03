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

import javax.swing.table.DefaultTableModel;

import dao.HistoricoEdicaoViaturaDao;
import db.DB;
import db.DbException;
import entities.HistoricoEdicaoViatura;

public class HistoricoEdicaoViaturaDaoJDBC implements HistoricoEdicaoViaturaDao {
	
	private Connection conn;

	public HistoricoEdicaoViaturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(HistoricoEdicaoViatura historicoEdicaoViatura) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO historico_edicao_viatura (data_edicao, hora_edicao, placa_anterior, modelo_anterior, ano_anterior, chassi_anterior, id_montadora_anterior, id_unidade_anterior, id_viatura, id_login)"
					+ " VALUE (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, new java.sql.Date(historicoEdicaoViatura.getData_edicao().getTime()));
			pst.setTime(2, new java.sql.Time(historicoEdicaoViatura.getHora_edicao().getTime()));
			pst.setString(3, historicoEdicaoViatura.getPlaca_anterior());
			pst.setString(4, historicoEdicaoViatura.getModelo_anterior());
			pst.setString(5, historicoEdicaoViatura.getAno_anterior());
			pst.setString(6, historicoEdicaoViatura.getChassi_anterior());
			pst.setInt(7, historicoEdicaoViatura.getMontadora_anterior().getIdMontadora());
			pst.setInt(8, historicoEdicaoViatura.getUnidade_anterior().getIdUnidade());
			pst.setInt(9, historicoEdicaoViatura.getViatura().getId_viatura());
			pst.setInt(10, historicoEdicaoViatura.getLogin().getIdlogin());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				historicoEdicaoViatura.setId_historico_edicao_viatura(id);
			} else {
				throw new DbException("ERRO DE INSERÇÃO DE HISTÓRICO DE EDIÇÃO DE VIATURA!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}
	}
	
	// CONSULTA PARA A TABELA DE HISTÓRICO DE ENTRADA DE PRODUTO
	// ORDENADO POR DATA E LIMITADO A 30 DIAS
	@Override
	public DefaultTableModel tableHistoricoEdicaoViatura(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("DATA");
		model.addColumn("MILITAR");
		model.addColumn("NºBM");
		model.addColumn("DADOS ANTERIORES");
		model.addColumn("PLACA NOVA");
		model.addColumn("MODELO NOVO");
		model.addColumn("ANO NOVO");
		model.addColumn("CHASSI NOVO");
		model.addColumn("MONTADORA NOVA");
		model.addColumn("UNIDADE NOVA");

		try {
			pst = conn.prepareStatement("SELECT date_format(hev.data_edicao,'%d/%m/%Y') 'DATA', l.nome 'MILITAR', l.nBM 'NºBM', hev_concat.DADOSANTERIORES 'DADOS ANTERIORES', v.placa 'PLACA NOVA', v.modelo 'MODELO NOVO', v.ano 'ANO NOVO', v.chassi 'CHASSI NOVO', m.nome_montadora 'MONTADORA NOVA', u.unidade 'UNIDADE NOVA'\r\n"
					+ "FROM historico_edicao_viatura hev\r\n"
					+ "JOIN (SELECT id_historico_edicao_viatura, group_concat(placa_anterior, ' / ', modelo_anterior, ' / ', ano_anterior, ' / ', chassi_anterior, ' / ', m.nome_montadora, ' / ', u.unidade) 'DADOSANTERIORES' FROM historico_edicao_viatura hev JOIN montadora m ON m.id_montadora = hev.id_montadora_anterior JOIN unidade u ON u.id_unidade=hev.id_unidade_anterior GROUP BY id_historico_edicao_viatura) hev_concat\r\n"
					+ "ON hev_concat.id_historico_edicao_viatura = hev.id_historico_edicao_viatura \r\n"
					+ "JOIN viatura v\r\n"
					+ "ON v.id_viatura = hev.id_viatura\r\n"
					+ "JOIN login l\r\n"
					+ "ON l.id_login = hev.id_login\r\n"
					+ "JOIN montadora m\r\n"
					+ "ON m.id_montadora = v.id_montadora\r\n"
					+ "JOIN unidade u\r\n"
					+ "ON u.id_unidade=v.id_unidade\r\n"
					+ "WHERE hev.data_edicao BETWEEN DATE_ADD(CURRENT_DATE, INTERVAL -30 DAY) AND CURRENT_DATE\r\n"
					+ "ORDER BY hev.data_edicao DESC, hev.hora_edicao DESC;");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { 
						rs.getString("DATA"), 
						rs.getString("MILITAR"), 
						rs.getString("NºBM"),
						rs.getString("DADOS ANTERIORES"),
						rs.getString("PLACA NOVA"), 
						rs.getString("MODELO NOVO"), 
						rs.getString("ANO NOVO"),
						rs.getString("CHASSI NOVO"), 
						rs.getString("MONTADORA NOVA"), 
						rs.getString("UNIDADE NOVA"),
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
		
	// CONSULTA PARA A TABELA DE HISTÓRICO DE ENTRADA DE PRODUTO
	// ORDENADO POR DATA À ESCOLHA DO USUÁRIO
	@Override
	public DefaultTableModel tableHistoricoEdicaoViaturaPesquisa(DefaultTableModel model, String dataInicio, String dataFim) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("DATA");
		model.addColumn("MILITAR");
		model.addColumn("NºBM");
		model.addColumn("DADOS ANTERIORES");
		model.addColumn("PLACA NOVA");
		model.addColumn("MODELO NOVO");
		model.addColumn("ANO NOVO");
		model.addColumn("CHASSI NOVO");
		model.addColumn("MONTADORA NOVA");
		model.addColumn("UNIDADE NOVA");

		try {
			pst = conn.prepareStatement("SELECT date_format(hev.data_edicao,'%d/%m/%Y') 'DATA', l.nome 'MILITAR', l.nBM 'NºBM', hev_concat.DADOSANTERIORES 'DADOS ANTERIORES', v.placa 'PLACA NOVA', v.modelo 'MODELO NOVO', v.ano 'ANO NOVO', v.chassi 'CHASSI NOVO', m.nome_montadora 'MONTADORA NOVA', u.unidade 'UNIDADE NOVA'\r\n"
					+ "FROM historico_edicao_viatura hev\r\n"
					+ "JOIN (SELECT id_historico_edicao_viatura, group_concat(placa_anterior, ' / ', modelo_anterior, ' / ', ano_anterior, ' / ', chassi_anterior, ' / ', m.nome_montadora, ' / ', u.unidade) 'DADOSANTERIORES' FROM historico_edicao_viatura hev JOIN montadora m ON m.id_montadora = hev.id_montadora_anterior JOIN unidade u ON u.id_unidade=hev.id_unidade_anterior GROUP BY id_historico_edicao_viatura) hev_concat\r\n"
					+ "ON hev_concat.id_historico_edicao_viatura = hev.id_historico_edicao_viatura \r\n"
					+ "JOIN viatura v\r\n"
					+ "ON v.id_viatura = hev.id_viatura\r\n"
					+ "JOIN login l\r\n"
					+ "ON l.id_login = hev.id_login\r\n"
					+ "JOIN montadora m\r\n"
					+ "ON m.id_montadora = v.id_montadora\r\n"
					+ "JOIN unidade u\r\n"
					+ "ON u.id_unidade=v.id_unidade\r\n"
					+ "WHERE hev.data_edicao BETWEEN ? AND ?\r\n" 
					+ "ORDER BY hev.data_edicao DESC, hev.hora_edicao DESC;\r\n");
			
			pst.setString(1, dataInicio);
			pst.setString(2, dataFim);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("DATA"), 
						rs.getString("DATA"), 
						rs.getString("MILITAR"), 
						rs.getString("NºBM"),
						rs.getString("DADOS ANTERIORES"),
						rs.getString("PLACA NOVA"), 
						rs.getString("MODELO NOVO"), 
						rs.getString("ANO NOVO"),
						rs.getString("CHASSI NOVO"), 
						rs.getString("MONTADORA NOVA"), 
						rs.getString("UNIDADE NOVA"),
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
