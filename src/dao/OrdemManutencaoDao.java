package dao;

import javax.swing.table.DefaultTableModel;

import entities.OrdemManutencao;

public interface OrdemManutencaoDao {

	void insert(OrdemManutencao ordemManutencao);
	
	OrdemManutencao findByNOM(String nOM);

	OrdemManutencao findByOM(OrdemManutencao ordemManutencao);
	
	void updateNOM(OrdemManutencao ordemManutencao);

	DefaultTableModel tableOrdemManutencao(DefaultTableModel model);

}
