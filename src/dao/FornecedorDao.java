package dao;

import javax.swing.table.DefaultTableModel;

import entities.Fornecedor;
import entities.Produto;

public interface FornecedorDao {
	
	void insert (Fornecedor obj);
	
	void updateFornecedor(Fornecedor fornecedor, Produto produto);
	
	Fornecedor findFornecedor(String fornecedor, String nota_fiscal, String orcamento, Integer id_produto);

	DefaultTableModel tableFornecedor(DefaultTableModel model, Integer id_produto);

	Fornecedor findFornecedorById(Integer id_fornecedor);

}
