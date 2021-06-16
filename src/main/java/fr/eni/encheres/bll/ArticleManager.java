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
	
	public Article getArticle(int id) throws BusinessException {
		Article article = null;
		article = articleDAO.selectById(id);
		return article;
	}
	
	public void addArticle(Article article) throws BusinessException {
		if (this.validerArticle(article)) {
			articleDAO.insert(article);
		}
	}
	
	public boolean validerArticle(Article a) {
		boolean ok = true;
		
		if (a == null) {
			ok = false;
		}
		
		if (a.getNomArticle() == null || a.getNomArticle().trim().length() == 0) {
			ok = false;
		}
			
		if (a.getDescription() == null || a.getDescription().trim().length() == 0) {
			ok = false;
		}
		
		return ok;
	}
}
