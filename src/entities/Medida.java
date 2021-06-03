/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Medida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_medida;
	private String medida;
	
	public Medida() {

	}
	
	public Medida(Integer id_medida, String medida) {
		super();
		this.id_medida = id_medida;
		this.medida = medida;
	}

	public Integer getId_medida() {
		return id_medida;
	}

	public void setId_medida(Integer id_medida) {
		this.id_medida = id_medida;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_medida == null) ? 0 : id_medida.hashCode());
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
		Medida other = (Medida) obj;
		if (id_medida == null) {
			if (other.id_medida != null)
				return false;
		} else if (!id_medida.equals(other.id_medida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medida [id_medida=" + id_medida + ", medida=" + medida + "]";
	}
	

}
