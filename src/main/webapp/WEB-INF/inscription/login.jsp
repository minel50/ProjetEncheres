<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>login</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
	<h1>Login</h1>
	<span>${resultat}</span>
	<form method="post" action ="Login">

	<input type=text id = "identifiant"  value ="<c:out value="${pseudo}" />" name ="pseudo" placeholder = "identifiant" required>
	<%--  <span class="erreur">${erreurs["pseudo"] }</span>  --%>
	<input type="password" id  ="motpasse" value ="" name ="motpasse" required>
	
	<input type="submit" value="Connexion">
	
	</form>
	<p>
	Se souvenir de moi <input type="checkbox" id="remember" name="remember"> 

</p>
	<a href = "" >Mot de passe oublié</a>
	
	
	
	
	
	
	
	
	
	
	
	
	<p>
	<a href ="Inscription">
	<button>
	Créer un compte
	</button>
	</a>
	</p>
</body>
</html>