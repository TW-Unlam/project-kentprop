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
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/general.css" rel="stylesheet">
    <link href="css/lista-publicaciones.css" rel="stylesheet">
</head>
<body>
<%--Botonera Estilo Nav Bar Horizontal--%>
<div class="   bg-secondary  dropdowndropdown">
    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        KENK-PROP
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="login">Login</a></li>
        <li><a class="dropdown-item" href="mis-publicaciones">Ver Mis Publicaciones</a></li>
        <li><a class="dropdown-item" href="mis-favoritos">Ver Mis Publicaciones favoritas</a></li>
        <li><a class="dropdown-item" href="#">Ver Mis Preguntas Realizadas</a></li>
    </ul>
</div>
<%--Botonera Estilo Nav Bar Horizontal--%>
<div class="bg-container">
    <div class="lista-container">

        <c:if test="${not empty msg_vacio}">
            <h4><i class="fa-solid fa-circle-exclamation"></i> ${msg_vacio}</h4>
            <%--//Bandera o algo que indique que la imagen fue colocada exit o algo parecido
            //para quue al apricvar colocar por defecto no aplique a todas las ya asignadas--%>
        </c:if>

        <c:if test="${empty msg_vacio}">
            <h2>Publicaciones encontradas</h2>

            <div class="lista-publicaciones">
                <c:forEach var="publi" items="${publicaciones}" >
                    <div class="card">
                        <a href="detalle-publicacion?id=${publi.publicacion.propiedad.id}">
                            <c:if test="${publi.imagen.publicacion.id eq publi.publicacion.id}">
                                <img src="${publi.imagen.urlImagen}" class="card-img-top" alt="Imagen de Propiedad">
                            </c:if>
                            <div class="card-body">
                                <div class="publicacion-top">
                                    <div>
                                        <h5>$ <c:out value="${publi.publicacion.precio}"/></h5>
                                        <span class="badge text-bg-primary"><c:out value="${publi.publicacion.tipoAccion.name()}"/></span>
                                    </div>
                                    <div class="separador"> </div>
                                    <div>
                                        <h5><c:out value="${publi.publicacion.propiedad.ubicacion.provincia}"/></h5>
                                        <p><c:out value="${publi.publicacion.propiedad.ubicacion.localidad}"/></p>
                                    </div>
                                </div>
                                <div class="publicacion-data">
                                    <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${publi.publicacion.propiedad.metrosCuadrados}"/> m<sup>2</sup>.</p>
                                    <p><i class="fa-solid fa-door-open"></i></span><c:out value="${publi.publicacion.propiedad.cantidadAmbientes}"/> amb.</p>
                                    <p><i class="fa-solid fa-house"></i><c:out value="${publi.publicacion.propiedad.tipoPropiedad.name()}"/> </p>
                                    <c:if test="${publi.publicacion.propiedad.cochera}">
                                        <p><i class="fa-solid fa-warehouse"></i> 1 coch. </p>
                                    </c:if>
                                </div>
                                <span class="publicacion-descripcion"><c:out value="${publi.publicacion.descripcion}"/></span>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>

        </c:if>
     <%--   <c:choose>
            <c:when test="${not empty msg_vacio}">
                <h2>Publicaciones encontradas</h2>
            </c:when>
            <c:otherwise>
                <h4><i class="fa-solid fa-circle-exclamation"></i> ${msg_vacio}</h4>
            </c:otherwise>
        </c:choose>--%>

    </div>
</div>



<script src="https://kit.fontawesome.com/39a92c78bd.js" crossorigin="anonymous"></script>
</body>
</html>
