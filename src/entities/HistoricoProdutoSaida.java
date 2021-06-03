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

public class HistoricoProdutoSaida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_produto_saida;
	private Date data_saida;
	private Time hora_saida;
	private Integer quantidade_saida;
	private Login login;
	private Produto produto;
	private Viatura viatura;
	private Unidade unidade;
	private Militar militar;
	
	public HistoricoProdutoSaida() {
		
	}
	
	public HistoricoProdutoSaida(Integer id_historico_produto_saida, Date data_saida, Time hora_saida,
			Integer quantidade_saida, Login login, Produto produto, Viatura viatura, Unidade unidade, Militar militar) {
		super();
		this.id_historico_produto_saida = id_historico_produto_saida;
		this.data_saida = data_saida;
		this.hora_saida = hora_saida;
		this.quantidade_saida = quantidade_saida;
		this.login = login;
		this.produto = produto;
		this.viatura = viatura;
		this.unidade = unidade;
		this.militar = militar;
	}

	public Integer getId_historico_produto_saida() {
		return id_historico_produto_saida;
	}

	public void setId_historico_produto_saida(Integer id_historico_produto_saida) {
		this.id_historico_produto_saida = id_historico_produto_saida;
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

	public Integer getQuantidade_saida() {
		return quantidade_saida;
	}

	public void setQuantidade_saida(Integer quantidade_saida) {
		this.quantidade_saida = quantidade_saida;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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
		result = prime * result + ((id_historico_produto_saida == null) ? 0 : id_historico_produto_saida.hashCode());
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
		HistoricoProdutoSaida other = (HistoricoProdutoSaida) obj;
		if (id_historico_produto_saida == null) {
			if (other.id_historico_produto_saida != null)
				return false;
		} else if (!id_historico_produto_saida.equals(other.id_historico_produto_saida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoProdutoSaida [id_historico_produto_saida=" + id_historico_produto_saida + ", data_saida="
				+ data_saida + ", hora_saida=" + hora_saida + ", quantidade_saida=" + quantidade_saida + ", login="
				+ login + ", produto=" + produto + ", viatura=" + viatura + ", unidade=" + unidade + ", militar="
				+ militar + "]";
	}

	
}
