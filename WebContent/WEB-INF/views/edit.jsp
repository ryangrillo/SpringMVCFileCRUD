<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
</head>
<body>
<form action="updateCelebrity.do" method="POST">
	<input type="text" name="name" value="${celeb.name}" />	
	<input type="text" name="jobTitle" value="${celeb.jobTitle}" />	
	<input type="text" name="image" value="${celeb.image}" />	
	<input type="text" name="quote" value="${celeb.quote}" />	
    <input type="submit" value="update">
	</form>	
</body>
</html>