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
	<link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
     <link rel="stylesheet" href="css/style.css">
	<title>Vente remportée</title>
</head>
<body>

<div class="container">
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
    	<input class="btn btn-success" type="submit" value="Retour" onclick ="history.back()"/>
    	<br>
	</form>
	
	<br>
	<br>
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
	
	
	</div>
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
		
</body>
</html>