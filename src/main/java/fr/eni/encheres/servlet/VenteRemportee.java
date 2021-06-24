package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class venteRemportee
 */
@WebServlet("/venteRemportee")
public class VenteRemportee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleManager articleManager = new ArticleManager();
		RetraitManager retraitManager = new RetraitManager();
		EnchereManager enchereManager = new EnchereManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		Integer noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		Article article;
		Retrait retrait;
		Enchere meilleureEnchere;
		Utilisateur meilleurAcheteur = null;
		
		
		
		try {
			
			
			//récupération article
			article = articleManager.getArticle(noArticle); //noArticle
			request.setAttribute("article", article);
			
			//récupération adresse de retrait spécifique à l'article si elle existe (renvoit null sinon)
			retrait = retraitManager.getRetrait(noArticle); 
			request.setAttribute("retrait", retrait);
			
			//récupération meilleure enchère (ou pas d'enchère) et meilleur acheteur
			meilleureEnchere = enchereManager.getMeilleureEnchereParArticle(noArticle);
			request.setAttribute("meilleureEnchere", meilleureEnchere);
			
			if (meilleureEnchere != null) {
				meilleurAcheteur = utilisateurManager.getUtilisateur(meilleureEnchere.getNoUtilisateur());
			}
			request.setAttribute("meilleurAcheteur", meilleurAcheteur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		
		
		//HttpSession session = request.getSession();
		//Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
		//int ID_USER = utilisateurConnecte.getNoUtilisateur();		
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/venteRemportee.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
