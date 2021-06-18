package fr.eni.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filtreNom;
	private Integer noCategorie;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Format date pour le transmettre à la JSP
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		request.setAttribute("formatDate", formatDate);
		
		CategorieManager categorieManager = new CategorieManager();
		ArticleManager articleManager = new ArticleManager();
		
		
		
		try {
			//Récupération liste des catégories
			List<Categorie> listeCategories = categorieManager.getListeCategorie();
			request.setAttribute("listeCategories", listeCategories);
			
			//Renvoit état des filtres
			request.setAttribute("filtreNom", filtreNom);
			request.setAttribute("noCategorie", noCategorie);
			
			//Récupération liste des articles en vente (c'est-à-dire disponibles pour enchères)
			List<Article> listeArticlesEnVente = articleManager.getListeArticlesEnVente(filtreNom, noCategorie);
			request.setAttribute("listeArticlesEnVente", listeArticlesEnVente);
			
		} catch (BusinessException e) {
			List<Integer> listeCodesErreurs = new ArrayList<>();
			listeCodesErreurs = e.getListeCodesErreur();
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
			e.printStackTrace();
		}
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filtreNom = request.getParameter("filtreNom");
		noCategorie = Integer.parseInt(request.getParameter("filtreCategorie"));
		
		doGet(request, response);
	}

}
