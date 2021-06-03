package dao;

import javax.swing.DefaultComboBoxModel;

import entities.Unidade;

public interface UnidadeDao {
	
	void insert (Unidade obj);
	
	void update (Unidade obj);
	
	void delete (Unidade obj);
	
	void findAll (Unidade obj);

	Unidade findByUnidade(String unidade);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxUnidade(DefaultComboBoxModel model);

}
