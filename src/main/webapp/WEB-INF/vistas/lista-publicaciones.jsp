<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %>
<%@ page contentType="text/html; charset=UTF-8" %><%--
  Created by IntelliJ IDEA.
  User: vpardo
  Date: 31/05/2022
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lista publicaciones</title>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <link href="css/general.css" rel="stylesheet">
    <link href="css/lista-publicaciones.css" rel="stylesheet">
</head>
<body>
<%--Botonera Estilo Nav Bar Horizontal--%>
<div class="bg-secondary dropdowndropdown">
    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        KENT-PROP
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <c:if test="${empty sessionScope}">
            <li><a class="dropdown-item" href="login">Login</a></li>
        </c:if>
        <c:if test="${sessionScope.ROL.equals('PROPIETARIO')}">
            <li><a class="dropdown-item" href="mis-publicaciones">Mis publicaciones</a></li>
        </c:if>
        <c:if test="${sessionScope.ROL.equals('USUARIO')}">
            <li><a class="dropdown-item" href="mis-preguntas">Mis preguntas</a></li>
            <li><a class="dropdown-item" href="mis-favoritos">Mis publicaciones favoritas</a></li>
        </c:if>
    </ul>
</div>
<%--Botonera Estilo Nav Bar Horizontal--%>
    <div class="bg-container">
        <div class="lista-container">
            <c:if test="${not empty destacadas}">
                <h3>Publicaciones destacadas</h3>
                <div class="lista-destacadas">
                    <c:forEach var="publiDestacada" items="${destacadas}">
                        <div class="card">
                            <a href="detalle-publicacion?id=${publiDestacada.publicacion.propiedad.id}">
                                <img src="${publiDestacada.imagen.urlImagen}" class="card-img-top" alt="Imagen de Propiedad">

                                <div class="card-body">
                                    <div class="publicacion-top">
                                        <div>
                                            <h5>$ <c:out value="${publiDestacada.publicacion.precio}"/></h5>
                                            <span class="badge text-bg-primary"><c:out value="${publiDestacada.publicacion.tipoAccion.name()}"/></span>
                                        </div>
                                        <div class="separador"> </div>
                                        <div>
                                            <h5><c:out value="${publiDestacada.publicacion.propiedad.ubicacion.provincia}"/></h5>
                                            <p><c:out value="${publiDestacada.publicacion.propiedad.ubicacion.localidad}"/></p>
                                        </div>
                                    </div>
                                    <div class="publicacion-data">
                                        <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${publiDestacada.publicacion.propiedad.metrosCuadrados}"/> m<sup>2</sup>.</p>
                                        <p><i class="fa-solid fa-door-open"></i></span><c:out value="${publiDestacada.publicacion.propiedad.cantidadAmbientes}"/> amb.</p>
                                        <p><i class="fa-solid fa-house"></i><c:out value="${publiDestacada.publicacion.propiedad.tipoPropiedad.name()}"/> </p>
                                        <c:if test="${publiDestacada.publicacion.propiedad.cochera}">
                                            <p><i class="fa-solid fa-warehouse"></i> 1 coch. </p>
                                        </c:if>
                                    </div>
                                    <span class="publicacion-descripcion"><c:out value="${publiDestacada.publicacion.descripcion}"/></span>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:choose>
                <c:when test="${empty msg_error}">
                    <h2>Publicaciones encontradas</h2>
                </c:when>
                <c:otherwise>
                    <h4><i class="fa-solid fa-circle-exclamation"></i> ${msg_error}</h4>
                </c:otherwise>
            </c:choose>

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
        </div>
    </div>
    <%--${publicaciones}--%>

  <%--  ${publicaciones.get(0).descripcion}
    ${publicaciones.get(0).fechaPublicacion}
    ${publicaciones.get(0).propiedad}--%>

   <%-- ${publicaciones.get(1).descripcion}
    ${publicaciones.get(2).fechaPublicacion}
    ${publicaciones.get(3).propiedad}--%>

    <script src="https://kit.fontawesome.com/39a92c78bd.js" crossorigin="anonymous"></script>
</body>
</html>
