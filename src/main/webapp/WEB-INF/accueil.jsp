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
				<input type="text" name="contient" placeholder="Le nom de l'article contient" />
				<button type="submit">Rechercher</button>
			</form>
		</p>
		<ul>
			<c:forEach var="a" items="${requestScope.listeArticlesEnVente}">
				<li>
					<p>
						${a.getNomArticle()} ${a.getDescription() }<br>
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