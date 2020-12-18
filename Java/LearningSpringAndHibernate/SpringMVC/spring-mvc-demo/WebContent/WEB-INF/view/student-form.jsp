<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Form</title>
</head>
<body>
 <form:form action="processForm" modelAttribute="student">
 	First name : <form:input path="firstName" />
 	<br><br>
 	Last name : <form:input path="lastName" />
 	<br><br>
 	<form:select path="country">
 		<form:options items="${student.countryOptions}" />
 	</form:select>
 	<br><br>
 	Java <form:radiobutton path="favouriteLanguage" value="Java" />
 	C# <form:radiobutton path="favouriteLanguage" value="C#" />
 	Ruby <form:radiobutton path="favouriteLanguage" value="Ruby" />
 	Python <form:radiobutton path="favouriteLanguage" value="Python" />
 	<br><br>
 	
 	Operating Systems:
 	Linux <form:checkbox path="operatingSystems" value="Linux" />
 	Mac os <form:checkbox path="operatingSystems" value="Mac OS" />
 	Ms Windows <form:checkbox path="operatingSystems" value="MS Windows" />
 	<input type="submit" value = "Submit" />
 	
 </form:form>

</body>
</html>