/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Originalidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idOriginalidade;
	private String originalidade;

	public Originalidade() {

	}

	public Originalidade(Integer idOriginalidade, String originalidade) {
		this.idOriginalidade = idOriginalidade;
		this.originalidade = originalidade;
	}

	public Integer getIdOriginalidade() {
		return idOriginalidade;
	}

	public void setIdOriginalidade(Integer idOriginalidade) {
		this.idOriginalidade = idOriginalidade;
	}

	public String getOriginalidade() {
		return originalidade;
	}

	public void setOriginalidade(String originalidade) {
		this.originalidade = originalidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOriginalidade == null) ? 0 : idOriginalidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Originalidade other = (Originalidade) obj;
		if (idOriginalidade == null) {
			if (other.idOriginalidade != null)
				return false;
		} else if (!idOriginalidade.equals(other.idOriginalidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Originalidade [idOriginalidade=" + idOriginalidade + ", originalidade=" + originalidade + "]";
	}

}
