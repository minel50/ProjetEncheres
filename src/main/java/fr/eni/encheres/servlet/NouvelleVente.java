package fr.eni.encheres.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/vente")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieManager categorieManager = new CategorieManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	private ArticleManager articleManager = new ArticleManager();
	private RetraitManager retraitManager = new RetraitManager();
	private List<Integer> listeCodesErreursAjoutArticle = new ArrayList<>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat formatDateHtml = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatHeureHtml = new SimpleDateFormat("hh:mm");
		
		try {
			List<Categorie> listeCategories = categorieManager.getListeCategorie();
			request.setAttribute("listeCategories", listeCategories);
			
			//envoi jour J et heure H + 1 pour affichage dans le formulaire html
			request.setAttribute("dateAujourdhui", formatDateHtml.format(new Date(System.currentTimeMillis())));
			request.setAttribute("heureHPlusUne", formatHeureHtml.format(new Date(System.currentTimeMillis() + 3600 * 1000)));
			request.setAttribute("dateDemain", formatDateHtml.format(new Date(System.currentTimeMillis() + 86400 * 1000)));	// 1 jour = 86400 s
			
		} catch (BusinessException e) {
			List<Integer> listeCodesErreurs = new ArrayList<>();
			listeCodesErreurs = e.getListeCodesErreur();
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
			e.printStackTrace();
		}
		
		
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/WEB-INF/vendreArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		SimpleDateFormat formatDateEtHeure = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Article nouvelArticle = null;
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
		
		try {
			nouvelArticle = new Article(
					request.getParameter("nomArticle"),
					request.getParameter("description"),
					formatDateEtHeure.parse(request.getParameter("dateDebutEnchere") + " " + request.getParameter("heureDebutEnchere")),
					formatDateEtHeure.parse(request.getParameter("dateFinEnchere") + " " + request.getParameter("heureFinEnchere")),
					Integer.parseInt(request.getParameter("prixInitial")),
					Integer.parseInt(request.getParameter("prixInitial")),	//A la création de la vente, le prix de vente est égal au prix initial.
					"création",
					utilisateurConnecte,
					categorieManager.getCategorie(Integer.parseInt(request.getParameter("noCategorie")))
					);
		
			articleManager.addArticle(nouvelArticle);
			
			//récupération de l'adresse saisie et comparaison avec celle de l'utilisateur
			String nouvelleRue = request.getParameter("rue");
			String nouveauCodePostal = request.getParameter("codePostal");
			String nouvelleVille = request.getParameter("ville");
			
			//si différente, création d'une nouvelle adresse de retrait pour cet article
			if (!nouvelleRue.equals(utilisateurConnecte.getRue()) || !nouveauCodePostal.equals(utilisateurConnecte.getCodePostal()) || !nouvelleVille.equals(utilisateurConnecte.getVille())) {
				Retrait nouveauRetrait = new Retrait(nouvelArticle, nouvelleRue, nouveauCodePostal, nouvelleVille);
				retraitManager.addRetrait(nouveauRetrait);
			}
			
			// si tout s'est bien passé (attribut testé dans la jsp) :
			request.setAttribute("succes", 1);
			
		} catch (BusinessException e) {
			listeCodesErreursAjoutArticle = e.getListeCodesErreur();
			request.setAttribute("listeCodesErreursAjoutArticle", listeCodesErreursAjoutArticle);
			request.setAttribute("articleEnCoursCreation", nouvelArticle);	//Si échec insertion, pour récupérer dans le formulaire les valeurs déjà saisies
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//response.sendRedirect(request.getContextPath()); //redirige vers la page d'accueil
			doGet(request, response);
		}
		
	}

}
