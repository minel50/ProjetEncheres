package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereDAO enchereDAO;
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public List<Enchere> getAllEncheresParUtilisateur(int noUtilisateur) throws BusinessException {
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectByUtilisateur(noUtilisateur);
		return listeEncheres;
	}
	
	public Enchere getMeilleureEnchereParArticle(int noArticle) throws BusinessException {
		Enchere enchere;
		enchere = enchereDAO.getMeilleureEnchereByArticle(noArticle);
		return enchere;
	}
	
	public void updateEnchere(Enchere enchere) throws BusinessException {
		enchereDAO.update(enchere);
	}
	
	public List<Enchere> getAllEncheresParArticle(int noArticle) throws BusinessException {
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectByArticle(noArticle);
		return listeEncheres;
	}
	
	public Enchere getEnchere(int noUtilisateur, int noArticle) throws BusinessException {
		Enchere enchere;
		enchere = enchereDAO.selectByUtilisateurEtArticle(noUtilisateur, noArticle);
		return enchere;
	}
	
	public void addEnchere(Enchere enchere) throws BusinessException {
		BusinessException exception = new BusinessException();
		this.validerEnchere(enchere, exception);
		
		if (!exception.hasErreurs()) {
			enchereDAO.insert(enchere);	
		} else {
			throw exception;
		}
	}
	
	//Reste à rajouter la validation de la date, et vérifier que l'enchère est supérieure à la dernière enchère
	public void validerEnchere(Enchere e, BusinessException exception) {
		if (e == null) {
			exception.ajouterErreur(CodesResultatBLL.OBJECT_NULL);
		} else {
			if (e.getNoUtilisateur() == 0) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MANQUE_UTILISATEUR);
			}
			if (e.getNoArticle() == 0) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MANQUE_ARTICLE);
			}
			if (e.getMontantEnchere() < 0) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MONTANT_NEGATIF);
			}
		}
	}
	
}
