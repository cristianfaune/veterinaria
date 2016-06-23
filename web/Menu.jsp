<%-- 
    Document   : Menu
    Created on : 18-nov-2015, 16:02:27
    Author     : CristianFaune
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <title>Sala de Espera</title>
    </head>

    <c:if test="${empty doctor}">
        <c:redirect url="index.jsp"/>
    </c:if>
    <body>
        <%@include file="Opciones.jsp" %>

        <div class="container col-md-8 col-sm-offset-2">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-8 col-sm-offset-2">
                    <ul class="nav nav-pills nav-justified">
                        <li role="presentation" class="active"><a href="<c:url value="/BuscarDuenoServlet"/>">Clientes</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroDuenoServlet"/>">Nuevo Dueño</a></li>
                        <li role="presentation"><a href="<c:url value="/RegistroMascotaServlet"/>">Nueva Mascota</a></li>
                        <li role="presentation"><a href="<c:url value="/BuscarModificarServlet"/>">Modificar</a></li>
                    </ul>
                </div>
            </div>
            <div container col-md-8 col-sm-offset-2>
                <form class="form-inline" action="<c:url value="/BuscarDuenoServlet"/>" method="post">
                    <div class="form-group col-sm-offset-2">
                        <label for="exampleInputName2"></label>
                        <input type="text" class="form-control" 
                               id="exampleInputName2" placeholder="Rut del dueño"
                               name="rutBuscar" autofocus="true"/>
                    </div>
                    <input type="submit" class="btn btn-default" value="Buscar"/>
                </form>
                <p class="text-danger col-sm-offset-2"><c:out value="${mapMensajeRut['errorRut']}"/></p>
                <br/>
                <c:if test="${empty param.rutBuscar}">
                    <h1>Bienvenido/a <c:out value="Dr/a. ${doctor.nombre}"/></h1>
                    <p><a class="btn btn-default" href="<c:url value="/BuscarDuenoServlet"/>"/>Aquí</a> puedes ver a todos tus clientes</p>
                    <br/>
                    <table class="table">
                        <tbody>
                            <c:forEach var="lst" items="${lstClientes}">
                                <tr>
                                    <td><c:out value="${lst.rut}"/></td>
                                    <td><c:out value="${lst.nombre} ${lst.apellidos}"/></td>
                                    <td><c:out value="${lst.direccion}"/></td>
                                    <td><c:out value="${lst.telefono}"/></td>
                                    <td>
                                        <form action="<c:url value="/EliminarClienteServlet"/>" method="post">
                                            <input type="hidden" name="rutDueno" value="${lst.rut}"/>
                                            <input class="btn btn-primary btn-xs" type="submit" value="Eliminar"/>
                                        </form>
                                        <br/>                         
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${not empty param.rutBuscar and empty mapMensajeRut}">
                    <p>Datos Dueño:</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Rut</th>
                                <th>Nombre</th>
                                <th>Dirección</th>
                                <th>Teléfono</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><c:out value="${dueno.rut}"/></td>
                                <td><c:out value="${dueno.nombre} ${dueno.apellidos}"/></td>
                                <td><c:out value="${dueno.direccion}"/></td>
                                <td><c:out value="${dueno.telefono}"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <br/>
                    <p>Datos Mascotas:</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nº Registro</th>
                                <th>Nombre</th>
                                <th>Raza</th>
                                <th>Fecha Nacimiento</th>
                                <th>Esterilizado</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="m" items="${listaMascotas}">
                                <tr>
                                    <td><c:out value="${m.idMascota}"/></td>
                                    <td><c:out value="${m.nombre}"/></td>
                                    <td><c:out value="${m.raza}"/></td>
                                    <td><fmt:formatDate value="${m.fechaNacimiento}" pattern="dd/MM/yyyy" /></td>
                                    <td><c:out value="${m.esterilizado}"/></td>
                                    <td>
                                        <form action="<c:url value="/SetMascotaServlet"/>" method="get">
                                            <input type="hidden" name="idMascota" value="${m.idMascota}"/>
                                            <input class="btn btn-primary btn-xs" type="submit" value="Sala Atención"/>
                                        </form>
                                        <br/>                         
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
