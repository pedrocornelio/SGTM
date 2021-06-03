/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Montadora implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idMontadora;
	private String nomeMontadora;
	
	public Montadora () {
	}
	
	public Montadora(Integer idMontadora, String nomeMontadora) {
		this.idMontadora = idMontadora;
		this.nomeMontadora = nomeMontadora;
	}
	
	public Integer getIdMontadora() {
		return idMontadora;
	}

	public void setIdMontadora(Integer idMontadora) {
		this.idMontadora = idMontadora;
	}

	public String getNomeMontadora() {
		return nomeMontadora;
	}

	public void setNomeMontadora(String nomeMontadora) {
		this.nomeMontadora = nomeMontadora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeMontadora == null) ? 0 : nomeMontadora.hashCode());
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
		Montadora other = (Montadora) obj;
		if (nomeMontadora == null) {
			if (other.nomeMontadora != null)
				return false;
		} else if (!nomeMontadora.equals(other.nomeMontadora))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getNomeMontadora();
	}
	
	

}
