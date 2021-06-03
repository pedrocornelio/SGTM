package dao;

import javax.swing.table.DefaultTableModel;

import entities.HistoricoProdutoEntrada;

public interface HistoricoProdutoEntradaDao {
	
void insert (HistoricoProdutoEntrada obj);

DefaultTableModel tableHistoricoProdutoEntrada(DefaultTableModel model);

DefaultTableModel tableHistoricoEntradaPesquisa(DefaultTableModel model, String dataInicio, String dataFim);

}
