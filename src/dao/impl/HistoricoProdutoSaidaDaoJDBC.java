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

import dao.HistoricoProdutoSaidaDao;
import db.DB;
import db.DbException;
import entities.HistoricoProdutoSaida;

public class HistoricoProdutoSaidaDaoJDBC implements HistoricoProdutoSaidaDao {

	private Connection conn;

	public HistoricoProdutoSaidaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	//INSERIR HISTORICO DE SAIDA DE PRODUTO POR UNIDADE
	@Override
	public void insertHistSaidaUnidade(HistoricoProdutoSaida historicoProdutoSaida) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("INSERT INTO historico_produto_saida (data_saida, hora_saida, quantidade_saida, id_login, id_produto, id_unidade, id_militar) VALUE (?,?,?,?,?,?,?)");
			pst.setDate(1, new java.sql.Date(historicoProdutoSaida.getData_saida().getTime()));
			pst.setTime(2, new java.sql.Time(historicoProdutoSaida.getHora_saida().getTime()));
			pst.setInt(3, historicoProdutoSaida.getQuantidade_saida());
			pst.setInt(4, historicoProdutoSaida.getLogin().getIdlogin());
			pst.setInt(5, historicoProdutoSaida.getProduto().getId_produto());
			pst.setInt(6, historicoProdutoSaida.getUnidade().getIdUnidade());
			pst.setInt(7, historicoProdutoSaida.getMilitar().getId_militar());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}

	}
	
	//INSERIR HISTORICO DE SAIDA DE PRODUTO POR VIATURA
		@Override
		public void insertHistSaidaViatura(HistoricoProdutoSaida historicoProdutoSaida) {
			PreparedStatement pst = null;

			try {
				pst = conn.prepareStatement("INSERT INTO historico_produto_saida (data_saida, hora_saida, quantidade_saida, id_login, id_produto, id_viatura, id_militar) VALUE (?,?,?,?,?,?,?)");
				pst.setDate(1, new java.sql.Date(historicoProdutoSaida.getData_saida().getTime()));
				pst.setTime(2, new java.sql.Time(historicoProdutoSaida.getHora_saida().getTime()));
				pst.setInt(3, historicoProdutoSaida.getQuantidade_saida());
				pst.setInt(4, historicoProdutoSaida.getLogin().getIdlogin());
				pst.setInt(5, historicoProdutoSaida.getProduto().getId_produto());
				pst.setInt(6, historicoProdutoSaida.getViatura().getId_viatura());
				pst.setInt(7, historicoProdutoSaida.getMilitar().getId_militar());

				pst.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeStatement(pst);

			}

		}
		
		// CONSULTA PARA A TABELA DE HISTÓRICO DE SAIDA DE PRODUTO
		// ORDENADO POR DATA E LIMITADO A 30 DIAS
		@Override
		public DefaultTableModel tableHistoricoProdutoSaida(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("CSM");
			model.addColumn("DATA");
			model.addColumn("REQUISITANTE");
			model.addColumn("NºBM");
			model.addColumn("ALMOXARIFE");
			model.addColumn("NºBM");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("QUANT");
			model.addColumn("");
			model.addColumn("PLACA/OBM");

			try {
				pst = conn.prepareStatement("SELECT hps.id_historico_produto_saida 'id', date_format(hps.data_saida,'%d/%m/%Y')'DATA',  mil.nome 'REQUISITANTE', mil.nBM 'NºBM', l.nome 'ALMOXARIFE', l.nBM 'NºBM', p.id_produto 'COD.CSM', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.descricao 'DESCRIÇÃO', hps.quantidade_saida 'QUANTIDADE', med.medida 'MEDIDA', COALESCE(u.unidade,v.placa) 'PLACA/OBM'\r\n" + 
						"FROM produto AS p\r\n" + 
						"	JOIN historico_produto_saida AS hps\r\n" + 
						"	ON hps.id_produto = p.id_produto\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
						"	on cr.id_produto = p.id_produto\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
						"	on cm.id_produto = p.id_produto\r\n" + 
						"	JOIN montadora as m\r\n" + 
						"	on m.id_montadora = cm.id_montadora\r\n" + 
						"	JOIN medida as med\r\n" + 
						"	on med.id_medida = p.id_medida\r\n" + 
						"	JOIN login AS l\r\n" + 
						"	ON l.id_login = hps.id_login\r\n" + 
						"	JOIN militar AS mil\r\n" + 
						"	ON mil.id_militar = hps.id_militar\r\n" + 
						"	LEFT JOIN viatura AS v\r\n" + 
						"	ON v.id_viatura = hps.id_viatura\r\n" + 
						"	LEFT JOIN unidade AS u\r\n" + 
						"	ON u.id_unidade = hps.id_unidade\r\n" + 
						"WHERE hps.data_saida BETWEEN DATE_ADD(CURRENT_DATE, INTERVAL -30 DAY) AND CURRENT_DATE\r\n" + 
						"ORDER BY hps.data_saida DESC, hps.hora_saida DESC;");
				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("COD.CSM"), 
							rs.getString("DATA"), 
							rs.getString("REQUISITANTE"), 
							rs.getString("NºBM"),
							rs.getString("ALMOXARIFE"), 
							rs.getString("NºBM"), 
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA"), 
							rs.getString("COD.RECEBIDO"), 
							rs.getString("DESCRIÇÃO"),
							rs.getString("QUANTIDADE"), 
							rs.getString("MEDIDA"), 
							//rs.getString("REQUISIÇÃO"),
							rs.getString("PLACA/OBM") });
				}
				return model;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(pst);
				DB.closeResultSet(rs);
			}
		}
		
		// CONSULTA PARA A TABELA DE HISTÓRICO DE SAIDA DE PRODUTO
		// ORDENADO POR DATA À ESCOLHA DO USUÁRIO
		@Override
		public DefaultTableModel tableHistoricoSaidaPesquisa(DefaultTableModel model, String dataInicio, String dataFim) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("CSM");
			model.addColumn("DATA");
			model.addColumn("REQUISITANTE");
			model.addColumn("NºBM");
			model.addColumn("ALMOXARIFE");
			model.addColumn("NºBM");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("QUANT");
			model.addColumn("");
			model.addColumn("PLACA/OBM");

			try {
				pst = conn.prepareStatement("SELECT hps.id_historico_produto_saida 'id', date_format(hps.data_saida,'%d/%m/%Y')'DATA',  mil.nome 'REQUISITANTE', mil.nBM 'NºBM', l.nome 'ALMOXARIFE', l.nBM 'NºBM', p.id_produto 'COD.CSM', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.descricao 'DESCRIÇÃO', hps.quantidade_saida 'QUANTIDADE', med.medida 'MEDIDA', COALESCE(u.unidade,v.placa) 'PLACA/OBM'\r\n" + 
						"FROM produto AS p\r\n" + 
						"	JOIN historico_produto_saida AS hps\r\n" + 
						"	ON hps.id_produto = p.id_produto\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
						"	on cr.id_produto = p.id_produto\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
						"	on cm.id_produto = p.id_produto\r\n" + 
						"	JOIN montadora as m\r\n" + 
						"	on m.id_montadora = cm.id_montadora\r\n" + 
						"	JOIN medida as med\r\n" + 
						"	on med.id_medida = p.id_medida\r\n" + 
						"	JOIN login AS l\r\n" + 
						"	ON l.id_login = hps.id_login\r\n" + 
						"	JOIN militar AS mil\r\n" + 
						"	ON mil.id_militar = hps.id_militar\r\n" + 
						"	LEFT JOIN viatura AS v\r\n" + 
						"	ON v.id_viatura = hps.id_viatura\r\n" + 
						"	LEFT JOIN unidade AS u\r\n" + 
						"	ON u.id_unidade = hps.id_unidade\r\n" + 
						"WHERE hps.data_saida BETWEEN ? AND ?\r\n" + 
						"ORDER BY hps.data_saida DESC, hps.data_saida DESC;");
				
				pst.setString(1, dataInicio);
				pst.setString(2, dataFim);
				
				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("COD.CSM"), 
							rs.getString("DATA"), 
							rs.getString("REQUISITANTE"), 
							rs.getString("NºBM"),
							rs.getString("ALMOXARIFE"), 
							rs.getString("NºBM"), 
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA"), 
							rs.getString("COD.RECEBIDO"), 
							rs.getString("DESCRIÇÃO"),
							rs.getString("QUANTIDADE"), 
							rs.getString("MEDIDA"), 
							//rs.getString("REQUISIÇÃO"),
							rs.getString("PLACA/OBM") });
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
