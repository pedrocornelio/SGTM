/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class CodigoRecebido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_codigo_recebido;
	private String marca;
	private String codigo_recebido;
	private Produto produto;
	
	public CodigoRecebido() {

	}
	
	public CodigoRecebido(Integer id_codigo_recebido, String marca, String codigo_recebido, Produto produto) {
		super();
		this.id_codigo_recebido = id_codigo_recebido;
		this.marca = marca;
		this.codigo_recebido = codigo_recebido;
		this.produto = produto;
	}

	public Integer getId_codigo_recebido() {
		return id_codigo_recebido;
	}

	public void setId_codigo_recebido(Integer id_codigo_recebido) {
		this.id_codigo_recebido = id_codigo_recebido;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCodigo_recebido() {
		return codigo_recebido;
	}

	public void setCodigo_recebido(String codigo_recebido) {
		this.codigo_recebido = codigo_recebido;
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
		result = prime * result + ((id_codigo_recebido == null) ? 0 : id_codigo_recebido.hashCode());
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
		CodigoRecebido other = (CodigoRecebido) obj;
		if (id_codigo_recebido == null) {
			if (other.id_codigo_recebido != null)
				return false;
		} else if (!id_codigo_recebido.equals(other.id_codigo_recebido))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CodigoRecebido [id_codigo_recebido=" + id_codigo_recebido + ", marca=" + marca + ", codigo_recebido="
				+ codigo_recebido + ", produto=" + produto + "]";
	}
	
}
