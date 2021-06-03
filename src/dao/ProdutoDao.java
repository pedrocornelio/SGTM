package dao;

import javax.swing.table.DefaultTableModel;

import entities.CodigoMontadora;
import entities.CodigoRecebido;
import entities.Localizacao;
import entities.Medida;
import entities.Originalidade;
import entities.Produto;

public interface ProdutoDao {

	void insert(Produto produto, CodigoMontadora codigoMontadora, CodigoRecebido codigoRecebido, Originalidade originalidade, Localizacao localizacao, Medida medida);

	void updateProduto(Produto produto);
	
	void updateQuantidade(Produto produto);

	void updateLocalizacao(Integer id_localizacao, Produto produto);

	void updateQuantidadeMinima(Integer quantidade_minima, Produto produto);

	void deleteById(Produto obj);

	Produto findById(Integer id);

	Produto searchProduct(String descricao, Integer id_montadora, String cod_montadora, String marca, String cod_recebido);

	Produto findByCodigoMontadora(String cod_montadora);

	Produto findByCodigoRecebido(String cod_recebido);

	DefaultTableModel tableEstoque(DefaultTableModel model);
	
	DefaultTableModel tableProdutoEntrada(DefaultTableModel model);
	
	DefaultTableModel tableLocalizacao(DefaultTableModel model);

	DefaultTableModel tableQuantidadeMinima(DefaultTableModel model);

	DefaultTableModel tableProcurarProdutoConsumo(String marca, String cod_recebido, String descricao, DefaultTableModel model);

}
