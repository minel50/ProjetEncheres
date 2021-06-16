package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	//Instructions SQL
	private static final String sqlInsert = "INSERT INTO categories(libelle) VALUES(?);";
	private static final String sqlSelectById = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?;";
	private static final String sqlSelectAll = "SELECT no_categorie, libelle FROM categories = ?;";
	private static final String sqlUpdate = "UPDATE categories SET libelle=? WHERE no_categorie = ?;";
	private static final String sqlDelete = "DELETE FROM categories WHERE no_categorie = ?;";
	
	@Override
	public void insert(Categorie categorie) throws BusinessException {
		
		if (categorie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		PreparedStatement pstmt = null;
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			pstmt = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());
			
			int nbRows = pstmt.executeUpdate();
			
			if(nbRows == 1) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					categorie.setNoCategorie(rs.getInt(1));
				}
			}
			
			
		} catch(Exception e) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public List<Categorie> selectAll() throws BusinessException {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Categorie> liste = new ArrayList<Categorie>();
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlSelectAll);
			
			while(rs.next()) {
				Categorie cat = new Categorie(rs.getInt("no_categorie"),
												rs.getString("libelle"));
				liste.add(cat);
			}
		} catch(Exception e) 
		{
			// Ajt erreur exception si echec
		} finally 
		{
			try {
				if(rs != null) {
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
				throw businessException;
			}
		}
		
		return liste;
	}
	
	@Override
	public Categorie selectById(int id) throws BusinessException {
		Categorie cat = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sqlSelectById);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cat = new Categorie(
						rs.getInt("no_categorie"),
						rs.getString("libelle")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
			throw businessException;
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return cat;
	}
	
	
	@Override
	public void update(Categorie cat) throws BusinessException {
		if (cat == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sqlUpdate);
			pstmt.setString(1, cat.getLibelle());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UDPATE_DATA_ECHEC);
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				
				if (con != null) {
					con.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	public void delete(Categorie cat) throws BusinessException {
		
		if (cat == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sqlDelete);
			pstmt.setInt(1, cat.getNoCategorie());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_DATA_ECHEC);
			throw businessException;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
