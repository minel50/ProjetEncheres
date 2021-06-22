package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int ID_SESSION = 7;
		String msgEchec = "";
		String msgSucces = "";
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		request.setCharacterEncoding("UTF-8");
		
		/*Utilisateur u9 = new Utilisateur("NaidroD", "DELAGRE", "Dorian", "naidrod@gamil.com", "0610060606", "9  rue de l'oise", "35230", "Orgères", "ABCD12", 700, 0);
		try {
			utilisateurManager.addUtilisateur(u9);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		try {
			Utilisateur u = utilisateurManager.getUtilisateur(ID_SESSION);
			request.setAttribute("pseudo", u.getPseudo());
			request.setAttribute("nom", u.getNom());
			request.setAttribute("prenom", u.getPrenom());
			request.setAttribute("email", u.getEmail());
			request.setAttribute("tel", u.getTelephone());
			request.setAttribute("rue", u.getRue());
			request.setAttribute("codePostal", u.getCodePostal());
			request.setAttribute("ville", u.getVille());
			request.setAttribute("credit", u.getCredit());
			request.setAttribute("msgEchec", msgEchec);
			request.setAttribute("msgSucces", msgSucces);
			
			
		} catch(NullPointerException e){
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}catch (BusinessException e) {
			e.printStackTrace();
			
		}
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
		
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
				
		int ID_SESSION = 7;
		String msgEchec = "";
		String msgSucces = "Modifications effectuées";
		
		//response.setContentType("text/html;charset=UTF-8");
		//PrintWriter out = response.getWriter();
		
		// Récupérer paramètres de la requête postée après demande de modif
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code-postal");
		String ville = request.getParameter("ville");
		String mdpActuel = request.getParameter("mdpActuel");
		String mdp = request.getParameter("mdp");
		String confirmation = request.getParameter("confirmation");
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		String msgFeedback;
		String mdpBDD;
		try {
			Utilisateur u = utilisateurManager.getUtilisateur(ID_SESSION);
			mdpBDD = u.getMotDePasse();
			
			// check si un des champs du profil a été modifié
			if(		
					(!pseudo.equals(u.getPseudo()) && !pseudo.isEmpty()) || 
					(!nom.equals(u.getNom()) && !nom.isEmpty()) ||
					(!prenom.equals(u.getPrenom()) && !prenom.isEmpty()) ||
					(!email.equals(u.getEmail()) && !email.isEmpty()) ||
					(!telephone.equals(u.getTelephone()) && !telephone.isEmpty()) ||
					(!rue.equals(u.getRue())  && !rue.isEmpty())||
					(!codePostal.equals(u.getCodePostal()) && !codePostal.isEmpty()) ||
					(!ville.equals(u.getVille())  && !ville.isEmpty())||
					(!mdp.isEmpty() && mdp != mdpBDD)
			) {
				
				//Modif des champs de l'utilisateur avec les données postées 
				u.setPseudo(pseudo);
				u.setNom(nom);
				u.setPrenom(prenom);
				u.setEmail(email);
				u.setTelephone(telephone);
				u.setRue(rue);
				u.setCodePostal(codePostal);
				u.setVille(ville);
				
				
				// check si demande modif du mot de passe
				if(!mdp.isEmpty() && mdp.trim() != "") {
									
					System.out.println("demande modification mot de pass");
					
					// check si le mdp match avec celui enregistré dans la BDD
					if(mdpBDD.equals(mdpActuel)) {
						
						if(mdp.equals(confirmation)) {
							System.out.println("Mot de pass modifié");
							u.setMotDePasse(mdp);
						}else {
							
							// Si nouveau mot de pass différent du mot de pass de confiramtion :
							// Reset les paramètres utilisateurs
							Utilisateur userReset = utilisateurManager.getUtilisateur(ID_SESSION);
							request.setAttribute("pseudo", userReset.getPseudo());
							request.setAttribute("nom", userReset.getNom());
							request.setAttribute("prenom", userReset.getPrenom());
							request.setAttribute("email", userReset.getEmail());
							request.setAttribute("tel", userReset.getTelephone());
							request.setAttribute("rue", userReset.getRue());
							request.setAttribute("codePostal", userReset.getCodePostal());
							request.setAttribute("ville", userReset.getVille());
							request.setAttribute("credit", userReset.getCredit());
							request.setAttribute("msgSucces", "");
							
							//Redirection avec message d'erreur
							System.out.println("Mot de pass de confirmation différent !");
							msgFeedback = "Mot de pass de confirmation différent !";
							request.setAttribute("msgEchec", msgFeedback);
							RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp");
							rd.forward(request, response);
							
						}
						
					} else {
						// Si le mot de pass actuel ne correspond pas :
						// Reset les paramètres utilisateurs
						Utilisateur userReset = utilisateurManager.getUtilisateur(ID_SESSION);
						request.setAttribute("pseudo", userReset.getPseudo());
						request.setAttribute("nom", userReset.getNom());
						request.setAttribute("prenom", userReset.getPrenom());
						request.setAttribute("email", userReset.getEmail());
						request.setAttribute("tel", userReset.getTelephone());
						request.setAttribute("rue", userReset.getRue());
						request.setAttribute("codePostal", userReset.getCodePostal());
						request.setAttribute("ville", userReset.getVille());
						request.setAttribute("credit", userReset.getCredit());
						request.setAttribute("msgSucces", "");
						
						//Redirection avec message d'erreur
						System.out.println("Mot de passe actuel incorrecte");
						msgFeedback = "Mot de passe actuel incorrecte";
						request.setAttribute("msgEchec", msgFeedback);
						RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp");
						rd.forward(request, response);
						
					}
				} else {
					System.out.println("mdp non modifié");
				}
				
				// envoyer les modifs de l'utilisateur vers la BDD
				utilisateurManager.updateUtilisateur(u);
				
				//Récupère les attributs de la BDD
				request.setAttribute("pseudo", u.getPseudo());
				request.setAttribute("nom", u.getNom());
				request.setAttribute("prenom", u.getPrenom());
				request.setAttribute("email", u.getEmail());
				request.setAttribute("tel", u.getTelephone());
				request.setAttribute("rue", u.getRue());
				request.setAttribute("codePostal", u.getCodePostal());
				request.setAttribute("ville", u.getVille());
				request.setAttribute("credit", u.getCredit());
				request.setAttribute("msgEchec", "");
				request.setAttribute("msgSucces", msgSucces);
				
				
				//Redirection vers la page Profil avec le message succès
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp");
				rd.forward(request, response);
				
			}else {
				doGet(request, response);
				System.out.println("Aucune modification sur les champs de l'utilisateur");
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
