/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.Aplicacao;

public interface AplicacaoDao {

	void insert(Aplicacao obj);

	void update(Aplicacao obj);

	void delete(Integer id_montadora, String modelo, String ano);

	void findAll(Aplicacao obj);

	void deleteById(Integer id);

	DefaultTableModel tableAplicacao(DefaultTableModel model, Integer id_produto);

}
