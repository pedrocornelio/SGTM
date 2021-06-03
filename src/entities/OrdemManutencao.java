/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class OrdemManutencao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_om;
	private String nOM;
	private Date data_inicio;
	private Time hora_inicio;
	private Date data_fim;
	private Time hora_fim;
	private String relato;
	private Militar militarBaixa;
	private Militar militarTriagem;
	private Militar militarLiberacao;
	private Militar militarAlta;
	private Servico servico;
	private Viatura viatura;
	
	public OrdemManutencao() {
	
	}
	
	public OrdemManutencao(Integer id_om, String nOM, Date data_inicio, Time hora_inicio, Date data_fim, Time hora_fim,
			String relato, Militar militarBaixa, Militar militarTriagem, Militar militarLiberacao, Militar militarAlta,
			Servico servico, Viatura viatura) {
		super();
		this.id_om = id_om;
		this.nOM = nOM;
		this.data_inicio = data_inicio;
		this.hora_inicio = hora_inicio;
		this.data_fim = data_fim;
		this.hora_fim = hora_fim;
		this.relato = relato;
		this.militarBaixa = militarBaixa;
		this.militarTriagem = militarTriagem;
		this.militarLiberacao = militarLiberacao;
		this.militarAlta = militarAlta;
		this.servico = servico;
		this.viatura = viatura;
	}
	
	public Integer getId_om() {
		return id_om;
	}

	public void setId_om(Integer id_om) {
		this.id_om = id_om;
	}

	public String getnOM() {
		return nOM;
	}

	public void setnOM(String nOM) {
		this.nOM = nOM;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public Time getHora_fim() {
		return hora_fim;
	}

	public void setHora_fim(Time hora_fim) {
		this.hora_fim = hora_fim;
	}

	public String getRelato() {
		return relato;
	}

	public void setRelato(String relato) {
		this.relato = relato;
	}

	public Militar getMilitarBaixa() {
		return militarBaixa;
	}

	public void setMilitarBaixa(Militar militarBaixa) {
		this.militarBaixa = militarBaixa;
	}

	public Militar getMilitarTriagem() {
		return militarTriagem;
	}

	public void setMilitarTriagem(Militar militarTriagem) {
		this.militarTriagem = militarTriagem;
	}

	public Militar getMilitarLiberacao() {
		return militarLiberacao;
	}

	public void setMilitarLiberacao(Militar militarLiberacao) {
		this.militarLiberacao = militarLiberacao;
	}

	public Militar getMilitarAlta() {
		return militarAlta;
	}

	public void setMilitarAlta(Militar militarAlta) {
		this.militarAlta = militarAlta;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Viatura getViatura() {
		return viatura;
	}

	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_om == null) ? 0 : id_om.hashCode());
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
		OrdemManutencao other = (OrdemManutencao) obj;
		if (id_om == null) {
			if (other.id_om != null)
				return false;
		} else if (!id_om.equals(other.id_om))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrdemManutencao [id_om=" + id_om + ", nOM=" + nOM + ", data_inicio=" + data_inicio + ", hora_inicio="
				+ hora_inicio + ", data_fim=" + data_fim + ", hora_fim=" + hora_fim + ", relato=" + relato
				+ ", militarBaixa=" + militarBaixa + ", militarTriagem=" + militarTriagem + ", militarLiberacao="
				+ militarLiberacao + ", militarAlta=" + militarAlta + ", servico=" + servico + ", viatura=" + viatura
				+ "]";
	}

}
