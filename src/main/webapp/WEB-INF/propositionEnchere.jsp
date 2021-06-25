<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="<%=request.getContextPath()%>/PropositionEnchere" method="post">
      <label for="mtEnchere">Ma proposition</label>
      <input type="number" id="mtEnchere" name="mtEnchere" 
      min="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }"
      max="${sessionScope.utilisateurConnecte.getCredit() }"
      value="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }">
      <input type="submit" value="Enchérir" <c:if test="${sessionScope.utilisateurConnecte.getCredit() <= requestScope.article.getPrixVente() }">disabled</c:if>>
      <c:if test="${sessionScope.utilisateurConnecte.getCredit() <= requestScope.article.getPrixVente() }"><span style="color: red">Vous n'avez que ${sessionScope.utilisateurConnecte.getCredit() } points !</span></c:if>
      <input name="noArticle" type="hidden" value="${requestScope.article.getNoArticle()}">
</form>