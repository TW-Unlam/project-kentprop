<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ar.edu.unlam.tallerweb1.modelo.Publicacion" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8" />
    <link href="css/bootstrap.min.css" rel="stylesheet" >
    <link href="css/general.css" rel="stylesheet">
    <link href="css/detalle-publicacion.css" rel="stylesheet">
    <title>Detalle De La publicacion</title>
</head>
<body>
<%--Botonera Estilo Nav Bar Horizontal--%>
<div class="   bg-secondary  dropdowndropdown">
    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        KENK-PROP
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="login">Login</a></li>
        <li><a class="dropdown-item" href="mis-publicaciones">Ver Mis Publicaciones</a></li>
        <li><a class="dropdown-item" href="#">Ver Mis Preguntas Realizadas</a></li>
    </ul>
</div>
<%--Botonera Estilo Nav Bar Horizontal--%>
    <div class="bg-container">
        <div class="detalle-publicacion-container">

            <div class="=publicacion-Favorito">
                <span class="postingGalleryFixedComponents__FixedContent-sc-1j3twev-0 etQqim">
                <a href="marcar-como-favorito">
                <button data-qa="CARD_FAV" aria-label="Favorito" font-weight="bold" class="button__StyledButton-sc-1b3blmr-0 lilJET">
                    <svg width="1em" height="1em" viewBox="0 0 16 14" fill="currentColor" xmlns="http://www.w3.org/2000/svg" stroke="currentColor" color="currentColor" font-size="25px" stroke-width="0">
                        <path d="M8 13.7c-.1 0-.3 0-.4-.1l-5.8-6a4.05 4.05 0 010-5.9C3.4.1 6.1.1 7.7 1.7l.3.4.4-.4c1.6-1.6 4.3-1.6 5.9 0 1.6 1.6 1.6 4.3 0 5.9l-5.9 5.9c-.1.1-.3.2-.4.2zM4.7 1.5c-.8 0-1.6.3-2.2.9-1.2 1.2-1.2 3.2 0 4.5L8 12.4l5.5-5.5c.6-.6.9-1.4.9-2.2 0-.8-.3-1.7-.9-2.3-1.2-1.2-3.2-1.2-4.5 0l-.6.7c-.2.2-.5.2-.7 0l-.8-.7c-.6-.6-1.4-.9-2.2-.9z" fill="#000">
                        </path>
                    </svg>
                </button>
                </a>
                </span>
            </div>

            <div class="publicacion-data">
                <div class="data">
                    <div><span class="badge text-bg-primary"><c:out value="${detalle.tipoAccion}"/></span></div>
                    <div class="data-top">
                        <div class="data-precio"><h4>$ <c:out value="${detalle.precio}"/></h4></div>
                        <div><h4><i class="fa-solid fa-location-dot"></i> ${detalle.propiedad.ubicacion.localidad}</h4></div>
                    </div>
                    <div class="data-middle">
                        <p><i class="fa-solid fa-house"></i><c:out value="${detalle.propiedad.tipoPropiedad}"/></p>
                        <p> . </p>
                        <p><i class="fa-solid fa-ruler-vertical"></i><c:out value="${detalle.propiedad.metrosCuadrados}"/>m<sup>2</sup></p>
                        <p> . </p>
                        <p><i class="fa-solid fa-door-open"></i></span><c:out value="${detalle.propiedad.cantidadAmbientes}"/> amb</p>
                        <c:if test="${publi.propiedad.cochera}">
                            <p> . </p>
                            <p><i class="fa-solid fa-warehouse"></i> 1 coch. </p>
                        </c:if>
                    </div>
                    <div class="data-bottom">
                        <p><span>Fecha de publicación: </span><c:out value="${detalle.fechaPublicacion}"/></p>
                        <div><span>Descripción de la propiedad: </span>
                            <div><c:out value="${detalle.descripcion}"/></div>
                        </div>
                    </div>
                </div>
                <div class="container-carousel">
                    <div id="carouselExampleIndicators" class="carousel slide  carousel-dark" data-bs-ride="true">
                        <div class="carousel-indicators">
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                        </div>
                        <div class="carousel-inner">
                            <c:forEach var="imag" items="${imagenes}" >
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="${imag.urlImagen}" alt="...">
                                </div>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="publicacion-data publicacion-questions">
                <div class="public-questions">
                    <div class="questions">
                        <h5>¿Tenés alguna pregunta sobre la propiedad?</h5>
                        <form:form action="hacer-pregunta-publicacion" modelAttribute="datosPregunta" method="POST" class="search_form">
                            <form:input path="publicacionId" placeholder="" id="publicacionId" type="hidden" class="form-control" value="${detalle.id} "/>
                            <form:input path="descripcion" placeholder="Escribila acá" id="descripcion" type="text" class="form-control"/>

                            <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Enviar</button>
                        </form:form>
                    </div>
                    <div class="public-questions-asked">
                        <hr/>
                        <h5>Preguntas realizadas por otros usuarios:</h5>

                        <c:choose>
                            <c:when test="${not empty preguntas_hechas}">
                                <c:forEach var="preguntas_h" items="${preguntas_hechas}" >
                                    <div class="question">
                                        <p>${preguntas_h.pregunta}</p>
                                        <p class="question-user">- ${preguntas_h.usuario.email} -</p>

                                        <c:if test="${not empty preguntas_h.respuesta}">
                                            <p class="response-user"><b>Respuesta: </b>${preguntas_h.respuesta}</p>
                                        </c:if>
                                    </div>

                                    <c:if test="${empty preguntas_h.respuesta}">
