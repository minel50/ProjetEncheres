package fr.eni.encheres.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.EnchereDAO;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * Servlet implementation class Test
 */
@WebServlet("/TestUtilisateurListeEnchere")
public class TestUtilisateurListeEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		try {
			List<Utilisateur> listeUtilisateurs = utilisateurDAO.selectAll();
			request.setAttribute("listeUtilisateurs", listeUtilisateurs);			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		/*EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();
		try {
			List<Enchere> listeEncheres = enchereDAO.selectAll();
			request.setAttribute("listeEncheres", listeEncheres);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/testUtilisateurListeEnchere.jsp");
		rd.forward(request, response);
	}

}
