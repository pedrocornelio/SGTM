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

public class HistoricoEdicaoProduto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_historico_edicao_produto;
	private Date data_edicao;
	private Time hora_edicao;
	private String descricao_anterior;
	private Produto id_produto_anterior;
	private Originalidade id_originalidade_anterior;
	private Medida id_medida_anterior;
	private CodigoMontadora id_codigo_montadora_anterior;
	private CodigoRecebido id_codigo_recebido_anterior;
	private Fornecedor id_fornecedor_anterior;
	private Login id_login;
	
	public HistoricoEdicaoProduto () {
		
	}
	
	public HistoricoEdicaoProduto(Integer id_historico_edicao_produto, Date data_edicao, Time hora_edicao,
			String descricao_anterior, Produto id_produto_anterior, Originalidade id_originalidade_anterior,
			Medida id_medida_anterior, CodigoMontadora id_codigo_montadora_anterior,
			CodigoRecebido id_codigo_recebido_anterior, Fornecedor id_fornecedor_anterior, Login id_login) {
		super();
		this.id_historico_edicao_produto = id_historico_edicao_produto;
		this.data_edicao = data_edicao;
		this.hora_edicao = hora_edicao;
		this.descricao_anterior = descricao_anterior;
		this.id_produto_anterior = id_produto_anterior;
		this.id_originalidade_anterior = id_originalidade_anterior;
		this.id_medida_anterior = id_medida_anterior;
		this.id_codigo_montadora_anterior = id_codigo_montadora_anterior;
		this.id_codigo_recebido_anterior = id_codigo_recebido_anterior;
		this.id_fornecedor_anterior = id_fornecedor_anterior;
		this.id_login = id_login;
	}
	
	public Integer getId_historico_edicao_produto() {
		return id_historico_edicao_produto;
	}

	public void setId_historico_edicao_produto(Integer id_historico_edicao_produto) {
		this.id_historico_edicao_produto = id_historico_edicao_produto;
	}

	public Date getData_edicao() {
		return data_edicao;
	}

	public void setData_edicao(Date data_edicao) {
		this.data_edicao = data_edicao;
	}

	public Time getHora_edicao() {
		return hora_edicao;
	}

	public void setHora_edicao(Time hora_edicao) {
		this.hora_edicao = hora_edicao;
	}

	public String getDescricao_anterior() {
		return descricao_anterior;
	}

	public void setDescricao_anterior(String descricao_anterior) {
		this.descricao_anterior = descricao_anterior;
	}

	
	public Produto getId_produto_anterior() {
		return id_produto_anterior;
	}

	public void setId_produto_anterior(Produto id_produto_anterior) {
		this.id_produto_anterior = id_produto_anterior;
	}

	public Originalidade getId_originalidade_anterior() {
		return id_originalidade_anterior;
	}

	public void setId_originalidade_anterior(Originalidade id_originalidade_anterior) {
		this.id_originalidade_anterior = id_originalidade_anterior;
	}

	public Medida getId_medida_anterior() {
		return id_medida_anterior;
	}

	public void setId_medida_anterior(Medida id_medida_anterior) {
		this.id_medida_anterior = id_medida_anterior;
	}

	public CodigoMontadora getId_codigo_montadora_anterior() {
		return id_codigo_montadora_anterior;
	}

	public void setId_codigo_montadora_anterior(CodigoMontadora id_codigo_montadora_anterior) {
		this.id_codigo_montadora_anterior = id_codigo_montadora_anterior;
	}

	public CodigoRecebido getId_codigo_recebido_anterior() {
		return id_codigo_recebido_anterior;
	}

	public void setId_codigo_recebido_anterior(CodigoRecebido id_codigo_recebido_anterior) {
		this.id_codigo_recebido_anterior = id_codigo_recebido_anterior;
	}

	public Fornecedor getId_fornecedor_anterior() {
		return id_fornecedor_anterior;
	}

	public void setId_fornecedor_anterior(Fornecedor id_fornecedor_anterior) {
		this.id_fornecedor_anterior = id_fornecedor_anterior;
	}

	public Login getId_login() {
		return id_login;
	}

	public void setId_login(Login id_login) {
		this.id_login = id_login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_historico_edicao_produto == null) ? 0 : id_historico_edicao_produto.hashCode());
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
		HistoricoEdicaoProduto other = (HistoricoEdicaoProduto) obj;
		if (id_historico_edicao_produto == null) {
			if (other.id_historico_edicao_produto != null)
				return false;
		} else if (!id_historico_edicao_produto.equals(other.id_historico_edicao_produto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HistoricoEdicaoProduto [id_historico_edicao_produto=" + id_historico_edicao_produto + ", data_edicao="
				+ data_edicao + ", hora_edicao=" + hora_edicao + ", descricao_anterior=" + descricao_anterior
				+ ", id_produto_anterior=" + id_produto_anterior + ", id_originalidade_anterior="
				+ id_originalidade_anterior + ", id_medida_anterior=" + id_medida_anterior
				+ ", id_codigo_montadora_anterior=" + id_codigo_montadora_anterior + ", id_codigo_recebido_anterior="
				+ id_codigo_recebido_anterior + ", id_fornecedor_anterior=" + id_fornecedor_anterior + ", id_login="
				+ id_login + "]";
	}

}
