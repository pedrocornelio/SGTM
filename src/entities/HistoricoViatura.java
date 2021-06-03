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

public class HistoricoViatura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_viatura;
	private Date data_entrada;
	private Time hora_entrada;
	private Date data_saida;
	private Time hora_saida;
	private Viatura viatura;
	private Unidade unidade;
	private Login login;
	private Militar militar;
	private OrdemManutencao om;
	
	public HistoricoViatura() {
		
	}
	
	public HistoricoViatura(Integer id_historico_viatura, Date data_entrada, Time hora_entrada, Date data_saida,
			Time hora_saida, Viatura viatura, Unidade unidade, Login login, Militar militar, OrdemManutencao om) {
		super();
		this.id_historico_viatura = id_historico_viatura;
		this.data_entrada = data_entrada;
		this.hora_entrada = hora_entrada;
		this.data_saida = data_saida;
		this.hora_saida = hora_saida;
		this.viatura = viatura;
		this.unidade = unidade;
		this.login = login;
		this.militar = militar;
		this.om = om;
	}
	
	public Integer getId_historico_viatura() {
		return id_historico_viatura;
	}
	
	public void setId_historico_viatura(Integer id_historico_viatura) {
		this.id_historico_viatura = id_historico_viatura;
	}
	
	public Date getData_entrada() {
		return data_entrada;
	}
	
	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	
	public Time getHora_entrada() {
		return hora_entrada;
	}
	
	public void setHora_entrada(Time hora_entrada) {
		this.hora_entrada = hora_entrada;
	}
	
	public Date getData_saida() {
		return data_saida;
	}
	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}
	
	public Time getHora_saida() {
		return hora_saida;
	}
	
	public void setHora_saida(Time hora_saida) {
		this.hora_saida = hora_saida;
	}
	
	public Viatura getViatura() {
		return viatura;
	}
	
	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}
	
	public Unidade getUnidade() {
		return unidade;
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Militar getMilitar() {
		return militar;
	}
	
	public void setMilitar(Militar militar) {
		this.militar = militar;
	}

	public OrdemManutencao getOm() {
		return om;
	}

	public void setOm(OrdemManutencao om) {
		this.om = om;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_historico_viatura == null) ? 0 : id_historico_viatura.hashCode());
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
		HistoricoViatura other = (HistoricoViatura) obj;
		if (id_historico_viatura == null) {
			if (other.id_historico_viatura != null)
				return false;
		} else if (!id_historico_viatura.equals(other.id_historico_viatura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoViatura [id_historico_viatura=" + id_historico_viatura + ", data_entrada=" + data_entrada
				+ ", hora_entrada=" + hora_entrada + ", data_saida=" + data_saida + ", hora_saida=" + hora_saida
				+ ", viatura=" + viatura + ", unidade=" + unidade + ", login=" + login + ", militar=" + militar
				+ ", om=" + om + "]";
	}

}
