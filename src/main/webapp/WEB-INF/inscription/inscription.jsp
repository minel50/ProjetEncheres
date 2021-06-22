<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>
	<h1 > Mon profil</h1>
	<h2>Inscription</h2>
	<span>${resultat }</span>
	

	
	
	<form method ="post" action = "<%=request.getContextPath()%>/Inscription">
		
			
			<input type="text" id="pseudo" name = "pseudo" value ="<c:out value = "${param.pseudo}"/>" placeholder="Pseudo" required>
				
			<input type="text" id="nom" name = "nom" value ="<c:out value = "${param.nom}"/>" placeholder="Nom" required>
			<input type="text" id="prenom" name = "prenom" value ="<c:out value = "${param.prenom}"/>" placeholder="Prénom" required>
			<input type="email" id="email" name = "email" value ="<c:out value = "${param.email}"/>" placeholder="Email" required>
			<%-- <span class="erreur">${erreurs["email"] }</span> --%>
			<input type="tel" id="tel" name = "telephone" value ="<c:out value = "${param.telephone}"/>" placeholder="Telephone" >
		 	<span class="erreur">${erreurs["telephone"] }</span> 
			<input type="text" id="rue" name = "rue" value ="<c:out value = "${param.rue}"/>" placeholder="Rue" required>
			<input type="text" id="codepostal" name = "codepostal" value ="<c:out value = "${param.codepostal}"/>" placeholder="Code Postal" required >
			<input type="text" id="ville" name = "ville" value ="<c:out value = "${param.ville}"/>" placeholder="Ville" required>
			
			<input type="password" id="motpasse" name = "motpasse" value ="<c:out value = "${param.motpasse}"/>" placeholder="Mot de Passe" required>
		 	<span class="erreur">${erreurs["motpasse"] }</span>
			 <input type="password" id="confirmation" name = "confirmation" value ="<c:out value = "${param.confirmation}"/>" placeholder="Confirmation Mot de Passe" required>
			 
			<input type ="submit" value = "Créer" >
		
			 <input type="reset" value="Annuler" onClick = 'a()'>
			 
			 
	</form>
				
		 <script>
			function a(){
				 window.open('accueil');
				
				return true;
			} 
			
		
		</script> 

	
	
</body>
</html>