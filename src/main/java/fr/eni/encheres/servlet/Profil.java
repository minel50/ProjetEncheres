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
		PrintWriter out = response.getWriter();
		
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
		
		
		out.println("\n");
		out.println("Pseudo : " + pseudo);
		out.println("\n");
		out.println("nom : " + nom);
		out.println("\n");
		out.println("prenom : " + prenom);
		out.println("\n");
		out.println("email : " + email);
		out.println("\n");
		out.println("telephone : " + telephone);
		out.println("\n");
		out.println("rue : " + rue);
		out.println("\n");
		out.println("code postal : " + codePostal);
		out.println("\n");
		out.println("ville : " + ville);
		out.println("\n");
		out.println("mdp Actuel : " + mdpActuel);
		out.println("\n");
		out.println("mdp : " + mdp);
		out.println("\n");
		out.println("confirmation : " + confirmation);
		
		
		
		String mdpBDD;
		try {
			Utilisateur u = utilisateurManager.getUtilisateur(7);
			mdpBDD = u.getMotDePasse();
			
			if(mdpBDD == mdpActuel && mdp == confirmation) {
				utilisateurManager.updateUtilisateur(u);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
