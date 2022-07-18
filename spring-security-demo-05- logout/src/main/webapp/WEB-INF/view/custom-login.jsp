<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>My Custom Login Page</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Dark.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <style>
    	.failed { color : red;}
    	.logout { color : green;}
    </style>
</head>

<body>
    <section class="login-dark">
        <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
       
            <h2 class="visually-hidden">Login Form</h2>
            <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
            <div class="mb-3"><input class="form-control" type="text" name="username" placeholder="Username"></div>
            <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="Password"></div>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Log In</button></div>
                <!-- check for login error  -->
		<c:if test="${param.error != null}">
			  <p class="failed">Forgot your email or password?</p>
		</c:if>
		<c:if test="${param.logout != null}">
			  <p class="logout" >You have been logged out.</p>
		</c:if>
                      
        </form:form>
    </section>
		<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>