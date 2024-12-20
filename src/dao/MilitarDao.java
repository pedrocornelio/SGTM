/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import entities.Militar;

public interface MilitarDao {
	
	void insert(Militar obj);
	
	Militar findById(Integer id);
	
	Militar findByNBM(String nbm);

	Militar findByName(String nome);
	
}
