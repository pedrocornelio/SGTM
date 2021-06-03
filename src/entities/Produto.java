/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_produto;
	private String descricao;
	private Originalidade originalidade;
	private Integer quantidade;
	private Localizacao localizacao;
	private Integer quantidade_minima;
	private Medida medida;

	public Produto() {

	}

	public Produto(Integer id_produto, String descricao, Originalidade originalidade, Integer quantidade,
			Localizacao localizacao, Integer quantidade_minima, Medida medida) {
		this.id_produto = id_produto;
		this.descricao = descricao;
		this.originalidade = originalidade;
		this.quantidade = quantidade;
		this.localizacao = localizacao;
		this.quantidade_minima = quantidade_minima;
		this.medida = medida;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public Integer getId_produtoGeneretadeKeys(int returnGeneratedKeys) {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Originalidade getOriginalidade() {
		return originalidade;
	}

	public void setOriginalidade(Originalidade originalidade) {
		this.originalidade = originalidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Integer getQuantidade_minima() {
		return quantidade_minima;
	}

	public void setQuantidade_minima(Integer quantidade_minima) {
		this.quantidade_minima = quantidade_minima;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_produto == null) ? 0 : id_produto.hashCode());
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
		Produto other = (Produto) obj;
		if (id_produto == null) {
			if (other.id_produto != null)
				return false;
		} else if (!id_produto.equals(other.id_produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", descricao=" + descricao + ", originalidade=" + originalidade
				+ ", quantidade=" + quantidade + ", localizacao=" + localizacao + ", quantidade_minima="
				+ quantidade_minima + ", medida=" + medida + "]";
	}

}
