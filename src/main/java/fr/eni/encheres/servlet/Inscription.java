package fr.eni.encheres.servlet;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
//import fr.eni.encheres.dal.DAOFactory;
//import fr.eni.encheres.dal.UtilisateurDAO;




/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_ERREURS = "erreurs";
	private static final String ATT_RESULTAT ="resultat";
	private static final String CHAMP_PASS ="motpasse";
	private static final String CHAMP_EMAIL ="email";
	private static final String CHAMP_PSEUDO= "pseudo";
	private static final String CHAMP_NOM= "nom";
	private static final String CHAMP_PRENOM= "prenom";
	private static final String CHAMP_TELEPHONE= "telephone";
	private static final String CHAMP_RUE= "rue";
	private static final String CHAMP_CODEPOSTAL= "codepostal";
	private static final String CHAMP_VILLE= "ville";
	private static final String CHAMP_CONFIRMATION= "confirmation";
	
	
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	


   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription/inscription.jsp");
	rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
	
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String motpasse = request.getParameter("motpasse");
		String confirmation = request.getParameter("confirmation");
		
		Utilisateur utilisateur = new Utilisateur();
		BusinessException businessException = new BusinessException()
;		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codepostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motpasse);

		String resultat;
		Map<String,String> erreurs = new HashMap<String,String>();
		
		/*Validation motpasse et confirmation*/
		try {
			validationMotsDePasse(motpasse,confirmation);
			
		} catch (Exception e) {
			erreurs.put(CHAMP_PASS, e.getMessage());
		}
		/*Validation email */
		 try {
			
			 verifEmail(email);
		} catch (Exception e) {
			erreurs.put(CHAMP_EMAIL,e.getMessage());
			
			
		}
		/*validation des champs*/
		 try {
			validationUtilisateur(pseudo);
		} catch (Exception e) {
		erreurs.put(CHAMP_PSEUDO, e.getMessage());
	
		
		}
		
	
		/*initialisation du resultat de la validation*/
		if(erreurs.isEmpty()) {
			/*ajout nouvel utilisateur dans BDD*/
			try {
			
				response.sendRedirect(request.getContextPath() +"/Liste");
				/*RequestDispatcher rd1=
						request.getRequestDispatcher("/WEB-INF/accueil.jsp");
				rd1.forward(request, response);*/
				utilisateurManager.addUtilisateur(utilisateur);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			resultat = "Succes de l'inscription";
			
			HttpSession session = request.getSession();
			System.out.println("inscription " +session.getId());
			
			
		} else {
			resultat = "Echec de l'inscription";
			
			/*stockage des messages d'erreur dans l'objet request*/	
			request.setAttribute(ATT_ERREURS, erreurs);
			request.setAttribute(ATT_RESULTAT, resultat);
			
			//on renvoie la réponse vers la jsp
			RequestDispatcher rd1=
					request.getRequestDispatcher("/WEB-INF/inscription/inscription.jsp");
			rd1.forward(request, response);
		}
		
		
				
		
		
		
		
		
		
		
		
		
	}
	
	public void validationMotsDePasse(String motpasse, String confirmation) throws Exception {
		if(!motpasse.equals(confirmation)) {
			throw new Exception("Les mots de passe sont différents, veuillez les saisir à nouveau");
		}
	}

	public void validationUtilisateur(String pseudo) throws Exception {
		if(pseudo == null|| pseudo.equals("")) {
			throw new Exception ("Certaines informations sont manquantes");
	}
		
	
	}
	
	
	public void verifEmail(String email) throws Exception {
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		listeUtilisateurs = utilisateurManager.getListeUtilisateur();
		for(Utilisateur user: listeUtilisateurs) {
			
			if( user.getEmail().equals(email)){
		throw new Exception ("Vous ne pouvez pas créer de compte avec cette adresse mail");}
	}
	
	}
	
		
	
		
	
    


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
	

