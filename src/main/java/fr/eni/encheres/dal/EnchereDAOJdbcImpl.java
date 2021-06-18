package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String sqlInsert = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	private static final String sqlSelectByUser = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String sqlSelectByArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = ?";
	private static final String sqlSelectByUtilisateurEtArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur = ? AND no_article = ?";

	@Override
	public void insert(Enchere enchere) throws BusinessException {
		
		if (enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw new BusinessException();
		}
		
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlInsert);
			stmt.setInt(1, enchere.getNoUtilisateur());
			stmt.setInt(2, enchere.getNoArticle());
			stmt.setDate(3, new Date(enchere.getDateEnchere().getTime()));
			stmt.setInt(4, enchere.getMontantEnchere());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
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

	@Override
	public List<Enchere> selectByUtilisateur(int noUtilisateur) throws BusinessException {
		Connection cnx = null;
		PreparedStatement stmt = null;
		List<Enchere> listeEncheres = new ArrayList<>();
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectByUser);
			stmt.setInt(1, noUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeEncheres.add(new Enchere(
						rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"),
						noUtilisateur,
						rs.getInt("no_article")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
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
		
		return listeEncheres;
	}

	@Override
	public List<Enchere> selectByArticle(int noArticle) throws BusinessException {
		Connection cnx = null;
		PreparedStatement stmt = null;
		List<Enchere> listeEncheres = new ArrayList<>();
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectByArticle);
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeEncheres.add(new Enchere(
						rs.getDate("date_enchere"),
						rs.getInt("montant_enchere"),
						rs.getInt("no_utilisateur"),
						noArticle
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
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
		
		return listeEncheres;
	}

	@Override
	public Enchere selectByUtilisateurEtArticle(int noUtilisateur, int noArticle) throws BusinessException {
		Connection cnx = null;
		PreparedStatement stmt = null;
		Enchere enchere = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectByUtilisateurEtArticle);
			stmt.setInt(1, noUtilisateur);
			stmt.setInt(2, noArticle);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {new Enchere(
							rs.getDate("date_enchere"),
							rs.getInt("montant_enchere"),
							noUtilisateur,
							noArticle
							);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.READ_DATA_ECHEC);
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
		
		return enchere;
	}

}
