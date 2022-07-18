<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType = "text/html;charset=utf-8" %>
<html>
<head>
	<title>wITh팀 메인 페이지 테스트</title>
</head>

<body>
	<h2>wITh팀 메인 페이지</h2>
	<hr>
	<p>WITh팀 메인 페이지에 오신것을 환영합니다.</p>
	
	<hr>
	<!--  display user name and role -->
	<p>
		유저명 : <security:authentication property="principal.username"/>
		</br></br>
		직급 : <security:authentication property="principal.authorities"/>
	</p>
	<hr>
	<!-- add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"	
	method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>