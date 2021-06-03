/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class EquipeServico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_equipe_servico;
	private Militar militar;

	public EquipeServico() {

	}

	public EquipeServico(Integer id_equipe_servico, Militar militar) {
		super();
		this.id_equipe_servico = id_equipe_servico;
		this.militar = militar;
	}

	public Integer getId_equipe_servico() {
		return id_equipe_servico;
	}

	public void setId_equipe_servico(Integer id_equipe_servico) {
		this.id_equipe_servico = id_equipe_servico;
	}

	public Militar getMilitar() {
		return militar;
	}

	public void setMilitar(Militar militar) {
		this.militar = militar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_equipe_servico == null) ? 0 : id_equipe_servico.hashCode());
		result = prime * result + ((militar == null) ? 0 : militar.hashCode());
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
		EquipeServico other = (EquipeServico) obj;
		if (id_equipe_servico == null) {
			if (other.id_equipe_servico != null)
				return false;
		} else if (!id_equipe_servico.equals(other.id_equipe_servico))
			return false;
		if (militar == null) {
			if (other.militar != null)
				return false;
		} else if (!militar.equals(other.militar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EquipeServico [id_equipe_servico=" + id_equipe_servico + ", militar=" + militar + "]";
	}

}
