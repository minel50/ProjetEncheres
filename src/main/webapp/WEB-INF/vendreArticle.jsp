<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.messages.LecteurMessage"%>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Enchères - vendre un article</title>
</head>

<body>
	<header>
		<h1>ENI - Enchères</h1>
	</header>
	
	<main>
		<h2>Nouvelle vente</h2>
		
		<form name="nouvelleVente" action="/encheres/vendre" method="POST">
			<label for="nomArticle">Article :</label>
			<input type="text" name="nomArticle" maxlength="30" required><br>
			
			<label for="description">Description :</label>
			<textarea name="description" rows="4" cols="75" maxlength="300" required draggable="false"></textarea><br>
			
			<label for="noCategorie">Catégorie :</label>
			<select name="noCategorie">
				<c:forEach var="cat" items="${requestScope.listeCategories }">
					<option value="${cat.getNoCategorie() }">${cat.getLibelle() }</option>
				</c:forEach>
			</select><br>
			
			<label for="photo">Photo de l'article :</label>
			Fonction uploader à écrire...<br>
			
			<label for="prixInitial">Mise à prix :</label>
			<input type="number" name="prixInitial" min="1" max="1000000000" required><br>
			
			<label for="dateDebutEnchere">Début de l'enchère :</label>
			<input type="date" name="dateDebutEnchere" min="" max="" value=""><br>
			
			<label for="dateFinEnchere">Fin de l'enchère :</label>
			<input type="date" name="dateFinEnchere" min="" max="" value=""><br>
			
			<fieldset>
				<legend>Retrait</legend>
				
				<label for="rue">Rue :</label>
				<input type="text" name="rue" value="${requestScope.utilisateurConnecte.getRue() }" maxlength="30" required><br>
				
				<label for="codePostal" >Code postal :</label>
				<input type="text" name="codePostal" value="${requestScope.utilisateurConnecte.getCodePostal() }" maxlength="15" required><br>
				
				<label for="ville" >Ville :</label>
				<input type="text" name="ville" value="${requestScope.utilisateurConnecte.getVille() }" maxlength="30" required><br>
				
			</fieldset>
			<button type="submit">Enregistrer</button>
			<button type="reset">Annuler</button>
		</form>
		
			<%
				List<Integer> listeCodesErreursAjoutArticle = (List<Integer>)request.getAttribute("listeCodesErreursAjoutArticle");
				if(listeCodesErreursAjoutArticle!=null)
				{
					%>
					<p style="color:red;">Erreur, l'article n'a pas pu être ajouté :</p>
					<%
					for(int codeErreur:listeCodesErreursAjoutArticle)
					{
			%>
				<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
			<%	
					}
				}
			%>
	</main>
	
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
</body>
</html>