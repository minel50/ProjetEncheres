package fr.eni.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_ERREURS = "erreurs";
	private static final String ATT_RESULTAT ="resultat";
	private static final String CHAMP_IDENTIFIANT= "pseudo";
	UtilisateurManager utilisateurManager = new UtilisateurManager();  
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
		
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("pseudo"));
				request.setAttribute("pseudo", cookie.getValue());
			}
		}*/
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> erreurs = new HashMap<String,String>();
		String resultat = null;
		String pseudo = request.getParameter("pseudo");
		String motpasse = request.getParameter("motpasse");
		String remember =request.getParameter("remember");
	
		/*Cookie cookie = new Cookie("pseudo", pseudo);
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);*/
	
	
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();

		try {
			listeUtilisateurs = utilisateurManager.getListeUtilisateur();
					for(Utilisateur user: listeUtilisateurs) 
				
							if((user.getPseudo().equals(pseudo)) && (user.getMotDePasse().equals(motpasse))){
					
							HttpSession session = request.getSession();
							session.setAttribute("identifiant", pseudo);
							session.setAttribute("motpasse", motpasse);
							session.setAttribute("utilisateurConnecte", user);
							System.out.println("login " +session.getId());
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription/details.jsp");//a changer pour jsp page page accueil liste enchere
							rd.forward(request, response);
					
							}
							else {
								
								
								resultat = "Vos identifiants ne correspondent pas";
								request.setAttribute(ATT_RESULTAT, resultat);
								/*RequestDispatcher rd1 =
										request.getRequestDispatcher("/WEB-INF/inscription/login.jsp");
								rd1.forward(request, response);*/
								
							}
					
		}
		catch(Exception e) {
			e.printStackTrace();
		}
					
		doGet(request, response);
		
			

							
							}
							
					
					
					
					
						
							
	
				
				
	}
	
		
	/*try {
			verifIdentifiant(pseudo,motpasse);
		} catch (Exception e) {
	
			erreurs.put(CHAMP_IDENTIFIANT, e.getMessage());
		}*/
		
		//request.setAttribute(ATT_ERREURS, erreurs);
		
	

/*public void verifIdentifiant(String pseudo, String motpasse) throws Exception {
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		listeUtilisateurs = utilisateurManager.getListeUtilisateur();
		for(Utilisateur user: listeUtilisateurs) {
			
			if(!user.getPseudo().equals(pseudo) && (user.getMotDePasse().equals(motpasse))){
		throw new Exception ("Aucun compte n'est asssocié à cette adresse email");}
	}
	
	}*/
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		

