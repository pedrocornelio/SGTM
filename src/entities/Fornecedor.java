/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Fornecedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_fornecedor;
	private String fornecedor;
	private String nota_fiscal_fornecedor;
	private String orcamento;
	private Double preco;
	private Produto produto;

	public Fornecedor() {
	}

	public Fornecedor(String fornecedor, String nota_fiscal_fornecedor, String orcamento, Double preco, Produto produto) {
		super();
		this.fornecedor = fornecedor;
		this.nota_fiscal_fornecedor = nota_fiscal_fornecedor;
		this.orcamento = orcamento;
		this.preco = preco;
		this.produto = produto;
	}
	
	public Integer getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(Integer id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNota_fiscal_fornecedor() {
		return nota_fiscal_fornecedor;
	}

	public void setNota_fiscal_fornecedor(String nota_fiscal_fornecedor) {
		this.nota_fiscal_fornecedor = nota_fiscal_fornecedor;
	}

	public String getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(String orcamento) {
		this.orcamento = orcamento;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_fornecedor == null) ? 0 : id_fornecedor.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (id_fornecedor == null) {
			if (other.id_fornecedor != null)
				return false;
		} else if (!id_fornecedor.equals(other.id_fornecedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Fornecedor [id_fornecedor=" + id_fornecedor + ", fornecedor=" + fornecedor + ", nota_fiscal_fornecedor="
				+ nota_fiscal_fornecedor + ", orcamento=" + orcamento + ", preco=" + preco + ", produto=" + produto
				+ "]";
	}

}
