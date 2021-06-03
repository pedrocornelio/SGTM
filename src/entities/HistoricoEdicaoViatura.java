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

public class HistoricoEdicaoViatura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_edicao_viatura;
	private Date data_edicao;
	private Time hora_edicao;
	private String placa_anterior;
	private String modelo_anterior;
	private String ano_anterior;
	private String chassi_anterior;
	private Montadora Montadora_anterior;
	private Unidade unidade_anterior;
	private Viatura viatura;
	private Login login;
	
	public HistoricoEdicaoViatura () {
		
	}
	
	public HistoricoEdicaoViatura(Integer id_historico_edicao_viatura, Date data_edicao, Time hora_edicao,
				String placa_anterior, String modelo_anterior, String ano_anterior, String chassi_anterior, Montadora montadora_anterior,
				Unidade unidade_anterior, Viatura viatura, Login login) {
			super();
			this.id_historico_edicao_viatura = id_historico_edicao_viatura;
			this.data_edicao = data_edicao;
			this.hora_edicao = hora_edicao;
			this.placa_anterior = placa_anterior;
			this.modelo_anterior = modelo_anterior;
			this.ano_anterior = ano_anterior;
			this.chassi_anterior = chassi_anterior;
			Montadora_anterior = montadora_anterior;
			this.unidade_anterior = unidade_anterior;
			this.viatura = viatura;
			this.login = login;
		}

	public Integer getId_historico_edicao_viatura() {
		return id_historico_edicao_viatura;
	}

	public void setId_historico_edicao_viatura(Integer id_historico_edicao_viatura) {
		this.id_historico_edicao_viatura = id_historico_edicao_viatura;
	}

	public Date getData_edicao() {
		return data_edicao;
	}

	public void setData_edicao(Date data_edicao) {
		this.data_edicao = data_edicao;
	}

	public Time getHora_edicao() {
		return hora_edicao;
	}

	public void setHora_edicao(Time hora_edicao) {
		this.hora_edicao = hora_edicao;
	}

	public String getPlaca_anterior() {
		return placa_anterior;
	}

	public void setPlaca_anterior(String placa_anterior) {
		this.placa_anterior = placa_anterior;
	}

	public String getModelo_anterior() {
		return modelo_anterior;
	}

	public void setModelo_anterior(String modelo_anterior) {
		this.modelo_anterior = modelo_anterior;
	}

	public String getAno_anterior() {
		return ano_anterior;
	}

	public void setAno_anterior(String ano_anterior) {
		this.ano_anterior = ano_anterior;
	}

	public String getChassi_anterior() {
		return chassi_anterior;
	}

	public void setChassi_anterior(String chassi_anterior) {
		this.chassi_anterior = chassi_anterior;
	}

	public Montadora getMontadora_anterior() {
		return Montadora_anterior;
	}

	public void setMontadora_anterior(Montadora montadora_anterior) {
		Montadora_anterior = montadora_anterior;
	}

	public Unidade getUnidade_anterior() {
		return unidade_anterior;
	}

	public void setUnidade_anterior(Unidade unidade_anterior) {
		this.unidade_anterior = unidade_anterior;
	}

	public Viatura getViatura() {
		return viatura;
	}

	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_historico_edicao_viatura == null) ? 0 : id_historico_edicao_viatura.hashCode());
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
		HistoricoEdicaoViatura other = (HistoricoEdicaoViatura) obj;
		if (id_historico_edicao_viatura == null) {
			if (other.id_historico_edicao_viatura != null)
				return false;
		} else if (!id_historico_edicao_viatura.equals(other.id_historico_edicao_viatura))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoEdicaoViatura [id_historico_edicao_viatura=" + id_historico_edicao_viatura + ", data_edicao="
				+ data_edicao + ", hora_edicao=" + hora_edicao + ", placa_anterior=" + placa_anterior
				+ ", modelo_anterior=" + modelo_anterior + ", ano_anterior=" + ano_anterior + ", chassi_anterior="
				+ chassi_anterior + ", Montadora_anterior=" + Montadora_anterior + ", unidade_anterior="
				+ unidade_anterior + ", viatura=" + viatura + ", login=" + login + "]";
	}

	
}
