/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Militar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_militar;
	private String nome;
	private String nBM;

	public Militar() {

	}

	public Militar(Integer id_militar, String nome, String nBM) {
		super();
		this.id_militar = id_militar;
		this.nome = nome;
		this.nBM = nBM;
	}

	public Integer getId_militar() {
		return id_militar;
	}

	public void setId_militar(Integer id_militar) {
		this.id_militar = id_militar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getnBM() {
		return nBM;
	}

	public void setnBM(String nBM) {
		this.nBM = nBM;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_militar == null) ? 0 : id_militar.hashCode());
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
		Militar other = (Militar) obj;
		if (id_militar == null) {
			if (other.id_militar != null)
				return false;
		} else if (!id_militar.equals(other.id_militar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Militar [id_militar=" + id_militar + ", nome=" + nome + ", nBM=" + nBM + "]";
	}

}
