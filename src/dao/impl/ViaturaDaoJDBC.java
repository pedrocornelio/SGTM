/*
 * 
 * @dev PEDRO CORNELIO
 * e-mail: pedrocornelio@gmail.com
 * 
 */

package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.ViaturaDao;
import db.DB;
import db.DbException;
import entities.Montadora;
import entities.Unidade;
import entities.Viatura;

public class ViaturaDaoJDBC implements ViaturaDao {
	
	private Connection conn;

	public ViaturaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Viatura instantiateViatura (ResultSet rs, Montadora montadora, Unidade unidade) throws SQLException {
		Viatura viatura = new Viatura();
		viatura.setId_viatura(rs.getInt("id_viatura"));
		viatura.setPlaca(rs.getString("placa"));
		viatura.setAno(rs.getString("ano"));
		viatura.setChassi(rs.getString("chassi"));
		viatura.setModelo(rs.getString("modelo"));
		viatura.setMontadora(montadora);
		viatura.setUnidade(unidade);
		return viatura;
	}
	
	private Montadora instantiateMontadora(ResultSet rs) throws SQLException {
		Montadora montadora = new Montadora();
		montadora.setIdMontadora(rs.getInt("id_montadora"));
		montadora.setNomeMontadora(rs.getString("nome_montadora"));
		return montadora;
	}
	
	private Unidade instantiateUnidade (ResultSet rs) throws SQLException {
		Unidade unidade = new Unidade();
		unidade.setIdUnidade(rs.getInt("id_unidade"));
		unidade.setNomeUnidade(rs.getString("unidade"));
		return unidade;
	}
	
	//instanciar hodômetro
	@Override
	public void insert(Viatura viatura) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO viatura (placa, modelo, ano, chassi, id_montadora, id_unidade) VALUE (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, viatura.getPlaca());
			pst.setString(2, viatura.getModelo());
			pst.setString(3, viatura.getAno());
			pst.setString(4, viatura.getChassi());
			pst.setInt(5, viatura.getMontadora().getIdMontadora());
			pst.setInt(6, viatura.getUnidade().getIdUnidade());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				viatura.setId_viatura(id);
			} else {
				throw new DbException("ERRO DE INSERÇÃO DE VIATURA!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public void updateViatura(Viatura viatura) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE viatura SET placa = ?, modelo = ?, ano = ?, chassi = ?, id_montadora = ?, id_unidade = ? where id_viatura = ?;");
			pst.setString(1, viatura.getPlaca());
			pst.setString(2, viatura.getModelo());
			pst.setString(3, viatura.getAno());
			pst.setString(4, viatura.getChassi());
			pst.setInt(5, viatura.getMontadora().getIdMontadora());
			pst.setInt(6, viatura.getUnidade().getIdUnidade());
			pst.setInt(7, viatura.getId_viatura());

			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(pst);
		}
	}
	
	@Override
	public Viatura findById(Integer id_viatura) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT v.id_viatura, v.placa, trim(v.modelo) 'modelo', v.ano, v.chassi, v.id_montadora, v.id_unidade, u.unidade, v.id_hodometro, m.nome_montadora \r\n"
					+ "FROM viatura as v\r\n"
					+ "JOIN montadora as m\r\n"
					+ "ON m.id_montadora = v.id_montadora\r\n"
					+ "LEFT JOIN unidade as u\r\n"
					+ "ON u.id_unidade = v.id_unidade "
					+ "WHERE v.id_viatura = ?");
			pst.setInt(1, id_viatura);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
				Montadora montadora = instantiateMontadora(rs);
				Unidade unidade = instantiateUnidade(rs);
				Viatura viatura = instantiateViatura(rs, montadora, unidade);
				
				return viatura;
			}
			
			JOptionPane.showMessageDialog(null, "VIATURA NÃO ENCONTRADA, CONFIRA O QUE FOI DIGITADO", "VIATURA",0);
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public Viatura findByPlaca(String placa) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("SELECT v.id_viatura, v.placa, trim(v.modelo) 'modelo', v.ano, v.chassi, v.id_montadora, v.id_unidade, u.unidade, v.id_hodometro, m.nome_montadora \r\n"
					+ "FROM viatura as v\r\n"
					+ "JOIN montadora as m\r\n"
					+ "ON m.id_montadora = v.id_montadora\r\n"
					+ "LEFT JOIN unidade as u\r\n"
					+ "ON u.id_unidade = v.id_unidade WHERE v.id_montadora = m.id_montadora AND v.placa = ?");
			pst.setString(1, placa);
			
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
				Montadora montadora = instantiateMontadora(rs);
				Unidade unidade = instantiateUnidade(rs);
				Viatura viatura = instantiateViatura(rs, montadora, unidade);
				
				return viatura;
			}
			
			JOptionPane.showMessageDialog(null, "PLACA NÃO ENCONTRADA, CONFIRA O QUE FOI DIGITADO", "VIATURA",0);
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public DefaultTableModel tableViatura(DefaultTableModel model) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		model.addColumn("REF");
		model.addColumn("PLACA");
		model.addColumn("MODELO");
		model.addColumn("ANO");
		model.addColumn("CHASSI");
		model.addColumn("MONTADORA");
		model.addColumn("UNIDADE");

		try {

			pst = conn.prepareStatement("SELECT v.id_viatura 'REF', v.placa 'PLACA', trim(v.modelo) 'MODELO', v.ano 'ANO', v.chassi 'CHASSI', m.nome_montadora 'MONTADORA', u.unidade 'UNIDADE'\r\n"
					+ "FROM viatura as v\r\n"
					+ "JOIN montadora as m\r\n"
					+ "ON m.id_montadora = v.id_montadora\r\n"
					+ "LEFT JOIN unidade as u\r\n"
					+ "ON u.id_unidade = v.id_unidade;");
			rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("REF"),
						rs.getString("PLACA"),
						rs.getString("MODELO"),
						rs.getString("ANO"),
						rs.getString("CHASSI"),
						rs.getString("MONTADORA"),
						rs.getString("UNIDADE"),
						});
			}
			return model;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}
}
