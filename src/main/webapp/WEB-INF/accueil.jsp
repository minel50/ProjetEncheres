<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href ="https://bootswatch.com/4/lux/bootstrap.min.css" rel ="stylesheet">
 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		<link href="style.css" rel =stylesheet type="text/css" >
	
</head>
<body>


<c:if test="${ empty sessionScope.utilisateurConnecte }">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#"></a>
	            <span class="visually-hidden"></span></a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	
	    <div class="collapse navbar-collapse" id="navbarColor01">
	      <ul class="navbar-nav me-auto">
	        <li class="nav-item">
	          <a class="nav-link active" href="Login">S'inscrire-Se connecter</a>
	            <span class="visually-hidden"></span>
	        </li>
	         </ul>
	          	
	          </div>
	 	</div>
	</nav>
</c:if>
    
<c:if test="${ !empty sessionScope.utilisateurConnecte }">	

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  	<div class="container-fluid">
	    	<a class="navbar-brand" href="#"></a>
	    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	      	<span class="navbar-toggler-icon"></span>
	    	</button>
	
	    	<div class="collapse navbar-collapse" id="navbarColor01">
	      	<ul class="navbar-nav me-auto">
	        	<li class="nav-item ">
	          	<a class="nav-link active" href="vente">Vendre un article</a>
	           
	        	</li>
	        	<li class="nav-item ">
	          	<a class="nav-link active" href="profil">Mon profil</a>
	            
	        	<li class="nav-item dopdown">
	          	<a class="nav-link active" href="Logout"> Déconnexion</a>
	           
	        	</li>
	         </ul>
	          </div>
	          	
	 		</div>
	</nav>
</c:if>	
<main>
<h3 class="text-center mt-3">Liste des enchères</h3>
<h4 class = "ml-5">Filtres :</h4>
	<form name="filtres" action="accueil" method="POST">
	
      	 
 	<div class="form-group w-25">
      	<input type="text" name="filtreNom" class = "form-control" value ="${requestScope.filtreNom }" placeholder="Le nom de l'article contient" />
		<label for="filtreCategorie" class ="ml-5 h4">Catégorie : </label>
	
 	
     
      <select class="form-select" id="exampleSelect1" name ="filtreCategorie" >
        <option value ="0">Toutes</option>
        <c:forEach var = "cat" items="${requestScope.listeCategories }">
        <option value="${cat.getNoCategorie() }" <c:if test="${requestScope.noCategorie == cat.getNoCategorie() }">selected</c:if>>${cat.getLibelle() }</option>
        	</c:forEach>
      </select>
   	<button type="submit" class="btn btn-primary">Rechercher</button>

	</div>
	
	
	<c:if test="${ !empty sessionScope.utilisateurConnecte }">
	<div id="formulaire">
	<div id="formulaireAchat">
		
	
	<label for="achat form-check-label">
    <input type="radio" id ="btnRadioAchat" name="choixAchatVente" value="achat" onclick="basculerSurAchat()" <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">checked</c:if>/> Achats</label><br>
 	<label for="encheresOuvertes">
	<input type="checkbox" class="checkboxAchat" id="cbAchatDefaut" name="encheresOuvertes" onclick="clicSurEncheresOuvertes()" <c:if test="${requestScope.encheresOuvertes eq 'on' || empty requestScope.choixAchatVente }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if>/>enchères ouvertes</label><br>

	
	<label for="mesEncheresEnCours">					
	<input type="checkbox" class="checkboxAchat" id = "cbAchatMesEncheresEnCours" name="mesEncheresEnCours" onclick="clicSurEncheresEnCours()" <c:if test="${requestScope.mesEncheresEnCours eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if> />mes enchères en cours</label><br>
	
	<label for="mesEncheresRemportees">				
	<input type="checkbox" class="checkboxAchat" id = "cbAchatMesEncheresRemportees" name="mesEncheresRemportees" onclick="clicSurEncheresRemportees()" <c:if test="${requestScope.mesEncheresRemportees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if> />	mes enchères remportées</label><br>		
	
    </div>
	<div id="formulaireVente">
	<label for="vente">
	<input type="radio" id ="btnRadioVente" name="choixAchatVente" value="vente" onclick="basculerSurVente()" <c:if test="${requestScope.choixAchatVente eq 'vente' }">checked</c:if>/>Mes ventes</label><br>
						

	<label for="mesVentesEnCours">				
	<input type="checkbox" class="checkboxVente" name="mesVentesEnCours" <c:if test="${requestScope.mesVentesEnCours eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />mes ventes en cours</label><br>
	
	
	<label for="mesVentesNonDebutees">				
	<input type="checkbox" class="checkboxVente" id="cbVenteDefaut" name="mesVentesNonDebutees" <c:if test="${requestScope.mesVentesNonDebutees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />mes ventes non débutées</label><br>

	<label for="mesVentesTerminees">				
	<input type="checkbox" class="checkboxVente" name="mesVentesTerminees" <c:if test="${requestScope.mesVentesTerminees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />mes ventes terminées</label><br>
	
