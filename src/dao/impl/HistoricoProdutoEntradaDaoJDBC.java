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

import dao.HistoricoProdutoEntradaDao;
import db.DB;
import db.DbException;
import entities.HistoricoProdutoEntrada;

public class HistoricoProdutoEntradaDaoJDBC implements HistoricoProdutoEntradaDao {

	private Connection conn;

	public HistoricoProdutoEntradaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	//INSERIR HISTORICO DE ENTRADA DE PRODUTO
	@Override
	public void insert(HistoricoProdutoEntrada historicoProdutoEntrada) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("INSERT INTO historico_produto_entrada (data_entrada, hora_entrada, quantidade_entrada, id_produto, id_login, id_fornecedor) VALUE (?,?,?,?,?,?)");
			pst.setDate(1, new java.sql.Date(historicoProdutoEntrada.getData_entrada().getTime()));
			pst.setTime(2, new java.sql.Time(historicoProdutoEntrada.getHora_entrada().getTime()));
			pst.setInt(3, historicoProdutoEntrada.getQuantidade_entrada());
			pst.setInt(4, historicoProdutoEntrada.getProduto().getId_produto());
			pst.setInt(5, historicoProdutoEntrada.getLogin().getIdlogin());
			pst.setInt(6, historicoProdutoEntrada.getFornecedor().getId_fornecedor());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}

	}
	
	// CONSULTA PARA A TABELA DE HISTÓRICO DE ENTRADA DE PRODUTO
	// ORDENADO POR DATA E LIMITADO A 30 DIAS
	@Override
	public DefaultTableModel tableHistoricoProdutoEntrada(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("DATA");
		model.addColumn("ALMOXARIFE");
		model.addColumn("NºBM");
		model.addColumn("CSM");
		model.addColumn("MONTADORA");
		model.addColumn("COD.MONTADORA");
		model.addColumn("MARCA");
		model.addColumn("COD.RECEBIDO");
		model.addColumn("DESCRIÇÃO");
		model.addColumn("QUANT");
		model.addColumn("FORNECEDOR");
		model.addColumn("NOTA FISCAL");
		model.addColumn("ORÇAMENTO");

		try {
			pst = conn.prepareStatement("SELECT date_format(hpe.data_entrada,'%d/%m/%Y')'DATA', l.nome 'ALMOXARIFE', l.nBM 'NºBM', p.id_produto 'COD.CSM', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.descricao 'DESCRIÇÃO', hpe.quantidade_entrada 'QUANTIDADE', med.medida ' ', f.fornecedor 'FORNECEDOR', f.nota_fiscal_fornecedor 'NOTA FISCAL', f.orcamento 'ORÇAMENTO'\r\n" + 
					"FROM produto AS p\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN medida as med\r\n" + 
					"	on med.id_medida = p.id_medida\r\n" + 
					"	JOIN historico_produto_entrada as hpe\r\n" + 
					"	ON hpe.id_produto = p.id_produto\r\n" + 
					"	LEFT JOIN fornecedor AS f \r\n" + 
					"	ON f.id_fornecedor = hpe.id_fornecedor\r\n" + 
					"	JOIN login AS l\r\n" + 
					"	ON l.id_login = hpe.id_login\r\n" + 
					"WHERE hpe.data_entrada BETWEEN DATE_ADD(CURRENT_DATE, INTERVAL -30 DAY) AND CURRENT_DATE\r\n" + 
					"ORDER BY hpe.data_entrada DESC, hpe.hora_entrada DESC;");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("DATA"), 
						rs.getString("ALMOXARIFE"), 
						rs.getString("NºBM"),
						rs.getString("COD.CSM"),
						rs.getString("MONTADORA"), 
						rs.getString("COD.MONTADORA"), 
						rs.getString("MARCA"),
						rs.getString("COD.RECEBIDO"), 
						rs.getString("DESCRIÇÃO"), 
						rs.getString("QUANTIDADE"),
						rs.getString("FORNECEDOR"), 
						rs.getString("NOTA FISCAL"), 
						rs.getString("ORÇAMENTO") });
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
	public DefaultTableModel tableHistoricoEntradaPesquisa(DefaultTableModel model, String dataInicio, String dataFim) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("DATA");
		model.addColumn("ALMOXARIFE");
		model.addColumn("NºBM");
		model.addColumn("CSM");
		model.addColumn("MONTADORA");
		model.addColumn("COD.MONTADORA");
		model.addColumn("MARCA");
		model.addColumn("COD.RECEBIDO");
		model.addColumn("DESCRIÇÃO");
		model.addColumn("QUANT");
		model.addColumn("FORNECEDOR");
		model.addColumn("NOTA FISCAL");
		model.addColumn("ORÇAMENTO");

		try {
			pst = conn.prepareStatement("SELECT date_format(hpe.data_entrada,'%d/%m/%Y')'DATA', l.nome 'ALMOXARIFE', l.nBM 'NºBM', p.id_produto 'COD.CSM', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.descricao 'DESCRIÇÃO', hpe.quantidade_entrada 'QUANTIDADE', med.medida ' ', f.fornecedor 'FORNECEDOR', f.nota_fiscal_fornecedor 'NOTA FISCAL', f.orcamento 'ORÇAMENTO'\r\n" + 
					"FROM produto AS p\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN medida as med\r\n" + 
					"	on med.id_medida = p.id_medida\r\n" + 
					"	JOIN historico_produto_entrada as hpe\r\n" + 
					"	ON hpe.id_produto = p.id_produto\r\n" + 
					"	LEFT JOIN fornecedor AS f \r\n" + 
					"	ON f.id_fornecedor = hpe.id_fornecedor\r\n" + 
					"	JOIN login AS l\r\n" + 
					"	ON l.id_login = hpe.id_login\r\n" + 
					"WHERE hpe.data_entrada BETWEEN ? AND ?\r\n" + 
					"ORDER BY hpe.data_entrada DESC, hpe.hora_entrada DESC;\r\n" + 
					"");
			
			pst.setString(1, dataInicio);
			pst.setString(2, dataFim);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("DATA"), 
						rs.getString("ALMOXARIFE"), 
						rs.getString("NºBM"),
						rs.getString("COD.CSM"),
						rs.getString("MONTADORA"), 
						rs.getString("COD.MONTADORA"), 
						rs.getString("MARCA"),
						rs.getString("COD.RECEBIDO"), 
						rs.getString("DESCRIÇÃO"), 
						rs.getString("QUANTIDADE"),
						rs.getString("FORNECEDOR"), 
						rs.getString("NOTA FISCAL"), 
						rs.getString("ORÇAMENTO") });
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
