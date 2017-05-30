<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="results.css">
<title>Celebrity Quotes</title>
</head>
<body style="background: ${randColor}">
	<div class="top-bar">
		<form class="top-bar-left" action="getFirstName.do" method="GET">
			<select name="firstName"
				onchange="if(this.value != 0) { this.form.submit(); }">
				<option value="0">Choose by Name</option>
				<c:forEach var="celebName" items="${celebNameList}">
					<option value="${celebName }">${celebName }</option>
				</c:forEach>
			</select>
		</form>
		<form class="top-bar-left" action="getJobTitle.do" method="GET">
			<select name="jobTitle"
				onchange="if(this.value != 0) { this.form.submit(); }">
				<option value="0">Choose by Job Title</option>
				<c:forEach var="celebJobTitle" items="${jobTitleList}">
					<option value="${celebJobTitle }">${celebJobTitle }</option>
				</c:forEach>
			</select>
		</form>
		<form class="top-bar-left" action="addForm.do" method="GET">
		<input type="hidden" name="id" value="${celebObject.id}" />
			<input type="submit" value="Add">
		</form>
		<form class="top-bar-left" action="updateForm.do" method="GET">
		<input type="hidden" name="id" value="${celebObject.id}" />
			<input type="submit" value="Update">
		</form>
		<form class="top-bar-left" action="removeCelebrity.do" method="POST">
			<input type="hidden" name="id" value="${celebObject.id}"> <input
				type="hidden" name="name" value="${celebObject.name}"> <input
				type="hidden" name="jobTitle" value="${celebObject.jobTitle}"> <input
				type="submit" value="delete">
		</form>
		<c:if test="${home != null }">
		<form class="top-bar-right" action="goHome.do" method="GET">
			<input
				type="submit" value="Go Home">
		</form>
		</c:if>
		<c:if test="${home == null }">
		<form class="top-bar-right" action="randomCeleb.do" method="GET">
			<input type="hidden" name="id" value="${celebObject.id}" /> <input
				type="submit" value="Get Another Quote">
		</form>
		</c:if>
	</div>

	<div class="celebrity-image"
		style="background-image: url(${celebObject.image})"></div>
	<div class="container">
		<c:if test="${celebObject != null }">
			<h1>
				<em>&ldquo;${celebObject.quote}&rdquo;</em>
			</h1>
			<p>
				<strong>${celebObject.name}</strong><br>${celebObject.jobTitle}</p>
		
		</c:if>
	</div>

	<br>
	<br>
	<c:forEach var="celeb" items="${celebList}">
	${celeb.name}
	
	
		<form action="goToEditCeleb.do" method="POST">
			<input type="hidden" name="id" value="${celeb.id}"> <input
				type="hidden" name="name" value="${celeb.name}"> <input
				type="hidden" name="jobTitle" value="${celeb.jobTitle}"> <input
				type="hidden" name="image" value="${celeb.image}"> <input
				type="hidden" name="quote" value="${celeb.quote}"> <input
				type="submit" value="update">
		</form>
		
		<br>
	</c:forEach>
	<div class="addForm">
		<c:if test="${add != null }">
			<form class="top-bar" action="addCelebrity.do" method="POST">
				Add Celebrity:<br /> Name:<input type="text" name="name" /><br />
				<br /> Job Title:<input type="text" name="jobTitle" /><br />
				<br /> Image:<input type="text" name="image" /><br />
				<br /> Quote:<input type="text" name="quote" /><br /> <br />
				<input type="submit" value="Submit"><br />
			</form>
		</c:if>
	</div>
	<div class="addForm">
		<c:if test="${update != null }">
			<form class="top-bar" action="updateCelebrity.do" method="POST">
				<input type="hidden" name="id" value="${celeb.id}" /> Name: <input
					type="text" name="name" value="${celeb.name}" /> Job Title: <input
					type="text" name="jobTitle" value="${celeb.jobTitle}" /> Image: <input
					type="text" name="image" value="${celeb.image}" /> Quote: <input
					type="text" name="quote" value="${celeb.quote}" /> <input
					type="submit" value="update">
			</form>
		</c:if>
	</div>
</body>
</html>
