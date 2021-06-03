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

import dao.OrdemManutencaoDao;
import db.DB;
import db.DbException;
import entities.OrdemManutencao;

public class OrdemManutencaoDaoJDBC implements OrdemManutencaoDao {

	private Connection conn;

	public OrdemManutencaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(OrdemManutencao ordemManutencao) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO ordem_manutencao (nOM, data_inicio, hora_inicio, relato, id_militar_baixa, id_militar_triagem, id_viatura) "
					+ "VALUE (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, ordemManutencao.getnOM());
			pst.setDate(2, new java.sql.Date(ordemManutencao.getData_inicio().getTime()));
			pst.setTime(3, new java.sql.Time(ordemManutencao.getHora_inicio().getTime()));
			pst.setString(4, ordemManutencao.getRelato());
			pst.setInt(5, ordemManutencao.getMilitarBaixa().getId_militar());
			pst.setInt(6, ordemManutencao.getMilitarTriagem().getId_militar());
			pst.setInt(7, ordemManutencao.getViatura().getId_viatura());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				ordemManutencao.setId_om(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public OrdemManutencao findByNOM(String nOM) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		OrdemManutencao om = new OrdemManutencao();
		
		try {
			pst = conn.prepareStatement("SELECT * FROM ordem_manutencao WHERE nOM = ?");
			
			pst.setString(1, nOM);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				om.setId_om(rs.getInt("id_om"));
				om.setnOM(rs.getString("nOM"));
				om.setData_inicio(rs.getDate("data_inicio"));
				om.setData_fim(rs.getDate("data_inicio"));
				om.setRelato(rs.getString("relato"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		
		return om;
	}
	
	@Override
	public OrdemManutencao findByOM(OrdemManutencao ordemManutencao) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		OrdemManutencao om = new OrdemManutencao();
		
		try {
			pst = conn.prepareStatement("SELECT * FROM ordem_manutencao WHERE nOM = ?");
			
			pst.setString(1, ordemManutencao.getnOM());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				om.setId_om(rs.getInt("id_om"));
				om.setnOM(rs.getString("nOM"));
				om.setData_inicio(rs.getDate("data_inicio"));
				om.setData_fim(rs.getDate("data_inicio"));
				om.setRelato(rs.getString("relato"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		
		return om;
	}
	
	// ATUALIZAR OS DADOS DA OM PARA FECHAMENTO
	@Override
	public void updateNOM(OrdemManutencao ordemManutencao) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE ordem_manutencao "
					+ "SET data_fim = ?, hora_fim = ?, "
					+ "id_militar_liberacao = ?, id_militar_alta = ? "
					+ "WHERE id_om = ?");
			pst.setDate(1, new java.sql.Date(ordemManutencao.getData_fim().getTime()));
			pst.setTime(2, new java.sql.Time(ordemManutencao.getHora_fim().getTime()));
			pst.setInt(3, ordemManutencao.getMilitarLiberacao().getId_militar());
			pst.setInt(4, ordemManutencao.getMilitarAlta().getId_militar());
			pst.setInt(5, ordemManutencao.getId_om());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	// CONSULTA PARA A TABELA DE ORDEM DE MANUTENÇÃO
	@Override
	public DefaultTableModel tableOrdemManutencao(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("NºOM");
		model.addColumn("DATA INICIO");
		model.addColumn("PLACA");
		model.addColumn("RELATO");
		model.addColumn("BAIXA");
		model.addColumn("TRIAGEM");

		try {

			pst = conn.prepareStatement("SELECT om.id_om, om.nOM, om.data_inicio, om.relato, v.placa, mbaixa.nome 'baixa', mtriagem.nome 'triagem' FROM ordem_manutencao om\r\n"
					+ "LEFT JOIN viatura v\r\n"
					+ "ON om.id_viatura = v.id_viatura\r\n"
					+ "LEFT JOIN militar mbaixa\r\n"
					+ "ON om.id_militar_baixa = mbaixa.id_militar \r\n"
					+ "LEFT JOIN militar mtriagem\r\n"
					+ "ON om.id_militar_triagem = mtriagem.id_militar \r\n"
					+ "WHERE om.data_inicio IS NOT NULL\r\n"
					+ "AND om.data_fim IS NULL\r\n"
					+ "ORDER BY om.id_om DESC");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("nOM"),
						rs.getString("data_inicio"),
						rs.getString("placa"),
						rs.getString("relato"),
						rs.getString("baixa"),
						rs.getString("triagem")						
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
