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
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/general.css" rel="stylesheet">
    <link href="css/detalle-publicaciones.css" rel="stylesheet">
    <title>Detalle De La publicacion</title>
</head>
<body>

<%--  ----------------- NAVBAR ----------------------%>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">
        <img href="home.jsp" src="images/kentprop-logo.png" alt="Logo">
    </a>

  <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item active">
              <a class="nav-link" href="home.jsp">Inicio <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="#">Alquileres</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="#">Ventas</a>
          </li>
          <li class="nav-item">
              <a class="nav-link disabled" href="#">Emprendimientos</a>
          </li>
      </ul>
      <div class="right float-right">
      <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="Usuario" aria-label="Search">
          <input class="form-control mr-sm-2" type="search" placeholder="Contraseña" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Ingresar</button>
      </form>
      </div>
  </div>
</nav>

<%--  ----------------- NAVBAR ----------------------%>

<div class="lista-container">
      <c:choose>
      <h2 class="ml-2">Detalle de la publicación</h2>
      </c:choose>

          <div class="lista-publicaciones">
              <div class="imagenes_publicacion">
                  <img src="images/PropiedadTipoCasa.jpg" alt="...">
                  <img src="images/PropiedadTipoCasa.jpg" alt="...">
                  <img src="images/PropiedadTipoCasa.jpg" alt="...">
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
                      <form:form action="generar-pregunta" method="POST" modelAttribute="" class="preguntar_form">
                      <form:input path="pregunta" placeholder="Escribila acá" id="pregunta" type="text" class="form-control"/>

                      <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Enviar</button>
                      </form:form>

                  </div>
                  <div class="preguntas-hechas">
                      <p>¿Tenés alguna pregunta sobre la propiedad?</p>
                      <h4>${preguntas_hechas}</h4>

                  </div>
                  <div class="ubicacion-propiedad">
                     <iframe src="${detalle.propiedad.coordenadas}" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                        --%>
                  </div>
          </div>
        </div>
</body>
</html>
