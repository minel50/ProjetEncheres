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
        <input type="text" id="pseudo" name="pseudo" value=<%= (String)request.getAttribute("pseudo") %> required
            minlength="4" maxlength="30" size="10">
        
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value=<%= (String)request.getAttribute("nom") %> required
            minlength="4" maxlength="30" size="10">
        
                <br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value=<%= (String)request.getAttribute("prenom") %> required
                minlength="4" maxlength="30" size="10">
        
        <label for="email">Email :</label>
        <input type="text" id="email" name="email" value=<%= (String)request.getAttribute("email") %> required
                minlength="4" maxlength="20" size="10">

                <br>

        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" value=<%= (String)request.getAttribute("tel") %> required
                minlength="4" maxlength="15" size="10">
        
        <label for="rue">Rue :</label>
        <input type="text" id="rue" name="rue" value="<%= (String)request.getAttribute("rue") %>" required
                minlength="4" maxlength="30" size="10">

                <br>

        <label for="code-postal">Code postal :</label>
        <input type="text" id="code-postal" name="code-postal" value=<%= (String)request.getAttribute("codePostal") %> required
                minlength="4" maxlength="10" size="10">
        
        <label for="ville">Ville :</label>
        <input type="text" id="ville" name="ville" value=<%= (String)request.getAttribute("ville") %> required
                minlength="4" maxlength="30" size="10">
				
				<br>

        <label for="mdpActuel">Mot de passe actuel :</label>
        <input type="password" id="mdpActuel" name="mdpActuel" required
        minlength="4" maxlength="30" size="10">
                
                <br>

        <label for="mdp">Mot de passe :</label>
        <input type="password" id="mdp" name="mdp" required
                minlength="4" maxlength="30" size="10">
        
        <label for="confirmation">Confirmation :</label>
        <input type="password" id="confirmation" name="confirmation" required
                minlength="4" maxlength="30" size="10">

                <br>
        
        <label for="credit">Crédit :</label> 
        
        		<br>
        
        <button type="submit" onclick="checkPassword(document.formInscription.mdp)">Créer</button>
        
            
	    </form>
		
		<form action="<%=request.getContextPath()%>" method="get">
	    <input type="submit" value="Annuler" />
		</form>
		
		<!--Check REGEX mot de pass-->
		<button onclick="checkPassword(document.formInscription.mdp)">check</button>
		
		
		<p style="color: red;" id="message-echec"></p>
		<p style="color: green;" id="message-succes"></p>		
		

		<script src="js/check-password.js"></script>
	

</body>
</html>