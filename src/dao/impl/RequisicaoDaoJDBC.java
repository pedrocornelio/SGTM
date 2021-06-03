/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.RequisicaoDao;
import db.DB;
import db.DbException;
import entities.Localizacao;
import entities.Login;
import entities.Medida;
import entities.Militar;
import entities.Montadora;
import entities.OrdemManutencao;
import entities.Produto;
import entities.Requisicao;
import entities.Viatura;

public class RequisicaoDaoJDBC implements RequisicaoDao {

	private Connection conn;

	public RequisicaoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Produto instantiateProduto(ResultSet rs, Localizacao localizacao, Medida medida) throws SQLException {
		Produto produto = new Produto();
		produto.setId_produto(rs.getInt("id_produto"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setLocalizacao(localizacao);
		produto.setMedida(medida);
		return produto;
	}
	
	private Localizacao instantiateLocalizacao(ResultSet rs) throws SQLException {
		Localizacao localizacao = new Localizacao();
		localizacao.setIdLocalizacao(rs.getInt("id_localizacao"));
		localizacao.setLocalizacao(rs.getString("localizacao"));
		return localizacao;
	}
	
	private Medida instantiateMedida (ResultSet rs) throws SQLException {
		Medida medida = new Medida();
		medida.setId_medida(rs.getInt("id_medida"));
		medida.setMedida(rs.getString("medida"));
		return medida;
	}
	
	private OrdemManutencao instantiateOrdemManutencao (ResultSet rs) throws SQLException {
		OrdemManutencao om = new OrdemManutencao();
		om.setId_om(rs.getInt("id_om"));
		om.setnOM(rs.getString("nOM"));
		return om;
	}
	
	private Viatura instantiateViatura (ResultSet rs, Montadora montadora) throws SQLException {
		Viatura viatura = new Viatura();
		viatura.setId_viatura(rs.getInt("id_viatura"));
		viatura.setPlaca(rs.getString("placa"));
		viatura.setAno(rs.getString("ano"));
		viatura.setChassi(rs.getString("chassi"));
		viatura.setModelo(rs.getString("modelo"));
		viatura.setMontadora(montadora);
		return viatura;
	}
	
	private Montadora instantiateMontadora(ResultSet rs) throws SQLException {
		Montadora montadora = new Montadora();
		montadora.setIdMontadora(rs.getInt("id_montadora"));
		montadora.setNomeMontadora(rs.getString("nome_montadora"));
		return montadora;
	}
	
	private Militar instantiateMilitar(ResultSet rs) throws SQLException {
		Militar militar = new Militar();
		militar.setId_militar(rs.getInt("id_login"));
		militar.setNome(rs.getString("nome"));
		militar.setnBM(rs.getString("nBM"));
		return militar;
	}

	@Override
	public void insert(Requisicao requisicao) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("INSERT INTO requisicao\r\n"
					+ "(atendido, data_requisicao, hora_requisicao, quantidade_requisicao, id_produto, id_viatura, id_om, id_militar)\r\n"
					+ "VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setBoolean(1, requisicao.getAtendido());
			pst.setDate(2, new java.sql.Date(requisicao.getData_requisicao().getTime()));
			pst.setTime(3, new java.sql.Time(requisicao.getHora_requisicao().getTime()));
			pst.setInt(4, requisicao.getQuantidade_requisicao());
			pst.setInt(5, requisicao.getProduto().getId_produto());
			pst.setInt(6, requisicao.getViatura().getId_viatura());
			pst.setInt(7, requisicao.getOM().getId_om());
			pst.setInt(8, requisicao.getMilitar().getId_militar());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				requisicao.setId_requisicao(id);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}

	}

	//UPDATE DO ATENDIMENTO
	@Override
	public void updateAtendido(Requisicao requisicao, boolean valor, Login login) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("UPDATE requisicao SET atendido = ?, id_login = ? WHERE id_requisicao = ?");
			pst.setBoolean(1, valor);
			pst.setInt(2, login.getIdlogin());
			pst.setInt(3, requisicao.getId_requisicao());
			
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	//CONSULTA DE REQUISIÇÃO
	@Override
	public Requisicao findByRequisicao(String nOM, String data, Integer codCSM) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Requisicao requisicao = new Requisicao();
		
		try {
			pst = conn.prepareStatement("SELECT * FROM requisicao AS r "
					+ "LEFT JOIN ordem_manutencao AS o "
					+ "ON r.id_om = o.id_om "
					+ "JOIN produto as p "
					+ "ON r.id_produto = p.id_produto "
					+ "JOIN localizacao as l "
					+ "ON l.id_localizacao = p.id_localizacao "
					+ "JOIN medida AS med "
					+ "ON p.id_medida = med.id_medida "
					+ "JOIN viatura AS v "
					+ "ON r.id_viatura = v.id_viatura "
					+ "JOIN montadora AS m "
					+ "ON v.id_montadora = m.id_montadora "
					+ "JOIN militar as mil "
					+ "ON mil.id_militar = r.id_militar "
					+ "WHERE o.nOM LIKE ? "
					+ "AND r.data_requisicao LIKE ? "
					+ "AND p.id_produto LIKE ?");
			pst.setString(1, nOM);
			pst.setString(2, data);
			pst.setInt(3, codCSM);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				Medida medida = instantiateMedida(rs);
				Localizacao localizacao = instantiateLocalizacao(rs);
				OrdemManutencao ordemManutencao = instantiateOrdemManutencao(rs);
				Produto produto = instantiateProduto(rs, localizacao, medida);
				Montadora montadora = instantiateMontadora(rs);
				Viatura viatura = instantiateViatura(rs, montadora);
				Militar militar = instantiateMilitar(rs);
				requisicao.setId_requisicao(rs.getInt("id_requisicao"));
				requisicao.setData_requisicao(rs.getDate("data_requisicao"));
				requisicao.setHora_requisicao(rs.getTime("hora_requisicao"));
				requisicao.setQuantidade_requisicao(rs.getInt("quantidade_requisicao"));
				requisicao.setOM(ordemManutencao);
				requisicao.setproduto(produto);
				requisicao.setViatura(viatura);
				requisicao.setMilitar(militar);
			}
			
			
			return requisicao;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	// CONSULTA PARA A TABELA DE REQUISIÇÃO
		@Override
		public DefaultTableModel tableRequisicao(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			model.addColumn("ATENDIDO");
			model.addColumn("NºOM");
			model.addColumn("DATA");
			model.addColumn("CSM");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA RECEBIDA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("LOCAL");
			model.addColumn("QUANT");
			model.addColumn("");
			model.addColumn("SOLICITANTE");
			model.addColumn("REF");

			try {

				pst = conn.prepareStatement("SELECT req.id_requisicao, om.nOM 'NºOM', req.atendido 'ATENDIDO', date_format(req.data_requisicao,'%d/%m/%Y') 'DATA', req.id_produto 'COD.CSM', p.descricao 'DESCRICAO', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA RECEBIDA', cr.CODIGORECEBIDO 'COD.RECEBIDO', loc.localizacao 'LOCAL', req.quantidade_requisicao 'QUANTIDADE', med.medida 'MEDIDA', m.nome 'SOLICITANTE'\r\n"
						+ "FROM requisicao AS req\r\n"
						+ "	JOIN produto AS p\r\n"
						+ "	ON req.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora) 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n"
						+ "	on cm.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n"
						+ "	on cr.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN login as log\r\n"
						+ "	ON req.id_login = log.id_login\r\n"
						+ "	JOIN localizacao as loc\r\n"
						+ "	ON loc.id_localizacao = p.id_localizacao\r\n"
						+ "	LEFT JOIN ordem_manutencao as om\r\n"
						+ "	ON om.id_om = req.id_om\r\n"
						+ "	LEFT JOIN medida as med\r\n"
						+ "	ON p.id_medida = med.id_medida\r\n"
						+ "	JOIN militar AS m\r\n"
						+ "	ON m.id_militar = req.id_militar"
						+ "	WHERE atendido = 0");
				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] {
							rs.getBoolean("ATENDIDO"),
							rs.getString("NºOM"),
							rs.getString("DATA"),
							rs.getString("COD.CSM"),
							rs.getString("DESCRICAO"),
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA RECEBIDA"),
							rs.getString("COD.RECEBIDO"),	
							rs.getString("LOCAL"),
							rs.getInt("QUANTIDADE"),
							rs.getString("MEDIDA"),
							rs.getString("SOLICITANTE"),
							rs.getInt("req.id_requisicao")
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
	
	// CONSULTA PARA A TABELA DE BUSCA DE REQUISIÇÃO
	@Override
	public DefaultTableModel tableBuscarRequisicao(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("NºOM");
		model.addColumn("DATA");
		model.addColumn("CSM");
		model.addColumn("DESCRIÇÃO");
		model.addColumn("LOCAL");
		model.addColumn("QUANTIDADE");
		model.addColumn("");
		model.addColumn("SOLICITANTE");

		try {

			pst = conn.prepareStatement("SELECT  om.nOM 'NºOM', req.atendido 'ATENDIDO', date_format(req.data_requisicao,'%d/%m/%Y') 'DATA', req.id_produto 'COD.CSM', p.descricao 'DESCRICAO', loc.localizacao 'LOCAL', req.quantidade_requisicao 'QUANTIDADE', med.medida 'MEDIDA', m.nome 'SOLICITANTE'\r\n" + 
					"	FROM requisicao AS req\r\n" + 
					"		JOIN produto AS p\r\n" + 
					"        ON req.id_produto = p.id_produto\r\n" + 
					"			LEFT JOIN login as log\r\n" + 
					"            ON req.id_login = log.id_login\r\n" + 
					"				JOIN localizacao as loc\r\n" + 
					"                ON loc.id_localizacao = p.id_localizacao\r\n" + 
					"					LEFT JOIN ordem_manutencao as om\r\n" + 
					"                    ON om.id_om = req.id_om\r\n" + 
					"                    LEFT JOIN medida as med\r\n" + 
					"                    ON p.id_medida = med.id_medida\r\n" + 
					"                    LEFT JOIN militar AS m\r\n" + 
					"                    ON m.id_militar = req.id_militar\r\n" + 
					"						WHERE atendido = 0");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("NºOM"),
						rs.getString("DATA"),
						rs.getString("COD.CSM"),
						rs.getString("DESCRICAO"),
						rs.getString("LOCAL"),
						rs.getInt("QUANTIDADE"),
						rs.getString("MEDIDA"),
						rs.getString("SOLICITANTE")
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
	
	
	// CONSULTA PARA A TABELA DE EDIÇÃO DE REQUISIÇÃO
		@Override
		public DefaultTableModel tableExcluirRequisicao(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			model.addColumn("NºOM");
			model.addColumn("DATA");
			model.addColumn("CSM");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA RECEBIDA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("QUANT");
			model.addColumn("");
			model.addColumn("SOLICITANTE");

			try {

				pst = conn.prepareStatement("SELECT  om.nOM 'NºOM', req.atendido 'ATENDIDO', date_format(req.data_requisicao,'%d/%m/%Y') 'DATA', req.id_produto 'COD.CSM', p.descricao 'DESCRICAO', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA RECEBIDA', cr.CODIGORECEBIDO 'COD.RECEBIDO', loc.localizacao 'LOCAL', req.quantidade_requisicao 'QUANTIDADE', med.medida 'MEDIDA', m.nome 'SOLICITANTE'\r\n"
						+ "FROM requisicao AS req\r\n"
						+ "	JOIN produto AS p\r\n"
						+ "	ON req.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora) 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n"
						+ "	on cm.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n"
						+ "	on cr.id_produto = p.id_produto\r\n"
						+ "	LEFT JOIN login as log\r\n"
						+ "	ON req.id_login = log.id_login\r\n"
						+ "	JOIN localizacao as loc\r\n"
						+ "	ON loc.id_localizacao = p.id_localizacao\r\n"
						+ "	LEFT JOIN ordem_manutencao as om\r\n"
						+ "	ON om.id_om = req.id_om\r\n"
						+ "	LEFT JOIN medida as med\r\n"
						+ "	ON p.id_medida = med.id_medida\r\n"
						+ "	JOIN militar AS m\r\n"
						+ "	ON m.id_militar = req.id_militar"
						+ "	WHERE atendido = 0");
				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] {
							rs.getString("NºOM"),
							rs.getString("DATA"),
							rs.getString("COD.CSM"),
							rs.getString("DESCRICAO"),
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA RECEBIDA"),
							rs.getString("COD.RECEBIDO"),	
							rs.getInt("QUANTIDADE"),
							rs.getString("MEDIDA"),
							rs.getString("SOLICITANTE")
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

		//RETIRAR REQUISIÇÃO DE ITEM
		@Override
		public void deleteRequisicao(Requisicao requisicao) {
			PreparedStatement pst = null;
			
			try {
				pst = conn.prepareStatement("DELETE FROM requisicao WHERE id_requisicao = ? ");
				pst.setInt(1, requisicao.getId_requisicao());
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeStatement(pst);
			}
		}
		
		// CONSULTA DE PRODUTO PARA REQUISIÇÃO DE ITEM EXISTENTE
		@Override
		public DefaultTableModel tableProdutoRequisicao(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("LOCAL");
			model.addColumn("CSM");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("MONTADORA");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("QUANT");
			model.addColumn("APLICAÇÃO");

			try {
				pst = conn.prepareStatement(
						"SELECT l.localizacao 'LOCAL', p.id_produto 'COD.CSM', p.descricao 'DESCRICAO', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.quantidade'QUANT', med.medida 'MEDIDA', app.APLICACAO\r\n" 
								+ "FROM produto AS p\r\n"
								+ "	LEFT JOIN (SELECT p.*, group_concat(trim(app.modelo),' ' ,app.ano) 'APLICACAO' FROM aplicacao AS app, produto AS p WHERE app.id_produto = p.id_produto GROUP BY app.id_produto) as app\r\n"  
								+ "	ON p.id_produto = app.id_produto\r\n" 
								+ "	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n"  
								+ "	ON cm.id_produto = p.id_produto\r\n"  
								+ "	LEFT JOIN montadora AS m\r\n" 
								+ "	ON m.id_montadora = cm.id_montadora\r\n"  
								+ "	LEFT JOIN localizacao AS l\r\n"  
								+ "	ON l.id_localizacao = p.id_localizacao\r\n"  
								+ "	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n"  
								+ "	ON cr.id_produto = p.id_produto\r\n" 
								+ "	LEFT JOIN medida AS med\r\n" 
								+ "	ON med.id_medida = p.id_medida\r\n" 
								+ "ORDER BY p.id_produto ASC");

				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { 
							rs.getString("LOCAL"),
							rs.getString("COD.CSM"),
							rs.getString("DESCRICAO"),
							rs.getString("MONTADORA"),
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA"), 
							rs.getString("COD.RECEBIDO"),
							rs.getString("QUANT"),
							rs.getString("APLICACAO") });
				}
				return model;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(pst);
				DB.closeResultSet(rs);
			}
		}

		//CONSULTA DE REQUISIÇÃO
		@Override
		public Requisicao findById(Integer idRequisicao) {
			PreparedStatement pst = null;
			ResultSet rs = null;
			Requisicao requisicao = new Requisicao();
			
			try {
				pst = conn.prepareStatement("SELECT * FROM requisicao AS r "
						+ "LEFT JOIN ordem_manutencao AS o "
						+ "ON r.id_om = o.id_om "
						+ "JOIN produto as p "
						+ "ON r.id_produto = p.id_produto "
						+ "JOIN localizacao as l "
						+ "ON l.id_localizacao = p.id_localizacao "
						+ "JOIN medida AS med "
						+ "ON p.id_medida = med.id_medida "
						+ "JOIN viatura AS v "
						+ "ON r.id_viatura = v.id_viatura "
						+ "JOIN montadora AS m "
						+ "ON v.id_montadora = m.id_montadora "
						+ "JOIN militar as mil "
						+ "ON mil.id_militar = r.id_militar "
						+ "WHERE id_requisicao = ?");
				pst.setInt(1, idRequisicao);
				
				rs = pst.executeQuery();
				
				if (rs.next()) {
					Medida medida = instantiateMedida(rs);
					Localizacao localizacao = instantiateLocalizacao(rs);
					OrdemManutencao ordemManutencao = instantiateOrdemManutencao(rs);
					Produto produto = instantiateProduto(rs, localizacao, medida);
					Montadora montadora = instantiateMontadora(rs);
					Viatura viatura = instantiateViatura(rs, montadora);
					Militar militar = instantiateMilitar(rs);
					requisicao.setId_requisicao(rs.getInt("id_requisicao"));
					requisicao.setData_requisicao(rs.getDate("data_requisicao"));
					requisicao.setHora_requisicao(rs.getTime("hora_requisicao"));
					requisicao.setQuantidade_requisicao(rs.getInt("quantidade_requisicao"));
					requisicao.setOM(ordemManutencao);
					requisicao.setproduto(produto);
					requisicao.setViatura(viatura);
					requisicao.setMilitar(militar);
				}
				
				
				return requisicao;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(pst);
				DB.closeResultSet(rs);
			}
		}

}