package fr.eni.encheres.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;

/**
 * Servlet implementation class TestCat
 */
@WebServlet("/TestCat")
public class TestCat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CategorieDAO catDAO = DAOFactory.getCategorieDAO();
		
		StringBuilder sb = new StringBuilder();
		
		System.out.println("Début du test");
		
		List<Categorie> listeCategories;
		try {
			listeCategories = catDAO.selectAll();
			for(Categorie c : listeCategories) {
				sb.append(c.toString());
				sb.append("\n");
				System.out.println(c);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(sb);
		
		
		response.getWriter().append(sb);
	}

}
