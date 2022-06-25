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
                <c:forEach var="publi" items="${listaDepublicaciones}" >
                    <div class="thumbnail">
                        <a href="detalle-publicacion?id=${publi.propiedad.id}">
                            <c:choose>
                                <c:when test="${empty listaDeImagenesDePublicaciones}">
                                    <img src="images/PropiedadDefault.jpg" alt="Imagen de Propiedad No Disponible">
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="imagen" items="${listaDeImagenesDePublicaciones}" >
                                        <c:if test="${imagen.publicacion.id eq publi.id}">
                                            <img src="${imagen.urlImagen}" alt="Imagen de Propiedad">
                                            <%--//Bandera o algo que indique que la imagen fue colocada exit o algo parecido
                                            //para quue al apricvar colocar por defecto no aplique a todas las ya asignadas--%>
                                        </c:if>

                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                            <div class="caption">
                                <div class="publicacion-top">
                                    <div>
                                        <h3>$ <c:out value="${publi.precio}"/></h3>
                                        <span class="label label-primary"><c:out value="${publi.tipoAccion.name()}"/></span></div>
                                    <div>
                                        <h3><c:out value="${publi.propiedad.ubicacion.provincia}"/></h3>
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
