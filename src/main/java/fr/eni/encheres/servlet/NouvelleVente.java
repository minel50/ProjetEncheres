package fr.eni.encheres.servlet;

import java.io.IOException;
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
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/vendre")
public class NouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieManager categorieManager = new CategorieManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	private ArticleManager articleManager = new ArticleManager();
	private RetraitManager retraitManager = new RetraitManager();
	private List<Integer> listeCodesErreursAjoutArticle = new ArrayList<>();
	
	private int noUtilisateur = 1; //simulation utilisateur connecté
	private Utilisateur utilisateurConnecte;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Categorie> listeCategories = categorieManager.getListeCategorie();
			request.setAttribute("listeCategories", listeCategories);
			
			utilisateurConnecte = utilisateurManager.getUtilisateur(noUtilisateur);
			request.setAttribute("utilisateurConnecte", utilisateurConnecte);
			
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
		SimpleDateFormat formatDateformulaire = new SimpleDateFormat("yyyy-MM-dd");
		request.setCharacterEncoding("UTF-8");
		
		try {
			Article nouvelArticle = new Article(
					request.getParameter("nomArticle"),
					request.getParameter("description"),
					formatDateformulaire.parse(request.getParameter("dateDebutEnchere")),
					formatDateformulaire.parse(request.getParameter("dateFinEnchere")),
					Integer.parseInt(request.getParameter("prixInitial")),
					Integer.parseInt(request.getParameter("prixInitial")),	//A la création de la vente, le prix de vente est égal au prix initial.
					"création",
					utilisateurManager.getUtilisateur(noUtilisateur),
					categorieManager.getCategorie(Integer.parseInt(request.getParameter("noCategorie")))
					);
		
			articleManager.addArticle(nouvelArticle);
			
			utilisateurConnecte = utilisateurManager.getUtilisateur(noUtilisateur);
			
			//récupération de l'adresse saisie et comparaison avec celle de l'utilisateur
			String nouvelleRue = request.getParameter("rue");
			String nouveauCodePostal = request.getParameter("codePostal");
			String nouvelleVille = request.getParameter("ville");
			
			//si différente, création d'une nouvelle adresse de retrait pour cet article
			if (nouvelleRue != utilisateurConnecte.getRue() || nouveauCodePostal != utilisateurConnecte.getCodePostal() || nouvelleVille != utilisateurConnecte.getVille()) {
				Retrait nouveauRetrait = new Retrait(nouvelArticle, nouvelleRue, nouveauCodePostal, nouvelleVille);
				retraitManager.addRetrait(nouveauRetrait);
			}
			
		} catch (BusinessException e) {
			listeCodesErreursAjoutArticle = e.getListeCodesErreur();
			request.setAttribute("listeCodesErreursAjoutArticle", listeCodesErreursAjoutArticle);
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//response.sendRedirect(request.getContextPath()); //redirige vers la page d'accueil
			doGet(request, response);
		}
		
	}

}
