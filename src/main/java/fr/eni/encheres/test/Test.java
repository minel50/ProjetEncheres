package fr.eni.encheres.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleDAO articleDAO = DAOFactory.getArticleDAO();
		
		// Test ajout d'un article dans la base :
		/*
		try {
			Article nouvelArticle = new Article("bateau", "voilier", new Date(), new Date(2021, 5, 27), 399, 0, "création", 1, 1);
			articleDAO.insert(nouvelArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
		}	
		*/
		
		//Test modification d'un article
		/*
		try {
			Article articleAModifier = articleDAO.selectById(4);
			articleAModifier.setDescription("nouvelle description");
			articleDAO.update(articleAModifier);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		*/
		
		//Test suppression d'un article
		/*
		Article articleASupprimer;
		try {
			articleASupprimer = articleDAO.selectById(4);
			articleDAO.delete(articleASupprimer);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		*/
		
		try {
			List<Article> listeArticles = articleDAO.selectAll();
			request.setAttribute("listeArticles", listeArticles);
			
			Article articleSelectedById = articleDAO.selectById(1);
			request.setAttribute("articleSelectedById", articleSelectedById);
			
			List<Article> listeArticlesVendusByUtilisateur = articleDAO.selectArticlesVendusByUtilisateur(1);
			request.setAttribute("listeArticlesVendusByUtilisateur", listeArticlesVendusByUtilisateur);
			
			List<Article> listeArticlesAchetesByUtilisateur = articleDAO.selectArticlesAchetesByUtilisateur(2);
			request.setAttribute("listeArticlesAchetesByUtilisateur", listeArticlesAchetesByUtilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test.jsp");
		rd.forward(request, response);
	}

}
