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
	<title>Test DAL Categories</title>
</head>
<body>
	<header>
		<h1>Tests DAL Articles</h1>
	</header>
	
		<h3>Affichage de toutes les catégories présentes en base de données : </h3>
		
		<p>
			<c:forEach var="element" items="${requestScope.listeCategories }">
				${element}<br>
			</c:forEach>
		</p>
		
</body>
</html>