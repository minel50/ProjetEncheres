package fr.eni.encheres.bll;

/**
 * classe modelisant UtilisateurManager
 * */



import java.util.List;



import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UtilisateurDAO;


public class UtilisateurManager {
	private static UtilisateurDAO utilisateurDAO;
	//private static ArticleDAO articleDAO;
	
	private static EnchereDAO enchereDAO;
	
	//Constructeur/instancie le DAO
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	
	
	
	
	
	
	public List<Utilisateur> getListeUtilisateur() throws BusinessException{
		
		List<Utilisateur> listeUtilisateurs =null;
		listeUtilisateurs = utilisateurDAO.selectAll();
		return listeUtilisateurs;
	}

	
	public Utilisateur getUtilisateur(int id) throws BusinessException {
		Utilisateur utilisateur = null;
		utilisateur = utilisateurDAO.selectById(id);
		return utilisateur;
	}
	
	
	public void addUtilisateur(Utilisateur utilisateur ) throws BusinessException {
		
			BusinessException businessException = new BusinessException();
			if(!businessException.hasErreurs()) {
				this.validerUtilisateur(utilisateur, businessException);
				utilisateurDAO.insert(utilisateur);
			}
			else {
				throw businessException;
			}
		
	}
	
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if(!businessException.hasErreurs()) {
			this.validerUtilisateur(utilisateur, businessException);
			utilisateurDAO.update(utilisateur);
		}
			
		
		
	}
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.delete(utilisateur);
	}
		


		
	
			
				
	
	
	
	
	
	public void validerUtilisateur(Utilisateur user,BusinessException businessException) {
		if(user == null) {
			businessException.ajouterErreur(CodesResultatBLL.OBJECT_NULL);
		}
		
		if(user.getPseudo()==null  || user.getPseudo().trim().length() == 0|| user.getPseudo().length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PSEUDO_USER);
		}
		if(user.getNom()==null  || user.getNom().trim().length() == 0|| user.getNom().length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_NOM_USER);
		}
		if(user.getPrenom()==null  || user.getPrenom().trim().length() == 0|| user.getPrenom().length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_PRENOM_USER);
		}
		if(user.getEmail()==null  || user.getEmail().trim().length() == 0|| user.getEmail().length()>20) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_EMAIL_USER);
			
		if(user.getTelephone()==null  || user.getTelephone().trim().length() == 0|| user.getTelephone().length()>15) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_TELEPHONE_USER);
			
		if(user.getRue()==null  || user.getRue().trim().length() == 0|| user.getRue().length()>20) {
				businessException.ajouterErreur(CodesResultatBLL.ERREUR_RUE_USER);
		}
		}
		if(user.getCodePostal()==null  || user.getCodePostal().trim().length() == 0|| user.getCodePostal().length()>10) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_CODEPOSTAL_USER);
		}
		if(user.getVille()==null  || user.getVille().trim().length() == 0|| user.getVille().length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_VILLE_USER);
		}
		if(user.getMotDePasse()==null  || user.getMotDePasse().trim().length() == 0|| user.getMotDePasse().length()>30) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_MOTDEPASSE_USER);
		}
		if(user.getCredit()< 0) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_CREDIT_NEGATIF);
		}
		if(user.getAdministrateur() != 0 && user.getAdministrateur() != 1) {
			businessException.ajouterErreur(CodesResultatBLL.ERREUR_CODE_ADMIN);
		}
	}}
	
	
	
	
	
	
	
	
	
	
	
}


