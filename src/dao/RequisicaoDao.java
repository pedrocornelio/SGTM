/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.Login;
import entities.Requisicao;

public interface RequisicaoDao {
	
	void insert (Requisicao requisicao);
	
	void updateAtendido(Requisicao requisicao, boolean valor, Login login);
	
	Requisicao findByRequisicao(String nOM, String data, Integer codCSM);

	DefaultTableModel tableRequisicao(DefaultTableModel model);

	DefaultTableModel tableBuscarRequisicao(DefaultTableModel model);

	DefaultTableModel tableExcluirRequisicao(DefaultTableModel model);

	void deleteRequisicao(Requisicao requisicao);

	DefaultTableModel tableProdutoRequisicao(DefaultTableModel model);

	Requisicao findById(Integer idRequisicao);

}
