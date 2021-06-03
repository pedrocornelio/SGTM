package dao.impl;

import java.sql.Connection;


import dao.TipoServicoDao;

public class TipoServicoDaoJDBC implements TipoServicoDao {

	private Connection conn;

	public TipoServicoDaoJDBC (Connection conn) {
		this.conn = conn;
	}


	
}
