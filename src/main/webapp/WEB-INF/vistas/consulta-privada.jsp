<%--
  Created by IntelliJ IDEA.
  User: sullc
  Date: 14/6/2022
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet" >
        <link href="css/general.css" rel="stylesheet">
        <link href="css/consulta-privada.css" rel="stylesheet">

    <title>Consulta Privada Al Publicante</title>
</head>
<body>

<div class="bg-container">
    <div class="private-question-container">
        <h3>Deje su consulta al propietario de la publicación</h3>

        <div id="FormConsultabox" >
            <form:form action="enviar-consulta-privada" method="POST" modelAttribute="datosConsulta">
                <form:input required="required" path="email" placeholder="Mail" type="email" class="form-control" />
                <form:input required="required" path="nombre" placeholder="Nombre" type="text" class="form-control" />
                <form:input required="required" path="telefono" placeholder="Teléfono" type="text" class="form-control" />
                <form:textarea required="required" path="mensaje" placeholder="Mensaje" type="text" class="form-control" />
                <form:input required="required" path="propiedadId" id="idPropiedad" type="hidden" class="form-control" value="${idPropiedad}" />

                <button class="btn btn-primary" Type="Submit"/>Consultar</button>
            </form:form>

            <c:if test="${not empty usuario}">
                <h4><span>${usuario.email}</span></h4>
                <h4><span>${usuario.rol}</span></h4>
                <br>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>