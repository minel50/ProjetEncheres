<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="<%=request.getContextPath()%>/PropositionEnchere" method="post">
      <label for="mtEnchere">Ma proposition</label>
      <input type="number" id="mtEnchere" name="mtEnchere" 
      min="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }"
      max="${sessionScope.utilisateurConnecte.getCredit() }"
      value="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }">
      <input type="submit" value="Enchérir">
      <input name="noArticle" type="hidden" value="${requestScope.article.getNoArticle()}">
</form>