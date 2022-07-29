<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Student Confirmation</title>
</head>
<body>

The student is confirmed : ${student1.firstName} ${student1.lastName}

<br><br>

Country : ${student1.country}

<br><br>

Favorite Language : ${student1.favoriteLanguage}

<br><br>

<ul>
	<c:forEach var="temp" items="${student1.operatingSystems}">
	
	<li> ${temp} </li>
	
	</c:forEach>
</ul>

</body>

</html>