package dao;

import javax.swing.DefaultComboBoxModel;

import entities.Localizacao;

public interface LocalizacaoDao {

	void insert(Localizacao obj);

	void update(Localizacao obj);

	void delete(Localizacao obj);

	void findAll(Localizacao obj);

	Localizacao findByLocalizacao(String localizacao);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxLocalizacao(DefaultComboBoxModel model);
}
