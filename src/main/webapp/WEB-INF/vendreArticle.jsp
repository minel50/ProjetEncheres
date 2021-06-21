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
	<title>Enchères - vendre un article</title>
</head>

<body>
	<header>
		<h1>ENI - Enchères</h1>
		<nav>
			<a href="" title="inscription">S'inscrire</a> - <a href="" title="connexion">Se connecter</a>
		</nav>
	</header>
	
	<main>
		<h2>Nouvelle vente</h2>
		
		<form name="nouvelleVente" action="/encheres/NomServlet" method="POST">
			<label for="nomArticle">Article :</label>
			<input type="text" name="nomArticle" maxlength="30" required><br>
			
			<label for="description">Description :</label>
			<textarea name="description" rows="4" cols="75" maxlength="300" required></textarea><br>
			
			<label for="categorie">Catégorie :</label>
			<select name="categorie">
				<c:forEach var="cat" items="${requestScope.listeCategories }">
					<option></option>
				</c:forEach>
			</select>
		</form>
	</main>
	
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
</body>
</html>