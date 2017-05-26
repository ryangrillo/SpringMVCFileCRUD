<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="results.css">
<title>CelebrityQuotes</title>
</head>
<body style="background: ${randColor}"> 
<div class="celebrity-image" style="background-image: url(${celebObject.image})">
</div>
<div class="container">
<c:if test="${celebObject != null }">
<h1><em>&ldquo;${celebObject.quote}&rdquo;</em></h1>
<p><strong>${celebObject.name}</strong><br>${celebObject.jobTitle}</p>
<%-- <img src="${celebObject.image}" /><br /> --%>
<form action="randomCeleb.do" method="GET">
<input type="submit" value="Get Another Quote">
</form>
</c:if>
<a href="index.html">Go Home</a>
</div>
<c:forEach var="celeb" items="${celebList}">
	${celeb.name}
	<form action="removeCelebrity.do" method="POST">
	<input type="hidden" name="name" value="${celeb.name}">
    <input type="submit" value="delete">
	</form>	
	<form action="updateCelebrity.do" method="POST">
	<input type="hidden" name="celebName" value="${celeb.name}">	
    <input type="submit" value="update">
	</form>	
</c:forEach> 
</body>
</html>
