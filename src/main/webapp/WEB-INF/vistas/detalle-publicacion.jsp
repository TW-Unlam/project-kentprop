<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %><%--
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
    <title>Detalle De La publicacion</title>
</head>
<body>
<c:if test="${not empty msg_error}">
    <h4><span>${msg_error}</span></h4>
    <br>
</c:if>

<c:if test="${not empty detalle}">
    <h4>${detalle.id}</h4>
<h4>${detalle.descripcion}</h4>
    <h4>   ${detalle.fechaPublicacion}</h4>
        <h4>  ${detalle.propiedad.metrosCuadrados}</h4>
        <h4>  ${detalle.propiedad.ubicacion.localidad}</h4>
    <br>
</c:if>


</body>
</html>
