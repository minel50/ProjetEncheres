package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

public class RetraitManager 
{
	private static RetraitDAO retraitDAO;
	
	public RetraitManager(){
		retraitDAO = DAOFactory.getRetraitDAO();
	}
	
	public List<Retrait> getListeRetrait() throws BusinessException
	{
		List<Retrait> listeRetrait = null;
		listeRetrait = retraitDAO.selectAll();
		return listeRetrait;
	}
	
	public Retrait getRetrait(int idArticle) throws BusinessException
	{
		Retrait retrait = null;
		retrait = retraitDAO.selectById(idArticle);
		return retrait;
	}
	
	public Retrait getRetraitByArticle(Article article) throws BusinessException
	{
		Retrait retrait = null;
		retrait = retraitDAO.selectById(article.getNoArticle());
		return retrait;
	}
	
	public void addRetrait(Retrait retrait) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		this.validerRetrait(retrait, exception);
		
		if(!exception.hasErreurs()) {
			retraitDAO.insert(retrait);
		}else {
			throw exception;
		}
	}
	
	public void updateRetrait(Retrait retrait) throws BusinessException
	{
		BusinessException exception = new BusinessException();
		this.validerRetrait(retrait, exception);
		
		if(!exception.hasErreurs()) {
			retraitDAO.update(retrait);
		}else {
			throw exception;
		}
	}
	
	public void deleteRetrait(Retrait retrait) throws BusinessException
	{
		
		retraitDAO.delete(retrait);
	}
	
	public void validerRetrait(Retrait r, BusinessException exception)
	{
		if(r==null) {
			exception.ajouterErreur(CodesResultatBLL.OBJECT_NULL);
		}else {
			
			if(r.getRue() == null || r.getRue().trim().length() == 0 || r.getRue().length() > 30)
			{
				exception.ajouterErreur(CodesResultatBLL.ERREUR_RUE_RETRAIT);
			}
			
			if(r.getCodePostal() == null || r.getCodePostal().trim().length() == 0 || r.getCodePostal().length() > 15)
			{
				exception.ajouterErreur(CodesResultatBLL.ERREUR_CODE_POSTAL_RETRAIT);
			}
			
			if(r.getVille() == null || r.getVille().trim().length() == 0 || r.getVille().length() > 30)
			{
				exception.ajouterErreur(CodesResultatBLL.ERREUR_VILLE_RETRAIT);
			}
			
		}
	}
}
