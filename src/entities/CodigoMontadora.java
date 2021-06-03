/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class CodigoMontadora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_codigo_montadora;
	private String codigo_montadora;
	private Montadora montadora;
	private Produto produto;

	public CodigoMontadora() {

	}
	
	public CodigoMontadora(Integer id_codigo_montadora, String codigo_montadora, Montadora montadora, Produto produto) {
		super();
		this.id_codigo_montadora = id_codigo_montadora;
		this.codigo_montadora = codigo_montadora;
		this.montadora = montadora;
		this.produto = produto;
	}
	
	public Integer getId_codigo_montadora() {
		return id_codigo_montadora;
	}

	public void setId_codigo_montadora(Integer id_codigo_montadora) {
		this.id_codigo_montadora = id_codigo_montadora;
	}

	public String getCodigo_montadora() {
		return codigo_montadora;
	}

	public void setCodigo_montadora(String codigo_montadora) {
		this.codigo_montadora = codigo_montadora;
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
		result = prime * result + ((id_codigo_montadora == null) ? 0 : id_codigo_montadora.hashCode());
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
		CodigoMontadora other = (CodigoMontadora) obj;
		if (id_codigo_montadora == null) {
			if (other.id_codigo_montadora != null)
				return false;
		} else if (!id_codigo_montadora.equals(other.id_codigo_montadora))
			return false;
		return true;
	}

}
