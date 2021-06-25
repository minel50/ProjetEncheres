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
    <link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
     <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<header>
		<c:import url="/WEB-INF/back.jsp"/>
	</header>
	
	<main>
		<c:if test="${ empty sessionScope.utilisateurConnecte }">
			<p>
				Merci de vous connecter pour accéder à cette page : <a href="Login" title="connexion">Se connecter</a>
			</p>
		</c:if>
		
		
		
		<c:if test="${ !empty sessionScope.utilisateurConnecte }">
		
			<br>
		
			<h3 class="col-md-12 text-center"> Profil du vendeur </h3>
			
			<br>
			
			<div class="container">
			
			<div class="row">
				<p class="offset-2 offset-sm-4">
					Pseudo : ${requestScope.vendeur.getPseudo() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Nom : ${requestScope.vendeur.getNom() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Prénom : ${requestScope.vendeur.getPrenom() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Email : ${requestScope.vendeur.getEmail() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Téléphone : ${requestScope.vendeur.getTelephone() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Rue : ${requestScope.vendeur.getRue() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Code postal : ${requestScope.vendeur.getCodePostal() }
				</p>
			</div>
			
			<div class="row">
				<p class="offset-2  offset-sm-4">
					Ville : ${requestScope.vendeur.getVille() }
				</p>
			</div>
			
		</div>
		</c:if>
	</main>
	<!-- 
	<a href ="javascript:history.back()">
		Retour 2
		<img src ="image/ENI.png"  onclick ="history.back()">
	</a>
	 -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>