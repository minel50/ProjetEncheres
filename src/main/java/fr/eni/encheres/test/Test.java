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

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
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
		Categorie categorie = new Categorie(1, "toto");
		
		// Test ajout d'un article dans la base :
		//Article nouvelArticle = new Article("voiture", "électrique", new Date(), new Date(2021, 5, 27), 127, 0, "création", categorie);
		//articleDAO.insert(nouvelArticle);
		
		//Test modification d'un article
		/*
		Article articleAModifier = articleDAO.selectById(2);
		articleAModifier.setDescription("nouvelle description");
		articleDAO.update(articleAModifier);
		*/
		
		//Test suppression d'un article
		/*
		Article articleASupprimer = articleDAO.selectById(3);
		articleDAO.delete(articleASupprimer);
		*/
					
		List<Article> listeArticles = articleDAO.selectAll();
		request.setAttribute("listeArticles", listeArticles);
		
		Article articleSelectedById = articleDAO.selectById(1);
		request.setAttribute("articleSelectedById", articleSelectedById);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/test.jsp");
		rd.forward(request, response);
	}

}
