/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Viatura implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_viatura;
	private String placa;
	private String modelo;
	private String ano;
	private String chassi;
	private Montadora montadora;
	private Unidade unidade;
	private Hodometro hodometro;
	
	public Viatura() {

	}
	
	public Viatura(Integer id_viatura, String placa, String modelo, String ano, String chassi, Montadora montadora, Unidade unidade, Hodometro hodometro) {
		super();
		this.id_viatura = id_viatura;
		this.placa = placa;
		this.montadora = montadora;
		this.modelo = modelo;
		this.ano = ano;
		this.chassi = chassi;
		this.unidade = unidade;
		this.hodometro = hodometro;
	}
	
	public Integer getId_viatura() {
		return id_viatura;
	}
	public void setId_viatura(Integer id_viatura) {
		this.id_viatura = id_viatura;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Montadora getMontadora() {
		return montadora;
	}
	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
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
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
	public Hodometro getHodometro() {
		return hodometro;
	}
	public void setHodometro(Hodometro hodometro) {
		this.hodometro = hodometro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
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
		Viatura other = (Viatura) obj;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Viatura [id_viatura=" + id_viatura + ", placa=" + placa + ", modelo=" + modelo + ", ano=" + ano
				+ ", chassi=" + chassi + ", montadora=" + montadora + ", unidade=" + unidade + ", hod�metro="
				+ hodometro + "]";
	}

}
