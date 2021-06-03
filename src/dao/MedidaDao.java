package dao;

import javax.swing.DefaultComboBoxModel;

import entities.Medida;

public interface MedidaDao {

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxMedida(DefaultComboBoxModel model);

	Medida findByMedida(String medida);

	Medida findById(Integer id);

}
