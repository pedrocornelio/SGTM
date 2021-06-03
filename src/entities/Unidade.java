/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Unidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idUnidade;
	private String nomeUnidade;
	private String codigoUndiade;

	public Unidade() {
	}

	public Unidade(Integer idUnidade, String nomeUnidade) {
		super();
		this.idUnidade = idUnidade;
		this.nomeUnidade = nomeUnidade;
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	
	public String getCodigoUndiade() {
		return codigoUndiade;
	}

	public void setCodigoUndiade(String codigoUndiade) {
		this.codigoUndiade = codigoUndiade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidade == null) ? 0 : idUnidade.hashCode());
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
		Unidade other = (Unidade) obj;
		if (idUnidade == null) {
			if (other.idUnidade != null)
				return false;
		} else if (!idUnidade.equals(other.idUnidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Unidade [idUnidade=" + idUnidade + ", nomeUnidade=" + nomeUnidade + ", codigoUndiade=" + codigoUndiade
				+ "]";
	}
	
	
}
