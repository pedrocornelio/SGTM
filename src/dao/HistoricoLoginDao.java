package dao;

import java.util.List;

import entities.HistoricoLogin;

public interface HistoricoLoginDao {
	
	void insert (HistoricoLogin obj);
	
	List<HistoricoLogin> findAll ();
	
}
