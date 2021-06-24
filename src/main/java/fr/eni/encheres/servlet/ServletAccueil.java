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
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filtreNom = "";
	private Integer noCategorie;
	private String choixAchatVente = "";
	private String encheresOuvertes= "";
	private String mesEncheresEnCours= "";
	private String mesEncheresRemportees= "";
	private String mesVentesEnCours= "";
	private String mesVentesNonDebutees= "";
	private String mesVentesTerminees= "";

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
			
			//Récupération liste des articles à afficher en fonction des choix de l'utilisateur faits
			List<Article> listeArticlesAAfficher = new ArrayList<>();	//initialisation d'une liste vide, puis en fonction des cases cochées sera ajoutée une liste avec la méthode addAll()
			HttpSession session = request.getSession();
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
			
			if (utilisateurConnecte != null && choixAchatVente.equals("achat")) {	//l'utilisateur a selectionné le bouton radio achat
				//on choisit de ne pas pouvoir cumuler les affichages (soit toutes les enchères en cours, soit les enchères en cours de l'utilisateur, soit les enchères remportées de l'utilisateur)
				if (encheresOuvertes != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesEnVente(filtreNom, noCategorie));
					
				} else if (mesEncheresEnCours != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesAvecEnchereParUtilisateur(utilisateurConnecte, filtreNom, noCategorie));
					
				} else if (mesEncheresRemportees != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesAvecEnchereRemporteeParUtilisateur(utilisateurConnecte, filtreNom, noCategorie));
					
				}
			} else if (utilisateurConnecte != null && choixAchatVente.equals("vente"))	{  //l'utilisateur a selectionné le bouton radio vente
				//on peut cumuler les affichages des différents types de vente
				if (mesVentesEnCours != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesVenteEnCoursParUtilisateur(utilisateurConnecte, filtreNom, noCategorie));
				}
				
				if (mesVentesNonDebutees != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesVenteNonDebuteeParUtilisateur(utilisateurConnecte, filtreNom, noCategorie));	
				}
				
				if (mesVentesTerminees != null) {
					listeArticlesAAfficher.addAll(articleManager.getListeArticlesVenteTermineeParUtilisateur(utilisateurConnecte, filtreNom, noCategorie));	
				}
				
			} else {  //requete générale mode non connecté
				listeArticlesAAfficher.addAll(articleManager.getListeArticlesEnVente(filtreNom, noCategorie));
			}
			
			request.setAttribute("listeArticlesAAfficher", listeArticlesAAfficher);
			
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
		choixAchatVente = request.getParameter("choixAchatVente");
		
		encheresOuvertes = request.getParameter("encheresOuvertes");
		mesEncheresEnCours = request.getParameter("mesEncheresEnCours");
		mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
		
		mesVentesEnCours = request.getParameter("mesVentesEnCours");
		mesVentesNonDebutees = request.getParameter("mesVentesNonDebutees");
		mesVentesTerminees = request.getParameter("mesVentesTerminees");
		
		//Attributs pour ré-afficher le formulaire avec les choix faits par l'utilisateur
		request.setAttribute("filtreNom", filtreNom);
		request.setAttribute("noCategorie", noCategorie);
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
