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
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/profil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp");
		rd.forward(request, response);
	}
	
	//A ajouter dans UtilisateurManager
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
		Utilisateur u = null;
		//article = articleDAO.selectById(id);
		return u;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp");
		//rd.forward(request, response);
		
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
		String mdp = request.getParameter("mdp");
		String confirmation = request.getParameter("confirmation");
		
		Utilisateur u = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, 0, 0);
		
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
		out.println("mdp : " + mdp);
		out.println("\n");
		out.println("confirmation : " + confirmation);
		out.println("\n");
		out.println("Utilisateur : " + u.toString());
		
		//Utiliser UtilisateurManager à la place
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		try {
			if(utilisateurDAO.checkEmailExists(email)) {
				out.println("L'email existe déjà dans la BDD");
				
			}else {
				if(utilisateurDAO.checkPseudoExists(pseudo)) {
					out.println("Le pseudo existe déjà dans la BDD");
				}else {
					out.println("N'existe pas dans la BDD");
					utilisateurDAO.insert(u);
				}
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}

}
