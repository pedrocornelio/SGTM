package dao;


import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import entities.CodigoMontadora;
import entities.Montadora;
import entities.Produto;

public interface CodigoMontadoraDao {

	public void insert(CodigoMontadora codigoMontadora, Montadora montadora, Produto produto);

	CodigoMontadora findCodigoMontadora (Integer idProduto, String codigoMontadora);

	void updateCodigoMontadora(CodigoMontadora codigoMontadora);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxCodigoMontadora(DefaultComboBoxModel model, Integer id_produto);

	DefaultTableModel tableEquivalenciaCodMontadora(DefaultTableModel model, Integer id_produto);

	CodigoMontadora findCodigoMontadoraNull(Integer idProduto);

}
