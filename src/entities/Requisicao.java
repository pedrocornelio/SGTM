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

public class Requisicao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_requisicao;
	private Boolean atendido;
	private Date data_requisicao;
	private Time hora_requisicao;
	private Integer quantidade_requisicao;
	private Produto produto;
	private Viatura viatura;
	private OrdemManutencao om;
	private Login login;
	private Militar militarLogin;
	
	public Requisicao() {

	}
	
	public Requisicao(Integer id_requisicao, Boolean atendido, Date data_requisicao, Time hora_requisicao,
			Integer quantidade_requisicao, Produto produto, Viatura viatura, OrdemManutencao om, Login login,
			Militar militarLogin) {
		super();
		this.id_requisicao = id_requisicao;
		this.atendido = atendido;
		this.data_requisicao = data_requisicao;
		this.hora_requisicao = hora_requisicao;
		this.quantidade_requisicao = quantidade_requisicao;
		this.produto = produto;
		this.viatura = viatura;
		this.om = om;
		this.login = login;
		this.militarLogin = militarLogin;
	}
	
	public Integer getId_requisicao() {
		return id_requisicao;
	}
	public void setId_requisicao(Integer id_requisicao) {
		this.id_requisicao = id_requisicao;
	}
	public Boolean getAtendido() {
		return atendido;
	}
	public void setAtendido(Boolean atendido) {
		this.atendido = atendido;
	}
	public Date getData_requisicao() {
		return data_requisicao;
	}
	public void setData_requisicao(Date data_requisicao) {
		this.data_requisicao = data_requisicao;
	}
	public Time getHora_requisicao() {
		return hora_requisicao;
	}
	public void setHora_requisicao(Time hora_requisicao) {
		this.hora_requisicao = hora_requisicao;
	}
	public Integer getQuantidade_requisicao() {
		return quantidade_requisicao;
	}
	public void setQuantidade_requisicao(Integer quantidade_requisicao) {
		this.quantidade_requisicao = quantidade_requisicao;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setproduto(Produto produto) {
		this.produto = produto;
	}
	public Viatura getViatura() {
		return viatura;
	}
	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}
	public OrdemManutencao getOM() {
		return om;
	}
	public void setOM(OrdemManutencao om) {
		this.om = om;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Militar getMilitar() {
		return militarLogin;
	}
	public void setMilitar(Militar militar) {
		this.militarLogin = militar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_requisicao == null) ? 0 : id_requisicao.hashCode());
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
		Requisicao other = (Requisicao) obj;
		if (id_requisicao == null) {
			if (other.id_requisicao != null)
				return false;
		} else if (!id_requisicao.equals(other.id_requisicao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requisicao [id_requisicao=" + id_requisicao + ", atendido=" + atendido + ", data_requisicao="
				+ data_requisicao + ", hora_requisicao=" + hora_requisicao + ", quantidade_requisicao="
				+ quantidade_requisicao + ", produto=" + produto + ", viatura=" + viatura + ", om="
				+ om + ", login=" + login + ", militarLogin=" + militarLogin + "]";
	}
	
	
	
}
