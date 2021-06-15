package fr.eni.encheres.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bo.Article;
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
		
		// Test ajout d'un article dans la base :
		Article a1 = new Article("F1 Ferrari", "vroum", new Date(2021, 5, 16), new Date(2021, 5, 27), 999999, 0, "création");
		
		
		System.out.println(a1);
		
		articleDAO.insert(a1);
	}

}
