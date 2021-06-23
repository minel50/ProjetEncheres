<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Enchères</title>
	
</head>

<body>
	<header>
		<h1>ENI - Enchères</h1>
		<nav>
			<c:if test="${ empty sessionScope.utilisateurConnecte }">
				<a href="Login" title="connexion">S'inscrire - Se connecter</a>
			</c:if>
			
			<c:if test="${ !empty sessionScope.utilisateurConnecte }">
				<a href="vente" title="vendre un article">Vendre un article</a>
				<a href="profil" title="afficher mon profil">Mon profil</a>
				<a href="Logout" title="se déconnecter">Déconnexion</a>
			</c:if>
		</nav>
	</header>
	<main>
		<h2>Liste des enchères</h2>
		<p>
			<h3>Filtres :</h3>
			<form name="filtres" action="/encheres/" method="POST">
				<input type="text" name="filtreNom" value ="${requestScope.filtreNom }" placeholder="Le nom de l'article contient" />
				<label for="filtreCategorie">Catégorie : </label>
				<select name="filtreCategorie">
					<option value="0">Toutes</option>
					<c:forEach var = "cat" items="${requestScope.listeCategories }">
						<option value="${cat.getNoCategorie() }" <c:if test="${requestScope.noCategorie == cat.getNoCategorie() }">selected</c:if>>${cat.getLibelle() }</option>
					</c:forEach>
				</select>
				
				<c:if test="${ !empty sessionScope.utilisateurConnecte }">
					<div id="formulaireAchat">
						<input type="radio" id ="btnRadioAchat" name="choixAchatVente" value="achat" onclick="basculerSurAchat()" <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">checked</c:if>/>
						<label for="achat">Achats</label><br>
						
						<input type="checkbox" class="checkboxAchat" id="cbAchatDefaut" name="encheresOuvertes" <c:if test="${requestScope.encheresOuvertes eq 'on' || empty requestScope.choixAchatVente }">checked</c:if> />
						<label for="encheresOuvertes">enchères ouvertes</label><br>
						
						<input type="checkbox" class="checkboxAchat" name="mesEncheresEnCours" <c:if test="${requestScope.mesEncheresEnCours eq 'on' }">checked</c:if> />
						<label for="mesEncheresEnCours">mes enchères en cours</label><br>
						
						<input type="checkbox" class="checkboxAchat" name="mesEncheresRemportees" <c:if test="${requestScope.mesEncheresRemportees eq 'on' }">checked</c:if> />
						<label for="mesEncheresRemportees">mes enchères remportées</label><br>
					</div>
					
					<div id="formulaireVente">
						<input type="radio" id ="btnRadioVente" name="choixAchatVente" value="vente" onclick="basculerSurVente()" <c:if test="${requestScope.choixAchatVente eq 'vente' }">checked</c:if>/>
						<label for="vente">Mes ventes</label><br>
						
						<input type="checkbox" class="checkboxVente" name="mesVentesEnCours" <c:if test="${requestScope.mesVentesEnCours eq 'on' }">checked</c:if> />
						<label for="mesVentesEnCours">mes ventes en cours</label><br>
						
						<input type="checkbox" class="checkboxVente" id="cbVenteDefaut" name="mesVentesNonDebutees" <c:if test="${requestScope.mesVentesNonDebutees eq 'on' }">checked</c:if> />
						<label for="mesVentesNonDebutees">mes ventes non débutées</label><br>
						
						<input type="checkbox" class="checkboxVente" name="mesVentesTerminees" <c:if test="${requestScope.mesVentesTerminees eq 'on' }">checked</c:if> />
						<label for="mesVentesTerminees">mes ventes terminées</label><br>
					</div>
				</c:if>
				
				<button type="submit">Rechercher</button>
			</form>
		</p>
		<ul>
			<c:forEach var="a" items="${requestScope.listeArticlesEnVente}">
				<li>
					<p>
						<a href="detailVente?noArticle=${a.getNoArticle() }" title="accéder à la vente de cet article">${a.getNomArticle() }</a><br>
						Prix : ${a.getPrixVente() } pts<br>
						Fin de l'enchère : ${formatDate.format(a.getDateFinEncheres()) }<br>
						Vendeur : ${a.getUtilisateur().getPseudo() }
					</p>
				</li>
			</c:forEach>
		</ul>
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
		
	</script>
</body>
</html>