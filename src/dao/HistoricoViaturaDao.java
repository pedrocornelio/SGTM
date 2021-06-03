package dao;

import javax.swing.table.DefaultTableModel;

import entities.HistoricoViatura;

public interface HistoricoViaturaDao {
	
	void insertHistoricoViatura(HistoricoViatura historicoViatura);

	DefaultTableModel tableHistoricoViatura(DefaultTableModel model);

}
