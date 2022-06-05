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


    publicaciones
    <c:forEach var="a" items="${publicaciones}" >
        <c:out value="a.publicaciones.id"/>
    </c:forEach>

    <c:if test="${not empty msg_error}">
        <h4><span>${msg_error}</span></h4>
        <br>
    </c:if>
    ${publicaciones}
</body>
</html>
