package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import dao.ServicoDao;
import db.DB;
import db.DbException;
import entities.Servico;

public class ServicoDaoJDBC implements ServicoDao {

	private Connection conn;

	public ServicoDaoJDBC (Connection conn) {
		this.conn = conn;
	}
	
	// CRIAÇÃO DE UM SERVIÇO NOVO
	@Override
	public void insert(Servico servico) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO servico"
					+ "(servico, id_equipe_servico, id_requisicao, id_tipo_servico)"
					+ "VALUE (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, servico.getServico());
			pst.setInt(2, servico.getEquipe_servico().getId_equipe_servico());
			pst.setInt(3, servico.getRequisicao().getId_requisicao());
			pst.setInt(4, servico.getTipoServico().getId_tipo_servico());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				servico.setId_servico(id);
			} else {
				throw new DbException("ERRO DE LANÇAMENTO DE SERVIÇO!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public void updateServico(Servico servico) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE servico"
					+ "SET servico = ?");
			pst.setString(1, servico.getServico());
			pst.setInt(2, servico.getEquipe_servico().getId_equipe_servico());
			pst.setInt(3, servico.getRequisicao().getId_requisicao());
			pst.setInt(4, servico.getTipoServico().getId_tipo_servico());

			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
		
	}

	@Override
	public void deleteServico(Servico servico) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Servico findById(Integer Servico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Servico findByServico(String nOM, String data, Integer codCSM) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultTableModel tableServico(DefaultTableModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
