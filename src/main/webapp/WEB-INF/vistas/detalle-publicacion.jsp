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
    <h4>   ${detalle.propiedad.id}</h4>
        <h4>  ${detalle.propiedad.metrosCuadrados}</h4>
        <h4>  ${detalle.propiedad.ubicacion.localidad}</h4>

          <a href="enviar-consulta?propiedadId=${detalle.propiedad.id}">Realizar Consultas</a>
            <%--Bloque que es visible si el elemento error no esta vacio	--%>



    </div>
</c:if>

<c:if test="${not empty usuario}">
    <h4><span>${usuario.email}</span></h4>
    <h4><span>${usuario.rol}</span></h4>
    <br>
</c:if>
<c:if test="${not empty usuario}">
    <h4><span>${usuario}</span></h4>
    <br>
</c:if>
${msg}
${msg-error}
    <br>

</body>
</html>
