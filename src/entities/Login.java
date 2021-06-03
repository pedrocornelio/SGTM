/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package entities;

import java.io.Serializable;

public class Login implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idlogin;
	private String nome;
	private String nBM;
	private String senha;
	private boolean liberarAcesso;
	private boolean almoxHist;
	private boolean almoxEdicao;
	private boolean almoxAdmin;
	private boolean oficina;
	private boolean gerencia;
	private boolean compras;

	public Login() {
	}

	public Login(Integer idlogin, String nome, String nBM, String senha, boolean liberarAcesso, boolean almoxHist,
			boolean almoxEdicao, boolean almoxAdmin, boolean oficina, boolean gerencia, boolean compras) {
		this.idlogin = idlogin;
		this.nome = nome;
		this.nBM = nBM;
		this.senha = senha;
		this.liberarAcesso = liberarAcesso;
		this.almoxHist = almoxHist;
		this.almoxEdicao = almoxEdicao;
		this.almoxAdmin = almoxAdmin;
		this.oficina = oficina;
		this.gerencia = gerencia;
		this.compras = compras;
	}

	public Integer getIdlogin() {
		return idlogin;
	}

	public void setIdlogin(Integer idlogin) {
		this.idlogin = idlogin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getnBM() {
		return nBM;
	}

	public void setnBM(String nBM) {
		this.nBM = nBM;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getLiberarAcesso() {
		return liberarAcesso;
	}

	public boolean setLiberarAcesso(Boolean liberarAcesso) {
		this.liberarAcesso = liberarAcesso;
		return this.liberarAcesso;
	}

	public boolean getAlmoxHist() {
		return almoxHist;
	}

	public void setAlmoxHist(boolean almoxHist) {
		this.almoxHist = almoxHist;
	}

	public boolean getAlmoxEdicao() {
		return almoxEdicao;
	}

	public void setAlmoxEdicao(boolean almoxEdicao) {
		this.almoxEdicao = almoxEdicao;
	}

	public boolean getAlmoxAdmin() {
		return almoxAdmin;
	}

	public void setAlmoxAdmin(boolean almoxAdmin) {
		this.almoxAdmin = almoxAdmin;
	}

	public boolean getOficina() {
		return oficina;
	}

	public void setOficina(boolean oficina) {
		this.oficina = oficina;
	}

	public boolean getGerencia() {
		return gerencia;
	}

	public void setGerencia(boolean gerencia) {
		this.gerencia = gerencia;
	}

	public boolean getCompras() {
		return compras;
	}

	public void setCompras(boolean compras) {
		this.compras = compras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idlogin == null) ? 0 : idlogin.hashCode());
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
		Login other = (Login) obj;
		if (idlogin == null) {
			if (other.idlogin != null)
				return false;
		} else if (!idlogin.equals(other.idlogin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [idlogin=" + idlogin + ", nome=" + nome + ", nBM=" + nBM + ", senha=" + senha + ", liberarAcesso="
				+ liberarAcesso + ", almoxHist=" + almoxHist + ", almoxEdicao=" + almoxEdicao + ", almoxAdmin="
				+ almoxAdmin + ", oficina=" + oficina + ", gerencia=" + gerencia + ", compras=" + compras + "]";
	}

}
