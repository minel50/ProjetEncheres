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
    <title>Profil utilisateur</title>
    <link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
</head>
<body>
	<header>
		
		<h1>ENI-Enchères</h1>
	</header>
	
	<div class="container">
	<p col-> Pseudo : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getPseudo() }
				</c:if>
	</p>
	
	<p> Nom : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getNom() }
				</c:if>
	</p>
	
	<p> Prénom : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getPrenom() }
				</c:if>
	</p>
	
	<p> Email : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getEmail() }
				</c:if>
	</p>
	
	<p> Téléphone : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getTelephone() }
				</c:if>
	</p>
	
	<p> Rue : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getRue() }
				</c:if>
	</p>
	
	<p> Code postal : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getCodePostal() }
				</c:if>
	</p>
	
	<p> Ville : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getVille() }
				</c:if>
	</p>
	
	<p>Crédit : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getCredit() }
				</c:if>
	</p> 
	
	<%-- <form action="<%=request.getContextPath()%>/profil">
    	<input type="submit" value="Retour" onclick ="history.back()"/>
	</form> --%>
	
	<form action="javascript:history.back()">
    	<input type="submit" value="Retour" onclick ="history.back()"/>
	</form>
	
	</div>
	
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