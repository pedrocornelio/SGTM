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

import dao.AplicacaoDao;
import db.DB;
import db.DbException;
import entities.Aplicacao;

public class AplicacaoDaoJDBC implements AplicacaoDao {
	
	private Connection conn;
	
	public AplicacaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	//INSERIR DADOS DA APLICA플O DO ITEM
	@Override
	public void insert(Aplicacao aplicacao) {
		PreparedStatement pst = null;
		try {  
			pst = conn.prepareStatement("INSERT INTO aplicacao (modelo,ano,id_montadora,id_produto) VALUE (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, aplicacao.getModelo());
			pst.setString(2, aplicacao.getAno());
			pst.setInt(3, aplicacao.getMontadora().getIdMontadora());
			pst.setInt(4, aplicacao.getProduto().getId_produto());
					
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			DB.closeStatement(pst);
			
		}
		
	}

	@Override
	public void update(Aplicacao obj) {
		// TODO Auto-generated method stub
		
	}
	
	//RETIRAR DADOS DA APLICA플O DO ITEM J� EXISTENTE
	@Override
	public void delete(Integer id_montadora, String modelo, String ano) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("DELETE FROM aplicacao WHERE id_montadora = ? AND modelo = ? AND ano = ?");
			pst.setInt(1, id_montadora);
			pst.setString(2, modelo);
			pst.setString(3, ano);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
		
	}
	
	//RETIRAR DADOS DA APLICA플O DO ITEM NOVO
	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM aplicacao WHERE id_aplicacao=?");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
		
	}
	
	@Override
	public void findAll(Aplicacao obj) {
		// TODO Auto-generated method stub
		
	}
	
	
	//CONSULTA PARA A TABELA DA APLICA플O
	@Override
	public DefaultTableModel tableAplicacao (DefaultTableModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("MONTADORA");
		model.addColumn("MODELO");
		model.addColumn("ANO");
			
		try {
			pst = conn.prepareStatement("SELECT m.nome_montadora 'MONTADORA', a.modelo 'MODELO', a.ano 'ANO', a.id_produto FROM aplicacao as a\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"		JOIN produto as p\r\n" + 
					"			WHERE a.id_montadora = m.id_montadora AND a.id_produto = p.id_produto AND a.id_produto = ?");
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();		
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("MONTADORA"),
						rs.getString("MODELO"),
						rs.getString("ANO"),
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
