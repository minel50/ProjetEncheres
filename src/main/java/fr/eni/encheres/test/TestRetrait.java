package fr.eni.encheres.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.RetraitDAO;

/**
 * Servlet implementation class TestCat
 */
@WebServlet("/TestRetrait")
public class TestRetrait extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleDAO artDAO = DAOFactory.getArticleDAO();
		RetraitDAO retDAO = DAOFactory.getRetraitDAO();
		
		
		//Test insert
		try {
			Article a1 = artDAO.selectById(4);
			Article a2 = artDAO.selectById(5);
			
			Retrait r1 = new Retrait(a1, "2 rue de la plaine basse", "35140", "Mezières");
			Retrait r2 = new Retrait(a2, "2 rue George Pompidou", "35140", "Saint-Aubin");
			
			retDAO.insert(r1);
			retDAO.insert(r2);
			
			System.out.println("Insertion de " + r1.toString());
			System.out.println("Insertion de " + r2.toString());
			
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		
		
		
			
		
		//Test Selectall
		StringBuilder sb = new StringBuilder();
		System.out.println("Début du test");
		
		List<Retrait> listeRetraits;
		try {
			listeRetraits = retDAO.selectAll();
			for(Retrait r : listeRetraits) {
				sb.append(r.toString());
				sb.append("\n");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb);
		response.getWriter().append(sb);
		
		
		
		//Test SelectById	
		try {
			Retrait r1 = retDAO.selectById(4);
			String retString =  r1.toString();
			
			System.out.println(retString);
			response.getWriter().append(retString);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		//Test Update
		
		try {
			
			Retrait r1 = retDAO.selectById(5);
			r1.setCodePostal("35250");
			retDAO.update(r1);
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		
		
		//Test Delete
		try {
			Retrait r1 = retDAO.selectById(5);
			
			retDAO.delete(r1);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
	}

}
