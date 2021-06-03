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

public class HistoricoLogin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_login;
	private Date data_login;
	private Time hora_login;
	private Login login;
	
	public HistoricoLogin() {
	}
	
	public HistoricoLogin(Integer id_historico_login, Date data, Time hora_login, Login login) {
		super();
		this.id_historico_login = id_historico_login;
		this.data_login = data;
		this.hora_login = hora_login;
		this.login = login;
	}

	public Integer getId_historico_login() {
		return id_historico_login;
	}

	public void setId_historico_login(Integer id_historico_login) {
		this.id_historico_login = id_historico_login;
	}

	public Date getData_login() {
		return data_login;
	}

	public void setData_login(Date data_login) {
		this.data_login = data_login;
	}

	public Time getHora_login() {
		return hora_login;
	}

	public void setHora_login(Time hora_login) {
		this.hora_login = hora_login;
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
		result = prime * result + ((id_historico_login == null) ? 0 : id_historico_login.hashCode());
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
		HistoricoLogin other = (HistoricoLogin) obj;
		if (id_historico_login == null) {
			if (other.id_historico_login != null)
				return false;
		} else if (!id_historico_login.equals(other.id_historico_login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoLogin [id_historico_login=" + id_historico_login + ", data_login=" + data_login
				+ ", hora_login=" + hora_login + ", login=" + login + "]";
	}

}
