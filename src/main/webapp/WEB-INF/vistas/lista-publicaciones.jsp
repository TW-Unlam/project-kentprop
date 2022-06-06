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
</head>
<body>
    <h1>Lista de Publicaciones localizadas-....</h1>
    <%--${publicaciones}--%>
   <c:forEach var="publi" items="${publicaciones}" >
    <span>  <c:out value="${publi.tipoAccion.name()}"/> </span>
    <span>  <c:out value="${publi.descripcion}"/> </span>
    <span> <c:out value="${publi.precio}"/> </span>
    <span>  <c:out value="${publi.propiedad.cantidadAmbientes}"/> </span>
    <span>  <c:out value="${publi.propiedad.cochera}"/> </span>
    <span>  <c:out value="${publi.propiedad.tipoPropiedad.name()}"/> </span>
    <span>  <c:out value="${publi.propiedad.metrosCuadrados}"/> </span>
    <span>  <c:out value="${publi.propiedad.ubicacion.localidad}"/> </span>
    <span>   <c:out value="${publi.propiedad.ubicacion.provincia}"/> </span>
    <a href="detalle-publicacion?id=${publi.propiedad.id}">VER DETALLES</a>
       <br>
    </c:forEach>

    <c:if test="${not empty msg_error}">
        <h4><span>${msg_error}</span></h4>
        <br>
    </c:if>

  <%--  ${publicaciones.get(0).descripcion}
    ${publicaciones.get(0).fechaPublicacion}
    ${publicaciones.get(0).propiedad}--%>

   <%-- ${publicaciones.get(1).descripcion}
    ${publicaciones.get(2).fechaPublicacion}
    ${publicaciones.get(3).propiedad}--%>

</body>
</html>
