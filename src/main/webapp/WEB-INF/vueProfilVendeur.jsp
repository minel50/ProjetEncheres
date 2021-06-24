<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
 <meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profil du vendeur</title>
</head>
<body>
	<header>
		<h1>ENI-Enchères</h1>
	</header>
	
	<main>
		<c:if test="${ empty sessionScope.utilisateurConnecte }">
			<p>
				Merci de vous connecter pour accéder à cette page : <a href="Login" title="connexion">Se connecter</a>
			</p>
		</c:if>
		
		<c:if test="${ !empty sessionScope.utilisateurConnecte }">
			<h2>Profil du vendeur</h2>
			<p>
				Pseudo : ${requestScope.vendeur.getPseudo() }
			</p>
			
			<p>
				Nom : ${requestScope.vendeur.getNom() }
			</p>
			
			<p>
				Prénom : ${requestScope.vendeur.getPrenom() }
			</p>
			
			<p>
				Email : ${requestScope.vendeur.getEmail() }
			</p>
			
			<p>
				Téléphone : ${requestScope.vendeur.getTelephone() }
			</p>
			
			<p>
				Rue : ${requestScope.vendeur.getRue() }
			</p>
			
			<p>
				Code postal : ${requestScope.vendeur.getCodePostal() }
			</p>
			
			<p>
				Ville : ${requestScope.vendeur.getVille() }
			</p>
		</c:if>
	</main>
	<!-- 
	<a href ="javascript:history.back()">
		Retour 2
		<img src ="image/ENI.png"  onclick ="history.back()">
	</a>
	 -->
	
</body>
</html>