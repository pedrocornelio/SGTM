package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import dao.FornecedorDao;
import db.DB;
import db.DbException;
import entities.Fornecedor;
import entities.Produto;

public class FornecedorDaoJDBC implements FornecedorDao {

	private Connection conn;

	public FornecedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Produto instantiateProduto(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId_produto(rs.getInt("id_produto"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setQuantidade(rs.getInt("quantidade"));
		return produto;
	}
	
	@Override
	public void insert(Fornecedor fornecedor) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO fornecedor"
					+ "(fornecedor, nota_fiscal_fornecedor, orcamento, preco, id_produto)"
					+ "VALUE (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, fornecedor.getFornecedor());
			pst.setString(2, fornecedor.getNota_fiscal_fornecedor());
			pst.setString(3, fornecedor.getOrcamento());
			pst.setDouble(4, fornecedor.getPreco());
			pst.setInt(5, fornecedor.getProduto().getId_produto());

			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				fornecedor.setId_fornecedor(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);

		}
		
	}

	@Override
	public Fornecedor findFornecedor(String fornecedor, String nota_fiscal, String orcamento, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Fornecedor obj = new Fornecedor();
		try {
			pst = conn.prepareStatement("SELECT * FROM fornecedor as f\r\n" + 
					"JOIN produto as p\r\n" + 
					"ON p.id_produto = f.id_produto\r\n" + 
					"WHERE fornecedor LIKE ? \r\n" + 
					"AND nota_fiscal_fornecedor LIKE ?\r\n" + 
					"AND orcamento LIKE ?\r\n" + 
					"AND f.id_produto = ?;");
			pst.setString(1, fornecedor);
			pst.setString(2, nota_fiscal);
			pst.setString(3, orcamento);
			pst.setInt(4, id_produto);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				Produto produto = instantiateProduto(rs);
				obj.setId_fornecedor(rs.getInt("id_fornecedor"));
				obj.setFornecedor(rs.getString("fornecedor"));
				obj.setNota_fiscal_fornecedor(rs.getString("nota_fiscal_fornecedor"));
				obj.setOrcamento(rs.getString("orcamento"));
				obj.setPreco(rs.getDouble("preco"));
				obj.setProduto(produto);
			}
			return obj;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		
	}
	
	@Override
	public Fornecedor findFornecedorById(Integer id_fornecedor) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		Fornecedor fornecedor = new Fornecedor();
		try {
			pst = conn.prepareStatement("SELECT * FROM fornecedor WHERE id_fornecedor = ?");
			pst.setInt(1, id_fornecedor);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				fornecedor.setId_fornecedor(rs.getInt("id_fornecedor"));
				fornecedor.setFornecedor(rs.getString("fornecedor"));
				fornecedor.setNota_fiscal_fornecedor(rs.getString("nota_fiscal_fornecedor"));
				fornecedor.setOrcamento(rs.getString("orcamento"));
			}
			return fornecedor;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void updateFornecedor (Fornecedor fornecedor, Produto produto){
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE fornecedor "
					+ "SET fornecedor = ?, nota_fiscal_fornecedor = ?, orcamento = ?, preco = ?"
					+ "WHERE id_fornecedor = ? and id_produto = ?");
			pst.setString(1, fornecedor.getFornecedor());
			pst.setString(2, fornecedor.getNota_fiscal_fornecedor());
			pst.setString(3, fornecedor.getOrcamento());
			pst.setDouble(4, fornecedor.getPreco());
			pst.setInt(5, fornecedor.getId_fornecedor());
			pst.setInt(6, produto.getId_produto());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	//CONSULTA PARA A TABELA DE FORNECEDOR
	@Override
	public DefaultTableModel tableFornecedor (DefaultTableModel model, Integer id_produto) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("REF");
		model.addColumn("FORNECEDOR");
		model.addColumn("NOTA FISCAL");
		model.addColumn("ORÇAMENTO");
		model.addColumn("PREÇO");
			
		try {
			pst = conn.prepareStatement("SELECT f.id_fornecedor 'REF', f.fornecedor 'FORNECEDOR', f.nota_fiscal_fornecedor 'NOTA FISCAL', f.orcamento 'ORÇAMENTO', f.preco 'PREÇO'\r\n" + 
					"FROM fornecedor AS f\r\n" + 
					"WHERE f.id_produto = ?;");
			pst.setInt(1, id_produto);
			rs = pst.executeQuery();		
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("REF"),
						rs.getString("FORNECEDOR"),
						rs.getString("NOTA FISCAL"),
						rs.getString("ORÇAMENTO"),
						rs.getString("PREÇO")
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
