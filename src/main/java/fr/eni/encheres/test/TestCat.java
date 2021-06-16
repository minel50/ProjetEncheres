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
		
		/*
		CategorieDAO catDAO = DAOFactory.getCategorieDAO();
		Categorie c1 = new Categorie("Mobilier");
		System.out.println("Insertion de " + c1.toString());
		try {
			catDAO.insert(c1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		CategorieDAO catDAO = DAOFactory.getCategorieDAO();
		
		try {
			List<Categorie> listCat = catDAO.selectAll();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(String elem:listCat) {
			
		}
		
		
	}

}
