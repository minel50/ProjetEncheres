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
			<form name="filtres" action="accueil" method="POST">
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
						
						<input type="checkbox" class="checkboxAchat" id="cbAchatDefaut" name="encheresOuvertes" onclick="clicSurEncheresOuvertes()" <c:if test="${requestScope.encheresOuvertes eq 'on' || empty requestScope.choixAchatVente }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if> />
						<label for="encheresOuvertes">enchères ouvertes</label><br>
						
						<input type="checkbox" class="checkboxAchat" id = "cbAchatMesEncheresEnCours" name="mesEncheresEnCours" onclick="clicSurEncheresEnCours()" <c:if test="${requestScope.mesEncheresEnCours eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if> />
						<label for="mesEncheresEnCours">mes enchères en cours</label><br>
						
						<input type="checkbox" class="checkboxAchat" id = "cbAchatMesEncheresRemportees" name="mesEncheresRemportees" onclick="clicSurEncheresRemportees()" <c:if test="${requestScope.mesEncheresRemportees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'vente' }">disabled</c:if> />
						<label for="mesEncheresRemportees">mes enchères remportées</label><br>
					</div>
					
					<div id="formulaireVente">
						<input type="radio" id ="btnRadioVente" name="choixAchatVente" value="vente" onclick="basculerSurVente()" <c:if test="${requestScope.choixAchatVente eq 'vente' }">checked</c:if>/>
						<label for="vente">Mes ventes</label><br>
						
						<input type="checkbox" class="checkboxVente" name="mesVentesEnCours" <c:if test="${requestScope.mesVentesEnCours eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />
						<label for="mesVentesEnCours">mes ventes en cours</label><br>
						
						<input type="checkbox" class="checkboxVente" id="cbVenteDefaut" name="mesVentesNonDebutees" <c:if test="${requestScope.mesVentesNonDebutees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />
						<label for="mesVentesNonDebutees">mes ventes non débutées</label><br>
						
						<input type="checkbox" class="checkboxVente" name="mesVentesTerminees" <c:if test="${requestScope.mesVentesTerminees eq 'on' }">checked</c:if> <c:if test="${requestScope.choixAchatVente eq 'achat' || empty requestScope.choixAchatVente }">disabled</c:if> />
						<label for="mesVentesTerminees">mes ventes terminées</label><br>
					</div>
				</c:if>
				
				<button type="submit">Rechercher</button>
			</form>
		</p>
		
		<ul>
			<c:forEach var="a" items="${requestScope.listeArticlesAAfficher}">
				<li>
					<p>
						<c:if test="${ empty sessionScope.utilisateurConnecte }">
							${a.getNomArticle() }<br>
						</c:if>
			
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
						
						Fin de l'enchère ${formatDate.format(a.getDateFinEncheres()) }<br>
						
						<c:if test="${ empty sessionScope.utilisateurConnecte }">
							Vendeur : ${a.getUtilisateur().getPseudo() }<br>
						</c:if>
			
						<c:if test="${ !empty sessionScope.utilisateurConnecte }">
							Vendeur : <a href="VueProfilVendeur?noVendeur=${a.getUtilisateur().getNoUtilisateur() }" title="accéder au profil du vendeur">${a.getUtilisateur().getPseudo() }</a><br>
						</c:if>
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