/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao;

import dao.impl.AplicacaoDaoJDBC;
import dao.impl.CodigoMontadoraDaoJDBC;
import dao.impl.CodigoRecebidoDaoJDBC;
import dao.impl.FornecedorDaoJDBC;
import dao.impl.HistoricoEdicaoProdutoDaoJDBC;
import dao.impl.HistoricoEdicaoViaturaDaoJDBC;
import dao.impl.HistoricoLoginDaoJDBC;
import dao.impl.HistoricoProdutoEntradaDaoJDBC;
import dao.impl.HistoricoProdutoSaidaDaoJDBC;
import dao.impl.LocalizacaoDaoJDBC;
import dao.impl.LoginDaoJDBC;
import dao.impl.MedidaDaoJDBC;
import dao.impl.MilitarDaoJDBC;
import dao.impl.MontadoraDaoJDBC;
import dao.impl.OrdemManutencaoDaoJDBC;
import dao.impl.OriginalidadeDaoJDBC;
import dao.impl.ProdutoDaoJDBC;
import dao.impl.RequisicaoDaoJDBC;
import dao.impl.ServicoDaoJDBC;
import dao.impl.TipoServicoDaoJDBC;
import dao.impl.UnidadeDaoJDBC;
import dao.impl.ViaturaDaoJDBC;
import db.DB;

public class DaoFactory {

	public static MilitarDao createMilitarDao() {
		return new MilitarDaoJDBC(DB.getConnection());
	}
	
	public static LoginDao createLoginDao() {
		return new LoginDaoJDBC(DB.getConnection());
	}
	
	public static HistoricoLoginDao createHistoricoLoginDao() {
		return new HistoricoLoginDaoJDBC(DB.getConnection());
	}
	
	@SuppressWarnings("unchecked")
	public static MontadoraDao<Object> createMontadoraDao() {
		return new MontadoraDaoJDBC(DB.getConnection());
	}
	
	public static UnidadeDao createUnidadeDao() {
		return new UnidadeDaoJDBC(DB.getConnection());
	}
	
	public static LocalizacaoDao createLocalizacaoDao() {
		return new LocalizacaoDaoJDBC(DB.getConnection());
	}
	
	public static AplicacaoDao createAplicacaoDao() {
		return new AplicacaoDaoJDBC(DB.getConnection());
	}
	
	public static OriginalidadeDao createOriginalidadeDao() {
		return new OriginalidadeDaoJDBC(DB.getConnection());
	}

	public static ProdutoDao createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());
	}
	
	public static HistoricoProdutoEntradaDao createHistoricoProdutoEntradaDao() {
		return new HistoricoProdutoEntradaDaoJDBC(DB.getConnection());
	}
	
	public static HistoricoProdutoSaidaDao createHistoricoProdutoSaidaDao() {
		return new HistoricoProdutoSaidaDaoJDBC(DB.getConnection());
	}
	
	public static FornecedorDao createFornecedorDao() {
		return new FornecedorDaoJDBC(DB.getConnection());
	}
	
	public static ViaturaDao createViaturaDao() {
		return new ViaturaDaoJDBC(DB.getConnection());
	}
	
	public static RequisicaoDao createRequisicaoDao() {
		return new RequisicaoDaoJDBC(DB.getConnection());
	}
	
	public static OrdemManutencaoDao createOrdemServicoDao() {
		return new OrdemManutencaoDaoJDBC(DB.getConnection());
	}
	
	public static MedidaDao createMedidaDao() {
		return new MedidaDaoJDBC(DB.getConnection());
	}
	
	public static CodigoMontadoraDao createCodigoMontadoraDao() {
		return new CodigoMontadoraDaoJDBC(DB.getConnection());
	}
	
	public static CodigoRecebidoDao createCodigoRecebidoDao() {
		return new CodigoRecebidoDaoJDBC(DB.getConnection());
	}
	
	public static HistoricoEdicaoViaturaDao createHistoricoEdicaoViaturaDao() {
		return new HistoricoEdicaoViaturaDaoJDBC(DB.getConnection());
	}
	
	public static HistoricoEdicaoProdutoDao createHistoricoEdicaoProdutoDao() {
		return new HistoricoEdicaoProdutoDaoJDBC(DB.getConnection());
	}
	
	public static ServicoDao createServicoDao() {
		return new ServicoDaoJDBC(DB.getConnection());
	}
	
	public static TipoServicoDao createTipoServicoDao() {
		return new TipoServicoDaoJDBC(DB.getConnection());
	}
}
