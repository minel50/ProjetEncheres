package fr.eni.encheres.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.Enchere;

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
		
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/propositionEnchere.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
		Enchere e1 = new Enchere();
		Date date1 = new Date();
		DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		System.out.println(formatDate.format(date1));
		
		int mtEnchere = Integer.parseInt(request.getParameter("mtEnchere"));
		
		e1.setDateEnchere(date1);
		e1.setMontantEnchere(mtEnchere);
		e1.seNoUtilisateur(7);
		e1.setNoArticle(4);
		
		try {
			enchereManager.addEnchere(e1);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println("L'utilisateur a enchéri de " + mtEnchere);
		
	}

}
