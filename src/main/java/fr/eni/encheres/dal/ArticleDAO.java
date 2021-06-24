package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleDAO {
	public void insert(Article article) throws BusinessException;
	public List<Article> selectAll() throws BusinessException;
	public Article selectById(int id) throws BusinessException;
	public void update(Article article) throws BusinessException;
	public void delete(Article article) throws BusinessException;
	public List<Article> selectArticlesEnVente(String filtreNom, Integer noCategorie) throws BusinessException;
	public List<Article> selectArticlesVendusByUtilisateur(int idUtilisateur) throws BusinessException;
	public List<Article> selectArticlesAchetesByUtilisateur(int idUtilisateur) throws BusinessException;
	public List<Article> selectArticlesVenteEnCoursByUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException;
	public List<Article> selectArticlesVenteNonDebuteeByUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException;
	public List<Article> selectArticlesVenteTermineeByUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException;
	public List<Article> selectArticlesAvecEnchereByUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException;
	public List<Article> selectArticlesAvecEnchereRemporteeByUtilisateur(Utilisateur utilisateur, String filtreNom, Integer noCategorie) throws BusinessException;
}
