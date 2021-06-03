/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Aplicacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idAplicacao;
	private String modelo;
	private String ano;
	private Montadora montadora;
	private Produto produto;
	private Integer id_produto;
	

	public Aplicacao() {
	}
		
	public Aplicacao(String modelo, String ano, Montadora montadora, Produto produto) {
		super();
		this.modelo = modelo;
		this.ano = ano;
		this.montadora = montadora;
		this.produto = produto;
	}
	
	public Aplicacao(String modelo, String ano, Montadora montadora, Integer id_produto) {
		super();
		this.modelo = modelo;
		this.ano = ano;
		this.montadora = montadora;
		this.setId_produto(id_produto);
	}

	public Integer getIdAplicacao() {
		return idAplicacao;
	}

	public void setIdAplicacao(Integer idAplicacao) {
		this.idAplicacao = idAplicacao;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
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
		result = prime * result + ((idAplicacao == null) ? 0 : idAplicacao.hashCode());
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
		Aplicacao other = (Aplicacao) obj;
		if (idAplicacao == null) {
			if (other.idAplicacao != null)
				return false;
		} else if (!idAplicacao.equals(other.idAplicacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aplicacao [idAplicacao=" + idAplicacao + ", modelo=" + modelo + ", ano=" + ano + ", montadora="
				+ montadora + ", produto=" + produto + "]";
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}


	
	
}
