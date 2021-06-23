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
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filtreNom = "";
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
			
			//Renvoit état des filtres pour les réafficher dans le formulaire
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
		//récupération choix de l'utilisateur (connecté ou non)
		filtreNom = request.getParameter("filtreNom");
		noCategorie = Integer.parseInt(request.getParameter("filtreCategorie"));
		
		//récupération choix de l'utilisareur connecté
		//pour les checkbox renvoit "on" si coché, null si non coché
		String choixAchatVente = request.getParameter("choixAchatVente");
		
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String mesEncheresEnCours = request.getParameter("mesEncheresEnCours");
		String mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
		
		String mesVentesEnCours = request.getParameter("mesVentesEnCours");
		String mesVentesNonDebutees = request.getParameter("mesVentesNonDebutees");
		String mesVentesTerminees = request.getParameter("mesVentesTerminees");
		
		//Débugage
		System.out.println("choix utilisateur:");
		System.out.println(choixAchatVente);
		
		System.out.println(encheresOuvertes);
		System.out.println(mesEncheresEnCours);
		System.out.println(mesEncheresRemportees);
		
		System.out.println(mesVentesEnCours);
		System.out.println(mesVentesNonDebutees);
		System.out.println(mesVentesTerminees);
		
		//Attributs pour ré-afficher le formulaire avec les bonnes cases cochées
		request.setAttribute("choixAchatVente", choixAchatVente);
		
		request.setAttribute("encheresOuvertes", encheresOuvertes);
		request.setAttribute("mesEncheresEnCours", mesEncheresEnCours);
		request.setAttribute("mesEncheresRemportees", mesEncheresRemportees);
		
		request.setAttribute("mesVentesEnCours", mesVentesEnCours);
		request.setAttribute("mesVentesNonDebutees", mesVentesNonDebutees);
		request.setAttribute("mesVentesTerminees", mesVentesTerminees);
		
		
		doGet(request, response);
	}

}
