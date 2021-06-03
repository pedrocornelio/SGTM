/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.Login;

public interface LoginDao {

	void insert(Login obj);

	void update(Boolean LiberarAcesso,Boolean AlmoxHist, Boolean AlmoxEdicao, Boolean AlmoxAdmin, Boolean Oficina, Boolean Gerencia, Boolean Requisicao, String Senha, Login obj);

	void deleteById(Integer id);

	Login findById(Integer id);
	
	Login findByNBM(String nbm);

	Login findByNBMSenha(String nBM, String senha);

	DefaultTableModel tableList(DefaultTableModel model);

}
