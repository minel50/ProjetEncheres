<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" href="app.css" />
	<title>Enchères - détail d'une vente</title>
	 <link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
     <link rel="stylesheet" href="css/style.css">
</head>

<body>
	<header>
		 <c:import url="/WEB-INF/back.jsp"/>
	</header>
	<main class="container">
		<br>
		
		<h3 class="col-md-12 text-center">Détail vente</h3>
		
		
		<div class="col-md-12 text-center">
			<p class="titre-produit">${requestScope.article.getNomArticle() }</p>
		</div>
		
		<div class="col-md-12 text-center">
			<p class="">Description : ${requestScope.article.getDescription() }</p>
		</div>
		
		<div class="col-md-12 text-center">
			<p class="">Catégorie : ${requestScope.article.getCategorie().getLibelle() }</p>
		</div>
		
		<div class="col-md-12 text-center">
			
				<c:if test="${!empty requestScope.meilleureEnchere }">
					<p class="text-success">Meilleure offre : ${requestScope.meilleureEnchere.getMontantEnchere() } pts par ${requestScope.meilleurAcheteur.getPseudo() }</p>
				</c:if>
				<c:if test="${empty requestScope.meilleureEnchere }">
					<p class="text-info">Aucune offre n'a été faite pour le moment ...</p>
				</c:if>
			
		</div>
		
		<div class="row">
			<p class="col-md-12 text-center">Mise à prix initiale : ${requestScope.article.getPrixInitial() } pts</p>
		</div>
		
		<div class="row">
			<p class="col-md-12 text-center">Fin de l'enchère : ${requestScope.formatAffichageDateHeure.format(requestScope.article.getDateFinEncheres()) }</p>
		</div>
		
		<div class="col-md-12 text-center">
			<c:if test="${empty requestScope.retrait }">
				<p class="">
					Retrait : ${requestScope.article.getUtilisateur().getRue() }<br>
					${requestScope.article.getUtilisateur().getCodePostal() } ${requestScope.article.getUtilisateur().getVille() }
				</p>
			</c:if>
			
			<c:if test="${!empty requestScope.retrait }">
				<p class="">
					Retrait : ${requestScope.retrait.getRue() }<br>
					${requestScope.retrait.getCodePostal() } ${requestScope.retrait.getVille() }
				</p>
			</c:if>
		</div>
		
		
		<div class="col-md-12 text-center">
			<p class="">Vendeur : ${requestScope.article.getUtilisateur().getPseudo() }</p>
		</div>
		
		<br>
		
		<c:import url="/WEB-INF/propositionEnchere.jsp"/>
		
	</main>
	
	
	<br>
	
	<div class="container">
		<footer class="text-center">&copy; Claire - Thomas - Grégory - 2021</footer>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>