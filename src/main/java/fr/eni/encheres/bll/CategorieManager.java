package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	
	private static CategorieDAO categorieDAO;

	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public List<Categorie> getListeCategorie() throws BusinessException 
	{
		List<Categorie> listeCategorie = null;
		listeCategorie = categorieDAO.selectAll();
		return listeCategorie;
	}
	
	public Categorie getCategorie(int id) throws BusinessException 
	{
		Categorie categorie = null;
		categorie = categorieDAO.selectById(id);
		return categorie;
	}
	
	public Categorie getCategorieByArticle(Article article) throws BusinessException 
	{
		Categorie categorie = null;		
		categorie = categorieDAO.selectById(article.getNoArticle());
		return categorie;
	}
	
	public void addCategorie(Categorie categorie) throws BusinessException 
	{
		BusinessException exception = new BusinessException();
		this.validerCategorie(categorie, exception);
		
		if(!exception.hasErreurs()) {
			categorieDAO.insert(categorie);
		} else {
			throw exception;
		}
	}
	
	public void updateCategorie(Categorie categorie) throws BusinessException 
	{
		BusinessException exception = new BusinessException();
		this.validerCategorie(categorie, exception);
		
		if(!exception.hasErreurs()) {
			categorieDAO.update(categorie);
		}else {
			throw exception;
		}
	}
	
	public void deleteCategorie(Categorie categorie) throws BusinessException 
	{
		categorieDAO.delete(categorie);
	}
	
	public void validerCategorie(Categorie c, BusinessException exception)
	{
		if(c==null) {
			exception.ajouterErreur(CodesResultatBLL.OBJECT_NULL);
		}else {
			
			if(c.getLibelle() == null || c.getLibelle().trim().length() == 0 || c.getLibelle().length() > 30) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_NOM_CATEGORIE);
			}
			
		}
	}
}
