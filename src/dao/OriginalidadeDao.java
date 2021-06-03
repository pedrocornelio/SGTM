/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import entities.Originalidade;

public interface OriginalidadeDao {

	void insert(Originalidade obj);

	void update(Originalidade obj);

	void delete(Originalidade obj);

	void findAll(Originalidade obj);

	Originalidade findByOriginalidade(String originalidade);

	Originalidade findById(Integer originalidade);

}
