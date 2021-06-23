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

	
	
	<form action="PropositionEnchere" method="post">
            <label for="mtEnchere">Ma proposition</label>
            <input type="number" id="mtEnchere" name="mtEnchere" value="" step="10">
            <input type="button" value="Enchérir">
    	</form>

</body>
</html>