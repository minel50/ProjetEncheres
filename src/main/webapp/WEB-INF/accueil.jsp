<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Enchères</title>
</head>

<body>
	<header>
		<h1>ENI - Enchères</h1>
		<nav>
			<a href="" title="inscription">S'inscrire</a> - <a href="" title="connexion">Se connecter</a>
		</nav>
	</header>
	<main>
		<h2>Liste des enchères</h2>
		<p>
			<h3>Filtres :</h3>
			<form name="filtres" action="/encheres/" method="POST">
				<input type="text" name="filtreNom" value ="${requestScope.filtreNom }" placeholder="Le nom de l'article contient" />
				<label for="filtreCategorie">Catégorie : </label>
				<select name="filtreCategorie">
					<option value="0">Toutes</option>
					<c:forEach var = "cat" items="${requestScope.listeCategories }">
						<option value="${cat.getNoCategorie() }" <c:if test="${requestScope.noCategorie == cat.getNoCategorie() }">selected</c:if>>${cat.getLibelle() }</option>
					</c:forEach>
				</select>
				<button type="submit">Rechercher</button>
			</form>
		</p>
		<ul>
			<c:forEach var="a" items="${requestScope.listeArticlesEnVente}">
				<li>
					<p>
						${a.getNomArticle() }<br>
						Prix : ${a.getPrixVente() } pts<br>
						Fin de l'enchère : ${formatDate.format(a.getDateFinEncheres()) }<br>
						Vendeur : ${a.getUtilisateur().getPseudo() }
					</p>
				</li>
			</c:forEach>
		</ul>
	</main>
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
</body>
</html>