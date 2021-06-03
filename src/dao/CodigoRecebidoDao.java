package dao;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import entities.CodigoRecebido;
import entities.Produto;

public interface CodigoRecebidoDao {

	public void insert(CodigoRecebido codigoRecebido, Produto produto);

	CodigoRecebido findByCodigoRecebido (Integer idProduto, String marca, String codigoRecebido);

	void updateCodigoRecebido(CodigoRecebido codigoRecebido);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxCodigoRecebido(DefaultComboBoxModel model, Integer id_produto);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxMarca(DefaultComboBoxModel model, Integer id_produto);

	DefaultTableModel tableEquivalenciaCodRecebido(DefaultTableModel model, Integer id_produto);
}
