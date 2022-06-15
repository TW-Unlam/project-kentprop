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
    <title>Consulta Privada Al Publicante</title>
</head>
<body>
<div id="FormConsulta" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
    <%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
    <%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
        <div id="FormConsultabox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
            <%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
            <%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
            <form:form action="enviar-consulta-privada" method="POST" modelAttribute="datosConsulta">
                <h3 class="form-signin-heading">Deje Su Consulta Al Propietario de la Publicacion</h3>
                <hr class="colorgraph"><br>
                <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                <form:input required="required" path="email" placeholder="email" id="ubicacion" type="text" class="form-control" />
                <form:input required="required" path="nombre" placeholder="nombre" id="ubicacion" type="text" class="form-control" />
                <form:input required="required" path="telefono" placeholder="telefono" id="ubicacion" type="text" class="form-control" />
                <form:input required="required" path="mensaje" placeholder="mensaje" id="ubicacion" type="text" class="form-control" />
                <form:input required="required" path="propiedadId" id="idPropiedad" type="hidden" class="form-control" value="${idPropiedad}" />
                <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Consultar</button>
            </form:form>
            <%-- <a href="registrar-usuario"	>Registrarme</a>--%>
            <%--Bloque que es visible si el elemento error no esta vacio	--%>
            <c:if test="${not empty usuario}">
                <h4><span>${usuario.email}</span></h4>
                <h4><span>${usuario.rol}</span></h4>
                <br>
            </c:if>
        </div>
</body>
</html>
