<%-- 
    Document   : Opciones
    Created on : 18-nov-2015, 23:52:58
    Author     : CristianFaune
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <c:if test="${empty doctor}">
        <c:redirect url="/ValidarIngreso"/>
    </c:if>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="<c:url value="/BuscarDuenoServlet"/>">
                            <img alt="Logo" src="imagenes/logo_vet.png" width="70px">
                        </a>
                    </div>
                </div>
            </nav>
            <div class="container">
                <strong><p class="text-right"><c:out value="Dr.${doctor.nombre} ${doctor.apellidos}"/></p></strong>
                <p class="text-right"><a  class="btn btn-danger"href="<c:url value="/CerrarSesionServlet"/>">Salir</a></p>
            </div>
        </div>
    </body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>

</html>
