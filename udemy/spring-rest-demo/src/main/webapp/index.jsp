<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>Spring REST DEMO</h3>
	
	<hr>
	<a href="${pageContext.request.contextPath}/test/hello">Hello</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/api/students">Get All Students</a>
</body>
</html>