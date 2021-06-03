/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class TipoServico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_tipo_servico;
	private String tipo_servico;

	public TipoServico() {

	}

	public Integer getId_tipo_servico() {
		return id_tipo_servico;
	}

	public void setId_tipo_servico(Integer id_tipo_servico) {
		this.id_tipo_servico = id_tipo_servico;
	}

	public String getTipo_servico() {
		return tipo_servico;
	}

	public void setTipo_servico(String tipo_servico) {
		this.tipo_servico = tipo_servico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_tipo_servico == null) ? 0 : id_tipo_servico.hashCode());
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
		TipoServico other = (TipoServico) obj;
		if (id_tipo_servico == null) {
			if (other.id_tipo_servico != null)
				return false;
		} else if (!id_tipo_servico.equals(other.id_tipo_servico))
			return false;
		return true;
	}
	
	

}
