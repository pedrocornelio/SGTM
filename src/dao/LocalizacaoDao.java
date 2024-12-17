package dao;

import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import entities.Localizacao;

public interface LocalizacaoDao {

	void insert(String localizacao);

	void update(Localizacao obj);

	void delete(String localizacao) throws SQLException;

	void findAll(Localizacao obj);

	Localizacao findByLocalizacao(String localizacao);

	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel comboBoxLocalizacao(DefaultComboBoxModel model);

	DefaultTableModel tableAlmoxarifado(DefaultTableModel model);
}
