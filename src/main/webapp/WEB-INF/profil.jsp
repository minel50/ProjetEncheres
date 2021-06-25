<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr"> 
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edition du profil</title>
     <link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
     <link rel="stylesheet" href="css/style.css">
</head>

<body>

	<header>
		 <c:import url="/WEB-INF/back.jsp"/>
	</header>
	
	<br>
	
    <h2 class="col-12 col-md-11 text-center titre-profil">Mon profil</h2>
    
    <div class="container">
    
    <form name="formInscription" action="<%=request.getContextPath()%>/profil" method="post">
	
	
	
        <label class="col-4 offset-md-2 col-md-2" for="pseudo">Pseudo:</label>
        <input class="col-6 col-md-4" type="text" id="pseudo" name="pseudo" value="<%= (String)request.getAttribute("pseudo") %>" required
            minlength="4" maxlength="30" size="10">
        
        <label class="col-4 offset-md-2 col-md-2" for="nom">Nom :</label>
        <input class="col-6 col-md-4" type="text" id="nom" name="nom" value="<%= (String)request.getAttribute("nom") %>" required
            minlength="4" maxlength="30" size="10">
        
                

        <label class="col-4 offset-md-2 col-md-2" for="prenom">Prénom :</label>
        <input class="col-6 col-md-4" type="text" id="prenom" name="prenom" value="<%= (String)request.getAttribute("prenom") %>" required
                minlength="4" maxlength="30" size="10">
        
        <label class="col-4 offset-md-2 col-md-2" for="email">Email :</label>
        <input class="col-6 col-md-4" type="text" id="email" name="email" value="<%= (String)request.getAttribute("email") %>" required
                minlength="4" maxlength="20" size="20">

                

        <label class="col-4 offset-md-2 col-md-2" for="telephone">Tel :</label>
        <input class="col-6 col-md-4" type="text" id="telephone" name="telephone"value="<%= (String)request.getAttribute("tel") %>" required
                minlength="4" maxlength="15" size="10">
        
        <label class="col-4 offset-md-2 col-md-2" for="rue">Rue :</label>
        <input class="col-6 col-md-4" type="text" id="rue" name="rue" value="<%= (String)request.getAttribute("rue") %>" required
                minlength="4" maxlength="30" size="20">

                

        <label class="col-4 offset-md-2 col-md-2" for="code-postal">Code postal :</label>
        <input class="col-6 col-md-4" type="text" id="code-postal" name="code-postal" value="<%= (String)request.getAttribute("codePostal") %>" required
                minlength="4" maxlength="10" size="10">
        
        <label class="col-4 offset-md-2 col-md-2" for="ville">Ville :</label>
        <input class="col-6 col-md-4" type="text" id="ville" name="ville" value="<%= (String)request.getAttribute("ville") %>" required
                minlength="4" maxlength="30" size="20">
				
				<br>
				<br>

        <label class="col-10 text-center mdp1" for="mdpActuel">Mot de pass actuel</label>
        <input class="col-10 text-center offset-md-3 col-md-4" type="password" id="mdpActuel" name="mdpActuel" 
        minlength="4" maxlength="30" size="10">
                
                <br>

        <label class="col-10 text-center mdp2" for="mdp">Nouveau mot de pass</label>
        <input class="col-10 text-center offset-md-3 col-md-4" type="password" id="mdp" name="mdp" 
                minlength="4" maxlength="30" size="10">
                
                <br>
        
        <label class="col-10 text-center mdp3" for="confirmation">Confirmation</label>
        <input class="col-10 text-center offset-md-3 col-md-4" type="password" id="confirmation" name="confirmation" 
                minlength="4" maxlength="30" size="10">

                <br>
                <br>
        
        <p class="col-10 text-center text-info" >Crédit disponible : <%= request.getAttribute("credit") %></p> 
        
        
        
        		<br>
        
        <button class="col-10 offset-md-3 col-md-4 text-center btn btn-primary" type="submit" >Enregistrer</button>
       <!-- ajouter onclick="checkPassword(document.formInscription.mdp)" -->
            
        
    </form>
    
    <br>
    
   
	<form action="<%=request.getContextPath()%>/SupprimerProfil" method="get">
    	<input class="col-10 offset-md-3 col-md-4 text-center btn btn-danger" type="submit" value="Supprimer mon compte" onclick="if (confirm('Are you...?')) commentDelete(1); return false"/>
	</form>
	
	</div> 
	
	<br>
	<br>
	<br>
	
	<!--Check REGEX mot de pass-->
	<!--  <button onclick=checkPassword(document.formInscription.mdp)>check javascript</button>-->
	
	
	<p style="color: red;" id="message-echec"><%= (String)request.getAttribute("msgEchec") %></p>
	<p style="color: green;" id="message-succes"><%= (String)request.getAttribute("msgSucces") %></p>		
	
	<script src="js/check-password.js"></script>
	<script src="js/check-fields.js"></script>
	<script src="js/popup-suppression.js"></script>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		
</body>
</html>