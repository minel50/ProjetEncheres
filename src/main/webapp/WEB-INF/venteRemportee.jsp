<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" href="app.css" />
	
	<title>Vente remportée</title>
</head>
<body>
	<h1>Vous avez remporté la vente</h1>
		
		<p>${requestScope.article.getNomArticle() }</p>
		
		<p>Description : ${requestScope.article.getDescription() }</p>
		
		<p>
			<c:if test="${!empty requestScope.meilleureEnchere }">
				Meilleure offre : ${requestScope.meilleureEnchere.getMontantEnchere() } pts par ${requestScope.meilleurAcheteur.getPseudo() }
			</c:if>
			
			<%-- <c:if test="${empty requestScope.meilleureEnchere }">
				Aucune offre n'a été faite pour le moment ...
			</c:if> --%>
			
		</p>
		
		<p>Mise à prix initiale : ${requestScope.article.getPrixInitial() } pts</p>
		
		<%-- <p>Fin de l'enchère : ${requestScope.formatAffichageDateHeure.format(requestScope.article.getDateFinEncheres()) }</p> --%>
		
		<c:if test="${empty requestScope.retrait }">
			<p>
				Retrait : ${requestScope.article.getUtilisateur().getRue() }<br>
				${requestScope.article.getUtilisateur().getCodePostal() } ${requestScope.article.getUtilisateur().getVille() }
			</p>
		</c:if>
		
		<c:if test="${!empty requestScope.retrait }">
			<p>
				Retrait : ${requestScope.retrait.getRue() }<br>
				${requestScope.retrait.getCodePostal() } ${requestScope.retrait.getVille() }
			</p>
		</c:if>
		
		<p>Vendeur : ${requestScope.article.getUtilisateur().getPseudo() }</p>
		
		<p>Téléphone : ${requestScope.article.getUtilisateur().getTelephone() }</p>
		
		
		<form action="javascript:history.back()">
    	<input type="submit" value="Retour" onclick ="history.back()"/>
    	<br>
	</form>
	
	
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
	
		
		
</body>
</html>