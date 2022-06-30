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
    <div class="bg-container">
        <div class="lista-container">
            <c:if test="${not empty destacadas}">
                <h3>Publicaciones destacadas</h3>
                <div class="lista-destacadas">
                    <c:forEach var="publiDestacada" items="${destacadas}">
                        <div class="card">
                            <a href="detalle-publicacion?id=${publiDestacada.propiedad.id}">
                                <c:forEach var="imagen" items="${listaDeImagenDePublicacionesDestacadas}" >
                                    <c:if test="${imagen.publicacion.id eq publiDestacada.id}">
                                        <img src="${imagen.urlImagen}" class="card-img-top" alt="Imagen de Propiedad">
                                    </c:if>
                                </c:forEach>
                                <div class="card-body">
                                    <div class="publicacion-top">
                                        <div>
                                            <h5>$ <c:out value="${publiDestacada.precio}"/></h5>
                                            <span class="badge text-bg-primary"><c:out value="${publiDestacada.tipoAccion.name()}"/></span>
                                        </div>
                                        <div class="separador"> </div>
                                        <div>
                                            <h5><c:out value="${publiDestacada.propiedad.ubicacion.provincia}"/></h5>
                                            <p><c:out value="${publiDestacada.propiedad.ubicacion.localidad}"/></p>
                                        </div>
                                    </div>
                                    <div class="publicacion-data">
                                        <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${publiDestacada.propiedad.metrosCuadrados}"/> m<sup>2</sup>.</p>
                                        <p><i class="fa-solid fa-door-open"></i></span><c:out value="${publiDestacada.propiedad.cantidadAmbientes}"/> amb.</p>
                                        <p><i class="fa-solid fa-house"></i><c:out value="${publiDestacada.propiedad.tipoPropiedad.name()}"/> </p>
                                        <c:if test="${publiDestacada.propiedad.cochera}">
                                            <p><i class="fa-solid fa-warehouse"></i> 1 coch. </p>
                                        </c:if>
                                    </div>
                                    <span class="publicacion-descripcion"><c:out value="${publiDestacada.descripcion}"/></span>
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
                        <a href="detalle-publicacion?id=${publi.propiedad.id}">
                            <c:forEach var="imagen" items="${listaDeImagenDePublicaciones}" >
                                <c:if test="${imagen.publicacion.id eq publi.id}">
                                    <img src="${imagen.urlImagen}" class="card-img-top" alt="Imagen de Propiedad">
                                </c:if>
                            </c:forEach>
                            <div class="card-body">
                                <div class="publicacion-top">
                                    <div>
                                        <h5>$ <c:out value="${publi.precio}"/></h5>
                                        <span class="badge text-bg-primary"><c:out value="${publi.tipoAccion.name()}"/></span>
                                    </div>
                                    <div class="separador"> </div>
                                    <div>
                                        <h5><c:out value="${publi.propiedad.ubicacion.provincia}"/></h5>
                                        <p><c:out value="${publi.propiedad.ubicacion.localidad}"/></p>
                                    </div>
                                </div>
                                <div class="publicacion-data">
                                    <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${publi.propiedad.metrosCuadrados}"/> m<sup>2</sup>.</p>
                                    <p><i class="fa-solid fa-door-open"></i></span><c:out value="${publi.propiedad.cantidadAmbientes}"/> amb.</p>
                                    <p><i class="fa-solid fa-house"></i><c:out value="${publi.propiedad.tipoPropiedad.name()}"/> </p>
                                    <c:if test="${publi.propiedad.cochera}">
                                        <p><i class="fa-solid fa-warehouse"></i> 1 coch. </p>
                                    </c:if>
                                </div>
                                <span class="publicacion-descripcion"><c:out value="${publi.descripcion}"/></span>
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
