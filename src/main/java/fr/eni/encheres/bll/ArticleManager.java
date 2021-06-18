package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
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
			
			//il faut gérer la conformité des dates : date début > date du jour et date fin > date début
			
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