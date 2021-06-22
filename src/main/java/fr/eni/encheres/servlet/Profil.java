package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

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
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			Utilisateur u = utilisateurManager.getUtilisateur(7);
			request.setAttribute("pseudo", u.getPseudo());
			request.setAttribute("nom", u.getNom());
			request.setAttribute("prenom", u.getPrenom());
			request.setAttribute("email", u.getEmail());
			request.setAttribute("tel", u.getTelephone());
			request.setAttribute("rue", u.getRue());
			request.setAttribute("codePostal", u.getCodePostal());
			request.setAttribute("ville", u.getVille());
			request.setAttribute("credit", u.getCredit());
			
			
		} catch (BusinessException e) {
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
		
		String mdpBDD;
		try {
			Utilisateur u = utilisateurManager.getUtilisateur(7);
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
				
				//Modif des champs
				u.setPseudo(pseudo);
				u.setNom(nom);
				u.setPrenom(prenom);
				u.setEmail(email);
				u.setTelephone(telephone);
				u.setRue(rue);
				u.setCodePostal(codePostal);
				u.setVille(ville);
				
				System.out.println("Utilisateur après modifs : " + u.toString());
				
				// check si demande modif du mot de passe
				if(!mdp.isEmpty() && mdp.trim() != "") {
				
					System.out.println("demande modification mot de pass");
					
					// check si le mdp match avec celui enregistré dans la BDD
					if(mdpBDD.equals(mdpActuel)) {
						
						if(mdp.equals(confirmation)) {
							System.out.println("Mot de pass modifié");
							u.setMotDePasse(mdp);
						}else {
							System.out.println("Le mot de pass de confiration différent !");
							doGet(request, response);
						}
						
					} else {
						System.out.println(mdpActuel);
						System.out.println(mdpBDD);
						System.out.println("Acien mot de passe incorrecte");
						doGet(request, response);
						
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
