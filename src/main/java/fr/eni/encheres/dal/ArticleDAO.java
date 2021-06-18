package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public void insert(Article article) throws BusinessException;
	public List<Article> selectAll() throws BusinessException;
	public Article selectById(int id) throws BusinessException;
	public void update(Article article) throws BusinessException;
	public void delete(Article article) throws BusinessException;
	public List<Article> selectArticlesEnVente() throws BusinessException;
	public List<Article> selectArticlesEnVenteAvecFiltre(String filtre) throws BusinessException;
	List<Article> selectArticlesVendusByUtilisateur(int idUtilisateur) throws BusinessException;
	List<Article> selectArticlesAchetesByUtilisateur(int idUtilisateur) throws BusinessException;
}