</div>	
</div>
	
	
</c:if>
	</form>
	
<div class =" col-12  " >

<ul >
	<c:forEach var="a" items="${requestScope.listeArticlesAAfficher}">


<div class = "col-3 col-sm-3 col-md-13 col-lg-3  ">
<div class="card text-black bg-white mb-3 "   style="max-width: 20rem;">
	<c:if test="${ empty sessionScope.utilisateurConnecte }">
 	 	<div class="card-header" >	${a.getNomArticle() }</div>
 	 </c:if>
 	 
  	<div class="card-body" > 
  		<li>
					<p> 
					
			
						<c:if test="${ !empty sessionScope.utilisateurConnecte && empty requestScope.mesEncheresRemportees && a.getEtatVente() ne 'vente terminée'}">
							<a href="detailVente?noArticle=${a.getNoArticle() }" title="accéder à la vente de cet article">${a.getNomArticle() }</a><br>
						</c:if>
						
						<c:if test="${ !empty sessionScope.utilisateurConnecte && requestScope.mesEncheresRemportees eq 'on' }">
							<a href="venteRemportee?noArticle=${a.getNoArticle() }" title="accéder à la page enchère remportée">${a.getNomArticle() }</a><br>
						</c:if>
						
						<c:if test="${ !empty sessionScope.utilisateurConnecte && requestScope.choixAchatVente eq 'vente' && a.getEtatVente() eq 'vente terminée' }">
							<a href="enchereRemportee?noArticle=${a.getNoArticle() }" title="accéder à la page vente terminée">${a.getNomArticle() }</a><br>
						</c:if>
						
						Prix : ${a.getPrixInitial() } pts<br>
						
						Fin de l'enchère : ${formatDate.format(a.getDateFinEncheres()) }<br>
						
						<c:if test="${ empty sessionScope.utilisateurConnecte }">
							Vendeur : ${a.getUtilisateur().getPseudo() }<br>
						</c:if>
			
						<c:if test="${ !empty sessionScope.utilisateurConnecte }">
							Vendeur : <a href="VueProfilVendeur?noVendeur=${a.getUtilisateur().getNoUtilisateur() }" title="accéder au profil du vendeur">${a.getUtilisateur().getPseudo() }</a><br>
						</c:if>
					</p>
				</li>
	
	
  
  
  
  
   
  </div>
</div>
</div>
</c:forEach>			
 
</ul>
</div>


 	</main>
	<footer>&copy; Claire - Thomas - Grégory - 2021</footer>
	
	<script  type="text/javascript">
	
		function basculerSurAchat() {
			var checkboxesAchat = document.getElementsByClassName("checkboxAchat");
			for (var i = 0; i < checkboxesAchat.length; i ++) {
				checkboxesAchat[i].disabled = false;
			}
			
			var checkboxesVente = document.getElementsByClassName("checkboxVente");
			for (var i = 0; i < checkboxesVente.length; i ++) {
				checkboxesVente[i].checked = false;
				checkboxesVente[i].disabled = true;
			}
			
			document.getElementById("cbAchatDefaut").checked = true;			
		}
		
		function basculerSurVente() {
			var checkboxesAchat = document.getElementsByClassName("checkboxAchat");
			for (var i = 0; i < checkboxesAchat.length; i ++) {
				checkboxesAchat[i].checked = false;
				checkboxesAchat[i].disabled = true;
			}
			
			var checkboxesVente = document.getElementsByClassName("checkboxVente");
			for (var i = 0; i < checkboxesVente.length; i ++) {
				checkboxesVente[i].disabled = false;
			}

			document.getElementById("cbVenteDefaut").checked = true;	
		}
		
		function clicSurEncheresOuvertes() {
			document.getElementById("cbAchatMesEncheresEnCours").checked = false;
			document.getElementById("cbAchatMesEncheresRemportees").checked = false;
		}
		
		function clicSurEncheresEnCours() {
			document.getElementById("cbAchatDefaut").checked = false;
			document.getElementById("cbAchatMesEncheresRemportees").checked = false;			
		}
		
		function clicSurEncheresRemportees() {
			document.getElementById("cbAchatDefaut").checked = false;
			document.getElementById("cbAchatMesEncheresEnCours").checked = false;			
		}
		
	</script>      
	
	
	
</body>
</html>
	
	
	
       































	