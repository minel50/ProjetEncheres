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
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Enchere
 */
@WebServlet("/PropositionEnchere")
public class PropositionEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EnchereManager enchereManager = new EnchereManager();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			Enchere eTest = enchereManager.getEnchere(2, 77);

			if(eTest == null) {
				System.out.println("Il n'y a pas d'enchere");
			} else {
				System.out.println("Il y a bien une enchere");
			}
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
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

			if(enchereManager.getEnchere(ID_USER, noArticle) == null) {
				System.out.println("Il n'y a pas d'enchere : 1ere enchère de l'utilisateur sur cet article");
				enchereManager.addEnchere(enchere);
			} else {
				System.out.println("Il y a déjà une enchere : ancienne enchère écrasée");
				enchereManager.updateEnchere(enchere);
			}
			
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println("L'utilisateur a enchéri de " + mtEnchere);
		
		response.sendRedirect(request.getContextPath() +"/accueil");
		
		
	}

}
