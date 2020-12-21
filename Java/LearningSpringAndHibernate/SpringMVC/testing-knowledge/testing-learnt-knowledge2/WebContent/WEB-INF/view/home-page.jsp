<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bob</title>
<style>
	.error{color:red}
</style>
</head>
<body>
	<form:form action="processForm" method="Get" modelAttribute="person">
		Name: <form:input type="text" path="firstName" />
		<form:errors path="firstName" cssClass="error"/>
		<br><br>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>