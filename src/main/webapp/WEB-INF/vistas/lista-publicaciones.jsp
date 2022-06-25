<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %><%--
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
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/general.css" rel="stylesheet">
    <link href="css/lista-publicaciones.css" rel="stylesheet">
</head>
<body>
    <div class="bg-container">
        <div class="lista-container">
            <c:choose>
                <c:when test="${empty msg_error}">
                    <h2>Publicaciones encontradas</h2>
                </c:when>
                <c:otherwise>
                    <h4><i class="fa-solid fa-circle-exclamation"></i> ${msg_error}</h4>
                </c:otherwise>
            </c:choose>

            <div class="lista-publicaciones">
                <c:forEach var="publicacion" items="${publicaciones}" >
                    <div class="thumbnail">
                        <a href="detalle-publicacion?id=${publicacion.propiedad.id}">

                            <c:choose>
                                <c:when test="${empty listaDeImagenDePublicaciones}">
                                    <img src="images/PropiedadDefault.jpg" alt="Imagen de Propiedad No Disponible">
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="imagen" items="${listaDeImagenDePublicaciones}" >
                                        <c:if test="${imagen.publicacion.id eq publicacion.id}">
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
                                    <h3>$ <c:out value="${publicacion.precio}"/></h3>
                                    <span class="label label-primary"><c:out value="${publicacion.tipoAccion.name()}"/></span></div>
                                <div>
                                    <h3><c:out value="${publicacion.propiedad.ubicacion.provincia}"/></h3>
                                    <p><c:out value="${publicacion.propiedad.ubicacion.localidad}"/></p>
                                </div>
                            </div>
                            <div class="publicacion-data">
                                <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${publicacion.propiedad.metrosCuadrados}"/> m<sup>2</sup>.</p>
                                <p><i class="fa-solid fa-door-open"></i></span><c:out value="${publicacion.propiedad.cantidadAmbientes}"/> amb.</p>
                                <p><i class="fa-solid fa-house"></i><c:out value="${publicacion.propiedad.tipoPropiedad.name()}"/> </p>
                                <c:if test="${publicacion.propiedad.cochera}">
                                    <p><i class="fa-solid fa-warehouse"></i>1-coch. </p>
                                </c:if>
                            </div>
                            <span class="publicacion-descripcion"><c:out value="${publicacion.descripcion}"/></span>
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
