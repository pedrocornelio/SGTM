/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Version implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String versionAlmoxarifado;
	private String versionOficina;
	private String versionGerencia;
	private String versionCompras;
	
	public Version(String versionAlmoxarifado, String versionOficina, String versionGerencia, String versionCompras) {
		super();
		this.versionAlmoxarifado = versionAlmoxarifado;
		this.versionOficina = versionOficina;
		this.versionGerencia = versionGerencia;
		this.versionCompras = versionCompras;
	}
	
	public String getVersionAlmoxarifado() {
		return versionAlmoxarifado;
	}
	public void setVersionAlmoxarifado(String versionAlmoxarifado) {
		this.versionAlmoxarifado = versionAlmoxarifado;
	}
	public String getVersionOficina() {
		return versionOficina;
	}
	public void setVersionOficina(String versionOficina) {
		this.versionOficina = versionOficina;
	}
	public String getVersionGerencia() {
		return versionGerencia;
	}
	public void setVersionGerencia(String versionGerencia) {
		this.versionGerencia = versionGerencia;
	}
	public String getVersionCompras() {
		return versionCompras;
	}
	public void setVersionCompras(String versionCompras) {
		this.versionCompras = versionCompras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((versionAlmoxarifado == null) ? 0 : versionAlmoxarifado.hashCode());
		result = prime * result + ((versionCompras == null) ? 0 : versionCompras.hashCode());
		result = prime * result + ((versionGerencia == null) ? 0 : versionGerencia.hashCode());
		result = prime * result + ((versionOficina == null) ? 0 : versionOficina.hashCode());
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
		Version other = (Version) obj;
		if (versionAlmoxarifado == null) {
			if (other.versionAlmoxarifado != null)
				return false;
		} else if (!versionAlmoxarifado.equals(other.versionAlmoxarifado))
			return false;
		if (versionCompras == null) {
			if (other.versionCompras != null)
				return false;
		} else if (!versionCompras.equals(other.versionCompras))
			return false;
		if (versionGerencia == null) {
			if (other.versionGerencia != null)
				return false;
		} else if (!versionGerencia.equals(other.versionGerencia))
			return false;
		if (versionOficina == null) {
			if (other.versionOficina != null)
				return false;
		} else if (!versionOficina.equals(other.versionOficina))
			return false;
		return true;
	}	
	
}
