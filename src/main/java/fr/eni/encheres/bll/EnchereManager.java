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
	
	public List<Enchere> getAllEncheres() throws BusinessException {
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectAll();
		return listeEncheres;
	}
	
	public List<Enchere> getAllEncheresParUtilisateur(int idUtilisateur) throws BusinessException {
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectByUtilisateur(idUtilisateur);
		return listeEncheres;
	}
	
	public List<Enchere> getAllEncheresParArticle(int idArticle) throws BusinessException {
		List<Enchere> listeEncheres;
		listeEncheres = enchereDAO.selectByArticle(idArticle);
		return listeEncheres;
	}
	
	public Enchere getEnchere(int idUtilisateur, int idArticle) throws BusinessException {
		Enchere enchere;
		enchere = enchereDAO.selectByUtilisateurEtArticle(idUtilisateur, idArticle);
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
			if (e.getUtilisateur() == null) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MANQUE_UTILISATEUR);
			}
			if (e.getArticle() == null) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MANQUE_ARTICLE);
			}
			if (e.getMontantEnchere() < 0) {
				exception.ajouterErreur(CodesResultatBLL.ERREUR_ENCHERE_MONTANT_NEGATIF);
			}
		}
	}
	
}
