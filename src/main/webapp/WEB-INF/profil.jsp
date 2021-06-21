<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription</title>
</head>

<body>
    <h1>Mon profil</h1>
    
    <form name="formInscription" action="<%=request.getContextPath()%>/profil" method="post">

        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo" required
            minlength="4" maxlength="30" size="10">
        
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required
            minlength="4" maxlength="30" size="10">
        
                <br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required
                minlength="4" maxlength="30" size="10">
        
        <label for="email">Email :</label>
        <input type="text" id="email" name="email" required
                minlength="4" maxlength="20" size="10">

                <br>

        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" required
                minlength="4" maxlength="15" size="10">
        
        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue" required
                minlength="4" maxlength="30" size="10">

                <br>

        <label for="code-postal">Code postal :</label>
        <input type="text" id="code-postal" name="code-postal" required
                minlength="4" maxlength="10" size="10">
        
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" required
                minlength="4" maxlength="30" size="10">

                <br>

        <label for="mdp">Mot de passe :</label>
        <input type="password" id="mdp" name="mdp" required
                minlength="4" maxlength="30" size="10">
        
        <label for="confirmation">Confirmation :</label>
        <input type="password" id="confirmation" name="confirmation" required
                minlength="4" maxlength="30" size="10">

                <br>
        
        <button type="submit" onclick="checkPassword(document.formInscription.mdp)">Créer</button>
        
            
    </form>
	
	<form action="<%=request.getContextPath()%>" method="get">
    <input type="submit" value="Annuler" />
	</form>
	
	<!--Check REGEX mot de pass-->
	<button onclick="checkPassword(document.formInscription.mdp)">check</button>

	<script src="js/check-password.js"></script>
	

</body>
</html>