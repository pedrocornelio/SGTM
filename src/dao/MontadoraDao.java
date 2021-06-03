package dao;

import javax.swing.DefaultComboBoxModel;

import entities.Montadora;

public interface MontadoraDao<E> {

	Montadora findByMontadora(String montadora);

	DefaultComboBoxModel<String[]> comboBoxMontadora(DefaultComboBoxModel<String> modelcombobox);
}
