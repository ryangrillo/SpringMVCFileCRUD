<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Celebrity Quotes</title>
<link rel="stylesheet" href="results.css">

</head>
<body>

	<form action="getFirstName.do" method="GET">
		<select name="firstName"
			onchange="if(this.value != 0) { this.form.submit(); }">
			<option value="0">Choose by Name</option>
			<c:forEach var="celebName" items="${celebNameList}">
				<option value="${celebName }">${celebName }</option>
			</c:forEach>
		</select>
	</form>

	<form action="getJobTitle.do" method="GET">
		<select name="jobTitle">
			<c:forEach var="celebJobTitle" items="${jobTitleList}">
				<option value="${celebJobTitle }">${celebJobTitle }</option>
			</c:forEach>
		</select> <input type="submit" value="Submit">
	</form>

	<form action="getAllCelebs.do" method="GET">
		List All Celebrities:<br> <input type="submit" value="Submit">
	</form>

	<div class="startapp">
		<form action="firstRandomCeleb.do" method="GET">
			Go Back to Application:<br> <input type="submit" value="Submit">
		</form>
	</div>


	<form action="addCelebrity.do" method="POST">
		Add Celebrity:<br /> Name:<input type="text" name="name" /><br />
		Job Title:<input type="text" name="jobTitle" /><br /> Image:<input
			type="text" name="image" /><br /> Quote:<input type="text"
			name="quote" /><br /> <input type="submit" value="Submit"><br />
	</form>
</body>
</html>