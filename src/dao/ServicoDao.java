/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.Servico;

public interface ServicoDao {

	void insert(Servico servico);

	void updateServico(Servico servico);

	void deleteServico(Servico servico);

	Servico findById(Integer Servico);

	Servico findByServico(String nOM, String data, Integer codCSM);

	DefaultTableModel tableServico(DefaultTableModel model);

}
