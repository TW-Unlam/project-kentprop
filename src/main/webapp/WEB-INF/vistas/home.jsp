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
				<a href="mis-publicaciones">VER MIS PUBLICACIONES</a>
			</div>

		</div>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>