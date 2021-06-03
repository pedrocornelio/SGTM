/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import javax.swing.table.DefaultTableModel;

import entities.Viatura;

public interface ViaturaDao {
	
	void insert(Viatura viatura);
	
	void updateViatura(Viatura viatura);
	
	Viatura findById(Integer id_viatura);
	
	Viatura findByPlaca(String placa);

	DefaultTableModel tableViatura(DefaultTableModel model);

}
