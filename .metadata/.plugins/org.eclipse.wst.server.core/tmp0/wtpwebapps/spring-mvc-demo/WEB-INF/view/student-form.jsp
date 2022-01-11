<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<title>Student Registration Form</title>
</head>
<body>
	<form:form action="processForm" modelAttribute="student1">
	
	First name : <form:input path="firstName"/>
	
	<br><br>
	
	Last name : <form:input path="lastName"/>
	
	<br><br>
	
	Country : 
	
	<form:select path="country">
		<!--<form:option value="Brazil" label="Brazil"/>
		<form:option value="France" label="France"/>
		<form:option value="Germany" label="Germany"/>
		<form:option value="India" label="India"/> -->
		<form:options items="${student1.countryOptions}"/>
		
	</form:select>
	
	<br><br>
	Favorite Language : 
	
	Java <form:radiobutton path="favoriteLanguage" value="Java"/>
	C# <form:radiobutton path="favoriteLanguage" value="C#"/>
	PHP <form:radiobutton path="favoriteLanguage" value="PHP"/>
	Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/>
	
	<br><br>
	
	Operating Systems : 
	Linux <form:checkbox path="operatingSystems" value="Linux"/>
	Mac OS <form:checkbox path="operatingSystems" value="Mac OS"/>
	Window <form:checkbox path="operatingSystems" value="Window"/>
	
	<br><br>
	
	<input type="submit" value="Submit"/>
	
	</form:form>

</body>
</html>