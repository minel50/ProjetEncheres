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
					${sessionScope.utilisateurConnecte.getNom }
				</c:if>
	
	</p>
	
	<p> Prénom : <c:if test="${ !empty sessionScope.utilisateurConnecte }">
					${sessionScope.utilisateurConnecte.getPrenom }
				</c:if>
	</p>
	
	<p> Email : <%= (String)request.getAttribute("email") %></p>
	
	<p> Téléphone : <%= (String)request.getAttribute("tel") %></p>
	
	<p> Rue : <%= (String)request.getAttribute("rue") %></p>
	
	<p> Code postal : <%= (String)request.getAttribute("codePostal") %></p>
	
	<p> Ville : <%= (String)request.getAttribute("ville") %></p>
	
	<p>Crédit : <%= request.getAttribute("credit") %></p> 
	
	<form action="<%=request.getContextPath()%>/Profil">
    	<input type="submit" value="Modifier" />
	</form>
	
</body>
</html>