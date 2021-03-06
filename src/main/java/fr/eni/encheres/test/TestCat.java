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
		
		//Test insert
		/*Categorie c1 = new Categorie("Bricolage");
		Categorie c2 = new Categorie("Jardin");
		Categorie c3 = new Categorie("Jardin");
		System.out.println("Insertion de " + c1.toString());
		System.out.println("Insertion de " + c2.toString());
		try {
			catDAO.insert(c1);
			catDAO.insert(c2);
			catDAO.insert(c3);
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		
		//Test Selectall
		StringBuilder sb = new StringBuilder();
		System.out.println("Début du test");
		
		List<Categorie> listeCategories;
		try {
			listeCategories = catDAO.selectAll();
			for(Categorie c : listeCategories) {
				sb.append(c.toString());
				sb.append("\n");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb);
		response.getWriter().append(sb);
		
		//Test SelectById	
		try {
			Categorie cat1 = catDAO.selectById(2);
			String CatString =  cat1.toString();
			
			System.out.println(CatString);
			response.getWriter().append(CatString);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//Test Update
		/*Categorie cat1;
		try {
			
			cat1 = catDAO.selectById(4);
			cat1.setLibelle("Décoration");
			catDAO.update(cat1);
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		
		
		//Test Delete
		try {
			Categorie cat1 = catDAO.selectById(7);
			
			catDAO.delete(cat1);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

}
