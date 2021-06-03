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

import dao.ProdutoDao;
import db.DB;
import db.DbException;
import entities.CodigoMontadora;
import entities.CodigoRecebido;
import entities.Localizacao;
import entities.Medida;
import entities.Montadora;
import entities.Originalidade;
import entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {

	private Connection conn;

	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	private Produto instantiateProduto(ResultSet rs, Montadora montadora, Originalidade originalidade,
			Localizacao localizacao, Medida medida) throws SQLException {
		Produto produto = new Produto();
		produto.setId_produto(rs.getInt("id_produto"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setOriginalidade(originalidade);
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setLocalizacao(localizacao);
		produto.setMedida(medida);
		return produto;
	}

	private Montadora instantiateMontadora(ResultSet rs) throws SQLException {
		Montadora montadora = new Montadora();
		montadora.setIdMontadora(rs.getInt("id_montadora"));
		montadora.setNomeMontadora(rs.getString("nome_montadora"));
		return montadora;
	}

	private Localizacao instantiateLocalizacao(ResultSet rs) throws SQLException {
		Localizacao localizacao = new Localizacao();
		localizacao.setIdLocalizacao(rs.getInt("id_localizacao"));
		localizacao.setLocalizacao(rs.getString("localizacao"));
		return localizacao;
	}

	private Originalidade instantiateOriginalidade(ResultSet rs) throws SQLException {
		Originalidade originalidade = new Originalidade();
		originalidade.setIdOriginalidade(rs.getInt("id_originalidade"));
		originalidade.setOriginalidade(rs.getString("tipo_originalidade"));
		return originalidade;
	}

	private Medida instantiateMedida(ResultSet rs) throws SQLException {
		Medida medida = new Medida();
		medida.setId_medida(rs.getInt("id_medida"));
		medida.setMedida(rs.getString("medida"));
		return medida;
	}

	/*private CodigoRecebido instantiateCodigoRecebido(ResultSet rs, Produto produto) throws SQLException {
		CodigoRecebido codRecebido = new CodigoRecebido();
		codRecebido.setId_codigo_recebido(rs.getInt("id_codigo_recebido"));
		codRecebido.setMarca(rs.getString("marca"));
		codRecebido.setCodigo_recebido(rs.getString("codigo_recebido"));
		codRecebido.setProduto(produto);
		return codRecebido;
	}

	private CodigoMontadora instantiateCodigoMontadora(ResultSet rs, Montadora montadora, Produto produto) throws SQLException {
		CodigoMontadora codMontadora = new CodigoMontadora();
		codMontadora.setId_codigo_montadora(rs.getInt("id_codigo_montadora"));
		codMontadora.setCodigo_montadora(rs.getString("codigo_montadora"));
		codMontadora.setMontadora(montadora);
		codMontadora.setProduto(produto);
		return codMontadora;

	}*/

	// CRIAÇÃO DE UM PRODUTO NOVO
	// CADA PRODUTO É CRIADO COM A QUANTIDADE MÍNIMA DE 0
	@Override
	public void insert(Produto produto, CodigoMontadora codigoMontadora, CodigoRecebido codigoRecebido, Originalidade originalidade, Localizacao localizacao, Medida medida) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO produto "
					+ "(descricao, id_originalidade, quantidade, id_localizacao, quantidade_minima, id_medida) "
					+ "VALUE (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, produto.getDescricao());
			pst.setInt(2, originalidade.getIdOriginalidade());
			pst.setInt(3, produto.getQuantidade());
			pst.setInt(4, localizacao.getIdLocalizacao());
			pst.setInt(5, produto.getQuantidade_minima());
			pst.setInt(6, medida.getId_medida());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				produto.setId_produto(id);
			} else {
				throw new DbException("ERRO DE PRODUTO!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void updateProduto(Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE produto "
					+ "SET descricao = ?, id_originalidade = ?, id_medida = ? "
					+ "WHERE id_produto = ?");
			pst.setString(1, produto.getDescricao());
			pst.setInt(2, produto.getOriginalidade().getIdOriginalidade());
			pst.setInt(3, produto.getMedida().getId_medida());
			pst.setInt(4, produto.getId_produto());

			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public void updateQuantidade(Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE produto SET quantidade = ? " + "WHERE id_produto = ?");
			pst.setInt(1, produto.getQuantidade());
			pst.setInt(2, produto.getId_produto());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void updateLocalizacao(Integer id_localizacao, Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE produto SET id_localizacao = ? WHERE id_produto = ?");
			pst.setInt(1, id_localizacao);
			pst.setInt(2, produto.getId_produto());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void updateQuantidadeMinima(Integer quantidade_minima, Produto produto) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE produto SET quantidade_minima = ? WHERE id_produto = ? ");

			pst.setInt(1, quantidade_minima);
			pst.setInt(2, produto.getId_produto());

			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public void deleteById(Produto obj) {
		// TODO Auto-generated method stub
	}

	// CONSULTA DE PRODUTO POR ID
	@Override
	public Produto findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT p.*, m.*, cm.*, cr.*, o.*, l.*, med.* FROM produto AS p\r\n" 
					+ "JOIN codigo_montadora as cm\r\n" 
					+ "	on cm.id_produto = p.id_produto\r\n" 
					+ "	JOIN montadora as m\r\n" 
					+ "	on m.id_montadora = cm.id_montadora\r\n" 
					+ "	JOIN codigo_recebido as cr\r\n"
					+ "	on cr.id_produto = p.id_produto\r\n"
					+ "	JOIN originalidade as o\r\n" 
					+ "	on o.id_originalidade = p.id_originalidade\r\n" 
					+ "	JOIN localizacao as l\r\n" 
					+ "	on l.id_localizacao = p.id_localizacao\r\n" 
					+ "	JOIN medida as med\r\n" 
					+ "	on med.id_medida = p.id_medida\r\n" 
					+ "WHERE p.id_produto = ? ");
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				Montadora montadora = instantiateMontadora(rs);
				Originalidade originalidade = instantiateOriginalidade(rs);
				Localizacao localizacao = instantiateLocalizacao(rs);
				Medida medida = instantiateMedida(rs);
				Produto produto = instantiateProduto(rs, montadora, originalidade, localizacao, medida);

				return produto;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}


	// CONSULTA DE PRODUTO POR INFORMAÇÕES
	@Override
	public Produto searchProduct(String descricao, Integer id_montadora, String cod_montadora, String marca, String cod_recebido) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT p.*, m.*, cm.*, cr.*, o.*, l.*, med.*\r\n" + 
					"FROM produto as p\r\n" + 
					"	JOIN codigo_montadora as cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	JOIN codigo_recebido as cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN originalidade as o\r\n" + 
					"	on o.id_originalidade = p.id_originalidade\r\n" +
					"	JOIN localizacao as l\r\n" +
					"	on l.id_localizacao = p.id_localizacao\r\n" +
					"	JOIN medida as med\r\n" +
					"	on med.id_medida = p.id_medida\r\n" +
					"WHERE p.descricao = ? and m.id_montadora = ? and cm.codigo_montadora = ? and cr.marca = ? or cr.codigo_recebido = ?");
			pst.setString(1, descricao);
			pst.setInt(2, id_montadora);
			pst.setString(3, cod_montadora);
			pst.setString(4, marca );
			pst.setString(5, cod_recebido );

			rs = pst.executeQuery();

			while (rs.next()) {
				Montadora montadora = instantiateMontadora(rs);
				Originalidade originalidade = instantiateOriginalidade(rs);
				Localizacao localizacao = instantiateLocalizacao(rs);
				Medida medida = instantiateMedida(rs);
				Produto produto = instantiateProduto(rs, montadora, originalidade, localizacao, medida);

				return produto;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA DE PRODUTO POR CODIGO MONTADORA
	@Override
	public Produto findByCodigoMontadora(String cod_montadora) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM produto as p \r\n" + 
					"	JOIN codigo_montadora as cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN codigo_recebido as cr\r\n" + 
					" 	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	JOIN originalidade as o\r\n" + 
					"	on o.id_originalidade = p.id_originalidade\r\n" + 
					"	JOIN localizacao as l\r\n" + 
					"	on l.id_localizacao = p.id_localizacao\r\n" +
					"	JOIN medida as med\r\n" +
					"	on med.id_medida = p.id_medida\r\n" + 
					"WHERE cm.codigo_montadora LIKE ?");
			pst.setString(1, cod_montadora);

			rs = pst.executeQuery();

			if (rs.next()) {
				Montadora montadora = instantiateMontadora(rs);
				Originalidade originalidade = instantiateOriginalidade(rs);
				Localizacao localizacao = instantiateLocalizacao(rs);
				Medida medida = instantiateMedida(rs);
				Produto produto = instantiateProduto(rs, montadora, originalidade, localizacao, medida);

				return produto;
			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA DE PRODUTO POR CODIGO RECEBIDO
	@Override
	public Produto findByCodigoRecebido(String cod_recebido) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM produto as p \r\n" + 
					"	JOIN codigo_montadora as cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN codigo_recebido as cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	JOIN originalidade as o\r\n" + 
					"	on o.id_originalidade = p.id_originalidade\r\n" + 
					"	JOIN localizacao as l\r\n" + 
					"	on l.id_localizacao = p.id_localizacao\r\n" +
					"	JOIN medida as med\r\n" +
					"	on med.id_medida = p.id_medida\r\n" +
					"WHERE cr.codigo_recebido LIKE ?");
			pst.setString(1, cod_recebido);

			rs = pst.executeQuery();

			if (rs.next()) {
				Montadora montadora = instantiateMontadora(rs);
				Originalidade originalidade = instantiateOriginalidade(rs);
				Localizacao localizacao = instantiateLocalizacao(rs);
				Medida medida = instantiateMedida(rs);
				Produto produto = instantiateProduto(rs, montadora, originalidade, localizacao, medida);

				return produto;
			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA PARA A TABELA DO ESTOQUE COM APLICAÇÃO E MEDIDA
	@Override
	public DefaultTableModel tableEstoque(DefaultTableModel model) {
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
		model.addColumn("");
		model.addColumn("APLICAÇÂO");
		model.addColumn("V");

		try {
			pst = conn.prepareStatement(
					"SELECT l.localizacao 'LOCAL', p.id_produto 'COD.CSM', p.descricao 'DESCRICAO', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.quantidade'QUANTIDADE', med.medida 'MEDIDA', app.APLICACAO, IF(p.quantidade > p.quantidade_minima, 1, IF(p.quantidade = p.quantidade_minima,0,-1)) 'VALIDADOR'\r\n" + 
					"FROM produto AS p\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(trim(app.modelo),' ' ,app.ano) 'APLICACAO' FROM aplicacao AS app, produto AS p WHERE app.id_produto = p.id_produto GROUP BY app.id_produto) as app\r\n" + 
					"	ON p.id_produto = app.id_produto\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
					"	ON p.id_produto = cm.id_produto\r\n" + 
					"	LEFT JOIN montadora AS m\r\n" + 
					"	ON m.id_montadora = cm.id_montadora\r\n" + 
					"	LEFT JOIN localizacao AS l\r\n" + 
					"	ON l.id_localizacao = p.id_localizacao\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
					"	ON cr.id_produto = p.id_produto\r\n" + 
					"	LEFT JOIN medida AS med\r\n" + 
					"	ON med.id_medida = p.id_medida\r\n" + 
					"ORDER BY l.localizacao, p.id_produto, p.descricao, m.nome_montadora ASC");

			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("LOCAL"),
						rs.getString("COD.CSM"),
						rs.getString("DESCRICAO"),
						rs.getString("MONTADORA"),
						rs.getString("COD.MONTADORA"),
						rs.getString("MARCA"), 
						rs.getString("COD.RECEBIDO"),
						rs.getInt("QUANTIDADE"), 
						rs.getString("MEDIDA"), 
						rs.getString("APLICACAO"),
						rs.getString("VALIDADOR")});
			}
			return model;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA DE PRODUTO PARA ENTRADA DE ITEM EXISTENTE
		@Override
		public DefaultTableModel tableProdutoEntrada(DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("CSM");
			model.addColumn("DESCRIÇÃO");
			model.addColumn("MONTADORA");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA");
			model.addColumn("COD.RECEBIDO");

			try {
				pst = conn.prepareStatement(
						"SELECT l.localizacao 'LOCAL', p.id_produto 'COD.CSM', p.descricao 'DESCRICAO', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA, cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.quantidade'QUANTIDADE', med.medida 'MEDIDA', app.APLICACAO\r\n" + 
						"FROM produto AS p\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(trim(app.modelo),' ' ,app.ano) 'APLICACAO' FROM aplicacao AS app, produto AS p WHERE app.id_produto = p.id_produto GROUP BY app.id_produto) as app\r\n" + 
						"	ON p.id_produto = app.id_produto\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
						"	ON p.id_produto = cm.id_produto\r\n" + 
						"	LEFT JOIN montadora AS m\r\n" + 
						"	ON m.id_montadora = cm.id_montadora\r\n" + 
						"	LEFT JOIN localizacao AS l\r\n" + 
						"	ON l.id_localizacao = p.id_localizacao\r\n" + 
						"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
						"	ON cr.id_produto = p.id_produto\r\n" + 
						"	LEFT JOIN medida AS med\r\n" + 
						"	ON med.id_medida = p.id_medida" +
						"-- ORDER BY p.id_produto ASC");

				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("COD.CSM"),
							rs.getString("DESCRICAO"),
							rs.getString("MONTADORA"),
							rs.getString("CODIGOMONTADORA"),
							rs.getString("MARCA"), 
							rs.getString("COD.RECEBIDO") });
				}
				return model;
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DB.closeStatement(pst);
				DB.closeResultSet(rs);
			}
		}
		
	// CONSULTA PARA A TABELA DA JANELA LOCALIZAÇÃO
	// ORDENADO POR LOCALIZAÇÃO, DESCRIÇÃO E MONTADORA
	@Override
	public DefaultTableModel tableLocalizacao(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("LOCAL");
		model.addColumn("CSM");
		model.addColumn("DESCRIÇÃO");
		model.addColumn("MONTADORA");
		model.addColumn("COD.MONTADORA");
		model.addColumn("MARCA");
		model.addColumn("COD.RECEBIDO");

		try {
			pst = conn.prepareStatement(
					"SELECT l.localizacao 'LOCAL', p.id_produto 'COD.CSM', p.descricao 'DESCRICAO', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.quantidade 'QUANTIDADE'\r\n" + 
					"FROM produto as p\r\n" + 
					"	JOIN localizacao as l\r\n" + 
					"	on l.id_localizacao = p.id_localizacao\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"ORDER BY p.id_produto, l.localizacao, p.id_produto, p.descricao, m.nome_montadora ASC");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("LOCAL"), 
						rs.getString("COD.CSM"),
						rs.getString("DESCRICAO"), 
						rs.getString("MONTADORA"),
						rs.getString("COD.MONTADORA"), 
						rs.getString("MARCA"), 
						rs.getString("COD.RECEBIDO") });
			}
			return model;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA PARA A TABELA DA JANELA GESTÃO DE QUANTIDADE
	// ORDENADO POR LOCALIZAÇÃO, DESCRIÇÃO E MONTADORA
	@Override
	public DefaultTableModel tableQuantidadeMinima(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		model.addColumn("LOCAL");
		model.addColumn("CSM");
		model.addColumn("DESCRIÇÃO");
		model.addColumn("MONTADORA");
		model.addColumn("COD.MONTADORA");
		model.addColumn("MARCA");
		model.addColumn("COD.RECEBIDO");
		model.addColumn("QUANT.MÍNIMA");

		try {
			pst = conn.prepareStatement(
					"SELECT l.localizacao 'LOCAL', p.id_produto 'COD.CSM', p.descricao 'DESCRIÇÃO', m.nome_montadora 'MONTADORA', cm.CODIGOMONTADORA 'COD.MONTADORA', cr.marca 'MARCA', cr.CODIGORECEBIDO 'COD.RECEBIDO', p.quantidade_minima 'QUANT.MINIMA'\r\n" + 
					"FROM produto AS p\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cm.codigo_montadora SEPARATOR ' / ') 'CODIGOMONTADORA', group_concat(cm.id_montadora)'id_montadora' FROM codigo_montadora AS cm, produto AS p WHERE cm.id_produto = p.id_produto GROUP BY cm.id_produto) AS cm\r\n" + 
					"	on cm.id_produto = p.id_produto\r\n" + 
					"	JOIN montadora as m\r\n" + 
					"	on m.id_montadora = cm.id_montadora\r\n" + 
					"	LEFT JOIN (SELECT p.*, group_concat(cr.codigo_recebido SEPARATOR ' / ') 'CODIGORECEBIDO', group_concat(cr.marca SEPARATOR ' / ') 'marca' FROM codigo_recebido AS cr, produto AS p WHERE cr.id_produto = p.id_produto GROUP BY cr.id_produto) AS cr\r\n" + 
					"	on cr.id_produto = p.id_produto\r\n" + 
					"	JOIN localizacao as l\r\n" + 
					"	on l.id_localizacao = p.id_localizacao\r\n" + 
					"ORDER BY l.localizacao, p.descricao, m.nome_montadora ASC");

			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getString("LOCAL"), 
						rs.getString("COD.CSM"),
						rs.getString("DESCRIÇÃO"), 
						rs.getString("MONTADORA"),
						rs.getString("COD.MONTADORA"), 
						rs.getString("MARCA"), 
						rs.getString("COD.RECEBIDO"),
						rs.getString("QUANT.MINIMA") });
			}

			return model;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	// CONSULTA PARA A TABELA DO PROCURAR PRODUTO NOVO CONSUMO
		@Override
		public DefaultTableModel tableProcurarProdutoConsumo(String marca, String cod_recebido, String descricao, DefaultTableModel model) {
			PreparedStatement pst = null;
			ResultSet rs = null;

			model.addColumn("CSM");
			model.addColumn("MONTADORA");
			model.addColumn("COD.MONTADORA");
			model.addColumn("MARCA");
			model.addColumn("COD.RECEBIDO");
			model.addColumn("DESCRIÇÃO");

			try {
				pst = conn.prepareStatement(
						"SELECT p.id_produto 'COD.CSM', m.nome_montadora'MONTADORA', cm.codigo_montadora 'COD.MONTADORA', cr.marca 'MARCA', cr.codigo_recebido 'COD.RECEBIDO', p.descricao 'DESCRICAO'\r\n" + 
						"FROM produto AS p\r\n" + 
						"	JOIN codigo_montadora as cm\r\n" + 
						"	on cm.id_produto = p.id_produto\r\n" + 
						"    JOIN montadora as m\r\n" + 
						"	on m.id_montadora = cm.id_montadora\r\n" + 
						"    JOIN codigo_recebido as cr\r\n" + 
						"    on cr.id_produto = p.id_produto\r\n" + 
						"WHERE cr.marca LIKE ? \r\n" + 
						"AND cr.codigo_recebido LIKE ? \r\n" + 
						"AND p.descricao LIKE ?");

				pst.setString(1, "%" + marca + "%");
				pst.setString(2, "%" + cod_recebido + "%");
				pst.setString(3, "%" + descricao + "%");

				rs = pst.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { 
							rs.getString("COD.CSM"),
							rs.getString("MONTADORA"), 
							rs.getString("COD.MONTADORA"),
							rs.getString("MARCA"), 
							rs.getString("COD.RECEBIDO"), 
							rs.getString("DESCRICAO") });
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
