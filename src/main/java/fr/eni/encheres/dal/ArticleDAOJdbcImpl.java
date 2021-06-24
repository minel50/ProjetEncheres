package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String sqlInsert = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sqlSelectAll = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	
	private static final String sqlSelectById = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	private static final String sqlSelectArticlesEnVente = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS\r\n"
															+ "WHERE date_debut_encheres <= CONVERT (datetime2, GETDATE()) AND date_fin_encheres >= CONVERT (datetime2, GETDATE())";
	private static final String sqlExtensionFiltreNom = " AND nom_article LIKE ? COLLATE French_CI_AI";
	private static final String sqlExtensionFiltreCategorie = " AND no_categorie = ?";
	
	private static final String sqlSelectArticlesVendusByUtilisateur = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	
	private static final String sqlSelectArticlesAchetesByUtilisateur = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie\r\n"
																		+ "FROM ARTICLES_VENDUS a INNER JOIN ENCHERES e ON a.no_article = e.no_article\r\n"
																		+ "WHERE e.no_utilisateur = ? AND a.prix_vente = e.montant_enchere;";
	
	private static final String sqlUpdate = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article = ?";
	
	private static final String sqlDelete = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
	
	
	
	@Override
	public void insert(Article article) throws BusinessException {
		
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setTimestamp(3, new Timestamp(article.getDateDebutEncheres().getTime()));
			stmt.setTimestamp(4, new Timestamp(article.getDateFinEncheres().getTime()));
			stmt.setInt(5, article.getPrixInitial());
			stmt.setInt(6, article.getPrixVente());
			stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.setInt(8, article.getCategorie().getNoCategorie());
			
			int nbRows = stmt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
			}
			
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
	public List<Article> selectAll() throws BusinessException {
		List<Article> listeArticles = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectAll);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeArticles.add(new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						"création",
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						categorieDAO.selectById(rs.getInt("no_categorie"))
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
		
		return listeArticles;
	}

	
	
	@Override
	public Article selectById(int id) throws BusinessException {
		Article article = null;
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectById);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				article = new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						"création",
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						categorieDAO.selectById(rs.getInt("no_categorie"))
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
		
		return article;
	}
	
	
	@Override
	public List<Article> selectArticlesEnVente(String filtreNom, Integer noCategorie) throws BusinessException {
		List<Article> listeArticles = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		//Construction de la requête en fonction des paramètres d'entrée
		int index = 1; //pour pointer sur le bon "?" des requêtes préparées
		StringBuilder requete = new StringBuilder();
		requete.append(sqlSelectArticlesEnVente);
		if (filtreNom != null) {
			requete.append(sqlExtensionFiltreNom);
		}
		if (noCategorie != null && noCategorie != 0) {
			requete.append(sqlExtensionFiltreCategorie);
		}
		
		try {
			cnx = ConnectionProvider.getConnection();
			
			stmt = cnx.prepareStatement(requete.toString());
			if (filtreNom != null) {
				stmt.setString(index, "%" + filtreNom + "%");
				index += 1;
			}
			if (noCategorie != null  && noCategorie != 0) {
				stmt.setInt(index, noCategorie);
				index += 1;
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeArticles.add(new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						"création",
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						categorieDAO.selectById(rs.getInt("no_categorie"))
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
		
		return listeArticles;
	}
	
	@Override
	public List<Article> selectArticlesVendusByUtilisateur(int idUtilisateur) throws BusinessException {
		List<Article> listeArticles = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectArticlesVendusByUtilisateur);
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeArticles.add(new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						"création",
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						categorieDAO.selectById(rs.getInt("no_categorie"))
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
		
		return listeArticles;
	}
	
	@Override
	public List<Article> selectArticlesAchetesByUtilisateur(int idUtilisateur) throws BusinessException {
		List<Article> listeArticles = new ArrayList<>();
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlSelectArticlesAchetesByUtilisateur);
			stmt.setInt(1, idUtilisateur);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				listeArticles.add(new Article(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						"création",
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						categorieDAO.selectById(rs.getInt("no_categorie"))
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
		
		return listeArticles;
	}

	@Override
	public void update(Article article) throws BusinessException {
		
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_NULL);
			throw businessException;
		}
		
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlUpdate);
			stmt.setString(1, article.getNomArticle());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, new Date(article.getDateDebutEncheres().getTime()));
			stmt.setDate(4, new Date(article.getDateFinEncheres().getTime()));
			stmt.setInt(5, article.getPrixInitial());
			stmt.setInt(6, article.getPrixVente());
			stmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			stmt.setInt(8, article.getCategorie().getNoCategorie());
			stmt.setInt(9, article.getNoArticle());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UDPATE_DATA_ECHEC);
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
	public void delete(Article article) throws BusinessException {
		
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);
			throw businessException;
		}
		
		Connection cnx = null;
		PreparedStatement stmt = null;
		
		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.prepareStatement(sqlDelete);
			stmt.setInt(1, article.getNoArticle());
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
