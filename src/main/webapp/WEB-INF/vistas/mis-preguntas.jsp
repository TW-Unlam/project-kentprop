<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %>
<%--
  Created by IntelliJ IDEA.
  User: sullc
  Date: 22/6/2022
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Mis Publicaciones</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/general.css" rel="stylesheet">
    <link href="css/lista-publicaciones.css" rel="stylesheet">
</head>
<body>
    <div class="bg-secondary dropdowndropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            KENT-PROP
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li><a class="dropdown-item" href="login">Login</a></li>
            <li><a class="dropdown-item" href="/project_kentprop/">Home</a></li>
            <c:if test="${sessionScope.ROL.equals('USUARIO')}">
                <li><a class="dropdown-item" href="mis-favoritos">Mis publicaciones favoritas</a></li>
            </c:if>
        </ul>
    </div>
    <div class="bg-container">
        <div class="lista-container">
            <c:if test="${not empty msg_vacio}">
                <h4><i class="fa-solid fa-circle-exclamation"></i> ${msg_vacio}</h4>
            </c:if>

            <c:if test="${empty msg_vacio}">
                <h2>Mis preguntas</h2>
                <div class="lista-publicaciones lista-mis-preguntas">
                    <c:forEach var="pregunta" items="${preguntasRealizadas}" >
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:out value="${pregunta.publicacion.propiedad.ubicacion.provincia}"/> -
                                    <c:out value="${pregunta.publicacion.propiedad.ubicacion.localidad}"/> |
                                    $ <c:out value="${pregunta.publicacion.precio}"/>
                                </h5>
                                <hr>
                                <h6 class="card-title"><c:out value="${pregunta.pregunta}"/></h6>
                                <c:choose>
                                    <c:when test="${not empty pregunta.respuesta}">
                                        <p class="card-text"><c:out value="${pregunta.respuesta}"/></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="card-text">La pregunta todavía no ha sido contestada.</p>
                                    </c:otherwise>
                                </c:choose>
                                <a href="detalle-publicacion?id=${pregunta.publicacion.id}" class="btn btn-primary text-white">Ir a la publicación</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="https://kit.fontawesome.com/39a92c78bd.js" crossorigin="anonymous"></script>
</body>
</html>
