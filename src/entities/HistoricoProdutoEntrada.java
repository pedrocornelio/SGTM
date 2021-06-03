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

public class HistoricoProdutoEntrada implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_produto_entrada;
	private Date data_entrada;
	private Time hora_entrada;
	private Integer quantidade_entrada;
	private Produto produto;
	private Login login;
	private Fornecedor fornecedor;
	
	public HistoricoProdutoEntrada() {
	}
	
	public HistoricoProdutoEntrada(Integer id_historico_produto_entrada, Date data_entrada, Time hora_entrada, Integer quantidade_entrada, Produto produto, Login login, Fornecedor fornecedor) {
		super();
		this.id_historico_produto_entrada = id_historico_produto_entrada;
		this.data_entrada = data_entrada;
		this.hora_entrada = hora_entrada;
		this.quantidade_entrada = quantidade_entrada;
		this.produto = produto;
		this.login = login;
		this.fornecedor = fornecedor;
	}

	public Integer getId_historico_produto_entrada() {
		return id_historico_produto_entrada;
	}

	public void setId_historico_produto_entrada(Integer id_historico_produto_entrada) {
		this.id_historico_produto_entrada = id_historico_produto_entrada;
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

	public Integer getQuantidade_entrada() {
		return quantidade_entrada;
	}

	public void setQuantidade_entrada(Integer quantidade_entrada) {
		this.quantidade_entrada = quantidade_entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_historico_produto_entrada == null) ? 0 : id_historico_produto_entrada.hashCode());
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
		HistoricoProdutoEntrada other = (HistoricoProdutoEntrada) obj;
		if (id_historico_produto_entrada == null) {
			if (other.id_historico_produto_entrada != null)
				return false;
		} else if (!id_historico_produto_entrada.equals(other.id_historico_produto_entrada))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoProdutoEntrada [id_historico_produto_entrada=" + id_historico_produto_entrada
				+ ", data_entrada=" + data_entrada + ", hora_entrada=" + hora_entrada + ", quantidade_entrada="
				+ quantidade_entrada + ", produto=" + produto + ", login=" + login + ", fornecedor=" + fornecedor + "]";
	}

}
