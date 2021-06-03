/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Localizacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idLocalizacao;
	private String nomeLocalizacao;

	public Localizacao() {
	}

	public Localizacao(Integer idLocalizacao, String nomeLocalizacao) {
		super();
		this.idLocalizacao = idLocalizacao;
		this.nomeLocalizacao = nomeLocalizacao;
	}

	public Integer getIdLocalizacao() {
		return idLocalizacao;
	}

	public void setIdLocalizacao(Integer idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
	}

	public String getLocalizacao() {
		return nomeLocalizacao;
	}

	public void setLocalizacao(String nomeLocalizacao) {
		this.nomeLocalizacao = nomeLocalizacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLocalizacao == null) ? 0 : idLocalizacao.hashCode());
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
		Localizacao other = (Localizacao) obj;
		if (idLocalizacao == null) {
			if (other.idLocalizacao != null)
				return false;
		} else if (!idLocalizacao.equals(other.idLocalizacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Localizacao [nomeLocalizacao=" + nomeLocalizacao + "]";
	}

}
