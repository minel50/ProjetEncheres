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
</head>
<body>
	<header>
		<h1>ENI-Enchères</h1>
	</header>
	
	<p> Pseudo : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
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
	
	<form action="<%=request.getContextPath()%>/profil">
    	<input type="submit" value="Modifier" />
	</form>
	
</body>
</html>