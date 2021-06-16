package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	//Instructions SQL
	private static final String sqlInsert = "INSERT INTO retraits(no_article, rue, code_postal, ville) VALUES(?, ?, ?, ?);";
	private static final String sqlSelectById = "SELECT no_article, rue, code_postal, ville FROM retraits WHERE no_article = ?;";
	private static final String sqlSelectAll = "SELECT no_article, libelle FROM retraits = ?;";
	private static final String sqlUpdate = "UPDATE retraits SET rue=?, code_postal=?, ville=? WHERE no_article = ?;";
	private static final String sqlDelete = "DELETE FROM retraits WHERE no_article = ?;";
	
	private ArticleDAO articleDAO = DAOFactory.getArticleDAO();
	
	@Override
	public void insert(Retrait retrait) throws BusinessException {
		
		if(retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		PreparedStatement pstmt = null;
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setInt(1, retrait.getArticle().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());
			
			pstmt.executeUpdate();
			
		} catch(Exception e) 
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		} finally {
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
	public List<Retrait> selectAll() throws BusinessException {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Retrait> liste = new ArrayList<Retrait>();
		
		try(Connection con = ConnectionProvider.getConnection())
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(sqlSelectAll);
			
			while(rs.next()) {
				Retrait retrait = new Retrait(articleDAO.selectById(rs.getInt("no_article")),
												rs.getString("rue"),
												rs.getString("code_postal"),
												rs.getString("ville"));
				liste.add(retrait);
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
	public Retrait selectById(int id) throws BusinessException {
		Retrait retrait = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sqlSelectById);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				retrait = new Retrait(
						articleDAO.selectById(rs.getInt("no_article")),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
						);
			}
			
		} catch (SQLException e) {
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
		
		
		return retrait;
	}
	
	@Override
	public void update(Retrait retrait) throws BusinessException {
		
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(sqlUpdate);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getArticle().getNoArticle());
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
		public void delete(Retrait retrait) throws BusinessException {
			
			if (retrait == null) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
				throw businessException;
			}
			
			Connection cnx = null;
			PreparedStatement stmt = null;
			
			try {
				cnx = ConnectionProvider.getConnection();
				stmt = cnx.prepareStatement(sqlDelete);
				stmt.setInt(1, retrait.getArticle().getNoArticle());
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_DATA_ECHEC);
				throw businessException;
				
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (cnx != null) {
						cnx.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	
	
}
