<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.Date" %>

<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	
	<form action="<%=request.getContextPath()%>/PropositionEnchere" method="post">
            <label for="mtEnchere">Ma proposition</label>
            <input type="number" id="mtEnchere" name="mtEnchere" 
            min="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }"
            max="${sessionScope.utilisateurConnecte.getCredit() }"
            value="${requestScope.meilleureEnchere.getMontantEnchere() + 1 }" step="10">
            <input type="submit" value="Enchérir">
            <input name="noArticle" type="hidden" value="${requestScope.article.getNoArticle()}">
            
            
    	</form>

</body>
</html>