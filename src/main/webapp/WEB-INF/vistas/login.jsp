<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
		<link href="css/general.css" rel="stylesheet">
		<link href="css/login.css" rel="stylesheet">
	</head>
	<body>
	<div class="bg-container">
		<div id="loginbox" class="login-container">
			<div class="search-container_img">
				<img src="images/kentprop-logo.png" alt="KentProp logo">
			</div>
			<form:form action="validar-login" method="POST" modelAttribute="datosLogin">
				<h3>Ingresa a Kentprop</h3>
					<form:input path="idPublicacion" id="idPublicacion" type="hidden" value="${idp}"/>
					<label class="form-label">Mail</label>
					<form:input class="form-control" path="email" id="email" type="email" />
					<label class="form-label">Contraseña</label>
					<form:input class="form-control" path="password" type="password" id="password"/>
					
					<button class="btn btn-primary" Type="Submit"/>Login</button>
				</form:form>

				<c:if test="${not empty error}">
			        <h4><span>${error}</span></h4>
			        <br>
		        </c:if>
				${msg}
			</div>
		</div>

		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
