package fr.eni.encheres.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Enchere
 */
@WebServlet("/PropositionEnchere")
public class PropositionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EnchereManager enchereManager = new EnchereManager();
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.sendRedirect(request.getContextPath() +"/accueil");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
		int ID_USER = utilisateurConnecte.getNoUtilisateur();		
		
		
		Enchere enchere = new Enchere();
		Date date = new Date();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		System.out.println(formatDate.format(date));
		
		int mtEnchere = Integer.parseInt(request.getParameter("mtEnchere"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		
		enchere.setDateEnchere(date);
		enchere.setMontantEnchere(mtEnchere);
		enchere.seNoUtilisateur(ID_USER);
		enchere.setNoArticle(noArticle);
		
		try {
			
			Enchere meilleureEnchere = enchereManager.getMeilleureEnchereParArticle(noArticle);
					
			// Si l'enchère existe déjà : MAJ de l'enchère, sinon : création d'une nouvelle
			if(meilleureEnchere == null) {
				
				System.out.println("Il n'y a pas d'enchere : 1ere enchère de l'utilisateur sur cet article");
				Utilisateur u = utilisateurManager.getUtilisateur(ID_USER);
				System.out.println("Crédit disponible : " + u.getCredit());
				
				
				u.setCredit(u.getCredit() - mtEnchere);
				System.out.println("Crédit restant après enchere : " + u.getCredit());
				
				utilisateurManager.updateUtilisateur(u);
				enchereManager.addEnchere(enchere);
				
			} else {
				
				System.out.println("Il y a déjà une enchere : ancienne enchère écrasée");
				
				//Re-créditer ancien meilleur enchérisseur
				Utilisateur meilleurAcheteur = utilisateurManager.getUtilisateur(meilleureEnchere.getNoUtilisateur());
				meilleurAcheteur.setCredit(meilleurAcheteur.getCredit() + meilleureEnchere.getMontantEnchere());
				utilisateurManager.updateUtilisateur(meilleurAcheteur);
				
				Utilisateur u = utilisateurManager.getUtilisateur(ID_USER);
				System.out.println("Crédit disponible : " + u.getCredit());
				int ancienneEnchere = enchereManager.getEnchere(ID_USER, noArticle).getMontantEnchere();
				int credit = u.getCredit() - mtEnchere;
				System.out.println("Ancienne enchère : " + ancienneEnchere);
				u.setCredit(credit);
				System.out.println("Crédit restant après enchere : " + u.getCredit());
				
				utilisateurManager.updateUtilisateur(u);
				enchereManager.updateEnchere(enchere);
			}
			
			
		} catch(NullPointerException e){
			System.out.println("Utilisateur non trouvé dans la base de données");
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() +"/accueil");
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println("L'utilisateur a enchéri à " + mtEnchere);
		response.sendRedirect(request.getContextPath() +"/accueil");
		
		
	}

}


/* ANCIENNE VERSION
//Si l'enchère existe déjà : MAJ de l'enchère, sinon : création d'une nouvelle
			if(enchereManager.getEnchere(ID_USER, noArticle) == null) {
				
				System.out.println("Il n'y a pas d'enchere : 1ere enchère de l'utilisateur sur cet article");
				Utilisateur u = utilisateurManager.getUtilisateur(ID_USER);
				System.out.println("Crédit disponible : " + u.getCredit());
				
				int credit = u.getCredit() - mtEnchere;
				u.setCredit(credit);
				System.out.println("Crédit restant après enchere : " + u.getCredit());
				
				utilisateurManager.updateUtilisateur(u);
				enchereManager.addEnchere(enchere);
				
			} else {
				System.out.println("Il y a déjà une enchere : ancienne enchère écrasée");
				
				Utilisateur u = utilisateurManager.getUtilisateur(ID_USER);
				System.out.println("Crédit disponible : " + u.getCredit());
				int ancienneEnchere = enchereManager.getEnchere(ID_USER, noArticle).getMontantEnchere();
				int credit = u.getCredit() - mtEnchere;
				System.out.println("Ancienne enchère : " + ancienneEnchere);
				u.setCredit(credit);
				System.out.println("Crédit restant après enchere : " + u.getCredit());
				
				utilisateurManager.updateUtilisateur(u);
				enchereManager.updateEnchere(enchere);
			}
*/