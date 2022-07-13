<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
		<link href="css/general.css" rel="stylesheet">
		<link href="css/home.css" rel="stylesheet">
	</head>
	<body>
<%--Botonera Estilo Nav Bar Horizontal--%>
	<div class="bg-secondary  dropdowndropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
			KENT-PROP
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
			<li><a class="dropdown-item" href="login">Login</a></li>
			<c:if test="${sessionScope.ROL.equals('PROPIETARIO')}">
				<li><a class="dropdown-item" href="mis-publicaciones">Mis publicaciones</a></li>
			</c:if>
			<c:if test="${sessionScope.ROL.equals('USUARIO')}">
				<li><a class="dropdown-item" href="mis-preguntas">Mis preguntas</a></li>
			</c:if>
		</ul>
	</div>

		<div class="bg-container">

			<div class="search-container">
				<div class="search-container_img">
					<img src="images/kentprop-logo.png" alt="KentProp logo">
				</div>
				<div class="search">
					<form:form action="buscar-publicaciones" method="POST" modelAttribute="datosBusqueda" class="search_form">
						<form:select path="tipoPropiedad">
							<form:options items="${tipoPropiedades}"></form:options>
						</form:select>
						<form:select path="tipoAccion">
							<form:options items="${tipoAcciones}"></form:options>
						</form:select>

				<form:input required="required" path="ubicacion" placeholder="Buscar por provincio o localidad" id="ubicacion" type="text" class="form-control" />

						<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Buscar</button>
					</form:form>
				</div>
			</div>

		</div>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>