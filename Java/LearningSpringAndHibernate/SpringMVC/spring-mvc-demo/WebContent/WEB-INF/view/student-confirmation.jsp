<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Confirmation</title>
</head>
<body>
	<p> Confirmation Successful </p>
	<br>
	<p>Student name: ${student.firstName} ${student.lastName}</p>
	<p>Country: ${student.country} </p>
	<p>Favourite language: ${student.favouriteLanguage} </p>
	
	<p>
	<ul>
		<c:forEach var="temp" items="${student.operatingSystems}">
			<li> ${temp}</li>
		</c:forEach>
	</ul>
	</p>
	
</body>
</html>