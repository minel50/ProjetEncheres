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
	<title>Test DAL Association entre utilisateurs et enchères</title>
</head>

<body>
	<header>
		<h1>Test DAL Association entre utilisateurs et enchères</h1>
	</header>
	<main>
		<h3>Affichage des utilisateurs et pour chacun les enchères émises</h3>
		<ul>
			<c:forEach var="utilisateur" items="${requestScope.listeUtilisateurs}">
				<li>Utilisateur #${utilisateur.getNoUtilisateur()} ${utilisateur.getPrenom()} ${utilisateur.getNom()}</li>
					<ul>
						<c:forEach var="enchere" items ="${utilisateur.getListeEncheres()}">
							<li>Article #${enchere.getNoArticle()}, montant: ${enchere.getMontantEnchere()} pts</li>
						</c:forEach>
					</ul>
			</c:forEach>
		</ul>
			
		<!-- <h3>Affichage de la liste des enchères</h3>
		<ul>
			<c:forEach var="element" items="${requestScope.listeEncheres}">
				<li>${element}</li>
			</c:forEach>
		</ul>-->
	</main>
</body>
</html>