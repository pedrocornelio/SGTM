/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.HistoricoProdutoSaida;

public interface HistoricoProdutoSaidaDao {

	void insertHistSaidaUnidade(HistoricoProdutoSaida historicoProdutoSaida);

	void insertHistSaidaViatura(HistoricoProdutoSaida historicoProdutoSaida);

	DefaultTableModel tableHistoricoProdutoSaida(DefaultTableModel model);

	DefaultTableModel tableHistoricoSaidaPesquisa(DefaultTableModel model, String dataInicio, String dataFim);

}
