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

import dao.HistoricoEdicaoProdutoDao;
import db.DB;
import db.DbException;
import entities.HistoricoEdicaoProduto;

public class HistoricoEdicaoProdutoDaoJDBC implements HistoricoEdicaoProdutoDao {
	
	private Connection conn;

	public HistoricoEdicaoProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insertDescOriginalMedida(HistoricoEdicaoProduto historicoEdicaoProduto) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO historico_edicao_produto "
					+ "(data_edicao, hora_edicao, descricao_anterior, id_produto_anterior, id_originalidade_anterior, id_medida_anterior, id_login)"
					+ " VALUE (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, new java.sql.Date(historicoEdicaoProduto.getData_edicao().getTime()));
			pst.setTime(2, new java.sql.Time(historicoEdicaoProduto.getHora_edicao().getTime()));
			pst.setString(3, historicoEdicaoProduto.getDescricao_anterior());
			pst.setInt(4, historicoEdicaoProduto.getId_produto_anterior().getId_produto());
			pst.setInt(5, historicoEdicaoProduto.getId_originalidade_anterior().getIdOriginalidade());
			pst.setInt(6, historicoEdicaoProduto.getId_medida_anterior().getId_medida());
			pst.setInt(7, historicoEdicaoProduto.getId_login().getIdlogin());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				historicoEdicaoProduto.setId_historico_edicao_produto(id);
			} else {
				throw new DbException("ERRO DE INSERÇÃO DE HISTÓRICO DE EDIÇÃO DE PRODUTO!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}
	}
	
	@Override
	public void insertFornecedor(HistoricoEdicaoProduto historicoEdicaoProduto) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO historico_edicao_produto "
					+ "(data_edicao, hora_edicao, descricao_anterior, id_originalidade_anterior, id_medida_anterior, id_codigo_montadora_anterior, id_codigo_recebido_anterior, id_fornecedor_anterior, id_login)"
					+ " VALUE (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setDate(1, new java.sql.Date(historicoEdicaoProduto.getData_edicao().getTime()));
			pst.setTime(2, new java.sql.Time(historicoEdicaoProduto.getHora_edicao().getTime()));
			pst.setString(3, historicoEdicaoProduto.getDescricao_anterior());
			pst.setInt(4, historicoEdicaoProduto.getId_originalidade_anterior().getIdOriginalidade());
			pst.setInt(5, historicoEdicaoProduto.getId_medida_anterior().getId_medida());
			pst.setInt(6, historicoEdicaoProduto.getId_codigo_montadora_anterior().getId_codigo_montadora());
			pst.setInt(7, historicoEdicaoProduto.getId_codigo_recebido_anterior().getId_codigo_recebido());
			pst.setInt(8, historicoEdicaoProduto.getId_fornecedor_anterior().getId_fornecedor());
			pst.setInt(9, historicoEdicaoProduto.getId_login().getIdlogin());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				historicoEdicaoProduto.setId_historico_edicao_produto(id);
			} else {
				throw new DbException("ERRO DE INSERÇÃO DE HISTÓRICO DE EDIÇÃO DE PRODUTO!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}
	}

}
