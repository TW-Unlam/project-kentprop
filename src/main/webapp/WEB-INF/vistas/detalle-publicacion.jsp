<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

<%--
  Created by IntelliJ IDEA.
  User: sullc
  Date: 5/6/2022
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/general.css" rel="stylesheet">
    <link href="css/detalle-publicaciones.css" rel="stylesheet">
    <title>Detalle De La publicacion</title>
</head>
<body>

<div class="lista-container">

    <h2 class="ml-2">Detalle de la publicación</h2>

    <div class="lista-publicaciones">
        <div class="imagenes_publicacion">

            <c:choose>
                <c:when test="${empty imagenes}">
                    <img src="images/PropiedadDefault.jpg" alt="Imagen de Propiedad No Disponible">
                </c:when>
                <c:otherwise>

                    <c:forEach var="imag" items="${imagenes}" >
                        <%--                      <c:if test="${imag.publicacion.id}==${detalle.id}">--%>
                        <img src="${imag.urlImagen}" alt="...">
                        <%--                      </c:if>--%>
                    </c:forEach>

                </c:otherwise>
            </c:choose>

        </div>

        <div class="caption">
            <div class="publicacion-top">
                <div>
                    <p>Precio: <h3>$ <c:out value="${publi.precio}"/></h3></p>
                </div>

                <p>Descripción de la propiedad: <h4>${detalle.descripcion}</h4></p>
                <p>Fecha de publicación: <h4>   ${detalle.fechaPublicacion}</h4></p>
                <p>Cantidad de ambientes:<h4>  ${detalle.propiedad.cantidadAmbientes}</h4>
                <p>Metros cuadrados: <h4>  ${detalle.propiedad.metrosCuadrados}</h4></p>
                <p>Localidad: <h4>  ${detalle.propiedad.ubicacion.localidad}</h4></p>

            </div>
        </div>

        <div class="preguntas">
            <p>¿Tenés alguna pregunta sobre la propiedad?</p>
            <form:form action="hacer-pregunta-publicacion" modelAttribute="datosPregunta" method="POST" class="search_form">
                <form:input path="id" placeholder="" id="id" type="hidden" class="form-control" value="${detalle.id} "/>
                <form:input path="descripcion" placeholder="Escribila acá" id="descripcion" type="text" class="form-control"/>

                <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Enviar</button>
            </form:form>

        </div>

        <div class="pregunta-privada">
            <p>¿Tenés alguna otra pregunta? Dejanos tus datos y consultas:</p>
        <a href="enviar-consulta?propiedadId=${detalle.propiedad.id}">Realizar Consulta</a>

        </div>
            <div class="preguntas-hechas">

            <p>Preguntas realizadas por otros usuarios:</p>

            <c:if test="${not empty preguntas_hechas}">
                <c:forEach var="preguntas_h" items="${preguntas_hechas}" >
                    <h5>${preguntas_h.id}</h5>
                    <h5>${preguntas_h.pregunta}</h5>
                    <h5>${preguntas_h.usuario}</h5>
                </c:forEach>
            </c:if>
        </div>

        <%--    <div class="ubicacion-propiedad">
                <iframe src="${detalle.propiedad.coordenadas}" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
         --%>
    </div>

</div>
</body>
</html>
