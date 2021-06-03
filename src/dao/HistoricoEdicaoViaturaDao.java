/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.HistoricoEdicaoViatura;

public interface HistoricoEdicaoViaturaDao {

	void insert(HistoricoEdicaoViatura historicoEdicaoViatura);

	DefaultTableModel tableHistoricoEdicaoViatura(DefaultTableModel model);

	DefaultTableModel tableHistoricoEdicaoViaturaPesquisa(DefaultTableModel model, String dataInicio, String dataFim);

}
