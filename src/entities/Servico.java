/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Servico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_servico;
	private String servico;
	private EquipeServico equipe_servico;
	private Requisicao requisicao;
	private TipoServico tipoServico;

	public Servico() {

	}
	
	public Servico(Integer id_servico, String servico, EquipeServico equipe_servico, Requisicao requisicao, TipoServico tipoServico) {
		super();
		this.id_servico = id_servico;
		this.servico = servico;
		this.equipe_servico = equipe_servico;
		this.requisicao = requisicao;
		this.tipoServico = tipoServico;
	}

	public Integer getId_servico() {
		return id_servico;
	}

	public void setId_servico(Integer id_servico) {
		this.id_servico = id_servico;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public EquipeServico getEquipe_servico() {
		return equipe_servico;
	}

	public void setEquipe_servico(EquipeServico equipe_servico) {
		this.equipe_servico = equipe_servico;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_servico == null) ? 0 : id_servico.hashCode());
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
		Servico other = (Servico) obj;
		if (id_servico == null) {
			if (other.id_servico != null)
				return false;
		} else if (!id_servico.equals(other.id_servico))
			return false;
		return true;
	}

}
