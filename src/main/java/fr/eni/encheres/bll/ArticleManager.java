package fr.eni.encheres.bll;

import java.util.Date;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	private static ArticleDAO articleDAO;
	
	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<Article> getListeArticles() throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectAll();
		return listeArticles;
	}
	
	public List<Article> getListeArticlesEnVente(String filtreNom, Integer noCategorie) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesEnVente(filtreNom, noCategorie);
		return listeArticles;
	}
	
	public List<Article> getListeArticlesVendusParUtilisateur(int idUtilisateur) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesVendusByUtilisateur(idUtilisateur);
		return listeArticles;
	}
	
	public List<Article> getListeArticlesAchetesParUtilisateur(int idUtilisateur) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesVendusByUtilisateur(idUtilisateur);
		return listeArticles;
	}
	
	public Article getArticle(int id) throws BusinessException {
		Article article = null;
		article = articleDAO.selectById(id);
		return article;
	}
	
	public void addArticle(Article article) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.validerArticle(article, exception);
		
		if (!exception.hasErreurs()) {
			articleDAO.insert(article);	
		} else {
			throw exception;
		}

	}
	
	public void updateArticle(Article article) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.validerArticle(article, exception);
		
		if (!exception.hasErreurs()) {
			articleDAO.update(article);
		} else {
			throw exception;
		}
		
	}
	
	public void deleteArticle(Article article) throws BusinessException {
		articleDAO.delete(article);
	}
	
	public List<Article> getListeArticlesVenteEnCoursParUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesVenteEnCoursByUtilisateur(utilisateur, filtreNom, noCategorie);
		return listeArticles;
	}
	
	public List<Article> getListeArticlesVenteNonDebuteeParUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesVenteNonDebuteeByUtilisateur(utilisateur, filtreNom, noCategorie);
		return listeArticles;
	}
	
	public List<Article> getListeArticlesVenteTermineeParUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException {
		List<Article> listeArticles = null;
		listeArticles = articleDAO.selectArticlesVenteTermineeByUtilisateur(utilisateur, filtreNom, noCategorie);
		return listeArticles;
	}
	
	public void validerArticle(Article a, BusinessException exception) throws BusinessException {

		if (a == null) {
			exception.ajouterErreur(CodesResultatBLL.OBJECT_NULL);
		} else {
			
			if (a.getNomArticle() == null || a.getNomArticle().trim().length() == 0 || a.getNomArticle().length() > 30) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_NOM_ARTICLE);
			}
			
			if (a.getDescription() == null || a.getDescription().trim().length() == 0 || a.getDescription().length() > 300) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_DESCRIPTION_ARTICLE);
			}
			
			if (a.getDateDebutEncheres() == null || a.getDateFinEncheres() == null) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_DATE_ENCHERES_NULL);
			}
			
			//Erreur si date de début dans le passé
			if (a.getDateDebutEncheres().before(new Date())) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_DATE_DEBUT_ENCHERE);
			}
			
			//Erreur si date de fin antérieure à la date de début
			if (a.getDateFinEncheres().before(a.getDateDebutEncheres())) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_DATE_FIN_ENCHERE);
			}
						
			if (a.getPrixInitial() < 0 || a.getPrixVente() < 0) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_PRIX_NEGATIF);
			}
			
			if (a.getUtilisateur() == null) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_MANQUE_UTILISATEUR);
			}
			
			if (a.getCategorie() == null) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_MANQUE_CATEGORIE);
			}
		}
		
	}
	
}