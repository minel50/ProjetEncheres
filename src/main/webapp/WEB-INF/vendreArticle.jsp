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
		<c:if test="${ !empty sessionScope.utilisateurConnecte }">
			<p>Vous êtes ${sessionScope.utilisateurConnecte.getPseudo() }</p>
		</c:if>
	</header>
	
	<main>
		<h2>Nouvelle vente</h2>
		
		<c:if test="${ empty sessionScope.utilisateurConnecte }">
			<p>Vous devez vous connecter à votre compte pour accéder à cette page : <a href="Login">Se connecter</a></p>
		</c:if>
		
		<c:if test="${ !empty sessionScope.utilisateurConnecte }">
			
			<c:if test="${ !empty requestScope.listeCodesErreursAjoutArticle }">
				<p style="color:red;">L'article n'a pas pu être ajouté :</p>
				<ul>
					<c:forEach var="codeErreur" items="${requestScope.listeCodesErreursAjoutArticle }">
						<li>${ LecteurMessage.getMessageErreur(codeErreur) }</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<c:if test="${ empty requestScope.listeCodesErreursAjoutArticle && requestScope.succes == 1}">
				<p style="color: green;">L'article a été créé avec succès</p>
			</c:if>
			
			<form name="nouvelleVente" action="vente" method="POST">
				<label for="nomArticle">Article :</label>
				<input type="text" name="nomArticle" maxlength="30" value="${requestScope.articleEnCoursCreation.getNomArticle() }" required><br>
				
				<label for="description">Description :</label>
				<textarea name="description" rows="4" cols="75" maxlength="300" required>${requestScope.articleEnCoursCreation.getDescription() }</textarea><br>
				
				<label for="noCategorie">Catégorie :</label>
				<select name="noCategorie">
					<c:forEach var="cat" items="${requestScope.listeCategories }">
						<option value="${cat.getNoCategorie() }" <c:if test="${requestScope.articleEnCoursCreation.getCategorie().getNoCategorie() == cat.getNoCategorie() }">selected</c:if>>
							${cat.getLibelle() }
						</option>
					</c:forEach>
				</select><br>
				
				<label for="prixInitial">Mise à prix :</label>
				<input type="number" name="prixInitial" min="1" max="1000000000" value="${requestScope.articleEnCoursCreation.getPrixInitial() }" required><br>
				
				<label for="dateDebutEnchere">Début de l'enchère :</label>
				<input type="date" name="dateDebutEnchere" min="${requestScope.dateAujourdhui }" value="${requestScope.dateAujourdhui }">
				<input type="time" name="heureDebutEnchere" value="${requestScope.heureHPlusUne }"><br>
				
				<label for="dateFinEnchere">Fin de l'enchère :</label>
				<input type="date" name="dateFinEnchere" min="${requestScope.dateAujourdhui }" value="${requestScope.dateDemain }">
				<input type="time" name="heureFinEnchere" value="22:00"><br>
				
				<fieldset>
					<legend>Retrait</legend>
					
					<label for="rue">Rue :</label>
					<input type="text" name="rue" value="${sessionScope.utilisateurConnecte.getRue() }" maxlength="30" required><br>
					
					<label for="codePostal" >Code postal :</label>
					<input type="text" name="codePostal" value="${sessionScope.utilisateurConnecte.getCodePostal() }" maxlength="15" required><br>
					
					<label for="ville" >Ville :</label>
					<input type="text" name="ville" value="${sessionScope.utilisateurConnecte.getVille() }" maxlength="30" required><br>
					
				</fieldset>
				<button type="submit">Enregistrer</button>
				<button type="reset">Annuler</button>
			</form>
		</c:if>
		
			
	</main>
	
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
</body>
</html>