<%--                                        <p class="response-user"><b>Respuesta: </b>${preguntas_h.respuesta}</p> --%>
                                        <c:if test="${(sessionScope.ROL.equals('PROPIETARIO')) and (detalle.propiedad.propietario.id.equals(sessionScope.id ))}">
                                        <form:form action="responder-pregunta-publicacion" modelAttribute="datosPregunta" method="POST" class="search_form">
                                            <form:input path="preguntaId" placeholder="" id="idResp" type="hidden" class="form-control" value="${preguntas_h.id} "/>
                                            <form:input path="descripcion" placeholder="Escribila acá la respuesta" id="descripcionRes" type="text" class="form-control"/>
                                            <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Enviar</button>
                                        </form:form>
                                        </c:if>

                                    </c:if>

                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>${msg_sin_preguntas}</p>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
                <div class="private-question">
                    <h5>¿Tenés alguna otra pregunta?</h5>
                    <p>Dejanos tus datos y consultas:</p>
                    <a href="enviar-consulta?propiedadId=${detalle.propiedad.id}"><i class="fa-solid fa-arrow-right"></i> Realizar Consulta</a>
                </div>
            </div>


            <h5>Ubicación de la propiedad:</h5>

            <div id="mapa">

                <%-- <iframe src="${detalle.propiedad.coordenadas}" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    --%>
            </div>

        </div>
    </div>
    <script src="https://kit.fontawesome.com/39a92c78bd.js" crossorigin="anonymous"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>


        <%-- CONFIGURACION DE LA API DE MAPS --%>
        <script async
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBm4u_d68qcskUzbwl0Gzn8-PvthYwicNY&callback=initMap">
        </script>

        <script type="text/javascript">

            function initMap(){

                const ubicacion = new Localizacion(()=>{

                    const options = {
                        center: {
                            lat: ubicacion.latitude,
                            lng: ubicacion.longitude
                        },
                        zoom: 17
                    }

                    var map = document.getElementById('mapa');

                    const mapa = new google.maps.Map(map, options);

                });

            }
        </script>
        <script type="text/javascript">
            class Localizacion{

                constructor(callback) {

                    if (navigator.geolocation){
                        //obtenemos la ubicación
                        navigator.geolocation.getCurrentPosition((position) =>{
                            this.latitude = ${detalle.propiedad.ubicacion.latitud};
                            this.longitude = ${detalle.propiedad.ubicacion.longitud};

                            callback();
                        });

                    }else{
                        alert("navegador no soporta geolocalización");
                    }
                }

            }
        </script>


</body>


</html>
